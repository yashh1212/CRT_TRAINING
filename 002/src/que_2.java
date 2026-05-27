


import java.io.*;
import java.net.*;
public class que_2{
	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket=new ServerSocket(1070);
		System.out.println("Restaurant Server Started");
		
		Socket socket = serverSocket.accept();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		
		String order = in.readLine();
		
		System.out.println("Order Received :"+order);
		
		out.println("Order Confirmed: "+order);
		
		socket.close();
		serverSocket.close();
	}
}