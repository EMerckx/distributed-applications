package tictactoe_game;

import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

/**
 * +++ A client for the TicTacToe game +++ Here are the strings that are sent
 * for communication in the Tic Tac Toe game:
 *
 * Client -> Server Server -> Client ---------------- ---------------- MOVE <n>
 * (0 <= n <= 8) WELCOME <char> (char in {X, O}) QUIT VALID_MOVE CONTINUE
 * OTHER_PLAYER_MOVED <n>
 * VICTORY DEFEAT TIE MESSAGE <text>
 * QUIT
 *
 */
public class TicTacToeClient implements AutoCloseable, Observer {

    private static final int PORT = 8901;
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;
    private final TicTacToeFrame frame;

    /**
     * Constructs the client by connecting to a server, laying out the GUI and
     * registering GUI listeners.
     *
     * @param serverAddress the address of the server
     * @param frame
     * @throws java.io.IOException
     */
    public TicTacToeClient(String serverAddress, TicTacToeFrame frame) throws IOException {
        // Setup networking
        socket = new Socket(serverAddress, PORT);
        // read the socket input Stream in a BufferedReader
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        // assign the socket output Stream to a PrintWriter
        out = new PrintWriter(socket.getOutputStream(), true);
        this.frame = frame;

    }

    /**
     * The main thread of the client will listen at messages from the server.
     *
     * @throws java.lang.Exception
     */
    public void play() throws Exception {
        boolean verder = true;
        do {
            //keep reading the messages sent by the server
            String response = in.readLine();
            if (response.startsWith("VALID_MOVE")) {
                frame.validMove();
            } else if (response.startsWith("OPPONENT_MOVED")) {
                /* The server sent to the client in this message also the location (sqaure) 
                 at which the other player did the move, so that it could be update it in its window */
                int loc = Integer.parseInt(response.substring(14));
                frame.opponentMoved(loc);
            } else if (response.startsWith("VICTORY")) {
                verder = end("You win");
            } else if (response.startsWith("DEFEAT")) {
                verder = end("You lose");
            } else if (response.startsWith("TIE")) {
                verder = end("You tied");
            } else if (response.startsWith("MESSAGE")) {
                frame.bericht(response.substring(8));
            } else if (response.startsWith("QUIT")) {
                verder = end("Opponent has quit");
            } else if (response.startsWith("WELCOME")) {
                // At the beginning decide about the marks and set the title of the client frame
                char mark = response.charAt(8);
                frame.setMark(mark);
            }
        } while (verder);
        out.println("QUIT");
        frame.dispose();

    }

    private boolean end(String message) {
        boolean verder = frame.end(message);
        if (verder) {
            out.println("CONTINUE");
        }
        return verder;
    }

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
        try (TicTacToeClient client = new TicTacToeClient(serverAddress, frame)) {
            frame.addListener(client);
            client.play();
        }
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }

    @Override
    public void update(Observable o, Object arg) {
        out.println("MOVE " + frame.getPosition());
    }
}
