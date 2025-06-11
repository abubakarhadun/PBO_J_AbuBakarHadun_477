import java.util.Scanner;

public class Codelab2{
    public static void main(String[] args){
        Scanner winput = new Scanner(System.in);
        RekeningBank[] rekening = new RekeningBank[2];
        
        rekening[0] = new RekeningBank();
        rekening[0].nomerRekening = "202410370110477";
        rekening[0].namaPemilik = "Abu Bakar Hadun";
        rekening[0].Saldo = 2000000;

        rekening[1] = new RekeningBank();
        rekening[1].nomerRekening = "202410370110452";
        rekening[1].namaPemilik = "Ilham antonika";
        rekening[1].Saldo = 10000;

        rekening[0].tampilkanInfo();
        System.err.println("\n\n");
        rekening[1].tampilkanInfo();

        System.out.println("\n\nrek 1 : "+rekening[0].namaPemilik+"\nrek 2 :" + rekening[1].namaPemilik+"\n\n----------------\npilih: ");
        int reknya = winput.nextInt();
        winput.nextLine();
        reknya -= 1;
 
        System.out.println("\n\n1. Setor tunai\n2. Tarik Tunai\n\n----------------\npilih: ");
        int pilihan = winput.nextInt();
        winput.nextLine();
        double Jumlah;
        switch(pilihan){
            case 1:
                System.out.println("Masukan jumlah: ");
                Jumlah = winput.nextDouble();
                winput.nextLine();
                rekening[reknya].setorUang(Jumlah);
                break;

            case 2:
                System.out.println("Masukan jumlah: ");
                Jumlah = winput.nextDouble();
                winput.nextLine();
                rekening[reknya].tarikUang(Jumlah);
                break;
        }
        
        winput.close();
        rekening[0].tampilkanInfo();
        System.err.println("\n\n");
        rekening[1].tampilkanInfo();

    }
}