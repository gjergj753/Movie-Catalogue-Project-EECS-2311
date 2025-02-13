package org.openjfx.hellofx.controller.Client;

import java.net.URL;
import java.util.ResourceBundle;

import org.openjfx.hellofx.model.Model;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class ClientController implements Initializable{

	public BorderPane client_parent;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Based on Text from CMController change the center of the window
		Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observableValue,oldVal,newVal) -> {
			switch(newVal) {
				case "Film" -> client_parent.setCenter(Model.getInstance().getViewFactory().getFilmView());
				default -> client_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
			}
		});
	}

}
