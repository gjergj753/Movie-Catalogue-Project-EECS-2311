module org.openjfx.hellofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
	requires java.sql;

    opens org.openjfx.hellofx to javafx.fxml;
    exports org.openjfx.hellofx;
    exports org.openjfx.hellofx.controller;
    exports org.openjfx.hellofx.controller.Client;
    exports org.openjfx.hellofx.dao;
    exports org.openjfx.hellofx.model;
    //exports org.openjfx.hellofx.service;
    exports org.openjfx.hellofx.view;
}
