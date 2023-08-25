import java.util.ArrayList;
public class Board {
  
    private String[][] squares;
    private ArrayList <Integer> lengths = new ArrayList <Integer>();
    
    public Board() {
        squares = new String[10][10];
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                squares[i][j] = "-";
            }
        }
    }
    
    public String toString() {
        String board = "";
        
        for (String[] row: squares) {
            for (String cell: row) {
                board += cell + " ";
            }
            
            board += "\n";
        }
        return board;
    }
    
    public boolean addShip(int row, int col, int len, boolean horizontal) {
        if (row < 0 || col < 0 || len <= 0 || row > 9 || col > 9) {
            return false;
        }
        
        if (horizontal && col + len - 1 > 9) {
            return false;
        }
        
        if (!horizontal && row + len - 1 > 9) {
            return false;
        }
        
        if (horizontal) {
          
            for (int i = 0; i < len; i++) {
                if (squares[row][col + i].equals("b")) {
                    return false;
                }
            }
            
            for (int i = 0; i < len; i++) {
                squares[row][col + i] = "b";
                lengths.add(len);
            }
        } else {
          
            for (int i = 0; i < len; i++) {
                if (squares[row + i][col].equals("b")) {
                    return false;
                }
            }
            
            for (int i = 0; i < len; i++) {
                squares[row + i][col] = "b";
                lengths.add(len);
            }
        }
        return true;
    }
    
    public boolean foundShip(int len) {
        return lengths.contains(len);
    }
    
    public int shoot(int row, int col) {
        if (row > 9 || row < 0 || col > 9 || col < 0) {
            return -1;
            
        } else if (squares[row][col].equals("-")) {
            squares[row][col] = "m";
            return 0;
            
        } else if (squares[row][col].equals("b")) {
            squares[row][col] = "x";
            return 1;
            
        } else {
            return 2;
        }
    }
    
    public boolean gameOver() {
        for (String[] row: squares) {
            for (String cell: row) {
                if (cell.equals("b")) {
                    return false;
                }
            }
        }
        return true;
    }
}
