package application;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Client extends Application {
	Scanner scanner = new Scanner(System.in);

  public void start(Stage primaryStage) {
	BorderPane borderPane = new BorderPane();
    	borderPane.setLeft(new Label("Please enter a number: "));
    
    TextField textField = new TextField();
    	textField.setAlignment(Pos.BOTTOM_RIGHT);
    	borderPane.setCenter(textField);
    
    BorderPane mainPane = new BorderPane();
    TextArea textArea = new TextArea();
    	mainPane.setCenter(textArea);
    	mainPane.setTop(borderPane);
    
    Scene scene = new Scene(mainPane, 250, 100);
    
    primaryStage.setTitle("Week11Project");
    primaryStage.setScene(scene);
    primaryStage.show();
    
    textField.setOnAction(e -> {
    	try{
			Socket socket = new Socket("localhost",8080);
			DataInputStream dataInput = new DataInputStream(socket.getInputStream());
			DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
			
			System.out.print("\nPlease enter a number: ");
			int userInput = scanner.nextInt();
			
			dataOutput.writeInt(userInput);
			String answerText = (String)dataInput.readUTF();
			textArea.appendText(answerText);
			}
		
		catch(Exception a){
			System.out.println(a);
			}
    });
  }

  
  public static void main(String[] args) {
    launch(args);
  }
}