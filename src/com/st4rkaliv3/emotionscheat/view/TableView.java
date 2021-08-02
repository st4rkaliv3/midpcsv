package com.st4rkaliv3.emotionscheat.view;

import javax.microedition.lcdui.*;
import com.st4rkaliv3.emotionscheat.logic.*;
import resources.AppRes;

public class TableView extends Canvas {
    
    protected Table table;
    
    public TableView(Table table){
        this.table = table;
        repaint();
    }

    protected void keyPressed(int keyCode) {
        switch (keyCode) {
            case KEY_STAR:
                table.setFreezeFirstColumn(!table.isFrozenFirstColumn());
                repaintFrame();
                break;
            case KEY_POUND:
                table.setFreezeFirstRow(!table.isFrozenFirstRow());
                repaintFrame();
                break;
            default:
                switch (getGameAction(keyCode)) {
                    case UP:
                        table.setCurrentRow(table.getCurrentRow()-1);
                        repaintFrame();
                        break;
                    case DOWN:
                        table.setCurrentRow(table.getCurrentRow()+1);
                        repaintFrame();
                        break;
                    case LEFT:
                        table.setCurrentColumn(table.getCurrentColumn()-1);
                        repaintFrame();
                        break;
                    case RIGHT:
                        table.setCurrentColumn(table.getCurrentColumn()+1);
                        repaintFrame();
                        break;
                }
                break;
        }
    }
    
    /** состояние холста:
     * 0 - Затирание холста;
     * 1 - Рисование текста;
     */
    protected int canvasState = 0;
    protected boolean canvasFrozen = false;
    
    protected int width = getWidth(), height = getHeight();
    
    protected Font font = Font.getDefaultFont();
    
    protected void repaintFrame(){
        canvasFrozen = false;
        canvasState = 0;
        repaint();
    }
    
    protected boolean drawTextBox(Graphics g, String str, int x0, int y0, int x1, int y1){
        return drawTextBox(g, str, x0, y0, x1, y1, 0);
    }
    protected boolean drawTextBox(Graphics g, String str, int x0, int y0, int x1, int y1, int fromString){
        int strlen = str.length();
        int lastStartIndex = 0, lastStartY = y0;
        int rl,rr;
        
        int wl,wr;
        
        int stringCounter = 0;
        
        int fontHeight = font.getHeight();
        while (lastStartIndex < strlen && lastStartY <= y1-fontHeight) {
            rl = rr = strlen;
            wl = font.stringWidth(str.substring(lastStartIndex, rl));
            while ((wl -= font.stringWidth(str.substring(rl,rr))) > width) {
                rr = rl;
                rl += lastStartIndex;
                rl >>= 1;
            }
            
            while (rl < rr-1) {
                wl = font.stringWidth(str.substring(lastStartIndex, rl));
                wr = wl + font.stringWidth(str.substring(rl, rr));
                if (width < ((wl+wr)>>1)) {
                    rr += rl;
                    rr >>= 1;
                } else if (width > ((wl+wr)>>1)) {
                    rl += rr;
                    rl >>= 1;
                } else {
                    break;
                }
            }
            
            if (stringCounter++ >= fromString) {
                g.drawString(str.substring(lastStartIndex,rl), (x0+x1)>>1, lastStartY, Graphics.HCENTER | Graphics.TOP);
                lastStartY += fontHeight;
            }
            
            lastStartIndex = rl;
        }
        return (lastStartIndex >= strlen); //если не хватило строк, вернет false
    }
    
    protected int[] stringOffsets = new int[]{0,0,0};
    protected void paint(Graphics g) {
        if (canvasFrozen) return;
        switch (canvasState) {
            case 0: {
                g.setColor(AppRes.BACKGROUND_COLOR);
                g.setFont(font);
                g.fillRect(0, 0, width, height);
                canvasState = 1;
            }break;
            case 1:{
                if (table.isFrozenFirstRow()) {
                    g.setColor(AppRes.FOREGROUND_COLOR_SECONDARY);
                    stringOffsets[0] = (drawTextBox(
                            g, table.getCell(0, table.getCurrentColumn()), 
                            0, 0, 
                            width-1, (height>>2)-2,
                            stringOffsets[0]
                    )? 0 : stringOffsets[0]+1);
                    g.drawLine(0, (height>>2)-1, width-1, (height>>2)-1);
                }
                g.setColor(AppRes.FOREGROUND_COLOR_PRIMARY);
                stringOffsets[1] = (drawTextBox(
                        g, table.getCell(table.getCurrentRow(), table.getCurrentColumn()), 
                        0, (table.isFrozenFirstRow()?height>>2:0), 
                        width-1, (table.isFrozenFirstColumn()?height-(height>>2):height-1)
                )? 0 : stringOffsets[1]+1);
                if (table.isFrozenFirstColumn()) {
                    g.setColor(AppRes.FOREGROUND_COLOR_SECONDARY);
                    g.drawLine(0, height-(height>>2)+1, width-1, height-(height>>2)+1);
                    stringOffsets[2] = (drawTextBox(
                            g, table.getCell(table.getCurrentRow(), 0), 
                            0, height-(height>>2)+3, 
                            width-1, height-1
                    )? 0 : stringOffsets[2]+1);
                }
                canvasFrozen = true;
                //TODO: улучшить показ не поместившегося по вертикали содержимого textBox'а (закомментированный блок ниже)
                /*try {
                    Thread.sleep(700);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }*/
            }break;
            default:break;
        }
        repaint();
    }
    
}
