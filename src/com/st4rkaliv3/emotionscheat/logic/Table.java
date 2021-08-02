package com.st4rkaliv3.emotionscheat.logic;

public class Table {
    protected String[][] data;
    protected boolean freezeFirstRow = false;
    protected boolean freezeFirstColumn = false;
    protected int[] currentCell = {0,0};

    public String[][] getData() {
        return data;
    }

    public boolean isFrozenFirstRow() {
        return freezeFirstRow;
    }

    public void setFreezeFirstRow(boolean freezeFirstRow) {
        this.freezeFirstRow = freezeFirstRow;
    }

    public boolean isFrozenFirstColumn() {
        return freezeFirstColumn;
    }

    public void setFreezeFirstColumn(boolean freezeFirstColumn) {
        this.freezeFirstColumn = freezeFirstColumn;
    }

    public int getCurrentColumn() {
        return currentCell[1];
    }
    public int getCurrentRow() {
        return currentCell[0];
    }
    public void setCurrentColumn(int i) {
        if (i<0) i=0;
        else if (i>=data[0].length) i=data[0].length-1;
        this.currentCell[1] = i;
    }
    public void setCurrentRow(int i) {
        if (i<0) i=0;
        else if (i>=data.length) i=data.length-1;
        this.currentCell[0] = i;
    }

    public String getCell(int row, int column){
        if (row<0 || column<0 || row>data.length || column>data[0].length)
            return "";
        return data[row][column];
    }
    
    public Table(String[][] data){
        this.data = data;
    }
}
