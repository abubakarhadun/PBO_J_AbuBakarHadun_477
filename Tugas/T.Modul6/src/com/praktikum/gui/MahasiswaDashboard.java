package com.praktikum.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.praktikum.data.DataStore;
import com.praktikum.data.Item;
import com.praktikum.users.Mahasiswa;

public class MahasiswaDashboard extends VBox {
    private Mahasiswa mahasiswa;
    private Stage primaryStage;
    private TableView<Item> table;
    private TextField namaBarangField, deskripsiField, lokasiField;

    public MahasiswaDashboard(Stage primaryStage, Mahasiswa mahasiswa) {
        this.primaryStage = primaryStage;
        this.mahasiswa = mahasiswa;
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_LEFT);

        Label welcome = new Label("Selamat datang, " + mahasiswa.getNama());
        Label laporLabel = new Label("Laporkan Barang Hilang/Temuan");

        HBox inputBox = new HBox(5);
        namaBarangField = new TextField();
        namaBarangField.setPromptText("Nama Barang");
        deskripsiField = new TextField();
        deskripsiField.setPromptText("Deskripsi");
        lokasiField = new TextField();
        lokasiField.setPromptText("Lokasi");
        Button laporBtn = new Button("Laporkan");
        inputBox.getChildren().addAll(namaBarangField, deskripsiField, lokasiField, laporBtn);

        table = new TableView<>();
        TableColumn<Item, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getItemName()));
        TableColumn<Item, String> lokasiCol = new TableColumn<>("Lokasi");
        lokasiCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLocation()));
        table.getColumns().addAll(namaCol, lokasiCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        refreshTable();

        laporBtn.setOnAction(e -> handleLapor());

        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> {
            LoginPane loginPane = new LoginPane(primaryStage);
            primaryStage.getScene().setRoot(loginPane);
        });

        getChildren().addAll(welcome, laporLabel, inputBox, new Label("Daftar Laporan Anda"), table, logoutBtn);
    }

    private void handleLapor() {
        String nama = namaBarangField.getText();
        String desk = deskripsiField.getText();
        String lokasi = lokasiField.getText();
        if (!nama.isEmpty() && !lokasi.isEmpty()) {
            Item item = new Item(nama, desk, lokasi, "Reported");
            DataStore.reportedItems.add(item);
            refreshTable();
            namaBarangField.clear();
            deskripsiField.clear();
            lokasiField.clear();
        }
    }

    private void refreshTable() {
        table.getItems().clear();
        for (Item item : DataStore.reportedItems) {
            table.getItems().add(item);
        }
    }
} 