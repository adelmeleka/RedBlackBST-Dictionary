package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DisplayHome {

	static Scene display(){
		
		Label dic = new Label("DICTIONARY");
		
		Button loadBtn = new Button("Load Dictionary");
		loadBtn.setOnAction(e -> 
			TestDictionary.primaryStage.setScene(DisplayLoad.display())
			);
		
		Button displayBtn= new Button("DisplayTree");
		displayBtn.setOnAction(e -> {
			TestDictionary.primaryStage.setScene(DisplayTree.display());
		});
		
		Button sizeBtn = new Button("Dictionary Informations");
		sizeBtn.setOnAction(e -> {
			TestDictionary.primaryStage.setScene(DisplayInfo.display());
		});

		Button addBtn= new Button("Add new word");
		addBtn.setOnAction(e -> {
			TestDictionary.primaryStage.setScene(DisplayAdd.display());
		});
		Button lookUpBtn= new Button("Search Word");
		lookUpBtn.setOnAction(e -> {
			TestDictionary.primaryStage.setScene(DisplayLookUp.display());
		});
		
		Button removeBtn= new Button("Remove Word");
		removeBtn.setOnAction(e -> {
			TestDictionary.primaryStage.setScene(DisplayRemove.display());
		});
		
		
		VBox layout1 = new VBox(20);     
		layout1.getChildren().addAll(dic,loadBtn,displayBtn,sizeBtn,addBtn,lookUpBtn,removeBtn);
		
		Scene homeScene= new Scene(layout1, 300, 350);
		
		return homeScene;
	}
	
}
