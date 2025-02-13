package org.openjfx.hellofx;

import javafx.application.Application;
import javafx.stage.Stage;
import org.openjfx.hellofx.model.Model;

public class Main extends Application{

	@Override
	public void start(Stage stage){
		Model.getInstance().getViewFactory().showLoginWindow();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
