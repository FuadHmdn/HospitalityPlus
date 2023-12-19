module pbo.hospitalyplus {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.junit.jupiter.api;
    requires java.sql;

    exports pbo.hospitalyplus;
    exports Controller;
    opens Controller to javafx.fxml;
}