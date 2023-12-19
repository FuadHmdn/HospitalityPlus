package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Pemesanan {
    private String id_pemesanan;
    private LocalDateTime tanggal_menginap;
    private LocalDateTime tanggal_pulang;
    private int jumlah_tamu;
    private String produk;

    public Pemesanan(int jumlah_tamu, LocalDateTime tanggal_menginap, LocalDateTime tanggal_pulang) {
        this.id_pemesanan = generateUniqueID();
        this.tanggal_menginap = tanggal_menginap;
        this.jumlah_tamu = jumlah_tamu;
        this.tanggal_pulang = tanggal_pulang;
    }

    public Pemesanan() {
        this.id_pemesanan = generateUniqueID();
    }

    private String generateUniqueID() {
        Random random = new Random();
        int randomValue = random.nextInt(900) + 100;
        return "IDP" + randomValue;
    }

    public Pemesanan(String produk) {
        this.produk = produk;
    }

    public String getId_pemesanan() {
        return id_pemesanan;
    }

    public void setId_pemesanan(String id_pemesanan) {
        this.id_pemesanan = id_pemesanan;
    }

    public LocalDateTime getTanggal_menginap() {
        return tanggal_menginap;
    }

    public void setTanggal_menginap(LocalDateTime tanggal_menginap) {
        this.tanggal_menginap = tanggal_menginap;
    }

    public int getJumlah_tamu() {
        return jumlah_tamu;
    }

    public String getTamu() {
        StringBuilder jumlahTamu = new StringBuilder();
        jumlahTamu.append(getJumlah_tamu());
        return jumlahTamu.toString();
    }

    public void setJumlah_tamu(int jumlah_tamu) {
        this.jumlah_tamu = jumlah_tamu;
    }

    public LocalDateTime getTanggal_pulang() {
        return tanggal_pulang;
    }

    public void setTanggal_pulang(LocalDateTime tanggal_pulang) {
        this.tanggal_pulang = tanggal_pulang;
    }

    public String konfirmasiPemesanan(Pemesanan pemesanan, String email) {
        Connection connection = ConnectorUtil.getConnection();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String tanggalMenginapString = null;
        if (pemesanan.getTanggal_menginap() != null) {
            tanggalMenginapString = pemesanan.getTanggal_menginap().format(formatter);
            System.out.println(tanggalMenginapString);
        } else {
            System.out.println("TGLKOSONG");
        }

        String query = "SELECT id_pemesanan FROM pemesanan WHERE email = ? AND tgl_masuk = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, tanggalMenginapString);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                pemesanan.setId_pemesanan(resultSet.getString("id_pemesanan"));
                String idPemesanan = resultSet.getString("id_pemesanan");
                connection.close();
                return idPemesanan;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}