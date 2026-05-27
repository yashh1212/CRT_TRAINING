import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            Socket socket = new Socket("localhost", 1090);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Get input expression from user
            System.out.print("Enter expression (e.g., 2+2 or 15*3): ");
            String expression = scanner.nextLine();
            
            // Send expression string to the server
            out.println(expression);
            
            // Read and display calculated response
            String response = in.readLine();
            System.out.println("Server Response -> Result = " + response);
            
            out.close();
            in.close();
            socket.close();
            
        } catch (Exception e) {
            System.out.println("Connection error. Ensure the Server is running.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}