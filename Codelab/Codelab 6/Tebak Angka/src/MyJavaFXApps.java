import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.stage.*;

import java.util.Random;

public class MyJavaFXApps extends Application {
    private int angkaAcak;
    private int jumlahPercobaan = 0;

    @Override
    public void start(Stage stage) {
        // Menghasilkan angka acak antara 1 dan 100
        Random random = new Random();
        angkaAcak = random.nextInt(100) + 1;

        // Komponen UI
        Label judulLabel = new Label("Tebak Angka 1-100");
        judulLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: blue;");

        TextField tebakanField = new TextField();
        tebakanField.setPromptText("Masukkan angka di sini");

        Button tombolTebak = new Button("Coba Tebak!");
        Label feedbackLabel = new Label();
        Label percobaanLabel = new Label("Jumlah percobaan: 0");

        Image imagekucing = new Image("file:src/kucing.png");
        ImageView imageView = new ImageView(imagekucing);
        imageView.setFitWidth(153);
        imageView.setFitHeight(102);

        tombolTebak.setOnAction(e -> {
            if (tombolTebak.getText().equals("Coba Tebak!")) {
                jumlahPercobaan++;
                try {
                    int tebakanUser = Integer.parseInt(tebakanField.getText());
                    if (tebakanUser < angkaAcak) {
                        feedbackLabel.setText("Terlalu kecil!");
                        feedbackLabel.setStyle("-fx-text-fill: orange;");
                    } else if (tebakanUser > angkaAcak) {
                        feedbackLabel.setText("Terlalu besar!");
                        feedbackLabel.setStyle("-fx-text-fill: red;");
                    } else {
                        feedbackLabel.setText("Tebakan benar!");
                        feedbackLabel.setStyle("-fx-text-fill: green;");
                        tombolTebak.setText("Main Lagi");
                        angkaAcak = random.nextInt(100) + 1;  // Menghasilkan angka baru
                    }
                    percobaanLabel.setText("Jumlah percobaan: " + jumlahPercobaan);
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Harap masukkan angka yang valid.");
                    feedbackLabel.setStyle("-fx-text-fill: red;");
                }
            } else {
                // Jika tombol mengatakan "Main Lagi", reset permainan
                jumlahPercobaan = 0;
                percobaanLabel.setText("Jumlah percobaan: 0");
                feedbackLabel.setText("");
                tombolTebak.setText("Coba Tebak!");
                tebakanField.clear();
            }
        });

        // Pengaturan Layout
        HBox TebakAngka = new HBox(imageView, judulLabel);
        TebakAngka.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(10, TebakAngka, tebakanField, tombolTebak, feedbackLabel, percobaanLabel);
        vbox.setAlignment(Pos.CENTER);


        vbox.setStyle("-fx-background-color: #e0f7fa; -fx-padding: 20;");
        tombolTebak.setStyle("-fx-background-color: #00FF00; -fx-padding: 15;");
        Scene scene = new Scene(vbox, 600, 400);
        stage.setTitle("Tebak Angka Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
