package com.praktikum.users;
import com.praktikum.actions.AdminActions;
import com.praktikum.main.LoginSystem;
import com.praktikum.data.Item;
import java.util.InputMismatchException;

import java.util.Scanner;

public class Admin extends User implements AdminActions {
    public String username;
    public String password;

     public Admin(String username, String password) {
        super(username, password);
        this.username = username;
        this.password = password;
    }

    public Scanner input = new Scanner(System.in);

    public void manageItems() {
        Scanner input = this.input;
        while (true) {
            System.out.println("--------KELOLA BARANG--------");
            System.out.println("1. Lihat Semua Laporan");
            System.out.println("2. Tandai Barang Telah Diambil (Claimed)");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            int pilih = -1;
            try {
                pilih = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                input.nextLine();
                continue;
            }
            if (pilih == 0) break;
            switch (pilih) {
                case 1:
                    // Lihat semua laporan
                    if (LoginSystem.reportedItems.isEmpty()) {
                        System.out.println("Belum ada laporan barang.");
                    } else {
                        int idx = 1;
                        for (Item item : LoginSystem.reportedItems) {
                            System.out.println(idx + ". " + item.getItemName() + " | " + item.getDescription() + " | " + item.getLocation() + " | Status: " + item.getStatus());
                            idx++;
                        }
                    }
                    break;
                case 2:
                    // Tandai barang telah diambil
                    if (LoginSystem.reportedItems.isEmpty()) {
                        System.out.println("Belum ada laporan barang.");
                        break;
                    }
                    System.out.println("Daftar barang yang statusnya 'Reported':");
                    int idx = 1;
                    for (Item item : LoginSystem.reportedItems) {
                        if ("Reported".equals(item.getStatus())) {
                            System.out.println(idx + ". " + item.getItemName() + " | " + item.getDescription() + " | " + item.getLocation());
                        }
                        idx++;
                    }
                    System.out.print("Masukkan nomor indeks barang yang ingin ditandai: ");
                    int index = 0;
                    try {
                        index = input.nextInt();
                        input.nextLine();
                        index = index - 1;
                        Item item = LoginSystem.reportedItems.get(index);
                        if ("Reported".equals(item.getStatus())) {
                            item.setStatus("Claimed");
                            System.out.println("Barang berhasil ditandai sebagai 'Claimed'.");
                        } else {
                            System.out.println("Barang sudah berstatus 'Claimed'.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Input harus berupa angka!");
                        input.nextLine();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Indeks tidak valid!");
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        displayAppMenu();
    }

    public void manageUsers() {
        Scanner input = this.input;
        while (true) {
            System.out.println("--------KELOLA MAHASISWA--------");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            int pilih = -1;
            try {
                pilih = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                input.nextLine();
                continue;
            }
            if (pilih == 0) break;
            switch (pilih) {
                case 1:
                    // Tambah Mahasiswa
                    System.out.print("Masukkan Nama Mahasiswa: ");
                    String nama = input.nextLine();
                    System.out.print("Masukkan NIM Mahasiswa: ");
                    String nim = input.nextLine();
                    LoginSystem.userList.add(new Mahasiswa(nama, nim));
                    System.out.println("Mahasiswa berhasil ditambahkan.");
                    break;
                case 2:
                    // Hapus Mahasiswa
                    System.out.print("Masukkan NIM Mahasiswa yang akan dihapus: ");
                    String nimHapus = input.nextLine();
                    boolean found = false;
                    for (int i = 0; i < LoginSystem.userList.size(); i++) {
                        User u = LoginSystem.userList.get(i);
                        if (u instanceof Mahasiswa && ((Mahasiswa) u).getNim().equals(nimHapus)) {
                            LoginSystem.userList.remove(i);
                            System.out.println("Mahasiswa berhasil dihapus.");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Mahasiswa dengan NIM tersebut tidak ditemukan.");
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        displayAppMenu();
    }

    public void displayAppMenu(){
        System.out.println("--------MENU--------");
        System.out.println("");
        System.out.println("1. kelola laporan Barang");
        System.out.println("2. kelola data Mahasiswa");
        System.out.println("0. Logout");

        int pilih =  input.nextInt();
        input.nextLine();
        switch (pilih){
            case 1:
                manageItems();
                break;
            case 2:
                manageUsers();
                break;
            case 0:
                System.out.println("Logout berhasil");
                System.out.println("");
                System.out.println("");
                System.out.println("");
                LoginSystem.mulai();
                break;
        }
    }

    @Override
    public void login() {
        System.out.println("Yang penting di override");
    }
}
