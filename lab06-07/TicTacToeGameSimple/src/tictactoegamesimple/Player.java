package tictactoegamesimple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Player is identified by a character mark which is either 'X' or 'O'.
 */
class Player extends Thread implements AutoCloseable {

    char mark;
    Player opponent;
    Socket socket;
    BufferedReader input;
    PrintWriter output;

    /**
     * Constructs a handler thread for a given socket and mark initializes the
     * stream fields, displays the first two welcoming messages.
     *
     * @param socket
     * @param mark
     */
    public Player(Socket socket) throws IOException {
        this.socket = socket;
        input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
        output.println("MESSAGE Waiting for opponent to connect");
    }

    /**
     * Accepts notification of who the opponent is.
     *
     * @param opponent
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     * Handles the otherPlayerMoved message.
     *
     * @param location
     */
    public void otherPlayerMoved(int location) {
        output.println("OPPONENT_MOVED" + location);
    }

    public void start(char mark) {
        this.mark = mark;
        output.println("WELCOME " + mark);
        // The thread is only started after everyone connects.
        output.println("MESSAGE All players connected");
    }

    protected int move() {
        int location = -1;
        try {
            String command = input.readLine();
            if (command.startsWith("MOVE")) {
                location = Integer.parseInt(command.substring(5));
            }
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return location;
    }

    protected void send(String bericht) {
        output.println(bericht);
    }

    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
