package Controller;

import JDBC.ConnectorUtil;
import JDBC.Pelanggan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pbo.hospitalyplus.HelloApplication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private ImageView logoPass;

    @FXML
    private ImageView logoUser;

    @FXML
    private TextField loginEmail;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private TextField mendaftarAlamat;
    @FXML
    private TextField mendaftarEmail;
    @FXML
    private TextField mendaftarNama1;
    @FXML
    private AnchorPane akutTidakada;
    @FXML
    private PasswordField mendaftarPassword;
    @FXML
    private TextField mendaftarTelpon;
    @FXML
    private AnchorPane succesfull;
    @FXML
    void OKButton(ActionEvent event) {
        succesfull.setVisible(false);
    }

    @FXML
    void alreadyHaveAccLogIn(ActionEvent event) throws IOException {
        tampilanSigIn.setVisible(false);
        tampilanLogIn.setVisible(true);
    }
    @FXML
    private AnchorPane tampilanSigIn;
    @FXML
    private AnchorPane tampilanLogIn;

    @FXML
    void signinButton(ActionEvent event) {
        tampilanSigIn.setVisible(true);
        tampilanLogIn.setVisible(false);
    }
    private String email;
    public String getEmail(){
        return email;
    }

    @FXML
    void tombolLogIn(ActionEvent event) throws IOException, SQLException {
        Connection connection = ConnectorUtil.getConnection();
        String emailPengguna = loginEmail.getText();
        email = emailPengguna;
        String passwordPengguna = loginPassword.getText();
        String sql = "SELECT email, password FROM pelanggan WHERE email = ? AND password = ?";
        boolean hasil = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, emailPengguna);
            preparedStatement.setString(2, passwordPengguna);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                hasil = true;
            } else {
                akutTidakada.setVisible(true);
                logoPass.setVisible(false);
                logoUser.setVisible(false);
            }
            if(hasil){
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                stage.setTitle("HospitalityPlus");
                stage.setScene(scene);
                stage.show();
                ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }


    @FXML
    void tombolCreateAccount(ActionEvent event) throws SQLException {
        String nama = mendaftarNama1.getText();
        String email = mendaftarEmail.getText();
        String telepon = mendaftarTelpon.getText();
        String alamat = mendaftarAlamat.getText();
        String password = mendaftarPassword.getText();

        Pelanggan pelanggan = new Pelanggan();
        pelanggan.addData(new Pelanggan(nama, email, alamat, telepon, password));

        succesfull.setVisible(true);
    }

    @FXML
    void OKAkunTidakTersedia(ActionEvent event) {
        akutTidakada.setVisible(false);
        logoPass.setVisible(true);
        logoUser.setVisible(true);
    }

}