package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;  
import javafx.event.EventHandler;  
import javafx.scene.Group;  
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;  
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;  
import javafx.scene.text.Font;  
import javafx.scene.text.FontWeight;  
import javafx.scene.text.Text;

public class TextAnalyzerFinal extends Application {
	/** Creating the path to retrieve the poem */
	Path poemFile = Paths.get("C:\\Users\\colla\\Desktop\\TextAnalyzer\\Poem.html");
	
	public void start(Stage primaryStage) throws Exception {
		Text greeting = new Text();
		Text question = new Text();
		Text no = new Text();
			/** Setting position, font, and text for greeting text */
			greeting.setX(90);  
			greeting.setY(20);  
			greeting.setFont(Font.font("Times New Roman",FontWeight.BOLD,15));  
			greeting.setText("Welcome to Text Analyzer!");  
			/** Setting position, font, and text for question text */
			question.setX(60);  
			question.setY(35);  
			question.setFont(Font.font("Times New Roman",15));  
			question.setText("Would you like to use the word counter?");
		/** Creating "Yes" button with position */
	    Button b1 = new Button("Yes");
	    	b1.setTranslateX(125);
	    	b1.setTranslateY(75);
	    /** Creating "No" button with position */
	    Button b2 = new Button("No");
	    	b2.setTranslateX(175);
	    	b2.setTranslateY(75);
	    	/** Creating event to count word occurrences when "Yes" button is clicked and display a message when "No" button is clicked */
	    	EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
	    		
	    		public void handle(MouseEvent event) {
	    			
	    			if(event.getSource()==b1) {
	    				String fileText = null;
	    				
	    				try {
	    					fileText = Files.readString(poemFile);
	    					} 
	    				catch (IOException e) {
	    						e.printStackTrace();
	    					}
	    				/** Changing all input text that is read within the file to lower case characters */
	    				fileText = fileText.toLowerCase();
	    				/** Limiting input text that is read to alphabetic characters a-z */
	    				Pattern allowedCharacters = Pattern.compile("[a-z]+");
	    				Matcher characterMatcher = allowedCharacters.matcher(fileText);
	    				/** Creating a TreeMap to contain all the words read within the input text */
	    				TreeMap<String, Integer> wordCounter = new TreeMap<>();
	    				
	    				while (characterMatcher.find()) {
	    					String word = characterMatcher.group();
	    					/** When the reader finds another instance of a word 
	    					 * it will add an integer of 1 to the total count for that word*/
	    					if (wordCounter.containsKey(word)) {
        					wordCounter.computeIfPresent(word, (w, c) -> Integer.valueOf(c.intValue() + 1));
        					}
	    					/** When the reader is no longer able to find another instance of a word
	    					 * it will keep the final total count for that word */
	    					else {
                        	wordCounter.computeIfAbsent(word, (w) -> Integer.valueOf(1));
                        	}
	    				}
	    				/** Creating a set from the wordCounter TreeMap */
	    				Set<Map.Entry<String, Integer>> fileSet = wordCounter.entrySet();
	    				/** Creating an ArrayList from the fileSet Set */
	    				List<Map.Entry<String, Integer>> fileList = new ArrayList<Map.Entry<String, Integer>>(fileSet);
	    				
	    				Collections.sort(fileList, new Comparator<Map.Entry<String, Integer>>() {
	    					
	    					public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
	    						/** Orders the fileList ArrayList from highest word count to lowest 
	    						 * by comparing the value counted of each word */
	    						return (b.getValue()).compareTo(a.getValue());
	    						}
	    					});
	    				/** Prints the now-ordered fileList ArrayList with the word and total count */
	    				for(Map.Entry<String, Integer> i:fileList) {
	    					System.out.println(i.getKey()+" -> "+i.getValue());
	    					}
	    				}
	    			
	    			if(event.getSource()==b2) {
	    				/** Creating event to display the no text when "No" button is clicked
	    				 * with position, font, color, and text */
	    				no.setX(110);
	    				no.setY(130);
	    				no.setFont(Font.font("Times New Roman",15));
	    				no.setFill(Color.RED);
	    				no.setText("Well, that's no fun.");
	    				}
	    			}
	    		};
	    		/** Setting handler MouseEvent to b1 and b2 buttons */
	    		b1.setOnMouseClicked(handler);
	    		b2.setOnMouseClicked(handler);
	    		/** Creating a group containing the
	    		 * greeting text, question text, b1 button, b2 button, and no text */
	    		Group g = new Group();
	    			g.getChildren().add(greeting);
	    			g.getChildren().add(question);
	    			g.getChildren().add(b1);
	    			g.getChildren().add(b2);
	    			g.getChildren().add(no);
	    		/** Creating a scene containing group g, 
	    		 * with size and title to be displayed */
	    		Scene s = new Scene(g,350,150);
	    		primaryStage.setScene(s);
	    		primaryStage.setTitle("Text Analyzer");
	    		primaryStage.show();
	    		}
	/** Launches the program */
	public static void main(String[] args) {
		launch(args);
		}
	}