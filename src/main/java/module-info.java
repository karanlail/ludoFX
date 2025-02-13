module com.karan.ludofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jshell;
    requires java.desktop;


    opens com.karan.ludofx to javafx.fxml;
    exports com.karan.ludofx;
    exports com.karan.ludofx.controllers;
    opens com.karan.ludofx.controllers to javafx.fxml;
    exports com.karan.ludofx.model;
    opens com.karan.ludofx.model to javafx.fxml;
}