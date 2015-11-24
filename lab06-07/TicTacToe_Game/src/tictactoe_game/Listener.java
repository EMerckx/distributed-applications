/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_game;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vongenae
 */
public class Listener implements Runnable, AutoCloseable {

    // Server is listening at port '8901'
    private static final int POORT = 8901;
    private ServerSocket listener;
    private AtomicBoolean active;
    BlockingQueue<Player> players;

    Listener(BlockingQueue<Player> players) {
        this.players = players;
        try {
            listener = new ServerSocket(POORT);
            //listener.setSoTimeout(1000);
            System.out.println("Tic Tac Toe Server is up  ...");
            active = new AtomicBoolean(true);
        } catch (IOException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void run() {

        while (active.get()) {
            try {
                Player player = new Player(listener.accept());
                players.add(player);
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void close() throws Exception {
        listener.close();
    }

    public void end() {
        active.set(false);
    }

}
