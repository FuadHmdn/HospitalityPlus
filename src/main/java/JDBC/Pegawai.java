package JDBC;
import org.junit.jupiter.api.Assertions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pegawai{
    public void konfirmasiPemesanan(String id_pemesanan) {
        Connection connection = ConnectorUtil.getConnection();
        String query = "UPDATE pemesanan SET status = 'CONFIRMED' WHERE id_pemesanan = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id_pemesanan);
            int update = preparedStatement.executeUpdate();
            System.out.println(update + " rows updated");
        } catch (SQLException exception) {
            Assertions.fail(exception);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void mengelolaKetersediaan(String id_Kamar, String status) throws SQLException {
        Connection connection = ConnectorUtil.getConnection();
        String query = "UPDATE kamar SET status_ketersediaan = ? WHERE id_kamar = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, id_Kamar);
            int update = preparedStatement.executeUpdate();
            System.out.println(update + " rows updated");
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            connection.close();
        }
    }

}
