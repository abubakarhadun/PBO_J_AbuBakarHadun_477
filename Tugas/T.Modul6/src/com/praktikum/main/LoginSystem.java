package com.praktikum.main;

import com.praktikum.data.*;
import com.praktikum.users.*;
import java.util.*;

public class LoginSystem {
    
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Item> reportedItems = new ArrayList<>();
    public static void main(String[] args) {
        mulai();
    }



    public static void mulai(){
        Scanner input = new Scanner(System.in);

        // Initialize default users
        Admin admin = new Admin("admin477", "password477");
        Mahasiswa mahasiswa = new Mahasiswa("AbuBakarHadun", "202410370110477");
        userList.add(admin);
        userList.add(mahasiswa);


        System.out.println("--------------PILIH LOGIN--------------");
        System.out.println("1. Admin");
        System.out.println("2. Mahasiswa");
        System.out.println("---------------------------------------");
        System.out.print("Pilih: ");
        User user = null;
        int pilih = 0;
        try {
            pilih = input.nextInt();
            input.nextLine(); 
        } catch (InputMismatchException e) {
            System.out.println("Input harus berupa angka!");
            input.nextLine();
        }
        String username, password, nama, nim;
        switch (pilih) {
            case 1:
                System.out.println("\nMasuk ke Halaman Admin");
                System.out.print("Masukkan username: ");
                username = input.nextLine();
                System.out.print("Masukkan Password: ");
                password = input.nextLine();
                user = loginAdmin(username, password);
                if (user == null) {
                    System.out.println("Login gagal! ERROR");
                    System.out.println("Silahkan coba lagi");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    mulai();
                }
                break;
            case 2:
                System.out.println("\nMasuk ke Halaman Mahasiswa");
                System.out.print("Masukkan nama: ");
                nama = input.nextLine();
                System.out.print("Masukkan NIM: ");
                nim = input.nextLine();
                user = loginMahasiswa(nama, nim);
                if (user == null) {
                    System.out.println("Login gagal! ERROR");
                    System.out.println("Silahkan coba lagi");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    mulai();
                }
                break;
            case 0:
                System.out.println("Salah input");
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                break;
        }

        if (user != null) {
            user.displayInfo();
            System.out.println("---------------------------------------");
            user.displayAppMenu();
        }

        input.close();
    }
        
    public static User loginAdmin(String username, String password) {
        for (User u : userList) {
            if (u instanceof Admin) {
                Admin a = (Admin) u;
                if (a.username.equals(username) && a.password.equals(password)) {
                    return a;
                }
            }
        }
        return null;
    }
    public static User loginMahasiswa(String nama, String nim) {
        for (User u : userList) {
            if (u instanceof Mahasiswa) {
                Mahasiswa m = (Mahasiswa) u;
                if (m.getNama().equals(nama) && m.getNim().equals(nim)) {
                    return m;
                }
            }
        }
        return null;
    }
}
