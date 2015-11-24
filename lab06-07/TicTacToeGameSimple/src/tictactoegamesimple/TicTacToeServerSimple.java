/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoegamesimple;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A server for a network multi-player tic tac toe game. Here are the strings
 * that are sent for communication in the Tic Tac Toe game:
 *
 * Client -> Server             Server -> Client 
 * ----------------             ---------------- 
 * MOVE <n> (0 <= n <= 8)       WELCOME <char> (char in {X, O}) 
 *                              QUIT                         
 *                              VALID_MOVE
 *                              OTHER_PLAYER_MOVED <n>
 *                              VICTORY 
 *                              DEFEAT 
 *                              TIE 
 *                              MESSAGE <text>
 *
 * This server allows an 10 pairs of players to play.
 */
public class TicTacToeServerSimple {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        try (Server ticTacToe = new Server()) {
            Thread ticTacToeThread = new Thread(ticTacToe);
            ticTacToeThread.start();
            Scanner sc = new Scanner(System.in);
            if (sc.nextLine().equals("quit")) {
                ticTacToe.end();
                messageToServer();
                messageToServer();
            }
        }
    }

    private static void messageToServer() {
        try (Socket socket = new Socket("localhost", Server.POORT)) {
        } catch (IOException ex) {
            Logger.getLogger(TicTacToeServerSimple.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
