module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.desktop;
    requires javafx.media;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo.controller;
    opens com.example.demo.Levels to javafx.fxml;
    opens com.example.demo.Objects.Planes to javafx.fxml;
    opens com.example.demo.Objects.Projectiles to javafx.fxml;
    opens com.example.demo.Objects.Powerups to javafx.fxml;
    opens com.example.demo.Managers to javafx.fxml;
    opens com.example.demo.screens to javafx.fxml;
    opens com.example.demo.Objects to javafx.fxml;

}