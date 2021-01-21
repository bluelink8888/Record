package com.github.bluelink.restapi.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtils {

  public static byte[] serializeObject(Object object) {

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

    return bao.toByteArray();
  }

  public static Object deserialize(byte[] bytes) {

    ByteArrayInputStream bais = null;
    ObjectInputStream ois = null;
    Object result = null;

    try {
      bais = new ByteArrayInputStream(bytes);
      ois = new ObjectInputStream(bais);
      result = ois.readUnshared();
    } catch (Exception e) {
      System.err.println("Deserialize error : " + e.getMessage());
    }

    return result;
  }
}
