package tictactoe_game;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A server for a network multi-player tic tac toe game. Here are the strings
 * that are sent for communication in the Tic Tac Toe game:
 *
 * Client -> Server Server -> Client ---------------- ---------------- MOVE <n>
 * (0 <= n <= 8) WELCOME <char> (char in {X, O}) QUIT VALID_MOVE CONTINUE
 * OTHER_PLAYER_MOVED <n>
 * VICTORY DEFEAT TIE MESSAGE <text>
 * QUIT
 *
 * This server allows an 10 pairs of players to play.
 */
public class TicTacToeServer {

    /**
     * Runs the application. Pairs up clients that connect.
     *
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        ServerThread ticTacToe = new ServerThread();
        Thread ticTacToeThread = new Thread(ticTacToe);
        ticTacToeThread.start();
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().equals("quit")) {
            ticTacToe.end();
        }
    }
}
