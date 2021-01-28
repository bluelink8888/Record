package com.github.bluelink.restapi.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class SerializeUtils {

  public static String serializeObject(Object object) throws Exception{

    // This map record all class implement interface, include it super class.
    Map<String, List<String>> classImplementInterfaceNameMap = new HashMap<>();
    // interface list implements by object
    classImplementInterfaceNameMap.put(object.getClass().getName(), Arrays.stream(object.getClass().getInterfaces())
        .map(Class::getName).collect(Collectors.toList()));
    // interface list implements by objects' super class
    boolean isChildClass = true;
    while (isChildClass){
      Class superClass = object.getClass().getSuperclass();
      classImplementInterfaceNameMap.put(superClass.getName(), Arrays.stream(superClass.getInterfaces())
          .map(Class::getName).collect(Collectors.toList()));
      isChildClass = object.getClass().getSuperclass().getName().equals("java.lang.Object");
    }

    Map<String, List<String>> classImplementedSerializableMap = classImplementInterfaceNameMap.entrySet().stream().filter(map->{
      // make sure implement serializable interface
      return map.getValue().stream().anyMatch(interfaceName-> "java.io.Serializable".equals(interfaceName));
    }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    // input object and all its parent class didn't implements serializable interface
    if (classImplementedSerializableMap.size() != classImplementInterfaceNameMap.size()) {
      Map<String, List<String>> classNoSerializableMap = classImplementInterfaceNameMap.entrySet().stream()
              .filter(map-> !classImplementedSerializableMap.containsKey(map.getKey()))
              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
      throw new NotSerializableException("There are some class didn't implement Serializable interface, class Name : "
          + classNoSerializableMap.keySet().stream().collect(Collectors.joining(",")));
    }

    ByteArrayOutputStream bao = null;
    ObjectOutputStream oos = null;
    try {
      bao = new ByteArrayOutputStream();
      oos = new ObjectOutputStream(bao);
      oos.writeUnshared(object);
      oos.flush();
      oos.reset();
    } catch (Exception e) {
      System.err.println("SerializeObject error : " + e.getMessage());
    }

    return Base64.encodeBase64String(bao.toByteArray());
  }

  public static Object deserialize(String base64String) throws Exception{

    ByteArrayInputStream bais = null;
    ObjectInputStream ois = null;
    Object result = null;

    try {
      byte[] byteArrayFromBase64Decode = Base64.decodeBase64(base64String);
      bais = new ByteArrayInputStream(byteArrayFromBase64Decode);
      ois = new ObjectInputStream(bais);
      result = ois.readUnshared();
    } catch (Exception e) {
      if (e instanceof NotSerializableException) {
        throw new NotSerializableException("Can not deserialize this object, please double you class and its super class");
      } else {
        System.err.println("Deserialize error : " + e.getMessage());
      }
    }

    return result;
  }
}