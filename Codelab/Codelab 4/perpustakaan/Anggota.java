package perpustakaan;

public static class Anggota {
    public static String nama = "AbuBakarHadun";
    public static String idAnggota = "477";

    Peminjaman namabuku = new Peminjaman("Hainuwele", "selama 7 hari");
    Peminjaman namadantanggalbuku = new Peminjaman("Hainuwele");
    
    namabuku.pinjamBuku();
    namadantanggalbuku.pinjamBuku();
}