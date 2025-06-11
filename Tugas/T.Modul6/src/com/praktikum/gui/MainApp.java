package com.praktikum.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.praktikum.data.DataStore;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;


public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        DataStore.userList.add(new Admin("admin477", "password477"));
        DataStore.userList.add(new Mahasiswa("AbuBakarHadun", "202410370110477"));

        LoginPane loginPane = new LoginPane(primaryStage);
        Scene scene = new Scene(loginPane, 400, 250);
        primaryStage.setTitle("Lost & Found Kampus");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
} 