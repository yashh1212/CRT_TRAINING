//Socket programming :  
//Socket= Communication End Point
//A socket act like a telephone connection between Client and Server 
//Client Scoket  --> Server  

//Key concepts ..
//ServerScoket : Waits for communication 
//Socket : actual communication channel 
//InputStream : receives data
// OutputStream : sends data 
//accept : waits for clients .

//Que 1 : Basic : Chat Message Sender … (Client -- > Server)
//	Problem: A customer sends a message to customer support ..
//	Solution: create, 1) A Server that waits for a client message .
//			2) A client that sends : "Hello Support Team."
//
//	Used for : BufferedReader & InputStreamReader


import java.io.*;
import java.net.*;
// (Networking : Socket&Communication)
//used for : Socket & ServerSocket 
public class socket001{
	public static void main(String[] args) throws IOException {
		//creates server on port number 1060
		//Internal working : JAVA opens port 1060 and listen for incoming client request.
		ServerSocket serverSocket=new ServerSocket(1060);
		System.out.println("Server Waiting for Client...");
		
		//Most IMP line ... 
		// Server waits until client the client connects.
		//Internal working : program pauses here and it stays blocked until client sends request.
		Socket socket = serverSocket.accept();
		
		//we will create input channel to receive message from client.
		//Breakdown:
		//getInputStream --> gets raw data from client .
		//InputStreamRead --> converts byte data into readable characters
		//BufferReader --> Reads text efficiently line by line .
		BufferedReader in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		//Reads one full line sent by client..
		String message = in.readLine();
		System.out.println("CLient says :"+message);
		
		//CLose connection with client .
		socket.close();
		
		//Stops server completely.
		//port 1060 becomes free again and the server is deleted .
		serverSocket.close();
	}
}