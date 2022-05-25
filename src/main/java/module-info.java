module com.sisfo {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.sisfo to javafx.fxml;
    exports com.sisfo;
}
