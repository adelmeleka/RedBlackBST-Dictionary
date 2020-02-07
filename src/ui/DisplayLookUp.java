package ui;

import dictionary.DictionaryRedBlackBST;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DisplayLookUp {

	public static Scene display(){
		
		Label lookLabel = new Label("LOOK UP");
		
		Label w = new Label("Word");
		TextField word = new TextField();
		
		Label result = new Label();
		
		Button searchBtn = new Button("Search");
		searchBtn.setOnAction(e->{
			
			String key = word.getText();
			
			if(key == null){
				
				result.setText("Enter Required Info");
				
			}else{
				
				int flag = 0;
				if(DictionaryRedBlackBST.lookUp(key) == null)
					 flag = 1;
					
				
				if(flag == 1){
					
					result.setText("Error! Word not found!");
				}
				else {
					
					String def = DictionaryRedBlackBST.lookUp(key);
					result.setText("Definition:  "+def);
				}
				
				
			}
		});

		Button backBtn = new Button("HOME");
		backBtn.setOnAction(e ->
			TestDictionary.primaryStage.setScene(DisplayHome.display())
				);
		
		VBox vbox = new VBox(20);     
		vbox.getChildren().addAll(lookLabel,w,word,searchBtn,result,backBtn);
		
		Scene lookUpScene = new Scene(vbox);
		
		return lookUpScene;
		
	}
	
}
