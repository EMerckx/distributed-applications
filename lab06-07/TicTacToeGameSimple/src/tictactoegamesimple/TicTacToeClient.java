package tictactoegamesimple;

import javax.swing.JFrame;

/**
 * +++ A client for the TicTacToe game +++ Here are the strings that are sent
 * for communication in the Tic Tac Toe game:
 *
 * Client -> Server             Server -> Client 
 * ----------------             ---------------- 
 * MOVE <n> (0 <= n <= 8)       WELCOME <char> (char in {X, O}) 
 *                              QUIT 
 *                              VALID_MOVE CONTINUE
 *                              OTHER_PLAYER_MOVED <n>
 *                              VICTORY 
 *                              DEFEAT 
 *                              TIE 
 *                              MESSAGE <text>
 *                              QUIT
 *
 */
public class TicTacToeClient {
    

    /**
     * Runs the client as an application.
     *
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        TicTacToeFrame frame = new TicTacToeFrame();
        new Thread(() -> {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setVisible(true);
            frame.setResizable(false);
        }).start();

        String serverAddress = (args.length == 0) ? "localhost" : args[1];
        try (ClientPlayer client = new ClientPlayer(serverAddress, frame)) {
            frame.addListener(client);
            client.play();
        }
    }

}
