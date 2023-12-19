package Controller;

import JDBC.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pbo.hospitalyplus.HelloApplication;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private AnchorPane layarIsiKeranjang;
    @FXML
    private Text teksFasilitas;
    @FXML
    private AnchorPane layarFasilitas;
    @FXML
    private AnchorPane menungguKonfirmasi;
    @FXML
    private AnchorPane sceneMandiri;
    @FXML
    private AnchorPane scenePaypal;
    @FXML
    private AnchorPane sceneVisa;
    @FXML
    private Label TotalPembayaran;
    @FXML
    private ChoiceBox<Integer> pilihan;
    private Integer[] pilihanPelanggan = {1,2,3,4,5};
    @FXML
    private ChoiceBox<String> jeniskamar;
    private String[] jenisKamar = {"VVIP", "VIP", "Regular"};
    @FXML
    private Label IDKamar;
    @FXML
    private AnchorPane DetailPesanan;
    @FXML
    private Label emailPemesanan;
    @FXML
    private Label idPemesanan;
    @FXML
    private Label jumlahtamuPesanan;
    @FXML
    private Label tanggalPemesanan;
    @FXML
    private TextField jumlahTamu;
    @FXML
    private BorderPane menuHome1;
    @FXML
    private AnchorPane menuHome2;
    @FXML
    private AnchorPane menuRoom;
    @FXML
    private AnchorPane menuRoom1;
    @FXML
    private AnchorPane menuRoom2;
    @FXML
    private AnchorPane menuRoom3;
    @FXML
    private DatePicker tanggalMinap;
    @FXML
    private DatePicker tanggalPulang;
    @FXML
    private AnchorPane pembayaranLayar;

    private Pemesanan pemesanan;
    LocalDateTime tanggalmenginap;
    LocalDateTime tanggalpulang;
    int jumlahtamu;
    String metodeBayar;
    private LayananTambahan layananTambahan;

    @FXML
    void TombolHomeRoom(ActionEvent event) throws IOException {
        menuRoom.setVisible(false);
        menuRoom1.setVisible(false);
        menuRoom2.setVisible(false);
        menuRoom3.setVisible(false);
        menuHome1.setVisible(true);
        menuHome2.setVisible(true);
    }

    @FXML
    void TombolLogOutHome(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("HospitalityPlus");
        stage.setScene(scene);
        stage.show();
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void TombolLogOutRoom(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("HospitalityPlus");
        stage.setScene(scene);
        stage.show();
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void TombolRooomHome(ActionEvent event) {
        menuRoom.setVisible(true);
        menuRoom1.setVisible(false);
        menuRoom2.setVisible(false);
        menuRoom3.setVisible(false);
        menuHome1.setVisible(false);
        menuHome2.setVisible(false);
    }

    @FXML
    void next1(ActionEvent event) {
        menuRoom.setVisible(false);
        menuRoom1.setVisible(true);
        menuRoom2.setVisible(false);
        menuRoom3.setVisible(false);
        menuHome1.setVisible(false);
        menuHome2.setVisible(false);
    }

    @FXML
    void next2(ActionEvent event) {
        menuRoom.setVisible(false);
        menuRoom1.setVisible(false);
        menuRoom2.setVisible(true);
        menuRoom3.setVisible(false);
        menuHome1.setVisible(false);
        menuHome2.setVisible(false);
    }

    @FXML
    void next3(ActionEvent event) {
        menuRoom.setVisible(false);
        menuRoom1.setVisible(false);
        menuRoom2.setVisible(false);
        menuRoom3.setVisible(true);
        menuHome1.setVisible(false);
        menuHome2.setVisible(false);
    }

    @FXML
    void next4(ActionEvent event) {
        menuRoom.setVisible(true);
        menuRoom1.setVisible(false);
        menuRoom2.setVisible(false);
        menuRoom3.setVisible(false);
        menuHome1.setVisible(false);
        menuHome2.setVisible(false);
    }

    @FXML
    void tombolBookNowHome(ActionEvent event) {
        jumlahtamu = Integer.parseInt(jumlahTamu.getText());
        tanggalmenginap = tanggalMinap.getValue().atStartOfDay();
        tanggalpulang = tanggalPulang.getValue().atStartOfDay();

        pemesanan = new Pemesanan(jumlahtamu, tanggalmenginap, tanggalpulang);

        String email = HelloApplication.getLoginControllerInstance().getEmail();
        String jenis_kamar = jeniskamar.getValue();
        pemesanan.setJumlah_tamu(jumlahtamu);
        pemesanan.setTanggal_menginap(tanggalmenginap);
        pemesanan.setTanggal_pulang(tanggalpulang);
        Pelanggan pelanggan = new Pelanggan();
        pelanggan.pemesananKamar(pemesanan, email, jenis_kamar);
        Pemesanan pemesananDao = new Pemesanan();
        pemesananDao.konfirmasiPemesanan(pemesanan, email);

        menuRoom.setVisible(true);
        menuRoom1.setVisible(false);
        menuRoom2.setVisible(false);
        menuRoom3.setVisible(false);
        menuHome1.setVisible(false);
        menuHome2.setVisible(false);
    }

    @FXML
    void BookNowRajabasa(ActionEvent event) throws SQLException {
        idPemesanan.setText(pemesanan.getId_pemesanan());
        emailPemesanan.setText(HelloApplication.getLoginControllerInstance().getEmail());
        LocalDateTime tanggalMenginap = pemesanan.getTanggal_menginap();
        tanggalPemesanan.setText(tanggalMenginap != null ? tanggalMenginap.toString() : "");
        jumlahtamuPesanan.setText(String.valueOf(pemesanan.getTamu()));
        IDKamar.setText("KMR002");
        String idKamar = IDKamar.getText();
        Kamar kamar = new Kamar();
        kamar.getHargaKamar(idKamar);
        TotalPembayaran.setText(String.valueOf(kamar.getHarga()));

        menuRoom.setDisable(true);
        DetailPesanan.setVisible(true);
    }

    @FXML
    void BookingNowHarmoni(ActionEvent event) throws SQLException {
        idPemesanan.setText(pemesanan.getId_pemesanan());
        emailPemesanan.setText(HelloApplication.getLoginControllerInstance().getEmail());
        LocalDateTime tanggalMenginap = pemesanan.getTanggal_menginap();
        tanggalPemesanan.setText(tanggalMenginap != null ? tanggalMenginap.toString() : "");
        jumlahtamuPesanan.setText(String.valueOf(pemesanan.getTamu()));
        IDKamar.setText("KMR003");
        String idKamar = IDKamar.getText();
        Kamar kamar = new Kamar();
        kamar.getHargaKamar(idKamar);
        TotalPembayaran.setText(String.valueOf(kamar.getHarga()));

        menuRoom.setDisable(true);
        DetailPesanan.setVisible(true);
    }

    @FXML
    void BookingNowKalianda(ActionEvent event) throws SQLException {
        idPemesanan.setText(pemesanan.getId_pemesanan());
        emailPemesanan.setText(HelloApplication.getLoginControllerInstance().getEmail());
        LocalDateTime tanggalMenginap = pemesanan.getTanggal_menginap();
        tanggalPemesanan.setText(tanggalMenginap != null ? tanggalMenginap.toString() : "");
        jumlahtamuPesanan.setText(String.valueOf(pemesanan.getTamu()));
        IDKamar.setText("KMR001");
        String idKamar = IDKamar.getText();
        Kamar kamar = new Kamar();
        kamar.getHargaKamar(idKamar);
        TotalPembayaran.setText(String.valueOf(kamar.getHarga()));

        menuRoom.setDisable(true);
        DetailPesanan.setVisible(true);
    }
    @FXML
    void BookingNowSuryaVista(ActionEvent event) throws SQLException {
        idPemesanan.setText(pemesanan.getId_pemesanan());
        emailPemesanan.setText(HelloApplication.getLoginControllerInstance().getEmail());
        LocalDateTime tanggalMenginap = pemesanan.getTanggal_menginap();
        tanggalPemesanan.setText(tanggalMenginap != null ? tanggalMenginap.toString() : "");
        jumlahtamuPesanan.setText(String.valueOf(pemesanan.getTamu()));
        IDKamar.setText("KMR004");
        String idKamar = IDKamar.getText();
        Kamar kamar = new Kamar();
        kamar.getHargaKamar(idKamar);
        TotalPembayaran.setText(String.valueOf(kamar.getHarga()));

        menuRoom.setDisable(true);
        DetailPesanan.setVisible(true);
    }

    @FXML
    void LanjutBayar(ActionEvent event) throws SQLException {
        Integer jumlahPilihan = pilihan.getValue();
        Pelanggan pelangganDao = new Pelanggan();
        String id_pemesanan = idPemesanan.getText();
        String id_kamar = IDKamar.getText();
        pelangganDao.pemesananLayananTambahan(jumlahPilihan, id_pemesanan, id_kamar);

        String hargaawal = TotalPembayaran.getText();
        double currentValue = Double.parseDouble(hargaawal);
        double newValue = currentValue + layananTambahan.getHargaLayananTambahan();
        String hargaakhir = String.valueOf(newValue);

        TotalPembayaran.setText(hargaakhir);
        ShareData.addPemesanan(id_pemesanan, HelloApplication.getLoginControllerInstance().getEmail());
        metodeBayar = "Mandiri";

        pembayaranLayar.setVisible(true);
        DetailPesanan.setVisible(false);
        sceneMandiri.setVisible(true);
        scenePaypal.setVisible(false);
        sceneVisa.setVisible(false);
    }
    @FXML
    void BatalPesan(ActionEvent event) {
        menuRoom.setDisable(false);
        menuRoom1.setDisable(false);
        menuRoom2.setDisable(false);
        menuRoom3.setDisable(false);
        DetailPesanan.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pilihan.setValue(1);
        pilihan.getItems().addAll(pilihanPelanggan);
        jeniskamar.setValue("VVIP");
        jeniskamar.getItems().addAll(jenisKamar);
        layananTambahan = new LayananTambahan();
        pilihan.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            layananTambahan.setHargaLayananTambahan(newValue * 100000);
        });
    }
    @FXML
    void Visa(ActionEvent event) {
        metodeBayar = "Visa";
        sceneMandiri.setVisible(false);
        scenePaypal.setVisible(false);
        sceneVisa.setVisible(true);
    }

    @FXML
    void bayar(ActionEvent event) throws SQLException, IOException {
        Pelanggan id = new Pelanggan();
        String id_pemesanan = idPemesanan.getText();
        Pembayaran pembayaran = new Pembayaran(layananTambahan);
        pembayaran.setMetodePembayaran(metodeBayar);
        id.memilihMetodePembayaran(pembayaran, id_pemesanan, pembayaran, pemesanan);

        double nilaiDesimal = Double.parseDouble(TotalPembayaran.getText());
        int totalBayar = (int) Math.round(nilaiDesimal);
        pembayaran.setTotalPembayaran(totalBayar);
        id.melakukanPembayaran(pembayaran, id_pemesanan);
        menungguKonfirmasi.setVisible(true);
        sceneMandiri.setVisible(false);
        scenePaypal.setVisible(false);
        sceneVisa.setVisible(false);

    }
    @FXML
    void Mandiri(ActionEvent event) {
        metodeBayar = "Mandiri";
        sceneMandiri.setVisible(true);
        scenePaypal.setVisible(false);
        sceneVisa.setVisible(false);
    }

    @FXML
    void Paypal(ActionEvent event) {
        metodeBayar = "Paypal";
        sceneMandiri.setVisible(false);
        scenePaypal.setVisible(true);
        sceneVisa.setVisible(false);
    }
    @FXML
    void BatalPembayaran(ActionEvent event) {
        pembayaranLayar.setVisible(false);
        DetailPesanan.setVisible(false);
        sceneMandiri.setVisible(true);
        scenePaypal.setVisible(false);
        sceneVisa.setVisible(false);
        menuRoom.setVisible(false);
        menuRoom1.setVisible(false);
        menuRoom2.setVisible(false);
        menuRoom3.setVisible(false);
        menuRoom.setDisable(false);
        menuRoom1.setDisable(false);
        menuRoom2.setDisable(false);
        menuRoom3.setDisable(false);
        menuHome2.setVisible(true);
        menuHome1.setVisible(true);
    }
    @FXML
    void MenungguKonfirmasiOK(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Pegawai");
        stage.setScene(scene);
        stage.show();
        menungguKonfirmasi.setVisible(false);
        pembayaranLayar.setVisible(false);
        menuRoom.setDisable(false);
        menuRoom1.setDisable(false);
        menuRoom2.setDisable(false);
        menuRoom3.setDisable(false);

    }
    @FXML
    void lihatFasilitasHarmoni(ActionEvent event) throws SQLException {
        Kamar kamar = new Kamar();
        String id = "KMR003";
        teksFasilitas.setText(kamar.getFasilitas(id));
        layarFasilitas.setVisible(true);
    }

    @FXML
    void lihatFasilitasKalianda(ActionEvent event) throws SQLException {
        Kamar kamar = new Kamar();
        String id = "KMR001";
        teksFasilitas.setText(kamar.getFasilitas(id));
        layarFasilitas.setVisible(true);
    }

    @FXML
    void lihatFasilitasRajabasa(ActionEvent event) throws SQLException {
        Kamar kamar = new Kamar();
        String id = "KMR002";
        teksFasilitas.setText(kamar.getFasilitas(id));
        layarFasilitas.setVisible(true);
    }

    @FXML
    void lihatFasilitasSurya(ActionEvent event) throws SQLException {
        Kamar kamar = new Kamar();
        String id = "KMR004";
        teksFasilitas.setText(kamar.getFasilitas(id));
        layarFasilitas.setVisible(true);
    }
    @FXML
    void tambahProdukKeranjang(ActionEvent event) {
        layarIsiKeranjang.setVisible(false);
    }
    @FXML
    void buttonKeranjang1(ActionEvent event) {
        layarIsiKeranjang.setVisible(true);
    }

    @FXML
    void buttonKeranjang2(ActionEvent event) {
        layarIsiKeranjang.setVisible(true);
    }

    @FXML
    void buttonKeranjang3(ActionEvent event) {
        layarIsiKeranjang.setVisible(true);
    }

    @FXML
    void buttonKeranjang4(ActionEvent event) {
        layarIsiKeranjang.setVisible(true);
    }

    @FXML
    void fasilitasKembali(ActionEvent event) {
        layarFasilitas.setVisible(false);
    }

    public void LihatSemuaFasilitas(MouseEvent mouseEvent) {
    }
}

