package utils;

import java.io.InputStreamReader;
import resources.AppRes;

public class FileUtils {
    public static String readFile(String resFileName, String resEncoding) {
        try{
            InputStreamReader isr = new InputStreamReader(AppRes.GetClass().getResourceAsStream(resFileName), resEncoding);
            StringBuffer sb = new StringBuffer();
            int chars = 0;
            while ((chars = isr.read()) != -1){
                sb.append((char) chars);
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String[] splitByDelimiter(String str, String delimiter){
        int parts = 1;
        int firstIndex = 0, lastIndex = 0;
        while ((lastIndex = str.indexOf(delimiter, lastIndex+1)) != -1) {
            parts++;
        }
        String[] data = new String[parts];
        lastIndex = 0;
        firstIndex = lastIndex;
        for (int i=0; i<parts; i++) {
            lastIndex = str.indexOf(delimiter, lastIndex+1);
            if (lastIndex == -1) {lastIndex = str.length();}
            data[i] = str.substring(firstIndex, lastIndex);
            firstIndex = lastIndex+1;
        }
        return data;
    }
}
