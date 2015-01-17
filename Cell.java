import java.util.*;


public class Cell {
    private Integer value;

    private SortedSet<Integer> allowedValues =  new TreeSet<Integer>();

    public Cell() {
        for(int i = 1; i < 10;i ++) {
            allowedValues.add(i);
        }
    }

    public int getValue() {
        return value;
    }

    public Cell copy(){
        return  new Cell(this.value);
    }

    public Cell(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        if(value < 1 || value > 9) {
            throw new IllegalArgumentException("setValue(value) illegal argument");
        }
        this.value = value;
    }

    public void setNull() {
        this.value = -1;
    }

    public boolean isNull() {
        return value == -1;
    }
}
