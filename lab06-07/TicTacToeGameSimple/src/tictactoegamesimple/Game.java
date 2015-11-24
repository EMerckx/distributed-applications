package tictactoegamesimple;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author khaluf
 */
/**
 * A two-player game.
 */
class Game implements Runnable {

    static final Character X = 'X';
    static final Character O = 'O';
    Map<Character, Player> players;

    /**
     * A board has nine squares. Each square is either un-owned or owned by a
     * player. So we use a simple array of player references. If null, the
     * corresponding square is un-owned, otherwise it stores a reference to the
     * player who owns it.
     */
    private final Player[] board = {
        null, null, null,
        null, null, null,
        null, null, null};

    /**
     * The current player.
     */
    Character currentPlayer;

    Game(Player playerX, Player playerO) {
        players = new HashMap<>();
        players.put(X, playerX);
        playerX.start(X);
        players.put(O, playerO);
        playerO.start(O);
    }

    /**
     * Returns whether the current state of the board is indicating the victory
     * of any of the players.
     */
    public boolean hasWinner() {
        return (board[0] != null && board[0] == board[1] && board[0] == board[2])
                || (board[3] != null && board[3] == board[4] && board[3] == board[5])
                || (board[6] != null && board[6] == board[7] && board[6] == board[8])
                || (board[0] != null && board[0] == board[3] && board[0] == board[6])
                || (board[1] != null && board[1] == board[4] && board[1] == board[7])
                || (board[2] != null && board[2] == board[5] && board[2] == board[8])
                || (board[0] != null && board[0] == board[4] && board[0] == board[8])
                || (board[2] != null && board[2] == board[4] && board[2] == board[6]);
    }

    /**
     * Returns whether there are no more empty squares.
     */
    public boolean boardFilledUp() {
        for (Player board1 : board) {
            if (board1 == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Called by the player threads when a player tries to make a move. This
     * method checks to see if the move is legal: that is, the player requesting
     * the move must be the current player and the square in which he/she is
     * trying to move must not already be occupied. If the move is legal the
     * game state is updated (the square is set and the next player becomes
     * current) and the other player is notified of the move so it can update
     * its client.
     */
    public boolean legalMove(int location, Player player) {
        if (location >= 0 && location < 9 && board[location] == null) {
            board[location] = player;
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        currentPlayer = X;
        Player player = players.get(currentPlayer);
        int location;
        boolean hasWinner = false;
        boolean boardFilledUp = false;
        boolean end = false;
        // Repeatedly get commands from the players and process them.
        do {
            // Tell the player that it is her turn.
            player.send("MESSAGE Your move");
            location = player.move();

            if (legalMove(location, player)) {
                player.send("VALID_MOVE");
                hasWinner = hasWinner();
                boardFilledUp = boardFilledUp();
                player.send(hasWinner ? "VICTORY"
                        : boardFilledUp ? "TIE"
                                : "");
                currentPlayer = currentPlayer == X ? O : X;
                player = players.get(currentPlayer);
                player.otherPlayerMoved(location);
                player.send(hasWinner ? "DEFEAT" : boardFilledUp ? "TIE" : "");
            } else if (location == -1) {
                player.close();
                end = true;
                currentPlayer = currentPlayer == X ? O : X;
                player = players.get(currentPlayer);
                player.send("QUIT");
            } else {
                player.send("MESSAGE ?");
            }
        } while (!boardFilledUp && !hasWinner && !end);

        if (end) {
            player.close();
        } else {
            for (Player p : players.values()) {
                p.close();
            }
        }
    }
}
