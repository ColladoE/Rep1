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

public class TextAnalyzerGui extends Application {
	Path poemFile = Paths.get("C:\\Users\\colla\\Desktop\\TextAnalyzer\\Poem.html");
	
	public void start(Stage primaryStage) throws Exception {
		Text greeting = new Text();
		Text question = new Text();
		Text no = new Text();
		
		greeting.setX(90);  
	    greeting.setY(20);  
	    greeting.setFont(Font.font("Times New Roman",FontWeight.BOLD,15));  
	    greeting.setText("Welcome to Text Analyzer!");  
      
	    question.setX(60);  
	    question.setY(35);  
	    question.setFont(Font.font("Times New Roman",15));  
	    question.setText("Would you like to use the word counter?");
	    
	    Button b1 = new Button("Yes");
	    b1.setTranslateX(125);
	    b1.setTranslateY(75);
	    
	    Button b2 = new Button("No");
	    b2.setTranslateX(175);
	    b2.setTranslateY(75);
	    
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
	    				
	    				fileText = fileText.toLowerCase();
	    				
	    				Pattern allowedCharacters = Pattern.compile("[a-z]+");
	    				Matcher characterMatcher = allowedCharacters.matcher(fileText);
	    				
	    				TreeMap<String, Integer> wordCounter = new TreeMap<>();
	    				
	    				while (characterMatcher.find()) {
	    					String word = characterMatcher.group();
	    					
	    					if (wordCounter.containsKey(word)) {
        					wordCounter.computeIfPresent(word, (w, c) -> Integer.valueOf(c.intValue() + 1));
        					}
	    					
	    					else {
                        	wordCounter.computeIfAbsent(word, (w) -> Integer.valueOf(1));
                        	}
	    				}
	    				
	    				Set<Map.Entry<String, Integer>> fileSet = wordCounter.entrySet();
	    				List<Map.Entry<String, Integer>> fileList = new ArrayList<Map.Entry<String, Integer>>(fileSet);
	    				
	    				Collections.sort(fileList, new Comparator<Map.Entry<String, Integer>>() {
	    					
	    					public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
	    						return (b.getValue()).compareTo(a.getValue());
	    						}
	    					});
	    				
	    				for(Map.Entry<String, Integer> i:fileList) {
	    					System.out.println(i.getKey()+" -> "+i.getValue());
	    					}
	    				}
	    			
	    			if(event.getSource()==b2) {
	    				
	    				no.setX(110);
	    				no.setY(130);
	    				no.setFont(Font.font("Times New Roman",15));
	    				no.setFill(Color.RED);
	    				no.setText("Well, that's no fun.");
	    				}
	    			}
	    		};
	    		
	    		b1.setOnMouseClicked(handler);
	    		b2.setOnMouseClicked(handler);
	    		
	    		Group g = new Group();
	    		g.getChildren().add(greeting);
	    		g.getChildren().add(question);
	    		g.getChildren().add(b1);
	    		g.getChildren().add(b2);
	    		g.getChildren().add(no);
	    		
	    		Scene s = new Scene(g,350,150);
	    		primaryStage.setScene(s);
	    		primaryStage.setTitle("Text Analyzer");
	    		primaryStage.show();
	    		}
	
	public static void main(String[] args) {
		launch(args);
		}
	}  