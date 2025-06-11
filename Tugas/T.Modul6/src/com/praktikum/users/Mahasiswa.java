package com.praktikum.users;
import com.praktikum.actions.MahasiswaAction;
import java.util.Scanner;
import com.praktikum.main.LoginSystem;
import com.praktikum.data.Item;
import java.util.InputMismatchException;

public class Mahasiswa extends User implements MahasiswaAction {
    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    Scanner input = new Scanner(System.in);

    @Override
    public void login() {
        System.out.println("Yang penting di override");
    }

    public void reportItem() {
        System.out.println("Report Item");
        System.out.print("    Nama Barang: ");
        String namaBarang = input.nextLine();
        System.out.print("    Deskripsi Barang: ");
        String deskripsiBarang = input.nextLine();
        System.out.print("    Lokasi terakhir: ");
        String lokasiTerakhir = input.nextLine();
        Item item = new Item(namaBarang, deskripsiBarang, lokasiTerakhir, "Reported");
        LoginSystem.reportedItems.add(item);
        System.out.println("Laporan berhasil dilaporkan!");
        System.out.println("Nama Barang: " + namaBarang);
        System.out.println("Deskripsi Barang: " + deskripsiBarang);
        System.out.println("Lokasi Terakhir: " + lokasiTerakhir);
        System.out.println("");
        System.out.println("klik enter untuk kembali");
        input.nextLine();
        displayAppMenu(); 
    }

    public void viewReportedItems() {
        if (LoginSystem.reportedItems.isEmpty()) {
            System.out.println("Belum ada laporan barang.");
        } else {
            int idx = 1;
            for (Item item : LoginSystem.reportedItems) {
                if ("Reported".equals(item.getStatus())) {
                    System.out.println(idx + ". " + item.getItemName() + " | " + item.getDescription() + " | " + item.getLocation());
                }
                idx++;
            }
        }
        System.out.println("");
        System.out.println("klik enter untuk kembali");
        input.nextLine();
        displayAppMenu();  
    }

    public void displayAppMenu() {
        System.out.println("--------MENU MAHASISWA--------");
        System.out.println("");
        System.out.println("1. Laporkan Barang Temuan/Hilang");
        System.out.println("2. Lihat Daftar Laporan");
        System.out.println("0. Logout");
        int pilih = -1;
        try {
            pilih = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Input harus berupa angka!");
            input.nextLine();
            displayAppMenu();
            return;
        }
        switch (pilih) {
            case 1:
                reportItem();
                break;
            case 2:
                viewReportedItems();
                break;
            case 0:
                System.out.println("Logout berhasil");
                System.out.println("");
                System.out.println("");
                System.out.println("");
                LoginSystem.mulai();
                break;
            default:
                System.out.println("Pilihan tidak valid! ..... klik enter untuk kembali");
                displayAppMenu();
                break;
        }
    }
}
