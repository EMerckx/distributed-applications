package chatbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EMerckx
 */
public class Server {

    public static final int PORT = 8080;
    private static HashSet<String> names = new HashSet<String>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("The chat server is running");

        // create a new ServerSocket as a listener
        ServerSocket listener = new ServerSocket(PORT);

        try {
            while (true) {
                (new ChatHandler(listener.accept())).start();
            }
        } finally {

        }
    }

    private static class ChatHandler extends Thread {

        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public ChatHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                log("New client connected");

                // create a new reader
                in = new BufferedReader(new InputStreamReader(socket.
                        getInputStream()));

                // create a new printer
                out = new PrintWriter(socket.getOutputStream(), true);

                // get the name of the chatter
                boolean isNameSet = false;
                while (!isNameSet) {
                    
                    log("Waiting for name");
                    // submit to the client that a name must be given
                    out.println("SUBMITNAME");

                    // read the name given from the client
                    name = in.readLine();

                    // if name is null, quit
                    if (name == null) {
                        return;
                    } else {
                        // lock the set of names
                        synchronized (names) {
                            //if the name doesn't exist, add it to the hash
                            if (!names.contains(name)) {
                                names.add(name);
                                isNameSet = true;
                            }
                        }
                    }

                }

                // show to the client the name is accepted
                out.println("NAMEACCEPTED");
                log("Client " + name + " has set his name");
                // add the printer to the hash
                writers.add(out);

                // get the messages from the chatter
                boolean quit = false;
                while (!quit) {
                    // read the message from the socket (send by the client)
                    String message = in.readLine();

                    if (message == null) {
                        quit = true;
                    } else {
                        // print the message via each writer to the socket
                        for (PrintWriter printWriter : writers) {
                            printWriter.println("MESSAGE " + name + ": "
                                    + message);
                        }
                    }
                }

                // log that the client has disconnected
                log("Client " + name + " disconnected");

            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null,
                        ex);
            } finally {
                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }

        }

        private void log(String message) {
            System.out.println(message);
        }
    }
}
