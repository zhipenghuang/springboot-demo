//package com.hzp.demo.utils;
//
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.lang.Character.UnicodeBlock;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//public class PasswordUtil {
//    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//    public PasswordUtil() {
//    }
//
//    public static String encode(String password) {
//        return encoder.encode(password);
//    }
//
//    public static boolean checkPassword(String password, String encodedPassword) {
//        return encoder.matches(password, encodedPassword);
//    }
//
//    public static boolean validatePattern(String password) {
//        if (password == null) {
//            return false;
//        } else {
//            String str = "((?=.*\\d)|(?=.*[a-zA-Z]))^.{6,18}$";
//            boolean result = password.matches(str);
//            if (!result) {
//                return result;
//            } else {
//                if (containChinese(password)) {
//                    result = false;
//                }
//
//                return result;
//            }
//        }
//    }
//
//    private static boolean containChinese(String password) {
//        char[] passwordchar = password.toCharArray();
//        char[] var2 = passwordchar;
//        int var3 = passwordchar.length;
//
//        for(int var4 = 0; var4 < var3; ++var4) {
//            char c = var2[var4];
//            UnicodeBlock ub = UnicodeBlock.of(c);
//            if (ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub == UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == UnicodeBlock.GENERAL_PUNCTUATION) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    public static boolean getEncryption(String target, String resouse) {
//        try {
//            MessageDigest bmd5 = MessageDigest.getInstance("MD5");
//            bmd5.update(target.getBytes());
//            StringBuilder buf = new StringBuilder();
//            byte[] b = bmd5.digest();
//            byte[] var6 = b;
//            int var7 = b.length;
//
//            for(int var8 = 0; var8 < var7; ++var8) {
//                byte aB = var6[var8];
//                int i = aB;
//                if (aB < 0) {
//                    i = aB + 256;
//                }
//
//                if (i < 16) {
//                    buf.append("0");
//                }
//
//                buf.append(Integer.toHexString(i));
//            }
//
//            return buf.toString().equals(resouse);
//        } catch (NoSuchAlgorithmException var10) {
//            var10.printStackTrace();
//            return "".equals(resouse);
//        }
//    }
//
//    public static String MD5encrypt(String resouse) {
//        try {
//            MessageDigest bmd5 = MessageDigest.getInstance("MD5");
//            bmd5.update(resouse.getBytes());
//            StringBuilder buf = new StringBuilder();
//            byte[] b = bmd5.digest();
//            byte[] var5 = b;
//            int var6 = b.length;
//
//            for(int var7 = 0; var7 < var6; ++var7) {
//                byte aB = var5[var7];
//                int i = aB;
//                if (aB < 0) {
//                    i = aB + 256;
//                }
//
//                if (i < 16) {
//                    buf.append("0");
//                }
//
//                buf.append(Integer.toHexString(i));
//            }
//
//            return buf.toString();
//        } catch (NoSuchAlgorithmException var9) {
//            var9.printStackTrace();
//            return "";
//        }
//    }
//
//    public static void main(String[] args) {
//        String s = PasswordUtil.MD5encrypt("123456");
//        System.out.println(s);
//        //String encode = PasswordUtil.encode(s);
//        //System.out.println(encode);
//
//    }
//}
//
