package JDBC;
import org.junit.jupiter.api.Assertions;
import java.sql.*;

public class Pelanggan {
    private String nama;
    private String email;
    private String noTelpon;
    private String alamat;
    private String password;

    public Pelanggan(String nama, String email, String alamat, String notelpon, String password) {
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.noTelpon = notelpon;
        this.password = password;
    }

    public Pelanggan() {
    }

    public String getNoTelpon() {
        return noTelpon;
    }

    public void setNoTelpon(String noTelpon) {
        this.noTelpon = noTelpon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void pemesananKamar(Pemesanan pemesanan, String email, String jenis_kamar) {
        Connection connection = ConnectorUtil.getConnection();
        String query = "INSERT INTO pemesanan (id_pemesanan, email, jenis_kamar, tamu, tgl_masuk, tgl_keluar, status) VALUES (?, ?, ?, ?, ?, ?, 'PENDING')";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pemesanan.getId_pemesanan());
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, jenis_kamar);
            preparedStatement.setInt(4, pemesanan.getJumlah_tamu());
            preparedStatement.setObject(5, pemesanan.getTanggal_menginap());
            preparedStatement.setObject(6, pemesanan.getTanggal_pulang());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addData(Pelanggan data) throws SQLException{
        Connection connection = ConnectorUtil.getConnection();
        String sql = "INSERT INTO pelanggan(nama, email, alamat, no_telepon, password) VALUES (?, ?, ?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, data.getNama());
            preparedStatement.setString(2, data.getEmail());
            preparedStatement.setString(3, data.getAlamat());
            preparedStatement.setString(4, data.getNoTelpon());
            preparedStatement.setString(5, data.getPassword());

            int update = preparedStatement.executeUpdate();
            System.out.println(update);

        } finally {
            connection.close();
        }
    }

    public void pemesananLayananTambahan(int layananTambahan, String id_pemesanan, String id_kamar) throws SQLException {
        Connection connection = ConnectorUtil.getConnection();
        String sql = "UPDATE pemesanan SET layanan_tambahan = ? , id_kamar = ? WHERE id_pemesanan = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, layananTambahan);
            preparedStatement.setString(2, id_kamar);
            preparedStatement.setString(3, id_pemesanan);
            int update = preparedStatement.executeUpdate();
            System.out.println(update);

        } finally {
            connection.close();
        }
    }

    public void memilihMetodePembayaran(Pembayaran id_pembayaran, String id_pemesanan, Pembayaran pembayaran, Pemesanan pemesanan) throws SQLException {
        Connection connection = ConnectorUtil.getConnection();
        String sql = "INSERT INTO pembayaran(id_pembayaran, id_pemesanan, metode_pembayaran, tanggal_pembayaran) VALUES (?, ?, ?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, pembayaran.getIdPembayaran());
            preparedStatement.setString(2, id_pemesanan);
            preparedStatement.setString(3, pembayaran.getMetodePembayaran());
            preparedStatement.setObject(4, pemesanan.getTanggal_menginap());
            int update = preparedStatement.executeUpdate();
            System.out.println(update);
        } finally {
            connection.close();
        }
    }
    public void melakukanPembayaran ( Pembayaran pembayaran, String id_pemesanan){
        Connection connection = ConnectorUtil.getConnection();
        String sql = "UPDATE pembayaran SET total_pembayaran = ? WHERE id_pemesanan = ? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, pembayaran.getTotalPembayaran());
            preparedStatement.setString(2, id_pemesanan);
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
}
