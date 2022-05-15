package com.ml.wxjsxy.util;

public class PathUtils {
    //获取图片路径
    private static final String P_PATH="images\\";

    public static String getRealPath(String relativePath){

        return P_PATH+relativePath;
    }
}
