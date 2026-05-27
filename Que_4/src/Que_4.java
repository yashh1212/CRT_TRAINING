import java.io.*;
import java.net.*;

class CalculatorHandler extends Thread {
    private Socket socket;

    public CalculatorHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Read the expression from the client (e.g., "2+2")
            String expression = in.readLine();
            if (expression != null) {
                System.out.println("[SERVER] Received calculation request: " + expression);
                
                String result = calculate(expression.replaceAll("\\s+", ""));
                
                // Send the solution back to the client
                out.println(result);
            }

            // Close connection channels for this client
            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // A helper method to parse and evaluate basic expressions (e.g., "2+2", "10*5")
    private String calculate(String expr) {
        try {
            char operator = ' ';
            int operatorIndex = -1;

            // Find the arithmetic operator in the string
            if (expr.contains("+")) { operator = '+'; operatorIndex = expr.indexOf('+'); }
            else if (expr.contains("-")) { operator = '-'; operatorIndex = expr.indexOf('-'); }
            else if (expr.contains("*")) { operator = '*'; operatorIndex = expr.indexOf('*'); }
            else if (expr.contains("/")) { operator = '/'; operatorIndex = expr.indexOf('/'); }

            if (operatorIndex == -1) {
                return "Error: Invalid Operator. Use +, -, *, or /";
            }

            // Split the numbers around the operator
            double num1 = Double.parseDouble(expr.substring(0, operatorIndex));
            double num2 = Double.parseDouble(expr.substring(operatorIndex + 1));
            double output = 0;

            switch (operator) {
                case '+': output = num1 + num2; break;
                case '-': output = num1 - num2; break;
                case '*': output = num1 * num2; break;
                case '/': 
                    if (num2 == 0) return "Error: Division by zero";
                    output = num1 / num2; 
                    break;
            }

            // Format output to remove trailing .0 for integers
            if (output % 1 == 0) {
                return String.valueOf((int) output);
            }
            return String.valueOf(output);

        } catch (Exception e) {
            return "Error: Could not parse expression.";
        }
    }
}

public class Que_4 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1090);
            System.out.println("=== Online Calculator Server Started on Port 1090 ===");
            
            while (true) {
                // Keep listening and hand off incoming clients to unique handler threads
                Socket socket = serverSocket.accept();
                new CalculatorHandler(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}