package main.java.helpers;

public class JAVAUtils {

    public static boolean getBool(int i) {
        if (i == Const.TRUE) {
            return true;
        }

        return false;
    }

    public static boolean isNull(int i) {
        if (i == Const.NULL) {
            return true;
        }

        return false;
    }

}
