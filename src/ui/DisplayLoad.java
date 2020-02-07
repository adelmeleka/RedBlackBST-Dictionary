package ui;

import dictionary.DictionaryRedBlackBST;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DisplayLoad {

	static Scene display(){

		Label loadLabel = new Label("LOAD");
		
		DictionaryRedBlackBST.loadDictionary("file.txt");
		int size = DictionaryRedBlackBST.dictionarySize();
		int height = DictionaryRedBlackBST.dictionaryHeight();
		
		Label result = new Label("");
		result.setText("Load Succssed! "
						+ "\n Number of loaded words: "+size
						+"\n Height of Tree (Longest Path): "+height);
		
		Button backBtn = new Button("HOME");
		backBtn.setOnAction(e ->
			TestDictionary.primaryStage.setScene(DisplayHome.display())
				);
		
//		StackPane pane = new StackPane();
		
		VBox vbox = new VBox(20);     
		vbox.getChildren().addAll(loadLabel,result,backBtn);
		
//		pane.getChildren().add(vbox);
		
//		Scene loadScene = new Scene(pane, 300, 250);
		Scene loadScene = new Scene(vbox, 300, 250);
		
		return loadScene;
	}
	
}
