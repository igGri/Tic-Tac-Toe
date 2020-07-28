package tictactoe;

import java.util.Scanner;


public class TicTacToe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] input = {'_', '_', '_', '_', '_', '_', '_', '_', '_'};
        printTable(input);
        char queue;
        int counter = 1;
        do {
            if (counter % 2 == 0) {
                queue = 'O';
            } else {
                queue = 'X';
            }
            int[] step = enterStep();
            /*input =*/ setXorO(queue, step, input);
            counter++;
        } while (!result(input));
    }
    public static int[] enterStep() {
        int first;
        int second;
        int[] step;
        do {
            first = 0;
            second = 0;
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the coordinates: ");
            String enter = sc.nextLine();
            try {
                String[] pieces = enter.split(" ");
                first = Integer.parseInt(pieces[0]);
                second = Integer.parseInt(pieces[1]);
                if (first >= 4 || first <= 0 || second >= 4 || second <= 0) {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
        while (first >= 4 || first <= 0 || second >= 4 || second <= 0);
        step = new int[]{first, second};
        return step;
    }

    public static void setXorO(char que, int[] step, char[] input) {
        boolean empty;
        do {
            switch (step[1]) {
                case 1:
                    if (input[5 + step[0]] == '_') {
                        input[5 + step[0]] = que;
                        empty = true;
                    } else {
                        empty = false;
                    }
                    break;
                case 2:
                    if (input[2 + step[0]] == '_') {
                        input[2 + step[0]] = que;
                        empty = true;
                    } else {
                        empty = false;
                    }
                    break;
                default:
                    if (input[-1 + step[0]] == '_') {
                        input[-1 + step[0]] = que;
                        empty = true;
                    } else {
                        empty = false;
                    }
                    break;
            }
            if (!empty) {
                System.out.println("This cell is occupied! Choose another one!");
                step = enterStep();
            }
        } while (!empty);
        printTable(input);
    }



    private static boolean result(char[] input) {
        String str = new String(input);
        int action = 0;
        boolean okx = false;
        boolean oko = false;

        int xs = countChar(str, 'X');
        int os = countChar(str, 'O');
        int _s = countChar(str, '_');
        if (xs + os == 9) {
            action = 4;
        }
        for (int i = 0; i < 9; i += 3) {
            if ((int) input[i] + (int) input[i + 1] + (int) input[i + 2] == 264) {
                action = 3;
                okx = true;
            }
            if ((int) input[i] + (int) input[i + 1] + (int) input[i + 2] == 237) {
                action = 2;
                oko = true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if ((int) input[i] + (int) input[i + 3] + (int) input[i + 6] == 264) {
                action = 3;
                okx = true;
            }
            if ((int) input[i] + (int) input[i + 3] + (int) input[i + 6] == 237) {
                action = 2;
                oko = true;
            }
        }
        if ((int) input[0] + (int) input[4] + (int) input[8] == 264 ||
                (int) input[2] + (int) input[4] + (int) input[6] == 264) {
            action = 3;
        }
        if ((int) input[0] + (int) input[4] + (int) input[8] == 237 ||
                (int) input[2] + (int) input[4] + (int) input[6] == 237) {
            action = 2;
        }
        if (Math.abs(xs - os) > 1 || (oko && okx)) {
            action = 1;
        }
        boolean result = false;
        switch (action) {
            case 1:
                System.out.println("Impossible");
                break;
            case 2:
                System.out.println("O wins");
                result = true;
                break;
            case 3:
                System.out.println("X wins");
                result = true;
                break;
            case 4:
                System.out.println("Draw");
                result = true;
                break;
        }
        return result;
    }

    private static int countChar(String str, char c) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c)
                count++;
        }
        return count;
    }

    public static void printTable(char[] input) {
        System.out.println("---------");
        for (int i = 0; i < 9; i += 3) {
            System.out.println("| " + input[i] + " " + input[i + 1] + " " + input[i + 2] + " |");
        }
        System.out.println("---------");
    }
}
