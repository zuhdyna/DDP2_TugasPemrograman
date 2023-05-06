package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import assignments.assignment1.NotaGenerator;

import java.util.ArrayList;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList = new ArrayList<Nota>();
    private static ArrayList<Member> memberList = new ArrayList<Member>();
    private static int notaCounter = 0;

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
        input.close();
    }

    private static void handleGenerateUser() {
        // TODO: handle generate user
        // input nama dan nomor hp
        System.out.println("Masukkan nama Anda: ");
        String nama = input.nextLine();
        System.out.println("Masukkan nomor handphone Anda: ");
        String nomorHP = input.nextLine();
        // handle jika nomor hp tidak berupa digit
        while (isNumeric(nomorHP) == false || nomorHP.equals("")){
            System.out.println("Field nomor hp hanya menerima digit.");
            nomorHP = input.nextLine();
        }
        // handle jika id yang akan dibuat ternyata sudah ada
        for (int i = 0; i < memberList.size(); i++){
            if (memberList.get(i).getId().equals(generateId(nama, nomorHP))){
                System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!\n", nama, nomorHP);
                return;
            }
        }
        // generate member baru
        memberList.add(new Member(nama, nomorHP));
        System.out.printf("Berhasil membuat member dengan ID %s!\n", memberList.get(memberList.size() - 1).getId());
    }

    private static void handleGenerateNota() {
        // TODO: handle ambil cucian
        // input id member
        System.out.println("Masukkan ID member: ");
        String idMember = input.nextLine();
        // handle jika id member tidak ada
        boolean isMemberExist = false;
        for (int i = 0; i < memberList.size(); i++){
            if (memberList.get(i).getId().equals(idMember)){
                isMemberExist = true;
                break;
            }
        }
        if (isMemberExist == false){
            System.out.printf("Member dengan ID %s tidak ditemukan!\n", idMember);
            return;
        }
        // cari member dengan id
        Member member = cariMember(idMember);
        // tambahkan bonusCounter
        member.setBonusCounter(member.getBonusCounter() + 1);
        // input paket laundry
        // Iterasi nama paket sampai sesuai dengan yang diinginkan
        String paketLaundry, paketLowered;
        boolean paketSudahBenar = false;
        do {
            System.out.println("Masukkan paket laundry: ");
            paketLaundry = input.nextLine();
            paketLowered = paketLaundry.toLowerCase();
            if (paketLowered.equals("express") || paketLowered.equals("fast") || paketLowered.equals("reguler")){
                paketSudahBenar = true;
            } else if (paketLaundry.equals("?")){
                showPaket();
            } else {
                System.out.println("Paket " + paketLaundry + " tidak diketahui");
                System.out.println("[Ketik ? untuk mencari tahu jenis paket]");
            }
        } while (paketSudahBenar == false);

        // input berat cucian
        System.out.println("Masukkan berat cucian Anda [Kg]: ");
        String beratCucianStr = input.nextLine();
        // handle jika berat cucian tidak berupa digit
        while (isNumeric(beratCucianStr) == false || beratCucianStr.equals("")){
            System.out.println("Berat cucian hanya menerima digit.");
            beratCucianStr = input.nextLine();
        }
        // ubah berat cucian dari string ke integer
        int beratCucian = Integer.parseInt(beratCucianStr);
        // jika kurang dari 2 kg
        if (beratCucian < 2) {
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            beratCucian = 2;
        }
        // masukkan ke list nota
        notaList.add(new Nota(member, paketLaundry, beratCucian, fmt.format(cal.getTime()), notaCounter));
        // generate nota
        String notaHasil = NotaGenerator.generateNotaVersi2(idMember, paketLaundry, beratCucian, fmt.format(cal.getTime()), member.getBonusCounter());
        // handle jika member sudah mendapatkan bonus maka akan reset menjadi 0
        if (member.getBonusCounter() == 3){
            member.setBonusCounter(0);
        }
        System.out.println("Berhasil menambahkan nota!");
        System.out.printf("[ID Nota = %d]\n", notaList.get(notaList.size() - 1).getIdNota());
        System.out.println(notaHasil);
        if (!notaList.get(notaList.size() - 1).getIsReady()){
            System.out.println("Status      	: Belum bisa diambil :(");
        }
        notaCounter += 1;
    }

    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem
        if (notaList.size() == 0){
            System.out.println("Terdaftar 0 nota dalam sistem.");
            return;
        }
        System.out.printf("Terdaftar %d nota dalam sistem.\n", notaList.size());
        for (int i = 0; i < notaList.size(); i++){
            if (notaList.get(i).getIsReady()){
                System.out.printf("- [%d] Status      	: Sudah dapat diambil!\n", notaList.get(i).getIdNota());
            } else {
                System.out.printf("- [%d] Status      	: Belum bisa diambil :(\n", notaList.get(i).getIdNota());
            }
        }
    }

    private static void handleListUser() {
        // TODO: handle list semua user pada sistem
        if (memberList.size() == 0){
            System.out.println("Terdaftar 0 member dalam sistem.");
            return;
        }
        System.out.printf("Terdaftar %d member dalam sistem.\n", memberList.size());
        for (int i = 0; i < memberList.size(); i++){
            System.out.printf("- %s : %s \n", memberList.get(i).getId(), memberList.get(i).getNama());
        }
    }

    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
        // input id nota
        System.out.println("Masukkan ID nota yang akan diambil:");
        String idNotaStr = input.nextLine();
        // handle jika id nota tidak berupa digit atau kurang dari nol
        while (isNumeric(idNotaStr) == false || idNotaStr.equals("") || (Integer.parseInt(idNotaStr) < 0)){
            System.out.println("ID nota berbentuk angka!");
            idNotaStr = input.nextLine();
        }
        // ubah id nota dari string ke integer
        int idNota = Integer.parseInt(idNotaStr);
        // mencari eksistensi nota dalam list nota
        for (int i = 0; i < notaList.size(); i++){
            if (notaList.get(i).getIdNota() == idNota){
                // handle jika nota belum siap diambil
                if (notaList.get(i).getIsReady() == false){
                    System.out.printf("Nota dengan ID %s gagal diambil!\n", idNotaStr);
                    return;
                }
                // handle jika nota bisa diambil
                System.out.printf("Nota dengan ID %s berhasil diambil!\n", idNotaStr);
                // menghilangkan nota
                notaList.remove(i);
                return;
            }
        }
        // handle jika nota tidak ditemukan
        System.out.printf("Nota dengan ID %d tidak ditemukan!\n", idNota);
    }

    private static void handleNextDay() {
        // TODO: handle ganti hari
        // tambahkan 1 hari pada object Calendar cal
        cal.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println("Dek Depe tidur hari ini... zzz...");
        // mengurangi 1 hari ke semua sisaPengerjaanNota
        for (int i = 0; i < notaList.size(); i++){
            if (notaList.get(i).getIsReady() == false){
                notaList.get(i).setSissaHariPengerjaan(notaList.get(i).getSissaHariPengerjaan() - 1);
                if (notaList.get(i).getSissaHariPengerjaan() == 0){
                    notaList.get(i).setReady(true);
                }
            }
        }
        // cek apakah nota sudah siap diambil
        for (int i = 0; i < notaList.size(); i++){
            if (notaList.get(i).getIsReady()){
                System.out.printf("Laundry dengan nota ID %d sudah dapat diambil!\n", notaList.get(i).getIdNota());
            }
        }
        System.out.println("Selamat pagi dunia!\nDek Depe: It's CuciCuci Time.");
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

    // method untuk mencari member berdasarkan id
    public static Member cariMember(String idMember){
        for (int i = 0; i < memberList.size(); i++){
            if (memberList.get(i).getId().equals(idMember)){
                return memberList.get(i);
            }
        }
        return null;
    }
    // method untuk show paket
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }
}
