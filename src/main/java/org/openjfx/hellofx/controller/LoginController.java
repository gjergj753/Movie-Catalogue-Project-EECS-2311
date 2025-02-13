package org.openjfx.hellofx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.openjfx.hellofx.model.Model;

public class LoginController implements Initializable {
	public Label username_lbl;
	public TextField username_fld;
	public Label password_lbl;
	public TextField password_fld;
	public Button login_btn;
	public Label error_lbl;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		login_btn.setOnAction(event -> Model.getInstance().getViewFactory().showClientWIndow());
	}


}
