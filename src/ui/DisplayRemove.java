package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import dictionary.DictionaryRedBlackBST;

public class DisplayRemove {

	public static Scene display(){
		
		Label removeLabel = new Label("REMOVE WORD");
		
		Label w = new Label("New Word");
		TextField word = new TextField();
		
		Label result = new Label();
		
		Button removeBtn = new Button("Remove Word");
		removeBtn.setOnAction(e->{
			
			String key = word.getText();
			
			if(key == null){
				
				result.setText("Enter Required Info");
				
			}else{
				
				int flag = 0;
				if(DictionaryRedBlackBST.lookUp(key) == null)
					 flag = 1;
					
				
				if(flag == 1){
					
					result.setText("Error! Word not Found!");
				}
				else {
				
					DictionaryRedBlackBST.remove(key);
					result.setText("Remove Done!");
				}
				
				
			}
		});
		
		Button removeMinBtn = new Button("Remove Min Word");
		removeMinBtn.setOnAction(e->{
			
			DictionaryRedBlackBST.removeMin();
			result.setText("Remove Min word Done!");
			
		});

		Button removeMaxBtn = new Button("Remove Max Word");
		removeMaxBtn.setOnAction(e->{
			
			DictionaryRedBlackBST.removeMax();
			result.setText("Remove Max word Done!");
			
		});
		
		Button backBtn = new Button("HOME");
		backBtn.setOnAction(e ->
			TestDictionary.primaryStage.setScene(DisplayHome.display())
				);
		
		VBox vbox = new VBox(20);     
		vbox.getChildren().addAll(removeLabel,w,word,removeBtn,removeMinBtn,removeMaxBtn,result,backBtn);
		
		Scene addScene = new Scene(vbox);
		
		return addScene;
		
	}
}
