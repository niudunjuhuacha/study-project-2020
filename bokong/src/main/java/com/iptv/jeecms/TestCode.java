package com.iptv.jeecms;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/**
 * @author: liuqi
 * @date: 2023/1/30 11:36
 * @description:
 */
public class TestCode {


    public static void main(String[] args) {

        final byte[] S_H = new byte[]{97, 112, 105, 46, 106, 101, 101, 99, 109, 115, 46, 99, 111, 109};
        final byte[] U_B = new byte[]{104, 116, 116, 112, 58, 47, 47, 49, 57, 50, 46, 49, 54, 56, 46, 48, 46, 50, 51, 54, 58, 57, 48, 48, 48, 47, 77, 79, 68, 85, 76, 69, 45, 87, 72, 79, 76, 69, 47, 97, 117, 116, 104, 111, 114, 105, 122, 97, 116, 105, 111, 110, 115};
        final byte[] U_H = new byte[]{117, 115, 101, 114, 46, 104, 111, 109, 101};
        final byte[] F_D = new byte[]{46, 103, 99, 50};
        final byte[] F_F = new byte[]{116, 109, 112};
        final byte[] F_C = new byte[]{103, 99, 50, 46, 99, 111, 110, 102};
        final byte[] F_N = new byte[]{46, 74, 67, 71};
        final byte[] E_H = new byte[]{69, 82, 82, 79, 82, 58, 32};
        final byte[] E_M_1 = new byte[]{-25, -114, -81, -27, -94, -125, -27, -68, -126, -27, -72, -72};
        final byte[] E_M_2 = new byte[]{-26, -114, -120, -26, -99, -125, -27, -73, -78, -24, -65, -121, -26, -100, -97, 33};
        final byte[] E_M_3 = new byte[]{-25, -114, -81, -27, -94, -125, -27, -113, -126, -26, -107, -80, -27, -68, -126, -27, -72, -72, 33};
        final byte[] C_E = new byte[]{-26, -100, -86, -27, -113, -106, -27, -120, -80, -26, -107, -80, -26, -115, -82, -27, -70, -109, -24, -65, -98, -26, -114, -91, -23, -123, -115, -25, -67, -82};
        final byte[] D_E = new byte[]{-26, -117, -65, -28, -72, -115, -27, -120, -80, -27, -97, -97, -27, -112, -115};
        final byte[] S_D = new byte[]{115, 101, 108, 101, 99, 116, 32, 115, 105, 116, 101, 95, 100, 111, 109, 97, 105, 110, 32, 102, 114, 111, 109, 32, 106, 99, 95, 115, 105, 116, 101};
        final byte[] E_S = new byte[]{69, 82, 82, 79, 82, 58, 32, -26, -93, -128, -26, -75, -117, -28, -72, -70, -23, -99, -98, -26, -77, -107, -28, -67, -65, -25, -108, -88, 44, 32, -27, -73, -78, -27, -127, -100, -26, -83, -94, -27, -112, -81, -27, -118, -88};
        final byte[] S_V = new byte[]{47, 97, 117, 116, 104, 111, 114, 105, 122, 97, 116, 105, 111, 110, 83, 116, 97, 114, 116, 86, 101, 114, 105, 102, 105, 99, 97, 116, 105, 111, 110};
        final byte[] R_V = new byte[]{47, 97, 117, 116, 104, 111, 114, 105, 122, 97, 116, 105, 111, 110, 82, 117, 110, 86, 101, 114, 105, 102, 105, 99, 97, 116, 105, 111, 110};
        final byte[] A_K = new byte[]{97};
        final byte[] P_K = new byte[]{98};
        final byte[] D_K = new byte[]{99};
        final byte[] S_K = new byte[]{100};
        final byte[] E_K = new byte[]{101};
        final byte[] C_S_K = new byte[]{90, 72, 120, 57, 84, 90, 50, 89, 82, 68, 53, 65, 114, 109, 110, 105, 110, 77, 67, 89, 109, 48, 49, 113, 56, 99, 71, 49, 53, 112, 76, 76};

        final byte[] E_1 = new byte[]{106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 83, 121, 115, 116, 101, 109};
        final byte[] E_2 = new byte[]{106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 83, 116, 114, 105, 110, 103};
        final byte[] E_3 = new byte[]{106, 97, 118, 97, 46, 105, 111, 46, 80, 114, 105, 110, 116, 83, 116, 114, 101, 97, 109};
        final byte[] E_4 = new byte[]{101, 114, 114};
        final byte[] E_5 = new byte[]{112, 114, 105, 110, 116, 108, 110};
        final byte[] E_6 = new byte[]{106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 82, 117, 110, 116, 105, 109, 101};
        final byte[] E_7 = new byte[]{103, 101, 116, 82, 117, 110, 116, 105, 109, 101};
        final byte[] E_8 = new byte[]{101, 120, 105, 116};
        final byte[] E_9 = new byte[]{106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 84, 104, 114, 101, 97, 100};
        final byte[] E_10 = new byte[]{115, 108, 101, 101, 112};



        System.out.println("S_H = " + getPer(S_H));
        System.out.println("U_B = " + getPer(U_B));
        System.out.println("U_H = " + getPer(U_H));
        System.out.println("F_D = " + getPer(F_D));
        System.out.println("F_F = " + getPer(F_F));
        System.out.println("F_C = " + getPer(F_C));
        System.out.println("F_N = " + getPer(F_N));
        System.out.println("E_H = " + getPer(E_H));
        System.out.println("E_M_1 = " + getPer(E_M_1));
        System.out.println("E_M_2 = " + getPer(E_M_2));
        System.out.println("E_M_3 = " + getPer(E_M_3));
        System.out.println("C_E = " + getPer(C_E));
        System.out.println("D_E = " + getPer(D_E));
        System.out.println("S_D = " + getPer(S_D));
        System.out.println("E_S = " + getPer(E_S));
        System.out.println("S_V = " + getPer(S_V));
        System.out.println("R_V = " + getPer(R_V));
        System.out.println("A_K = " + getPer(A_K));
        System.out.println("P_K = " + getPer(P_K));
        System.out.println("D_K = " + getPer(D_K));
        System.out.println("S_K = " + getPer(S_K));
        System.out.println("E_K = " + getPer(E_K));
        System.out.println("C_S_K = " + getPer(C_S_K));

        System.out.println("======================================================================");

        System.out.println("E_1 = " + getPer(E_1));
        System.out.println("E_2 = " + getPer(E_2));
        System.out.println("E_3 = " + getPer(E_3));
        System.out.println("E_4 = " + getPer(E_4));
        System.out.println("E_5 = " + getPer(E_5));
        System.out.println("E_6 = " + getPer(E_6));
        System.out.println("E_7 = " + getPer(E_7));
        System.out.println("E_8 = " + getPer(E_8));
        System.out.println("E_9 = " + getPer(E_9));
        System.out.println("E_10 = " + getPer(E_10));

        try {
            Class<?> sc = Class.forName(getPer(new byte[]{106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 83, 121, 115, 116, 101, 109}));
            Class<?> sc1 = Class.forName(getPer(new byte[]{106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 83, 116, 114, 105, 110, 103}));
            Class<?> pc = Class.forName(getPer(new byte[]{106, 97, 118, 97, 46, 105, 111, 46, 80, 114, 105, 110, 116, 83, 116, 114, 101, 97, 109}));
            Field f1 = sc.getDeclaredField(getPer(new byte[]{101, 114, 114}));
            Object o1 = f1.get(sc);
            Method m1 = pc.getMethod(getPer(new byte[]{112, 114, 105, 110, 116, 108, 110}), sc1);
            System.out.println("E_10 = " + getPer(E_10));


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static String getPer(byte[] ba) {
        return new String(ba, StandardCharsets.UTF_8);
    }
}
