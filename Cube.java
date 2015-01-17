/**
 * Класс для представления одной из 9 кубиков в судоку.
 */
public class Cube {

    final Cell[][] instance;

    private Cube() {
        instance = new Cell[3][3];
    }
    public static Cube generateCube(int pointerX, int pointerY, Matrix matrix){
        Cube result = new Cube();
        Cell[][] cells = matrix.getCells();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3 ; j++) {
             result.instance[i][j] = cells[pointerX+i][pointerY+j];
            }
        }
        return result;
    }
    public boolean isValid() {
        int sum = 0;
        for (int i = 0; i < instance.length; i++) {
            for (int j = 0; j < instance[i].length; j++) {
                sum+= instance[i][j].getValue();
            }
        }
        int expected = 45;
        return sum == expected;
    }

    public void setValue(int n, int m, int value) {
        if (n > instance.length || m > instance[0].length || value < 0 || value > 9) {
            throw new IllegalArgumentException("setValue(n,m) illegal arguments");
        }
        instance[n][m] = new Cell(value);
    }

    public Cell getCell(int n,int m) {
        if (n > instance.length || m > instance[0].length ) {
            throw new IllegalArgumentException("getValue(n,m) illegal arguments");
        }
         return instance[n][m];
    }


    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res += instance[i][j].getValue() + " ";
            }
            res+="\n";
        }
        return res;
    }
}
