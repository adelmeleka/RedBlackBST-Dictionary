package ui;

import dictionary.DictionaryRedBlackBST;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DisplayInfo {
	
	public static Scene display(){
		
		Label infoLabel = new Label("LOAD");
		
		int size = DictionaryRedBlackBST.dictionarySize();
		int height = DictionaryRedBlackBST.dictionaryHeight();
		
		Label result = new Label("");
		result.setText("Number of loaded words: "+size
						+"\n Height of Tree (Longest Path): "+height);
		
		Button backBtn = new Button("HOME");
		backBtn.setOnAction(e ->
			TestDictionary.primaryStage.setScene(DisplayHome.display())
				);
		
		VBox vbox = new VBox(20);     
		vbox.getChildren().addAll(infoLabel,result,backBtn);
		
		Scene infoScene = new Scene(vbox);
		
		return infoScene;
		
	}
	
}
