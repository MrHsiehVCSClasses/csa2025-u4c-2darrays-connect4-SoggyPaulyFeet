package u8pp;

import java.util.Scanner;

//this is the game board and contains all the basic logic for the game.
public class Connect4 {

    public static final int RED_WIN = 0;
    public static final int YELLOW_WIN = 1;
    public static final int NO_WINNER = 2;
    public static final int BOTH_WIN = 3;

    public static final int RED = 1;
    public static final int YELLOW = -1;
    public static final int EMPTY = 0;

    private int[][] board;
    private int nextPlayer;

    public Connect4() {
        board = new int[6][7];
        nextPlayer = RED;
    }
    
    //This takes in 2D array of integers representing the game board and initializes the game state.

    public Connect4(int[][] board) {
        if (isBoardValid(board)) {
            this.board = copyBoard(board);

            int redCount = 0;
            int yellowCount = 0;

            for(int r = 0; r < this.board.length; r++){
                for(int c = 0; c < this.board[r].length; c++) {
                    if(this.board[r][c] == RED) {
                        redCount++;
                    } else if(this.board[r][c] == YELLOW) {
                        yellowCount++;
                    }
                }
            }
            if (redCount == yellowCount) {
                nextPlayer = RED;
            } else {
                nextPlayer = YELLOW;
            }
        } else {
            this.board = new int[6][7];
            nextPlayer = RED;
        }
    }
    // It is recommended to use private helper methods

    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            String rowOutput = "";
            for (int space : row) {
                if (space == RED) {
                    rowOutput += "🔴";
                } else if (space == Connect4.YELLOW) {
                    rowOutput += "🟡";
                } else {
                    rowOutput += "⬛";
                }
            }
            System.out.println(rowOutput);
        }
    }
    
    // This returns true if the board is full and false otherwise.

    public static boolean isFull(int[][] board) {
        for(int r=0; r<board.length; r++){
            for(int c=0; c<board[r].length; c++) {
                if (board[r][c] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    //This returns true if the boards in a valid state
    public static boolean isBoardValid(int[][] board) {
        int redCount = 0;
        int yellowCount = 0;

        for(int r=0; r<board.length; r++) {
            for(int c=0; c<board[r].length; c++) {
                if (board[r][c] == RED) {
                    redCount++;
                } else if (board[r][c] == YELLOW) {
                    yellowCount++;
                }
                if(board[r][c] != EMPTY) {
                    if(r<board.length-1 && board[r+1][c] == EMPTY) {
                        return false;
                    }
                }
            }
        }
        return redCount == yellowCount || redCount == yellowCount + 1;
    }
    // This returns 0 if red wins, 1 if yellow wins, 2 if there is no winner, and 3 if both players win.
    public static int getWinner(int[][] board){
        boolean redWins = hasFour(board, RED);
        boolean yellowWins = hasFour(board, YELLOW);

        if (redWins && yellowWins) {
            return BOTH_WIN;
        } else if (redWins) {
            return RED_WIN;
        } else if (yellowWins) {
            return YELLOW_WIN;
        } else {
            return NO_WINNER;
        }
    }
    // This shows true if the given player has four pieces in a row and returns false otherwise.
    private static boolean hasFour(int[][] board, int piece) {
        for (int r=0; r<board.length; r++) {
            for (int c=0; c<=board[r].length - 4; c++) {
                if(board[r][c] == piece && board[r][c+1] == piece && board[r][c+2] == piece && board[r][c+3] == piece) {
                    return true;
                }
            }
        }
        
        for(int r=0; r<=board.length-4; r++) {
            for(int c=0; c<board[r].length; c++) {
                if(board[r][c] == piece && board[r+1][c] == piece && board[r+2][c] == piece && board[r+3][c] == piece) {
                    return true;
                }
            }
        }
        for (int r=0; r<=board.length-4; r++) {
            for (int c=0; c<=board[r].length-4; c++) {
                if (board[r][c] == piece && board[r+1][c+1] == piece && board[r+2][c+2] == piece && board[r+3][c+3] == piece) {
                    return true;
                }  
            }
        }
        for (int r=0; r<=board.length - 4; r++) {
            for (int c=3; c< board[r].length; c++) {
                if(board[r][c] == piece
                    && board[r+1][c-1] == piece
                    && board[r+2][c-2] == piece
                    && board[r+3][c-3] == piece) {
                        return true;
                    }   
            }
        }
        return false;
    }
    // This shows true if the given player has four pieces in a row and returns false otherwise.
    public int[][] getBoard() {
        return copyBoard(board);
    }
    // This returns the integer representing the next player to move 1 for red -1 for yellow.
    public int getNextPlayer() {
        return nextPlayer;
    }
    // This takes in a column number and drops the next players piece in that column if it possible.
    public boolean dropPiece(int col) {
        if (col < 0 || col >= board[0].length) {
            return false;
        }

        for (int r = board.length - 1; r >= 0; r--) {
            if (board[r][col] == EMPTY) {
                board[r][col] = nextPlayer;
                
                if(nextPlayer == RED) {
                    nextPlayer = YELLOW;
                } else {
                    nextPlayer = RED;
                }
                return true;
            }
        }
        return false;
    }
    // This actually processes and plays the game of connect four.
    public void play(Scanner sc) {
        while (getWinner(board) == NO_WINNER && !isFull(board) && sc.hasNext()) {
            printBoard(board);

            if(!sc.hasNextInt()) {
                sc.next();
                continue;
            }
            int col = sc.nextInt();
            dropPiece(col);
        }
        printBoard(board);
        	
    }
    // This is a helper method which creates a copy of the 2D array.
    private static int[][] copyBoard(int[][] original) {
        int[][] copy = new int[original.length][original[0].length];
        for(int r=0; r<original.length; r++) {
            for(int c=0; c<original[r].length; c++){
                copy[r][c] = original[r][c];
            }
        }
        return copy;
    }

    
}
