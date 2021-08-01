package com.st4rkaliv3.emotionscheat.logic;

import com.st4rkaliv3.emotionscheat.Test;
import resources.AppRes;
import java.io.InputStream;
import java.io.InputStreamReader;
import utils.FileUtils;

public class CSVReader {
    public static String[][] read(String resFileName, String encoding, String rowSeparator, String colSeparator){
        String raw = FileUtils.readFile(resFileName, encoding);
        if (raw == null) return null;
        
        String[] rows = FileUtils.splitByDelimiter(raw, rowSeparator);
        //cleaning
        raw = null;
        System.gc();
        if (rows.length == 0 || (rows.length == 1 && rows[0].equals(""))) return null;
        
        int actualRows = 0;
        for (int i=0;i<rows.length;i++) {
            if (!rows[i].equals("")) {
                actualRows++;
            }
        }
        
        String[] topCols = FileUtils.splitByDelimiter(rows[0], colSeparator);
        
        String[][] data = new String[actualRows][topCols.length];
        data[0] = topCols;
        
        int j=1;
        for (int i=1;i<rows.length;i++) {
            if (!rows[i].equals("")) {
                data[j] = FileUtils.splitByDelimiter(rows[i], colSeparator);
                j++;
            }
        }
        
        //cleaning
        rows = null;
        System.gc();
        
        return data;
    }
}
