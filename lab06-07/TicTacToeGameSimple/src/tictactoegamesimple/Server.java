/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoegamesimple;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vongenae
 */
public class Server implements Runnable, AutoCloseable {

    // Server is listening at port '8901'
    protected static final int POORT = 8901;
    private ServerSocket listener;
    private AtomicBoolean active;

    public Server() {
        try {
            listener = new ServerSocket(POORT);
            System.out.println("Tic Tac Toe Server is up  ...");
            active = new AtomicBoolean(true);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    public void end() {        
        active.set(false);
    }

    @Override
    public void run() {
        ExecutorService execServ = Executors.newFixedThreadPool(10);
        while (active.get()) {
            try {
                Player player1 = new Player(listener.accept());
                Player player2 = new Player(listener.accept());
                Game game = new Game(player1, player2);
                execServ.submit(game);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void close() throws Exception {
        listener.close();
    }
}
