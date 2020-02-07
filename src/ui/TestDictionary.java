package ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestDictionary extends Application {
			
		 public static void main(String[] args) {
		        
			 Application.launch(args);
			 
		    }
		 
		 static Stage primaryStage = new Stage();
		@Override
		public void start(Stage stage) throws Exception {
			
			
			
			primaryStage.setScene(DisplayHome.display());
			
			primaryStage.show();
		
		}

		 	
	
}
