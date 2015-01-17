import java.util.Random;

public class GeneratorSudoku {
    private final  int NUMBER_OF_SWAPS = 30;
    private final int NUMBER_OF_DELETED_SELLS = 51;

    private final static Random RANDOM = new Random();

    public Sudoku generateSudoku() {
        Sudoku sudoku = Sudoku.generateSolverSudoku();

        for (int i = 0; i < NUMBER_OF_SWAPS; i++) {
            switch (RANDOM.nextInt(5)) {
                case 0: {
                    Matrix.transposition(sudoku.getMatrix());
                    break;
                }
                case 1: {
                    int pointer = RANDOM.nextInt(3);
                    swapTwoHorizontalRows(pointer*3+ RANDOM.nextInt(3),pointer*3+ RANDOM.nextInt(3),sudoku.getMatrix());
                    break;
                }

                case 2: {
                    int pointer = RANDOM.nextInt(3);
                    swapTwoVerticalRows(pointer*3+ RANDOM.nextInt(3),pointer*3+ RANDOM.nextInt(3),sudoku.getMatrix());
                    break;
                }

                case 3: {
                    swapTwoHorizontalRowsOfCubes(RANDOM.nextInt(3),RANDOM.nextInt(3),sudoku.getArrayOfCubes());
                    break;
                }
                case 4: {
                    swapTwoVerticalRowsOfCubes(RANDOM.nextInt(3),RANDOM.nextInt(3),sudoku.getArrayOfCubes());
                    break;
                }
            }
        }
        deleteCells(sudoku);
        return sudoku;
    }

    private void deleteCells(Sudoku sudoku) {
        Matrix matrix = sudoku.getMatrix();
        int numberOfDeleted= 0;
        int i,j;
        while (numberOfDeleted < NUMBER_OF_DELETED_SELLS) {
            i = RANDOM.nextInt(9);
            j = RANDOM.nextInt(9);// выбор случайной клетки для удаления
            if(! matrix.getCells()[i][j].isNull()) {
                numberOfDeleted++;
                matrix.getCells()[i][j].setNull();
            }
        }
    }

    private void swapTwoVerticalRows(int a, int b, Matrix matrix) {
        if (a < matrix.getCells().length || b < matrix.getCells().length) {
            int temp;
            Cell[][] cells = matrix.getCells();
            for (int i = 0; i < cells[a].length; i++) {
                temp = cells[a][i].getValue();
                cells[a][i].setValue(cells[b][i].getValue());
                cells[b][i].setValue(temp);
            }
        } else {
            throw new IllegalArgumentException("swapTwoHorizontalRows(a,b,matrix) illegal argument");
        }
    }

    private void swapTwoHorizontalRows(int a, int b, Matrix matrix) {
        Cell[][] cells = matrix.getCells();
        if (a < matrix.getCells().length || b < cells.length) {
            int temp;
            for (int i = 0; i < cells[a].length; i++) {
                temp = cells[i][a].getValue();
                cells[i][a].setValue(cells[i][b].getValue());
                cells[i][b].setValue(temp);
            }
        } else {
            throw new IllegalArgumentException("swapTwoVerticalRows(a,b,matrix) illegal argument");
        }
    }

    private void swapTwoHorizontalRowsOfCubes(int a,int b, Cube[][] cubes) {
        if (a < cubes.length || b < cubes.length) {
            for (int i = 0; i < cubes[a].length; i++) {
                swapValuesInTwoCubes(cubes[a][i],cubes[b][i]);
            }
        } else {
            throw new IllegalArgumentException("swapTwoHorizontalRows(a,b,matrix) illegal argument");
        }
    }

    private void swapTwoVerticalRowsOfCubes(int a,int b, Cube[][] cubes) {
        if (a < cubes.length || b < cubes.length) {
            for (int i = 0; i < cubes[a].length; i++) {
                swapValuesInTwoCubes(cubes[i][a],cubes[i][b]);
            }
        } else {
            throw new IllegalArgumentException("swapTwoHorizontalRows(a,b,matrix) illegal argument");
        }
    }

    private void swapValuesInTwoCubes(Cube a, Cube b) {
        int temp;
        for (int i = 0; i < a.instance.length; i++) {
            for (int j = 0; j < a.instance[i].length; j++) {
                temp = a.instance[i][j].getValue();
                a.instance[i][j].setValue(b.instance[i][j].getValue());
                b.instance[i][j].setValue(temp);
            }
        }
    }
}