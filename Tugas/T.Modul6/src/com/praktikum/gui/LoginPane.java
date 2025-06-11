package com.praktikum.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.praktikum.data.DataStore;
import com.praktikum.users.*;

public class LoginPane extends VBox {
    private ComboBox<String> roleCombo;
    private TextField namaField;
    private PasswordField passwordField;
    private Label messageLabel;
    private Stage primaryStage;

    public LoginPane(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setSpacing(20);
        setPadding(new Insets(20, 20, 20, 20));
        setAlignment(Pos.CENTER);

        Label title = new Label("Login Sistem Lost & Found");
        roleCombo = new ComboBox<>();
        roleCombo.getItems().addAll("Mahasiswa", "Admin");
        roleCombo.setValue("Mahasiswa");

        namaField = new TextField();
        namaField.setPromptText("Nisrina");
        passwordField = new PasswordField();
        passwordField.setPromptText("");

        Button loginBtn = new Button("Login");
        messageLabel = new Label();

        loginBtn.setOnAction(e -> handleLogin());


        Button click = new Button("CLICK ME");
        click.setOnAction(e-> {
            
        });
        getChildren().addAll(title, roleCombo, namaField, passwordField, loginBtn, messageLabel, click);
    }

    private void handleLogin() {
        String role = roleCombo.getValue();
        String nama = namaField.getText();
        String pass = passwordField.getText();
        if (role.equals("Admin")) {
            for (var u : DataStore.userList) {
                if (u instanceof Admin) {
                    Admin a = (Admin) u;
                    if (a.username.equals(nama) && a.password.equals(pass)) {
                        AdminDashboard dash = new AdminDashboard(primaryStage, a);
                        primaryStage.getScene().setRoot(dash);
                        return;
                    }
                }
            }
            messageLabel.setText("Login gagal, periksa kredensial.");
        } else {
            for (var u : DataStore.userList) {
                if (u instanceof Mahasiswa) {
                    Mahasiswa m = (Mahasiswa) u;
                    if (m.getNama().equals(nama) && m.getNim().equals(pass)) {
                        MahasiswaDashboard dash = new MahasiswaDashboard(primaryStage, m);
                        primaryStage.getScene().setRoot(dash);
                        return;
                    }
                }
            }
            messageLabel.setText("Login gagal, periksa kredensial.");
        }
    }
} 