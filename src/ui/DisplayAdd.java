package ui;

import dictionary.DictionaryRedBlackBST;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DisplayAdd {

	public static Scene display(){
		
		Label addLabel = new Label("ADD NEW WORD");
		
		Label w = new Label("New Word");
		TextField word = new TextField();
		
		Label d = new Label("Definition");
		TextField definition = new TextField();
		
		Label result = new Label();
		
		Button addBtn = new Button("Add Word");
		addBtn.setOnAction(e->{
			
			String key = word.getText();
			String value = definition.getText();
			
			if(key.equals("") |value.equals("")){
				
				result.setText("Enter Required Info");
				
			}else{
				
				int flag = 0;
				if(DictionaryRedBlackBST.lookUp(key) == null)
					 flag = 1;
					
				
				if(flag == 0){
					
					result.setText("Error! Word Already exists!");
				}
				else {
					
					DictionaryRedBlackBST.insert(key, value);
					result.setText("Add Done!");
				}
				
				
			}
		});

		Button backBtn = new Button("HOME");
		backBtn.setOnAction(e ->
			TestDictionary.primaryStage.setScene(DisplayHome.display())
				);
		
		VBox vbox = new VBox(20);     
		vbox.getChildren().addAll(addLabel,w,word,d,definition,addBtn,result,backBtn);
		
		Scene addScene = new Scene(vbox);
		
		return addScene;
		
	}
}
