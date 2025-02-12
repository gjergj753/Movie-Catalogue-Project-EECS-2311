package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		try {
		FXMLLoader fxmlLoader=new FXMLLoader(getClass().getClassLoader().getResource("/Fxml/Login.fxml"));
		Scene scene=new Scene(fxmlLoader.load());
		stage.setScene(scene);
		stage.show();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
