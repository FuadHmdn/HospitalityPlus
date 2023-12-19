package Controller;

import JDBC.Pegawai;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class AdminController {

    @FXML
    private TextField ID_PEMESANAN;

    @FXML
    private ListView<String> ListView;
    private ObservableList<String> items;

    @FXML
    private TextField idKamar;

    @FXML
    private AnchorPane layar1;

    @FXML
    private AnchorPane layar2;

    @FXML
    private TextField statusKetersediaan;
    @FXML
    void KelolaKetersediaan(ActionEvent event) {
        layar2.setVisible(true);
        layar1.setDisable(true);
    }

    @FXML
    void KonfirmasiPemesanan(ActionEvent event) {
        String ID = ID_PEMESANAN.getText();
        Pegawai pegawaiDao = new Pegawai();
        pegawaiDao.konfirmasiPemesanan(ID);
        updateItemList();
    }

    private void updateItemList() {
        items.clear();
        items.addAll(ShareData.getPemesananList());
        ListView.setItems(items);
    }

    private void initializeItemList() {
        items = FXCollections.observableArrayList(ShareData.getPemesananList());
        ListView.setItems(items);
    }

    @FXML
    void ubah(ActionEvent event) throws SQLException {
        String id_kamar = idKamar.getText();
        String status = statusKetersediaan.getText();
        Pegawai pegawai = new Pegawai();
        pegawai.mengelolaKetersediaan(id_kamar, status);
        layar2.setVisible(false);
        layar1.setDisable(false);
    }
    @FXML
    public void initialize() {
        initializeItemList();
    }
}