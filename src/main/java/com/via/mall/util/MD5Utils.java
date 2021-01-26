package com.via.mall.util;

import com.via.mall.common.Constant;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Qingqing.He
 * @date 2020/12/29 16:02
 * 描述：MD5工具  哈希运算，不具备解密能力
 */
public class MD5Utils {
    public static String getMD5Str(String strValue) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest((strValue + Constant.SALT).getBytes()));
    }

    //测试生成md5的值
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String md5 = getMD5Str("ferfv2SSC");
        System.out.println(md5);
    }
}
