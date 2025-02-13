package org.openjfx.hellofx.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.openjfx.hellofx.controller.Client.ClientController;



public class ViewFactory {
	
	private final StringProperty clientSelectedMenuItem;
	private AnchorPane dashboardView;
	private AnchorPane filmView;

	public ViewFactory() {
		this.clientSelectedMenuItem=new SimpleStringProperty("");
	}
	
	public StringProperty getClientSelectedMenuItem() {
		return clientSelectedMenuItem;
	}

	public AnchorPane getDashboardView() {
		if(dashboardView==null) {
			try {
				dashboardView=new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dashboardView;
	}
	
	public AnchorPane getFilmView() {
		if(dashboardView==null) {
			try {
				filmView=new FXMLLoader(getClass().getResource("/Fxml/Client/Film.fxml")).load();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return filmView;
	}

	public void showLoginWindow() {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
		createStage(loader);
	}

	public void showClientWIndow() {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
		ClientController clientController=new ClientController();
		loader.setController(clientController);
		createStage(loader);
	}

	private void createStage(FXMLLoader loader) {
		Scene scene=null;
		try {
			scene=new Scene(loader.load());
		}catch (Exception e) {
			e.printStackTrace();
		}
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.setTitle("Movie Catalogue");
		stage.show();
	}
	
	public void closeStage(Stage stage) {
		stage.close();
	}

}
