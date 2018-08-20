/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;

public class BrutalForce {

    static {
        try {
            System.loadLibrary("natives/brutal_force");
        } catch (UnsatisfiedLinkError e) {
            try {
                NativeUtils.loadLibraryFromJar("/natives/brutal_force.dll");
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

    private static native void unhook();

    private static native void hook();

    public static void stop() {
        try {
            unhook();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void start() {
        try {
            hook();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void kill(String string) {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM " + string).waitFor();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
