package com.praktikum.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.praktikum.data.DataStore;
import com.praktikum.data.Item;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.Admin;

public class AdminDashboard extends VBox {
    private Stage primaryStage;
    private Admin admin;
    private TableView<Item> itemTable;
    private TableView<Mahasiswa> mahasiswaTable;
    private TextField namaField, nimField;

    public AdminDashboard(Stage primaryStage, Admin admin) {
        this.primaryStage = primaryStage;
        this.admin = admin;
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_LEFT);

        Label welcome = new Label("Halo, Administrator " + admin.username);
        Label laporanLabel = new Label("Laporan Barang");

        // Table laporan barang
        itemTable = new TableView<>();
        TableColumn<Item, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getItemName()));
        TableColumn<Item, String> lokasiCol = new TableColumn<>("Lokasi");
        lokasiCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLocation()));
        TableColumn<Item, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
        itemTable.getColumns().addAll(namaCol, lokasiCol, statusCol);
        itemTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        refreshItemTable();

        Button claimedBtn = new Button("Tandai Claimed");
        claimedBtn.setOnAction(e -> handleClaimed());

        // Table data mahasiswa
        mahasiswaTable = new TableView<>();
        TableColumn<Mahasiswa, String> namaMhsCol = new TableColumn<>("Nama");
        namaMhsCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNama()));
        TableColumn<Mahasiswa, String> nimCol = new TableColumn<>("NIM");
        nimCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNim()));
        mahasiswaTable.getColumns().addAll(namaMhsCol, nimCol);
        mahasiswaTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        refreshMahasiswaTable();

        HBox addMhsBox = new HBox(5);
        namaField = new TextField();
        namaField.setPromptText("Nama Mahasiswa");
        nimField = new TextField();
        nimField.setPromptText("NIM");
        Button tambahBtn = new Button("Tambah");
        tambahBtn.setOnAction(e -> handleTambahMahasiswa());
        addMhsBox.getChildren().addAll(namaField, nimField, tambahBtn);

        Button hapusBtn = new Button("Hapus");
        hapusBtn.setOnAction(e -> handleHapusMahasiswa());
        addMhsBox.getChildren().add(hapusBtn);

        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> {
            LoginPane loginPane = new LoginPane(primaryStage);
            primaryStage.getScene().setRoot(loginPane);
        });

        HBox tablesBox = new HBox(20, new VBox(laporanLabel, itemTable, claimedBtn), new VBox(new Label("Data Mahasiswa"), mahasiswaTable, addMhsBox));
        getChildren().addAll(welcome, tablesBox, logoutBtn);
    }

    private void handleClaimed() {
        Item selected = itemTable.getSelectionModel().getSelectedItem();
        if (selected != null && selected.getStatus().equals("Reported")) {
            selected.setStatus("Claimed");
            refreshItemTable();
        }
    }

    private void handleTambahMahasiswa() {
        String nama = namaField.getText();
        String nim = nimField.getText();
        if (!nama.isEmpty() && !nim.isEmpty()) {
            DataStore.userList.add(new Mahasiswa(nama, nim));
            refreshMahasiswaTable();
            namaField.clear();
            nimField.clear();
        }
    }

    private void handleHapusMahasiswa() {
        Mahasiswa selected = mahasiswaTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            DataStore.userList.removeIf(u -> (u instanceof Mahasiswa) && ((Mahasiswa) u).getNim().equals(selected.getNim()));
            refreshMahasiswaTable();
        }
    }

    private void refreshItemTable() {
        itemTable.getItems().clear();
        for (Item item : DataStore.reportedItems) {
            itemTable.getItems().add(item);
        }
    }

    private void refreshMahasiswaTable() {
        mahasiswaTable.getItems().clear();
        for (var u : DataStore.userList) {
            if (u instanceof Mahasiswa) {
                mahasiswaTable.getItems().add((Mahasiswa) u);
            }
        }
    }
} 