package application;

import java.io.*;
import java.net.*;

public class Server {
	
	public static String isPrime(int num) {
		
		if(num < 2) {
			return "Your number is not a prime number.";
			}
		
		int i = 2;
		
		while(i < num) {
			if(num%i==0) {
				return "Your number is not a prime number.";
				}
			
			i++;
			}
		
		return "Your number is a prime number.";
		}
	
	public static void main(String[] args){
		
		try{
			ServerSocket serverSocket = new ServerSocket(8080);
			Socket socket = serverSocket.accept();
			DataInputStream dataInput = new DataInputStream(socket.getInputStream());
			DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
			int userInput = (int)dataInput.readInt();
			
			dataOutput.writeUTF(isPrime(userInput));
			}
		
		catch(Exception e){
			System.out.println(e);
			}
		}
	}