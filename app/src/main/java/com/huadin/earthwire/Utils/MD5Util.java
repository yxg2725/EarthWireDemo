package com.huadin.earthwire.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.baidu.location.h.c.h;

/**
 * MD5加密
 * @author Jack Zhang
 *
 */
public class MD5Util
{
  public static final String md5(final byte[] s)
  {
    final String MD5 = "MD5";
    try
    {
      // Create MD5 Hash
      MessageDigest digest = MessageDigest.getInstance(MD5);
      digest.update(s);
      byte messageDigest[] = digest.digest();

      // Create Hex String
      StringBuilder hexString = new StringBuilder();
      for (byte aMessageDigest : messageDigest)
      {
        String h = Integer.toHexString(0xFF & aMessageDigest);
        while (h.length() < 2)
          h = "0" + h;
        hexString.append(h);
      }

      return hexString.toString();

    } catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    return "";
  }
}
