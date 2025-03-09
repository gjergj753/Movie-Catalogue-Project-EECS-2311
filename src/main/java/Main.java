package main.java;

import javax.swing.UIManager;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.themes.*;

import main.java.view.LoginPage;

public class Main {

	

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(new FlatMacDarkLaf());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		new LoginPage();
	}

}
