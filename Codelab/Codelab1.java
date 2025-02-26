import java.util.Scanner;
import java.time.LocalDate;

public class Codelab1{
    public static Scanner penginput = new Scanner(System.in);
    public static void main (String[] args){
        System.out.println("Masukkan Nama: ");
        String nama = penginput.nextLine();
        System.out.println("Masukkan Jenis kelamin(P/L): ");
        String kelamin = penginput.nextLine();
        System.out.println("Masukkan tahun lahir: ");
        int tahunlahir = penginput.nextInt();


        System.out.println("Data Diri:");
        System.out.println("Nama\t: "+nama);
        if(kelamin.equalsIgnoreCase("p")){
        System.out.println("Jenis kelamin: Perempuan");
        }
        if(kelamin.equalsIgnoreCase("l")){
            System.out.println("jenis kelamin: Laki - Laki");
        }
        int tahunsekarang = LocalDate.now().getYear();
        System.out.println("Umur\t: "+ (tahunsekarang - tahunlahir));
    }
}