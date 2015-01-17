/**
 * Created by Юрий on 11.01.2015.
 */
public class Matrix {
    private final Cell[][] matrix;

    private Matrix(int size) {
        matrix = new Cell[size][size];
    }

    public static Matrix generateBaseMatrix() {
        Matrix result = new Matrix(9);


        for (int i = 0; i < 9; i++) {
            result.matrix[0][i] = new Cell(i + 1);
        }
        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                result.matrix[i][j] = result.matrix[i - 1][j >= 6 ? j + 3 - 9 : j + 3].copy();
            }
        }
        for (int i = 3; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                result.matrix[i][j] = result.matrix[i - 3][j == 8 ? 0 : j + 1].copy();
            }

        }
        return result;
    }


    public Cell[][] getCells() {
        return matrix;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result += matrix[i][j].isNull() ? " " : matrix[i][j].getValue() + " ";
            }
            result += "\n";
        }
        return result;
    }


    public static void transposition(Matrix matrix) {
        int temp;
        Cell[][] arr = matrix.getCells();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr[i].length; j++) {
                temp = arr[i][j].getValue();
                arr[i][j].setValue(arr[j][i].getValue());
                arr[j][i].setValue(temp);
            }
        }
    }
}
