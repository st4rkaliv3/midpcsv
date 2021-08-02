package com.st4rkaliv3.emotionscheat;

import com.st4rkaliv3.emotionscheat.logic.CSVReader;
import com.st4rkaliv3.emotionscheat.logic.Table;
import resources.AppRes;
import com.st4rkaliv3.emotionscheat.view.TableView;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

public class Midlet extends MIDlet {
    Display d = Display.getDisplay(this);
    public void startApp() {
        TableView tv = new TableView(
                new Table(
                        CSVReader.read(
                                AppRes.FILE_NAME,
                                AppRes.FILE_ENCODING,
                                AppRes.ROW_SEPARATOR,
                                AppRes.COL_SEPARATOR
                        )
                )
        );
        d.setCurrent(tv);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
