package com.github.bluelink.restapi.model.serialize;

import java.util.Random;

public class SerializeObjectMather {

  public static NoSerializableChild getNoSerializableChild() {
    NoSerializableChild noSerializableChild = new NoSerializableChild();
    noSerializableChild.setId("noSerializable");
    noSerializableChild.setName("testChild");
    noSerializableChild.setDescription("This is created for test serializable");
    return noSerializableChild;
  }

  public static SerializableChild getSerializableChild() {
    SerializableChild serializableChild = new SerializableChild();
    serializableChild.setId("serializeable");
    serializableChild.setName("testChild");
    serializableChild.setDescription("This is created for test serializable");
    serializableChild.setNumber(1);
    return serializableChild;
  }
}
