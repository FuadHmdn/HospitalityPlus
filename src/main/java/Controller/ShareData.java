package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShareData {
    private static ObservableList<String> pemesananList = FXCollections.observableArrayList();

    public static void addPemesanan(String idPemesanan, String email) {
        String combinedData = idPemesanan + " - " + email;
        pemesananList.add(combinedData);
    }

    public static ObservableList<String> getPemesananList() {
        return pemesananList;
    }
}
