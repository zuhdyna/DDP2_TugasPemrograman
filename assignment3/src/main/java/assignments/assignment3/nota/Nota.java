package assignments.assignment3.nota;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
// import tanggal
import java.util.Calendar;

public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services;
    private long baseHarga;
    private int sisaHariPengerjaan;
    private  int berat;
    private int id;
    private String tanggalMasuk;
    private boolean isDone;
    static public int totalNota;

    public Nota(Member member, int berat, String paket, String tanggal) {
        //TODO
        // assign semua info
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;
        this.id = totalNota;
        totalNota++;
        // assign baseHarga, sisaHariPengerjaan, dan isDone sesuai paket
        if (paket.toLowerCase().equals("express")){
            this.baseHarga = 12000*berat;
            this.sisaHariPengerjaan = 1;
            this.isDone = false;
        } else if (paket.toLowerCase().equals("fast")){
            this.baseHarga = 10000*berat;
            this.sisaHariPengerjaan = 2;
            this.isDone = false;
        } else if (paket.toLowerCase().equals("reguler")){
            this.baseHarga = 7000*berat;
            this.sisaHariPengerjaan = 3;
            this.isDone = false;
        }
        // assign services
        this.services = new LaundryService[0];
    }

    public void addService(LaundryService service){
        //TODO
        // membuat array baru dengan panjang array lama + 1
        LaundryService[] newServiceList = new LaundryService[services.length + 1];
        // menyalin semua isi array lama ke array baru
        for(int i = 0; i < services.length; i++){
            newServiceList[i] = services[i];
        }
        // menambahkan service baru ke array baru
        newServiceList[services.length] = service;
        // mengganti array lama dengan array baru
        services = newServiceList;
    }

    public String kerjakan(){
        // TODO
        // iterasi setiap service
        if (!isDone){
            for (int i = 0; i < services.length; i++) {
                // jika service belum selesai, kerjakan
                if(!services[i].isDone()){
                    // jika service terakhir, isDone = true
                    if( i == services.length-1){
                        isDone = true;
                    }
                    return "Nota "+ this.id + " : " +services[i].doWork();
                }
            }
        } else {
            return "Nota "+ this.id + " : " + "Sudah selesai.";
        }
        return "";
    }
    public void toNextDay() {
        // TODO
        // jika belum selesai atau sisa hari pengerjaan > 0, kurangi sisa hari pengerjaan
        if (!isDone == true || sisaHariPengerjaan > 0){
            this.sisaHariPengerjaan--;
        }
    }

    public long calculateHarga(){
        // TODO
        // iterasi setiap service
        long totalHarga = 0;
        for (int i = 0; i < services.length; i++) {
            // tambahkan harga setiap service ke totalHarga
            totalHarga += services[i].getHarga(berat);
        }
        // tambahkan baseHarga ke totalHarga
        totalHarga += this.baseHarga;
        // menghitung denda
        if (this.sisaHariPengerjaan < 0){
            totalHarga += 2000*(this.sisaHariPengerjaan);
        }
        return totalHarga;
    }

    public String getNotaStatus(){
        // TODO
        // jika isDone == true, return "Sudah selesai"
        // jika isDone == false, return "Belum selesai"
        if (isDone){
            return "Nota "+ this.id + " : " + "Sudah selesai.";
        } else {
            return "Nota "+ this.id + " : " + "Belum selesai.";
        }
    }

    @Override
    public String toString(){
        // TODO
        // string semua data nota
        String outputNota = String.format("[ID Nota = %d]\n", this.id);
        outputNota += NotaGenerator.generateNota(this.member.getId(), this.getPaket(), this.berat, this.tanggalMasuk);
        // tambahkan services
        outputNota += "\n--- SERVICE LIST ---";
        for (int i = 0; i < services.length; i++) {
            outputNota += "\n-" + services[i].getServiceName() + " @ Rp." + services[i].getHarga(berat);
        }
        // tambahkan total harga
        outputNota += "\nHarga Akhir: " + calculateHarga();
        // jika terkena kompensasi, tambahkan pesan
        if (this.sisaHariPengerjaan < 0){
            outputNota += " Ada kompensasi keterlambatan "+ (-1*this.sisaHariPengerjaan) + " * 2000 hari";
        }
        return outputNota;
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    }
}
