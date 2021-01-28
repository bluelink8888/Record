package com.github.bluelink.restapi.utils;

import com.github.bluelink.restapi.model.serialize.NoSerializableChild;
import com.github.bluelink.restapi.model.serialize.SerializableChild;
import com.github.bluelink.restapi.model.serialize.SerializeObjectMather;
import org.junit.jupiter.api.Test;

import java.io.NotSerializableException;

import static org.assertj.core.api.Assertions.*;

public class TestSerializeUtils {

  SerializableChild serializableChild = SerializeObjectMather.getSerializableChild();

  NoSerializableChild noSerializableChild = SerializeObjectMather.getNoSerializableChild();

  String serializableChildSerializeResult = null;

  // This test make sure serialize can work without exception
  @Test
  void testSerializeUtilsSerializeObjectWithoutException() throws Exception{
    serializableChildSerializeResult = SerializeUtils.serializeObject(serializableChild);
    assertThat(serializableChildSerializeResult).isNotNull();
  }

  // This test make sure when serialize a object, it didn't implements Serializable interface will return expect exception
  @Test
  void testSerializeUtilsSerializeObjectWithDoesNotImplementsObject() throws Exception{
    assertThatThrownBy(()->{
      SerializeUtils.serializeObject(noSerializableChild);
    }).isInstanceOf(NotSerializableException.class);
  }

  // This test make sure deserialize can work without exception
  // TODO this test must create object mother for test, or it will always return null
  void testSerializeUtilsDeserializeObjectWithoutException() throws Exception{
    assertThat(SerializeUtils.deserialize(serializableChildSerializeResult)).isEqualTo(serializableChild);
  }

  // This test make sure when deserialize byte array, it origin object didn't implements Serializable interface will return expect exception
  void testSerializeUtilsDeserializeObjectWithDoesNotImplementsObject(){
    /* This test actually already cannot test is SerializeUtil,
     because serializeObject already make sure all class already will implement Serializable interface
     */
  }
}
