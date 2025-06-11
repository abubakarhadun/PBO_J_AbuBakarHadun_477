public class Codelab1 {
    public static void main(String[] args) {
        Hewan[] daftarHewan = new Hewan[2];

        daftarHewan[0] = new Hewan("Kucing", "Mamalia", "Nyann~~");
        daftarHewan[1] = new Hewan("Anjing", "Mamalia", "Woof-Woof!!");

        for (int i = 0; i < daftarHewan.length; i++) {
            daftarHewan[i].tampilkanInfo();
            System.out.println();
        }
    }
}