package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    // atribut class Nota
    private String paket, tanggalMasuk;
    private int idNota, berat, sissaHariPengerjaan;
    private Member member;
    private boolean isReady;
    // constructor class Nota
    public Nota(Member member, String paket, int berat, String tanggalMasuk, int notaCounter) {
        // TODO: buat constructor untuk class ini
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.idNota = notaCounter;
        // handle lama pengerjaan
        String paketLowered = paket.toLowerCase();
        if (paketLowered.equals("reguler")){
            this.sissaHariPengerjaan = 3;
        } else if (paketLowered.equals("fast")){
            this.sissaHariPengerjaan = 2;
        } else if (paketLowered.equals("express")){
            this.sissaHariPengerjaan = 1;
        }
        this.isReady = false;
        // increment notaCounter
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    // method-method setter
    public void setSissaHariPengerjaan(int sissaHariPengerjaan) {
        this.sissaHariPengerjaan = sissaHariPengerjaan;
    }
    public void setReady(boolean ready) {
        isReady = ready;
    }
    // method-method getter
    public String getPaket() {
        return paket;
    }
    public String getTanggalMasuk() {
        return tanggalMasuk;
    }
    public int getIdNota() {
        return idNota;
    }
    public int getBerat() {
        return berat;
    }
    public int getSissaHariPengerjaan() {
        return sissaHariPengerjaan;
    }
    public Member getMember() {
        return member;
    }
    public boolean getIsReady() {
        return isReady;
    }
}
