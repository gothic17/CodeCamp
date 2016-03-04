import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    public void listen() throws IOException {

        Socket pingSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            pingSocket = new Socket("157.24.191.6", 8083);
            out = new PrintWriter(pingSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
        }  catch (IOException e) {
            System.out.println("No input!");
            return;
        }

        out.println("ping");
        String s =  in.readLine();
		if (s != null) {
        	System.out.println(in.readLine());
        }
        out.close();
        in.close();
        pingSocket.close();
        System.out.println("dwadwadwa");
    }
}