/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_game;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vongenae
 */
public class PlayerListener implements Runnable {

    private final AtomicBoolean active;
    private BlockingQueue<Player> players;

    PlayerListener(BlockingQueue<Player> list) {
        this.players = list;
        active = new AtomicBoolean(true);
    }

    public void end() {
        active.set(false);
    }

    @Override
    public void run() {
        ExecutorService execServ = Executors.newFixedThreadPool(10);
        while (active.get()) {
            try {
                Player player1 = players.take();
                Player player2 = players.take();
                Game game = new Game(players, player1, player2);
                execServ.submit(game);
            } catch (InterruptedException ex) {
                Logger.getLogger(PlayerListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
