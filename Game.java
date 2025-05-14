import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {

    public static void main(String[] args) {
        int[][] board = new int[4][4];

        initBoard(board);
        System.out.println();
        System.out.println();
        /*
        board[0][0] = 32;
        board[0][1] = 2;
        board[0][2] = 4;
        board[0][3] = 2;
        board[1][0] = 2;
        board[1][1] = 32;
        board[1][2] = 8;
        board[1][3] = 64;
        board[2][0] = 8;
        board[2][1] = 4;
        board[2][2] = 32;
        board[2][3] = 2;
        board[3][0] = 2;
        board[3][1] = 128;
        board[3][2] = 2;
        board[3][3] = 4;*/
        print(board);

        Scanner in = new Scanner(System.in);
        char input;
        while (true) {
            try {
                input = in.next().toLowerCase().charAt(0);
                if (input == 'e')
                    break;

                switch (input) {
                    case 'w':
                        moveBoard(board, 0);
                        break;
                    case 'a':
                        moveBoard(board, 1);
                        break;
                    case 's':
                        moveBoard(board, 2);
                        break;
                    case 'd':
                        moveBoard(board, 3);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            print(board);

        }
        in.close();
    }

    private static void print(int[][] arr) {
        for (int[] a : arr) {
            for (int i : a)
                System.out.printf("%4d", i);
            System.out.println();
        }

    }

    private static void print(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
    }

    private static int[][] initBoard(int[][] board) {
        int row, col;
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        for (int i = 0; i < 2; i++) {
            row = (int) (Math.random() * 4) + 0;
            col = (int) (Math.random() * 4) + 0;

            if (!rows.contains(row) && !cols.contains(col)) {
                board[row][col] = genNumber();
                rows.add(row);
                cols.add(col);
            } else
                i--;
        }
        return board;
    }

    private static int genNumber() {
        int rand = (int) (Math.random() * 101);
        if (rand < 10)
            return 4;
        else
            return 2;

    }

    private static void moveBoard(int[][] board, int dir) {
        // w 0 ; a = 1; s = 2; d = 3

        if (dir == 0){
            shiftUp(board);
            merge(board, dir);
            shiftUp(board);
        }

        if (dir == 1) {
            shiftLeft(board);
            merge(board, dir);
            shiftLeft(board);
        }

        if (dir == 2){
            shiftDown(board);
            merge(board, dir);
            shiftDown(board);
        }
            

        if (dir == 3){
            shiftRight(board);
            merge(board, dir);
            shiftRight(board);
        }

        //newNumber(board);
    }

    private static void shiftLeft(int[][] board) {
        int[] shiftIdx = new int[4];
        for (int i = 0; i < board.length; i++) { // dobi zacetke shifta na levi strani
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    shiftIdx[i] = j;
                } else // ce dobi prazno polje breaka iz loopa;
                    break;
            }
        }

        for (int i = 0; i < board.length; i++) {
            for(int j = shiftIdx[i]; j < board[i].length; j++){
                if(board[i][shiftIdx[i]] == 0){
                    if (board[i][j] != 0 && j != shiftIdx[i]) {
                        board[i][shiftIdx[i]] = board[i][j];
                        board[i][j] = 0;
                        shiftIdx[i]++;
                    }
                }else if(board[i][shiftIdx[i]] != 0){
                    if(j+1 < 4 && board[i][j+1] !=0){
                        board[i][shiftIdx[i] + 1] = board[i][j];
                        board[i][j] = 0;
                        shiftIdx[i]++; // posodobi shift idx;
                        
                    }
                }
            }
        }

        System.out.println("Print after shift:");
        print(board);
        System.out.println();

    }

    private static void shiftRight(int[][] board) {
        int[] shiftIdx = new int[4];

        for (int i = 0; i < shiftIdx.length; i++) {
            shiftIdx[i] = 3;
        }

        for (int i = 0; i < board.length; i++) { // dobi zacetke shifta na desni strani
            for (int j = board[i].length - 1; j >= 0; j--) {
                if (board[i][j] != 0) {
                    shiftIdx[i] = j;
                } else // ce dobi prazno polje breaka iz loopa;
                    break;
            }
        }

        System.out.println("Shift registers:");
        print(shiftIdx);
        System.out.println();

        for (int i = 0; i < board.length; i++) {
            for(int j = shiftIdx[i]; j >= 0; j--){
                if(board[i][shiftIdx[i]] == 0){
                    if (board[i][j] != 0 && j != shiftIdx[i]) {
                        board[i][shiftIdx[i]] = board[i][j];
                        board[i][j] = 0;
                        shiftIdx[i]--;
                    }
                }else if(board[i][shiftIdx[i]] != 0){
                    if(j-1 >= 0 && board[i][j - 1] !=0){
                        board[i][shiftIdx[i] - 1] = board[i][j];
                        board[i][j] = 0;
                        shiftIdx[i]--; // posodobi shift idx;
                        
                    }
                }
            }
        }

    }

    private static void shiftUp(int[][] board) {
        int[] shiftIdx = new int[4];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[j][i] != 0)
                    shiftIdx[i] = j;
                else
                    break;
            }
        }

        for (int i = 0; i < board.length; i++) {
                for (int j = shiftIdx[i] + 1; j < board[i].length; j++) {
                    if(board[shiftIdx[i]][i] == 0){
                        if (board[j][i] != 0) {
                            board[shiftIdx[i]][i] = board[j][i];
                            board[j][i] = 0;
                            shiftIdx[i]++;
                        }
                    } else if (board[j][i] != 0) {
                        board[shiftIdx[i] + 1][i] = board[j][i];
                        board[j][i] = 0;
                        shiftIdx[i]++;
                    }
                }
            
        }
    }

    private static void shiftDown(int[][] board) {
        int[] shiftIdx = new int[4];

        for (int i = 0; i < shiftIdx.length; i++) {
            shiftIdx[i] = 3;
        }

        for (int i = 0; i < board.length; i++) { // dobi zacetke shifta na desni strani
            for (int j = board[i].length - 1; j >= 0; j--) {
                if (board[j][i] != 0) {
                    shiftIdx[i] = j;
                } else // ce dobi prazno polje breaka iz loopa;
                    break;
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = shiftIdx[i]; j >= 0; j--) {
                if (board[shiftIdx[i]][i] == 0) {
                    if (board[j][i] != 0 && j != shiftIdx[i]) {
                        board[shiftIdx[i]][i] = board[j][i];
                        board[j][i] = 0;
                        shiftIdx[i]--;
                    }
                } else if (board[j][i] != 0 && j != shiftIdx[i]) {
                    board[shiftIdx[i] - 1][i] = board[j][i];
                    board[j][i] = 0;
                    shiftIdx[i]--;
                }
            }

        }

    }

    private static void merge(int[][] board, int dir) {

        if(dir == 2){
            for (int i = board.length - 1; i >= 0; i--) {
                for (int j = board[i].length - 1; j > 0; j--) {
                    if (board[j][i] == board[j - 1][i]) {
                        board[j - 1][i] = board[j][i] * 2;
                        board[j][i] = 0;
                        j--;
                    }
                }
            }
        }

        if(dir == 1){
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[i].length; j++){
                    if(j+1 < board[i].length && board[i][j] !=0 && board[i][j] == board[i][j+1]){
                        board[i][j + 1] = board[i][j] * 2;
                        board[i][j] = 0;
                        j++;
                    }
                }
            }
        }

        if (dir == 0) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length - 1; j++) {
                    if (board[j][i] == board[j + 1][i]) {
                        board[j + 1][i] = board[j][i] * 2;
                        board[j][i] = 0;
                        j++;
                    }
                }
            }
        }

        if(dir == 3){
            for(int i = 0; i < board.length; i++){
                for(int j = board[i].length - 1; j > 0 ; j--){
                    if(board[i][j] != 0 && board[i][j] == board[i][j-1]){
                        board[i][j-1] = board[i][j] * 2;
                        board[i][j] = 0;
                        j--;
                    }
                }
            }
        }

    }

    private static void newNumber(int[][] board){
        int row = (int)(Math.random()*board.length);
        int col = (int)(Math.random()*board.length);
        while(true){
                if(board[row][col] == 0){
                    board[row][col] = genNumber();
                    break;
                }else{
                    row = (int)(Math.random()*board.length);
                    col = (int)(Math.random()*board.length);
                }

        }

    }

}
