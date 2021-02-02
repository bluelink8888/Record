package com.github.bluelink.restapi.utils;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.*;

public class TestLZ4ArchiveUtils {

  private final String testString = "test";

  @Test
  public void testCompressWithoutError(){
    try {
      assertThat(LZ4ArchiveUtils.compress(testString.getBytes(StandardCharsets.UTF_8))).isNotNull();

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Test
  public void testSafeDecompressWithoutError(){
    try {
      System.out.println(new String(LZ4ArchiveUtils.decompress(LZ4ArchiveUtils.compress(testString.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8));
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

  }
}
