package pbo.hospitalyplus;

import Controller.HomeController;
import Controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    // #254187
    //#ddc003
    private static LoginController loginControllerInstance;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        loginControllerInstance = loader.getController();
        Scene scene = new Scene(root);
        stage.setTitle("HospitalityPlus");
        stage.setScene(scene);
        stage.show();
    }
    public static LoginController getLoginControllerInstance() {
        return loginControllerInstance;
    }

    public static void main(String[] args) {
        launch();
    }
}