import java.io.*;
import java.net.*;

public class Que_3_client{
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket=new Socket("Localhost",1080);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		out.println("500");
		
		String response = in.readLine();
		System.out.println(response);
		
		socket.close();
	}
}