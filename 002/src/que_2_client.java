import java.io.*;
import java.net.*;
public class que_2_client{
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("Localhost",1070);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		
		out.println("Burger");
		
		String response = in.readLine();
		
		System.out.println(response);
		
		socket.close();
	}
}