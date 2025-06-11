public class Main{
    public static void main(String[] a){    
        KarakterGame karakterUmum = new KarakterGame("Karakter Utama",  100);
        Pahlawan Brimstone = new Pahlawan("Brimstone", 150);
        Musuh Viper = new Musuh("Viper", 200);

        System.out.println("Status Awal: ");
        System.out.println("    Brimstone memiliki kesehatan: "+Brimstone.getKesehatan());
        System.out.println("    Viper memiliki kesehatan: "+Viper.getKesehatan());

        
        System.out.println("");
            karakterUmum.Serang(Viper);
        System.out.println("");


            Brimstone.serang(Viper);
            Viper.serang(Brimstone);
        
        System.out.println("");
        System.out.println("");
    }
}


class Pahlawan extends KarakterGame{
    Pahlawan(String nama, int Kesehatan){
        super(nama, Kesehatan);
    }

    public void serang(KarakterGame target){
        System.out.println(getNama()+" Menyerang "+target.getNama()+" menggunakan Orbital Strike!");
        target.setKesehatan(target.getKesehatan() - 20);
        System.out.println(target.getNama()+ " Sekarang memiliki Kesehatan: "+target.getKesehatan());   
    }
}

class Musuh extends KarakterGame{
    Musuh(String nama, int Kesehatan){
        super(nama, Kesehatan);
    }

    public void serang(KarakterGame target){
        System.out.println(getNama()+" Menyerang "+target.getNama()+" menggunakan Snake Bite!");
        target.setKesehatan(target.getKesehatan() - 15);
        System.out.println(target.getNama()+ " Sekarang memiliki Kesehatan: "+target.getKesehatan());   
    }
}


class KarakterGame{
    private String nama;
    private int Kesehatan;

    KarakterGame(String nama, int Kesehatan){
        this.nama = nama;
        this.Kesehatan = Kesehatan;
    }

    public void setNama(String name){
        nama = name;
    }

    public String getNama(){
        return nama;
    }

    public void setKesehatan(int kesehatan){
        Kesehatan = kesehatan;
    }

    public int getKesehatan(){
        return Kesehatan;
    }

    public void Serang(KarakterGame target){
        System.out.println(nama+" Menyerang "+ target.getNama()+"!!!,                      tapi nggk kena ");
    }    
}
