package com.github.bluelink.restapi.utils;

import com.github.bluelink.restapi.model.serialize.SerializableChild;
import com.github.bluelink.restapi.model.serialize.SerializeObjectMather;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.*;

public class TestLZ4ArchiveUtils {

  private final String testString = "test";

  SerializableChild serializableChild = SerializeObjectMather.getSerializableChild();

  @Test
  public void testCompressAndDecompressStringWithoutError() {
    try {
      // Compress no error
      String compressResult = LZ4ArchiveUtils.compress(testString.getBytes(StandardCharsets.UTF_8));
      assertThat(compressResult).isNotNull();
      // Decompress will equal to origin testString
      assertThat(new String(LZ4ArchiveUtils.decompress(compressResult), StandardCharsets.UTF_8)).isEqualTo(testString);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Test
  public void testSerializeAndArchiveWithoutError() {
    try {
      String serializeResult = SerializeUtils.serializeObject(serializableChild);
      assertThat(serializeResult).isNotNull();
      String compressResult =  LZ4ArchiveUtils.compress(serializeResult.getBytes(StandardCharsets.UTF_8));
      assertThat(compressResult).isNotNull();
      String decompressResult = new String(LZ4ArchiveUtils.decompress(compressResult), StandardCharsets.UTF_8);
      assertThat(decompressResult).isNotNull();
      assertThat(SerializeUtils.deserialize(decompressResult)).isEqualTo(serializableChild);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

}
