package JDBC;

import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Kamar {
    private String jenis_kamar;
    private float harga;
    private boolean ketersediaan;
    private String fasilitas;
    private String rating;

    public String getFasilitas(String ID_Kamar) throws SQLException {
        Connection connection = ConnectorUtil.getConnection();
        String sql = "SELECT fasilitas FROM kamar WHERE ID_Kamar = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ID_Kamar);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    fasilitas = resultSet.getString("fasilitas");
                    return fasilitas;
                } else {
                    throw new SQLException("Data kamar dengan ID " + ID_Kamar + " tidak ditemukan");
                }
            }
        }
    }

    public double getHargaKamar(String idKamar) throws SQLException {
        Connection connection = ConnectorUtil.getConnection();
        String sql = "SELECT harga_kamar FROM kamar WHERE id_kamar = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, idKamar);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                harga = resultSet.getFloat("harga_kamar");
                connection.close();
            }
        } catch (SQLException exception) {
            Assertions.fail(exception);
        }

        return harga;
    }

    public String getJenis_kamar() {
        return jenis_kamar;
    }

    public void setJenis_kamar(String jenis_kamar) {
        this.jenis_kamar = jenis_kamar;
    }

    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }

    public boolean isKetersediaan() {
        return ketersediaan;
    }

    public void setKetersediaan(boolean ketersediaan) {
        this.ketersediaan = ketersediaan;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}


//package JDBC;
//
//import MODEL.Kamar;
//import org.junit.jupiter.api.Assertions;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class kamarDao {
//    public String getFasilitas(String ID_Kamar) throws  SQLException{
//        Connection connection = ConnectorUtil.getConnection();
//        String sql = "SELECT fasilitas FROM kamar WHERE ID_Kamar = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, ID_Kamar);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    String fasilitas = resultSet.getString("fasilitas");
//                    return fasilitas;
//                } else {
//                    throw new SQLException("Data kamar dengan ID " + ID_Kamar + " tidak ditemukan");
//                }
//            }
//        }
//    }
//
//
//    public double getHargaKamar(Kamar kamar, String idKamar) throws SQLException {
//        Connection connection = ConnectorUtil.getConnection();
//        String sql = "SELECT harga_kamar FROM kamar WHERE id_kamar = ?";
//        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setString(1, idKamar);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                kamar.setHarga(resultSet.getFloat("harga_kamar"));
//                connection.close();
//            }
//        }catch (SQLException exception){
//            Assertions.fail(exception);
//        }
//
//        return kamar.getHarga();
//    }
//}
