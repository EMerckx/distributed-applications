package chatbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author EMerckx
 */
public class Client {

    // input and output
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    // GUI
    JFrame jFrame;
    JTextField jTextField;
    JTextArea jTextArea;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.jFrame.setVisible(true);
        client.run();
    }

    public Client() throws IOException {

        // init socket, reader and printer

        // construct GUI
        jFrame = new JFrame("ChatBox");
        jTextField = new JTextField(40);
        jTextArea = new JTextArea(15, 40);

        // layout GUI
        jTextField.setEditable(false);
        jTextArea.setEditable(false);
        jFrame.add(jTextField, "North");
        jFrame.add(new JScrollPane(jTextArea), "Center");
        jFrame.pack();

        // set action listener on the textfield
        jTextField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // get the message from the TextField
                String message = jTextField.getText();
                jTextField.setText("");
                out.println(message);
            }
        });
    }

    private void run() throws IOException {
        
        
        socket = new Socket("localhost", 8080);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        
        while (true) {
            
            // read the line send by the server
            String line = in.readLine();
            System.out.println(line);

            if (line.startsWith("SUBMITNAME")) {
                // get the name and submit it to the server 
                String name = getName();
                out.println(name);
            
            } else if (line.startsWith("NAMEACCEPTED")) {
                // enable TextField to chat
                jTextField.setEditable(true);
                
            } else if (line.startsWith("MESSAGE")) {
                // add message to TextArea
                jTextArea.append(line.substring(8) + "\n");
            }
        }
    }

    private String getName() {
        String name = JOptionPane.showInputDialog(jFrame, "Choose your name",
                "Name selection", JOptionPane.PLAIN_MESSAGE);
        return name;
    }

}
