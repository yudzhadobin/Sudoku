/**
 * Created by Юрий on 28.12.2014.
 */
public class main {
    public static void main(String[] args) {
    // GeneratorSudoku generatorSudoku = new GeneratorSudoku();
    // Sudoku sudoku = generatorSudoku.generateSudoku();
    // sudoku.toString();

       GeneratorSudoku generatorSudoku = new GeneratorSudoku();
       Sudoku sudoku = generatorSudoku.generateSudoku();
       System.out.println(sudoku.getMatrix().toString());
       System.out.println(sudoku.isSolved());
    }
}
