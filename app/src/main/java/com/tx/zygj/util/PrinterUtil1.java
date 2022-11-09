package com.tx.zygj.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class PrinterUtil1 {


    /**
     * 此过程会将中文转成 a1、a2...对应编码表数字
     * 例：汉字 “啊” 转成 b0 a1
     * b0 对应编码表 {@link #surface()}
     * a1 对应 bo 编码表下的 a1对应的汉字 {@link #bytecode()}
     * <p>
     * 具体编码表
     *
     * @see "https://blog.csdn.net/hherima/article/details/50801360"
     */
    public static byte[] byteToHex() throws UnsupportedEncodingException {
        String s = "承德县东城加油站";
        byte[] bytes = s.getBytes("gb2312"); //将字符串转成byte数组
        byte[] byteArray = new byte[s.length() * 2];
        for (int i = 0; i < bytes.length; i++) {
            String temp = Integer.toHexString(bytes[i]).substring(6, 8); //将byte转成16进制并截取
            if (i % 2 == 0) {
                //找GBK编码表对应的编码
                for (Map.Entry<String, Byte> entry : bytecode().entrySet()) {
                    if (entry.getKey().equals(temp)) {
                        byteArray[i] = entry.getValue();
                    }
                }
            } else {
                //找属于那个GBK编码表
                for (Map.Entry<String, Byte> entry : surface().entrySet()) {
                    if (entry.getKey().equals(temp)) {
                        byteArray[i] = entry.getValue();
                    }
                }
            }
        }
        return byteArray;
    }



    /**
     * 编码表
     */
    public static Map<String, Byte> surface() {
        Map<String, Byte> surface = new HashMap<>();
        surface.put("a1", (byte) 0xa1);
        surface.put("a2", (byte) 0xa2);
        surface.put("a3", (byte) 0xa3);
        surface.put("a4", (byte) 0xa4);
        surface.put("a5", (byte) 0xa5);
        surface.put("a6", (byte) 0xa6);
        surface.put("a7", (byte) 0xa7);
        surface.put("a8", (byte) 0xa8);
        surface.put("a9", (byte) 0xa9);

        surface.put("b0", (byte) 0xb0);
        surface.put("b1", (byte) 0xb1);
        surface.put("b2", (byte) 0xb2);
        surface.put("b3", (byte) 0xb3);
        surface.put("b4", (byte) 0xb4);
        surface.put("b5", (byte) 0xb5);
        surface.put("b6", (byte) 0xb6);
        surface.put("b7", (byte) 0xb7);
        surface.put("b8", (byte) 0xb8);
        surface.put("b9", (byte) 0xb9);
        surface.put("ba", (byte) 0xba);
        surface.put("bb", (byte) 0xbb);
        surface.put("bc", (byte) 0xbc);
        surface.put("bd", (byte) 0xbd);
        surface.put("be", (byte) 0xbe);
        surface.put("bf", (byte) 0xbf);

        surface.put("c0", (byte) 0xc0);
        surface.put("c1", (byte) 0xc1);
        surface.put("c2", (byte) 0xc2);
        surface.put("c3", (byte) 0xc3);
        surface.put("c4", (byte) 0xc4);
        surface.put("c5", (byte) 0xc5);
        surface.put("c6", (byte) 0xc6);
        surface.put("c7", (byte) 0xc7);
        surface.put("c8", (byte) 0xc8);
        surface.put("c9", (byte) 0xc9);
        surface.put("ca", (byte) 0xca);
        surface.put("cb", (byte) 0xcb);
        surface.put("cc", (byte) 0xcc);
        surface.put("cd", (byte) 0xcd);
        surface.put("ce", (byte) 0xce);
        surface.put("cf", (byte) 0xcf);


        surface.put("d0", (byte) 0xd0);
        surface.put("d1", (byte) 0xd1);
        surface.put("d2", (byte) 0xd2);
        surface.put("d3", (byte) 0xd3);
        surface.put("d4", (byte) 0xd4);
        surface.put("d5", (byte) 0xd5);
        surface.put("d6", (byte) 0xd6);
        surface.put("d7", (byte) 0xd7);
        surface.put("d8", (byte) 0xd8);
        surface.put("d9", (byte) 0xd9);
        surface.put("da", (byte) 0xda);
        surface.put("db", (byte) 0xdb);
        surface.put("dc", (byte) 0xdc);
        surface.put("dd", (byte) 0xdd);
        surface.put("de", (byte) 0xde);
        surface.put("df", (byte) 0xdf);


        surface.put("e0", (byte) 0xe0);
        surface.put("e1", (byte) 0xe1);
        surface.put("e2", (byte) 0xe2);
        surface.put("e3", (byte) 0xe3);
        surface.put("e4", (byte) 0xe4);
        surface.put("e5", (byte) 0xe5);
        surface.put("e6", (byte) 0xe6);
        surface.put("e7", (byte) 0xe7);
        surface.put("e8", (byte) 0xe8);
        surface.put("e9", (byte) 0xe9);
        surface.put("ea", (byte) 0xea);
        surface.put("eb", (byte) 0xeb);
        surface.put("ec", (byte) 0xec);
        surface.put("ed", (byte) 0xed);
        surface.put("ee", (byte) 0xee);
        surface.put("ef", (byte) 0xef);

        surface.put("f0", (byte) 0xf0);
        surface.put("f1", (byte) 0xf1);
        surface.put("f2", (byte) 0xf2);
        surface.put("f3", (byte) 0xf3);
        surface.put("f4", (byte) 0xf4);
        surface.put("f5", (byte) 0xf5);
        surface.put("f6", (byte) 0xf6);
        surface.put("f7", (byte) 0xf7);

        return surface;

    }

    /**
     * 编码表对应的汉字
     */
    public static Map<String, Byte> bytecode() {
        Map<String, Byte> bytecode = new HashMap<>();
        bytecode.put("a1", (byte) 0xa1);
        bytecode.put("a2", (byte) 0xa2);
        bytecode.put("a3", (byte) 0xa3);
        bytecode.put("a4", (byte) 0xa4);
        bytecode.put("a5", (byte) 0xa5);
        bytecode.put("a6", (byte) 0xa6);
        bytecode.put("a7", (byte) 0xa7);
        bytecode.put("a8", (byte) 0xa8);
        bytecode.put("a9", (byte) 0xa9);
        bytecode.put("aa", (byte) 0xba);
        bytecode.put("ab", (byte) 0xbb);
        bytecode.put("ac", (byte) 0xbc);
        bytecode.put("ad", (byte) 0xbd);
        bytecode.put("ae", (byte) 0xbe);
        bytecode.put("af", (byte) 0xbf);


        bytecode.put("b0", (byte) 0xb0);
        bytecode.put("b1", (byte) 0xb1);
        bytecode.put("b2", (byte) 0xb2);
        bytecode.put("b3", (byte) 0xb3);
        bytecode.put("b4", (byte) 0xb4);
        bytecode.put("b5", (byte) 0xb5);
        bytecode.put("b6", (byte) 0xb6);
        bytecode.put("b7", (byte) 0xb7);
        bytecode.put("b8", (byte) 0xb8);
        bytecode.put("b9", (byte) 0xb9);
        bytecode.put("ba", (byte) 0xba);
        bytecode.put("bb", (byte) 0xbb);
        bytecode.put("bc", (byte) 0xbc);
        bytecode.put("bd", (byte) 0xbd);
        bytecode.put("be", (byte) 0xbe);
        bytecode.put("bf", (byte) 0xbf);

        bytecode.put("c0", (byte) 0xc0);
        bytecode.put("c1", (byte) 0xc1);
        bytecode.put("c2", (byte) 0xc2);
        bytecode.put("c3", (byte) 0xc3);
        bytecode.put("c4", (byte) 0xc4);
        bytecode.put("c5", (byte) 0xc5);
        bytecode.put("c6", (byte) 0xc6);
        bytecode.put("c7", (byte) 0xc7);
        bytecode.put("c8", (byte) 0xc8);
        bytecode.put("c9", (byte) 0xc9);
        bytecode.put("ca", (byte) 0xca);
        bytecode.put("cb", (byte) 0xcb);
        bytecode.put("cc", (byte) 0xcc);
        bytecode.put("cd", (byte) 0xcd);
        bytecode.put("ce", (byte) 0xce);
        bytecode.put("cf", (byte) 0xcf);


        bytecode.put("d0", (byte) 0xd0);
        bytecode.put("d1", (byte) 0xd1);
        bytecode.put("d2", (byte) 0xd2);
        bytecode.put("d3", (byte) 0xd3);
        bytecode.put("d4", (byte) 0xd4);
        bytecode.put("d5", (byte) 0xd5);
        bytecode.put("d6", (byte) 0xd6);
        bytecode.put("d7", (byte) 0xd7);
        bytecode.put("d8", (byte) 0xd8);
        bytecode.put("d9", (byte) 0xd9);
        bytecode.put("da", (byte) 0xda);
        bytecode.put("db", (byte) 0xdb);
        bytecode.put("dc", (byte) 0xdc);
        bytecode.put("dd", (byte) 0xdd);
        bytecode.put("de", (byte) 0xde);
        bytecode.put("df", (byte) 0xdf);


        bytecode.put("e0", (byte) 0xe0);
        bytecode.put("e1", (byte) 0xe1);
        bytecode.put("e2", (byte) 0xe2);
        bytecode.put("e3", (byte) 0xe3);
        bytecode.put("e4", (byte) 0xe4);
        bytecode.put("e5", (byte) 0xe5);
        bytecode.put("e6", (byte) 0xe6);
        bytecode.put("e7", (byte) 0xe7);
        bytecode.put("e8", (byte) 0xe8);
        bytecode.put("e9", (byte) 0xe9);
        bytecode.put("ea", (byte) 0xea);
        bytecode.put("eb", (byte) 0xeb);
        bytecode.put("ec", (byte) 0xec);
        bytecode.put("ed", (byte) 0xed);
        bytecode.put("ee", (byte) 0xee);
        bytecode.put("ef", (byte) 0xef);

        bytecode.put("f0", (byte) 0xf0);
        bytecode.put("f1", (byte) 0xf1);
        bytecode.put("f2", (byte) 0xf2);
        bytecode.put("f3", (byte) 0xf3);
        bytecode.put("f4", (byte) 0xf4);
        bytecode.put("f5", (byte) 0xf5);
        bytecode.put("f6", (byte) 0xf6);
        bytecode.put("f7", (byte) 0xf7);
        bytecode.put("f8", (byte) 0xf8);
        bytecode.put("f9", (byte) 0xf9);
        bytecode.put("fa", (byte) 0xfa);
        bytecode.put("fb", (byte) 0xfb);
        bytecode.put("fc", (byte) 0xfc);
        bytecode.put("fd", (byte) 0xfd);
        bytecode.put("fe", (byte) 0xfe);


        return bytecode;
    }
}
