package com.github.bluelink.restapi.utils;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4SafeDecompressor;
import org.apache.commons.codec.binary.Base64;

import java.util.Arrays;

public class LZ4ArchiveUtils {

  /**
   *  <pre>
   *    This method input value must serialize before.
   *    It will return a string encode by base64 and compress by LZ4.
   *    So return must decode before then decompress it.
   *  </pre>
   *
   * @param data
   * @return
   */
  public static String compress(String data){
    // Compress must serialize object before
    // Because data already encode by apache base54, so here should decode before compress
    byte[] dataDecodeBase64 = Base64.decodeBase64(data);

    Integer decompressedLength = null;
    Integer maxCompressedLength = null;
    Integer compressedLength = null;
    byte[] compressed = null;
    String result = null;

    try {
      LZ4Factory factory = LZ4Factory.safeInstance();
      decompressedLength = dataDecodeBase64.length;
      LZ4Compressor compressor = factory.highCompressor();
      maxCompressedLength = compressor.maxCompressedLength(decompressedLength);
      compressed = new byte[maxCompressedLength];
      result = Base64.encodeBase64String(compressed);
    } catch (Exception e) {
      System.err.println("Compress error : " + e.getMessage());
    } finally {
      if (decompressedLength != null)
        decompressedLength = null;
      if (maxCompressedLength != null)
        maxCompressedLength = null;
      if (compressedLength != null)
        compressedLength = null;
      if (compressed != null)
        compressed = null;
    }

    return result;
  }

  /**
   * <pre>
   *  This method will decodeBase64 first then decompress input value
   * </pre>
   *
   * @param data
   * @return
   */
  public static String safeDecompress(String data){
    byte[] dataDecodeBase64 = null;
    Integer decompressedLength = null;
    Integer decompressLength = null;
    byte[] restored = null;
    String result = null;

    try {
      dataDecodeBase64 = Base64.decodeBase64(data);
      decompressedLength = dataDecodeBase64.length;
      LZ4Factory factory = LZ4Factory.safeInstance();
      LZ4SafeDecompressor decompressor = factory.safeDecompressor();
      restored = new byte[decompressedLength];
      decompressLength = decompressor.decompress(dataDecodeBase64, 0, dataDecodeBase64.length, restored, 0);
      result = Base64.encodeBase64String(Arrays.copyOf(restored, decompressLength));
    } catch (OutOfMemoryError me) {
      System.err.println("SafeDecompress out of memory error : " + me.getMessage());
    } catch (Exception e){
      System.err.println("SafeDecompress other error : " + e.getMessage());
    } finally {
      if (dataDecodeBase64 != null)
        dataDecodeBase64 = null;
      if (decompressedLength != null)
        decompressedLength = null;
      if (restored != null)
        restored = null;
      if (decompressLength != null)
        decompressLength = null;
    }

    return result;
  }
}
