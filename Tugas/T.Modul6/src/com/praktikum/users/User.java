package com.praktikum.users;

public abstract class User {
    private String nama;
    private String nim;

    public User(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    void setNama(String name) {
        nama = name;
    }

    public String getNama() {
        return nama;
    }

    void setNim(String Nim) {
        nim = Nim;
    }

    public String getNim() {
        return nim;
    }

    abstract void login();

    public void displayInfo() {
        System.out.println("Nama: " + nama + ", NIM: " + nim);
    }

    public abstract void displayAppMenu();
}
