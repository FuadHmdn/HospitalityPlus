package JDBC;

import java.util.Random;

public class Pembayaran {
    private int totalPembayaran;
    private String metodePembayaran;
    private String idPembayaran;
    private LayananTambahan layananTambahan;

    public Pembayaran() {
        this.idPembayaran = generateUniqueID();
    }

    public Pembayaran(LayananTambahan layananTambahan) {
        this.layananTambahan = layananTambahan;
        this.idPembayaran = generateUniqueID();
    }

    private String generateUniqueID() {
        Random random = new Random();
        int randomValue = random.nextInt(900) + 100;
        return "BYR" + randomValue;
    }

    public String getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(String idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public int getTotalPembayaran() {
        return totalPembayaran + layananTambahan.getHargaLayananTambahan();
    }

    public void setTotalPembayaran(int totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }
}
