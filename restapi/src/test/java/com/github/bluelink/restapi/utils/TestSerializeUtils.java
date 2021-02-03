package com.github.bluelink.restapi.utils;

import com.github.bluelink.restapi.model.serialize.NoSerializableChild;
import com.github.bluelink.restapi.model.serialize.SerializableChild;
import com.github.bluelink.restapi.model.serialize.SerializeObjectMather;
import org.junit.jupiter.api.*;

import java.io.NotSerializableException;

import static org.assertj.core.api.Assertions.*;

public class TestSerializeUtils {

  SerializableChild serializableChild =  SerializeObjectMather.getSerializableChild();

  NoSerializableChild noSerializableChild =  SerializeObjectMather.getNoSerializableChild();

  // This test make sure serialize can work without exception
  @Test
  void testSerializeUtilsSerializeObjectAndDeserializeObjectWithoutException() throws Exception{
    String serializableChildSerializeResult = SerializeUtils.serializeObject(serializableChild);
    assertThat(serializableChildSerializeResult).isNotNull();
    assertThat(SerializeUtils.deserialize(serializableChildSerializeResult)).isEqualTo(serializableChild);
  }

  // This test make sure when serialize a object, it didn't implements Serializable interface will return expect exception
  @Test
  void testSerializeUtilsSerializeObjectWithDoesNotImplementsObject() throws Exception{
    assertThatThrownBy(()->{
      SerializeUtils.serializeObject(noSerializableChild);
    }).isInstanceOf(NotSerializableException.class);
  }
}
