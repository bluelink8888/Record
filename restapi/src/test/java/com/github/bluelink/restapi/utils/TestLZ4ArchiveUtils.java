package com.github.bluelink.restapi.utils;

import org.junit.jupiter.api.Test;

public class TestLZ4ArchiveUtils {

  @Test
  public void testCompressWithoutError(){
    LZ4ArchiveUtils.compress("");
  }

  @Test
  public void testSafeDecompressWithoutError(){
    LZ4ArchiveUtils.safeDecompress("");
  }
}
