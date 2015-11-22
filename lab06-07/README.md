# Labo 6-7: Sockets

In this lab we are going to focus on network programming through the design and the implementation of a TicTacToe network game in Netbeans, see in the figure below.



The Server can be connected to several clients as we can see in the above figure. Thus it can receive messages from any of those clients at any time and needs to process it.

It is a two-player Game where the Server is the manager of the game, which is responsible of making several decisions including:

* Validity of the players' moves
* Whether there is a victory and who is the winner and who is the loser
* To send to each player to inform about the move of the other player to update its board

A client on the other hand represents a player of the two players. It exchanges messages with the server that inform the client about the next action it is allowed to take, such as

* If it is his/her turn or the other player's turn
* If the move done but it is a valid one or not
* If he/she has won, lost, etc.

## Tasks

Some constraints for this lab:

* Work in a group of two students.
* Use threadpools.
* The server can handle only 10 games at a time.

## Extra

When the game is finished, players can decide to stay connected and wait for an oppent to play again.