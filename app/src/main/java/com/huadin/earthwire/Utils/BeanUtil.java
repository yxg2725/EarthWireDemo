package com.huadin.earthwire.Utils;

import com.huadin.earthwire.Model.net.bean.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by 华电 on 2017/5/10.
 */

public class BeanUtil {
  public static byte[] getUserBeanInfo(User user){
    // 创建字节输出流
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    // 创建对象输出流，并封装字节流

    ObjectOutputStream oos = null;
    try {
      oos = new ObjectOutputStream(baos);
      oos.writeObject(user);
    } catch (IOException e) {
      e.printStackTrace();

    }finally {
      try {
        baos.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return baos.toByteArray();
  }
}
