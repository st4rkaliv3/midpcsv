package resources;

import utils.FileUtils;

public class AppRes {
//    private static void applyColorSet(){
//        String[] values = FileUtils.splitByDelimiter(FileUtils.readFile(COLORS_SET_FILE_NAME, FILE_ENCODING), "\n");
//        if (values.length < 3) return;
//        BACKGROUND_COLOR = Integer.parseInt(values[0], 16);
//        FOREGROUND_COLOR_PRIMARY = Integer.parseInt(values[1], 16);
//        FOREGROUND_COLOR_SECONDARY = Integer.parseInt(values[2], 16);
//    }
    
    // public static final String COLORS_SET_FILE_NAME = "colors.txt";
            
    public static final String FILE_NAME = "data.csv";
    public static final String FILE_ENCODING = "UTF-8";
    public static final String ROW_SEPARATOR = "\n";
    public static final String COL_SEPARATOR = "\t";
    
    // TODO: применить цвета из colors.txt
    public static int BACKGROUND_COLOR = 0xffffff;
    public static int FOREGROUND_COLOR_PRIMARY = 0x000000;
    public static int FOREGROUND_COLOR_SECONDARY = 0x3f3f3f;
    
    private AppRes() {}
    private static AppRes instance = new AppRes();
    public static Class GetClass(){return instance.getClass();}
}
