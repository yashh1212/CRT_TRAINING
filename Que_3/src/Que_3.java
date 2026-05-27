//Q3)difficult : Multi-Client Bank Transaction.
//	problem: Multiple ATM users Access Bank Transactions Server.
// 	Solution : Create : 1)server handles multiple clients using threads.
//						2)each client sends deposit Amount.
//						3)server updates balance.]
import java.io.*;
import java.net.*;
class ClientHandler003 extends Thread// Threads is stored by default in lang package which is the brain of java
{
	static int balance = 6000;
	Socket socket;
	ClientHandler003(Socket socket){
		this.socket=socket;
	}
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			
			int deposit = Integer.parseInt(in.readLine());
			synchronized(ClientHandler003.class) {
				balance = balance+ deposit;
				out.println("Deposit Sucessfull. Updated Bank Balance"+balance);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
public class Que_3{
	public static void main(String[]args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(1080);
		System.out.println("Banks Server Startes....");
		
		while(true) {
			Socket socket = serverSocket.accept();
			new ClientHandler003(socket).start();
		}
	}
}