class RekeningBank{
    String nomerRekening;
    String namaPemilik;
    public double Saldo;

    void tampilkanInfo(){
        System.out.println("nomer rekening: "+nomerRekening);
        System.out.println("Dengan atas nama Pemilik: "+namaPemilik);
        System.out.println("saldo: "+Saldo);
    }
    void setorUang(double jumlah){
        Saldo+=jumlah;
    }

    void tarikUang(double jumlah){
        Saldo-=jumlah;
    }


}
