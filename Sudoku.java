import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Отображает одну из граней судоку (стандартное судоку)
 */
public class Sudoku {
    private Cube[][] instance;
    private Matrix matrix;

    public static Sudoku generateSolverSudoku(){
        return new Sudoku();
    }

    private Sudoku(){
        this(Matrix.generateBaseMatrix());
    }

    public void updateState(Matrix matrix) {
        this.matrix = matrix;
        for (int i = 0; i < instance.length; i++) {
            for (int j = 0; j < instance[i].length; j++) {
                instance[i][j] = Cube.generateCube(i * 3, j * 3, matrix);
            }
        }
    }
    private Sudoku(Matrix matrix) {
        this.matrix = matrix;
        this.instance = new Cube[3][3];
        for (int i = 0; i < instance.length; i++) {
            for (int j = 0; j < instance[i].length; j++) {
                instance[i][j] = Cube.generateCube(i * 3, j * 3, matrix);
            }
        }
    }

    public boolean isSolved() {
        boolean result = true;
        SortedSet<Integer> allowedValues = new TreeSet<Integer>();
        for (int i = 1; i < 10; i++) {
            allowedValues.add(i);
        }
        for (int i = 0; i < instance.length; i++) {
            for (int j = 0; j < instance[i].length; j++) {
                if (!instance[i][j].isValid()) {
                    return false;
                }
            }
        }
        SortedSet<Integer> sub = new TreeSet<Integer>();
        for (int i = 0; i < matrix.getCells().length; i++) {
            sub.addAll(allowedValues);
            for (int j = 0; j < matrix.getCells()[i].length; j++) {
               if(! sub.contains(matrix.getCells()[i][j].getValue()))
                {
                    System.out.println(i+ " " + j +"\n");
                }
                if(matrix.getCells()[i][j].isNull()) {
                    result = false;
                }
                sub.remove(matrix.getCells()[i][j].getValue());
            }
            if (!sub.isEmpty()) {
                return false;
            }
        }
        for (int i = 0; i < matrix.getCells().length; i++) {
            sub.addAll(allowedValues);
            for (int j = 0; j < matrix.getCells()[i].length; j++) {
                if(! sub.contains(matrix.getCells()[j][i].getValue()))
                {
                    System.out.println(i+ " " + j +"\n");
                }
                sub.remove(matrix.getCells()[j][i].getValue());
            }
            if (!sub.isEmpty()) {
                return false;
            }
        }
        return result;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public Cube[][] getArrayOfCubes() {
        return instance;
    }

    public String toString() {
        return matrix.toString();
    }


}
