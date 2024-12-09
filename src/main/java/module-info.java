module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    // Update these lines to match new structure
    opens com.example.demo.controller to javafx.fxml;
    opens com.example.demo.actors to javafx.fxml;
    opens com.example.demo.actors.projectiles to javafx.fxml;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.ui to javafx.fxml;

    exports com.example.demo.controller;
    exports com.example.demo.actors;
    exports com.example.demo.actors.projectiles;
    exports com.example.demo.levels;
    exports com.example.demo.ui;
}
