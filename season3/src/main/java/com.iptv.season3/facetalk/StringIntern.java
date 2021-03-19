package com.iptv.season3.facetalk;

/**
 * @author liuqi
 * @description: 58同城Java字符串常量池
 * @create 2020-11-12 11:41
 */
public class StringIntern {


    public static void main(String[] args) {

        String str1 = new StringBuilder("ali").append("baba").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern());

        System.out.println();

        String str2 = new StringBuilder("java").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern());

        int[] ints = {2, 7, 11, 15};

        int[] ints1 = new int[]{2, 7, 11, 15};
    }

}
