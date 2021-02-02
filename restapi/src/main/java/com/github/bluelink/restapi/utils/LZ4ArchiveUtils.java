package com.github.bluelink.restapi.utils;

import java.util.Arrays;
import org.apache.commons.codec.binary.Base64;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4SafeDecompressor;

public class LZ4ArchiveUtils {

  /**
   *  <pre>
   *  This method using LZ4 library compress input byte array.
   *  Base64 encode it after compress
   *  </pre>
   *
   * @param data something you want compress
   * @return compress result and encode by apache Base64
   */
  public static String compress(byte[] data) {
    Integer decompressedLength = null;
    Integer maxCompressedLength = null;
    Integer compressedLength = null;
    byte[] compressed = null;
    String result = null;

    try {
      LZ4Factory factory = LZ4Factory.safeInstance();
      decompressedLength = data.length;
      LZ4Compressor compressor = factory.highCompressor();
      maxCompressedLength = compressor.maxCompressedLength(decompressedLength);
      compressed = new byte[maxCompressedLength];
      compressedLength = compressor.compress(data, 0, decompressedLength, compressed, 0, maxCompressedLength);
      result = Base64.encodeBase64String(Arrays.copyOf(compressed, compressedLength));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (decompressedLength != null) decompressedLength = null;
      if (maxCompressedLength != null) maxCompressedLength = null;
      if (compressedLength != null) compressedLength = null;
      if (compressed != null) compressed = null;
    }

    return result;
  }


  /**
   *
   *  <pre>
   *    This method create for decompress value after compress method.
   *    It will decode before decompress, then return value before compressed.
   *  </pre>
   *
   * @param compressResultBase64Encoded the value after compress method
   * @return value before compressed
   */
  public static byte[] decompress(String compressResultBase64Encoded) {
    final int decompressLenghtRate = 10; // assume got 1/10 compress ratio
    byte[] compressResultBase64Decoded = null;
    Integer decompressedLength = null;
    Integer decompressLength = null;
    byte[] restored = null;
    byte[] result = null;

    try {
      // Must decode because compress already encode by apache Base64
      compressResultBase64Decoded = Base64.decodeBase64(compressResultBase64Encoded);
      decompressedLength = compressResultBase64Decoded.length * decompressLenghtRate;
      LZ4Factory factory = LZ4Factory.safeInstance();
      LZ4SafeDecompressor decompressor = factory.safeDecompressor();
      restored = new byte[decompressedLength];
      decompressLength = decompressor.decompress(compressResultBase64Decoded, 0, compressResultBase64Decoded.length, restored, 0);
      result = Arrays.copyOf(restored, decompressLength);
    } catch (OutOfMemoryError e) {
      e.printStackTrace();
    } finally {
      if (compressResultBase64Decoded != null) compressResultBase64Decoded = null;
      if (decompressedLength != null) decompressedLength = null;
      if (restored != null) restored = null;
      if (decompressLength != null) decompressLength = null;
    }

    return result;
  }
}
