package assignments.assignment1;

import java.util.Scanner;

import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.lang.model.element.Element;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    // Method untuk membuat checksum
    public static int generateChecksum(String userIDraw){
        int modifiedAsciiChar;
        if (userIDraw.length() == 0){
            return 0;
        } else {
            char currentChar = userIDraw.charAt(0);
            if (Character.isLetter(currentChar)){
                modifiedAsciiChar = (int)currentChar - 'A' + 1;
            } else if (Character.isDigit(currentChar)){
                modifiedAsciiChar = Integer.parseInt("" + currentChar);
            } else {
                modifiedAsciiChar = 7;
            }
            return modifiedAsciiChar + generateChecksum(userIDraw.substring(1));
        }
    }
    // Method untuk mengecek apakah suatu string merupakan bilangan
    public static boolean isIntegerChecker(String angka){
        // Iterasi untuk mengakses setiap karakter String
        for (int i = 0; i < angka.length(); i++){
            char currentChar = angka.charAt(i);
            if (Character.isDigit(currentChar) == false){
                return false;
            }
        }
        return true;
    }
    // Method untuk mengambil suku nama pertama dan dikembalikan dalam bentuk uppercase
    public static String firstNameUpperPicker(String nama){
        String firstName;
        // Jika tidak ditemukan berarti namanya hanya terdiri dari satu suku nama saja
        if (nama.indexOf(" ") == -1){
            firstName = nama;
        } else {
            String[] partsNama = nama.split(" ");
            firstName = partsNama[0];
        }
        return firstName.toUpperCase();
    }
    // Method untuk mengubah tanggalTerima menjadi tanggal selesai
    public static String tanggalGenerator(String tanggalTerima, int lamaProses){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tanggalLokal = LocalDate.parse(tanggalTerima, formatter);
        // menambahkan tanggal sebanyak lamaProses yang diberikan
        tanggalLokal = tanggalLokal.plusDays(lamaProses);
        String tanggalBaru = tanggalLokal.format(formatter).toString();
        return tanggalBaru;
    }
    // 
    public static void main(String[] args) {
        // TODO: Implement interface menu utama
        Scanner sc = new Scanner(System.in);
        String pilihan;
        // melakukan iterasi untuk menu utama sampai user memasukkan nilai pilihan = 0
        do {
            String nama, nomorHP;
            printMenu();
            System.out.print("Pilihan : ");
            pilihan = sc.nextLine();
            System.out.println("================================");
            // jika pilihan = 1, Generate ID
            // jika pilihan = 2, Generate nota
            // jika pilihan = 0, maka akan continue dan kondisi while akan false
            // Selain ketiga hal tersebut, akan meminta input kembali
            if (pilihan.equals("1")){
                // input nama
                System.out.println("Masukkan nama Anda: ");
                nama = sc.nextLine();

                // Memasukkan nomor HP sampai nomor HP merupakan bilangan
                System.out.println("Masukkan nomor handphone Anda: ");
                nomorHP = sc.nextLine();
                while (isIntegerChecker(nomorHP) == false || nomorHP.equals("")){
                    System.out.println("Nomor HP hanya menerima digit");
                    nomorHP = sc.nextLine();
                }

                // Memanggil method generate id
                String id = generateId(nama, nomorHP);
                System.out.println("ID Anda : " + id);
            } else if (pilihan.equals("2")){
                // Input nama
                System.out.println("Masukkan nama Anda: ");
                nama = sc.nextLine();

                // memasukkan nomor HP sampai nomor HP merupakan bilangan
                System.out.println("Masukkan nomor handphone Anda: ");
                nomorHP = sc.nextLine();
                while (isIntegerChecker(nomorHP) == false || nomorHP.equals("")){
                    System.out.println("Nomor hp hanya menerima digit");
                    nomorHP = sc.nextLine();
                }

                // input tanggal terima
                System.out.println("Masukkan tanggal terimaL ");
                String tanggalTerima = sc.nextLine();
                boolean paketSudahBenar = false;
                String paket;
                // Iterasi nama paket sampai sesuai dengan yang diinginkan
                do {
                    System.out.println("Masukkan paket laundry: ");
                    paket = sc.nextLine();
                    if (paket.equals("express") || paket.equals("fast") || paket.equals("reguler")){
                        paketSudahBenar = true;
                    } else if (paket.equals("?")){
                        showPaket();
                    } else {
                        System.out.println("Paket " + paket + " tidak diketahui");
                        System.out.println("[Ketik ? untuk mencari tahu jenis paket]");
                    }
                } while (paketSudahBenar == false);

                // Input berat cucian
                System.out.println("Masukkan berat cucian Anda [Kg]: ");
                String beratString = sc.nextLine();
                // Mengecek string berat cucian
                while (isIntegerChecker(beratString) == false || Integer.parseInt(beratString) <= 0){
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif");
                    beratString = sc.nextLine();
                }
                // Jika berat sudah sesuai dengan apa yang diminta maka kit abisa mengubahnya menjadi integer
                int berat = Integer.parseInt(beratString);

                // Generate id
                String id = generateId(nama, nomorHP);

                // Memanggil dan mencetak hasil nota
                System.out.println(generateNota(id, paket, berat, tanggalTerima));
            } else if (pilihan.equals("0")){
                continue;
            } else {
                System.out.println("Perintah tidak diketahui, silahkan periksa kembali.");
            }
        } while (pilihan.equals("0") == false);
    }

    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
        // TODO: Implement generate ID sesuai soal.
        String id;
        String idRaw = firstNameUpperPicker(nama) + "-" + nomorHP;
        int checksum = generateChecksum(idRaw);
        if (checksum < 10){
            id = idRaw + "-" + "0" + checksum;
        } else {
            id = idRaw + "-" + checksum%100;
        }
        return id;
    }

    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        // TODO: Implement generate nota sesuai soal.
        // variable untuk nota akhir
        String notaString;
        // variable untuk menghitung harga dan tanggal
        int hargaPerKilo = 0, lamaProses = 0;
        // Jika kurang dari 2 kg, akan dianggap 2 kg
        if (berat < 2){
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat = 2;
        }
        // Assign nilai untuk jenis paket yang sesuai
        if (paket.equals("express")){
            hargaPerKilo = 12000;
            lamaProses = 1;
        } else if (paket.equals("fast")){
            hargaPerKilo = 10000;
            lamaProses = 2;
        } else if (paket.equals("reguler")){
            hargaPerKilo = 7000;
            lamaProses = 3;
        }
        // memasukkan string akhir dari nota yang diinginkan
        notaString = "ID    : " + id + "\n" +
        "Paket : " + paket + "\n" +
        "Harga :\n" +
        berat + " kg x "+ hargaPerKilo +" = "+ berat*hargaPerKilo +"\n" +
        "Tanggal Terima  : " + tanggalTerima + "\n" +
        "Tanggal Selesai : " + tanggalGenerator(tanggalTerima, lamaProses);
        return notaString;
    }
}