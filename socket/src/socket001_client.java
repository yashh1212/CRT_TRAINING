import java.io.*;
import java.net.*;
// (Networking : Socket&Communication)
//used for : Socket & ServerSocket 
public class socket001_client{
	public static void main(String[] args) throws UnknownHostException, IOException {
		//Client connects to server at 1060.
		Socket socket = new Socket("Localhost",1060);
		//creates output channel to send data to server .
		//True keyword : enables automatic flushing and sends message immediately.
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		out.println("Hello Support Team");
		
		//client disconnects after sending message.
		socket.close();
		
		// Internal Data flow....
		//Client --> OutputStrean --> Networks --> Server InputStream 
	}
}