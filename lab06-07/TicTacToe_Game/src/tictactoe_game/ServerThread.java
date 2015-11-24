/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_game;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author vongenae
 */
public class ServerThread implements Runnable {

    private final BlockingQueue<Player> players;
    PlayerListener endGame;
    Listener listener;

    public ServerThread() {
        players = new LinkedBlockingQueue<>();;
        endGame = new PlayerListener(players);
        listener = new Listener(players);
    }


    public void end() {
        endGame.end();
        listener.end();
    }


    @Override
    public void run() {
        new Thread(endGame).start();
        new Thread(listener).start();
    }
}
