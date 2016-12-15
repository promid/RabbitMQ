package utils;

/**
 * Created by DBQ on 2016/12/15.
 */
public class MyUtils {

    public static String getMessage(String[] strings){
        if (strings.length < 1)
            return "Hello World!";
        return joinStrings(strings, " ");
    }

    public static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) {
            return "";
        }
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }

    public static String getSeverity(String[] strings){
        if (strings.length < 1)
            return "info";
        return strings[0];
    }

}
