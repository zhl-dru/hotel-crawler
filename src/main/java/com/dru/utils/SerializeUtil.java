﻿package com.dru.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
	/**
	 * @Description:序列化
	 */
	public static byte[] serialize(Object object) {
        ObjectOutputStream oos;
        ByteArrayOutputStream baos;

        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);

            byte[] bytes = baos.toByteArray();

            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	/**
	 * @Description:反序列化
	 */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais;
        ObjectInputStream ois;

        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);

            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
