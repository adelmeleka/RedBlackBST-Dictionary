package ui;

import dictionary.DictionaryRedBlackBST;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class DisplayTree {

	 public static Scene display(){
		 
		 	VBox vBox = new VBox();
	    	    
	        Pane canvase = DictionaryRedBlackBST.GraphicDisplay();
	        canvase.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	        
	        Button backBtn = new Button("HOME");
				backBtn.setOnAction(e ->
					TestDictionary.primaryStage.setScene(DisplayHome.display())
						);
			canvase.getChildren().add(backBtn);
	        
	        ScrollPane scrollPane = new ScrollPane(canvase);
	        scrollPane.setPrefSize(10000, 1000);
	        scrollPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	        scrollPane.setFitToWidth(true);
	        scrollPane.setFitToHeight(true);
	        scrollPane.setStyle("-fx-focus-color: transparent;");
	        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
	        
	        vBox.getChildren().addAll(scrollPane,backBtn);
	        
	        Scene DisplayTreeScene = new Scene(vBox);
	       

	        return DisplayTreeScene;
	  }
	    
}
