package assignments.assignment3.user.menu;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
// import nota
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.user.Member;

public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        // TODO
        // membuat switch case untuk memproses pilihan
        switch (choice){
            // saya ingin laundry
            case 1:
                // menampilkan menu paket dan meminta input paket (Menggunakan solusi TP1)
                String paket = "";
                while (true) {
                    System.out.println("Masukkan paket laundry:");
                    paket = in.nextLine();
                    if (paket.equals("?")) {
                        NotaGenerator.showPaket();
                        continue;
                    }
                    // Jika harga paket kurang dari 0, maka paket tidak ada di daftar
                    if (NotaGenerator.getHargaPaket(paket.toLowerCase()) < 0) {
                        System.out.printf("Paket %s tidak diketahui\n", paket);
                        System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                    } else {
                        break;
                    }
                }

                // input berat (menggunakan solusi TP1)
                System.out.println("Masukkan berat cucian anda [Kg]: ");
                String beratString = in.nextLine();
                // cek apakah berat sebuah angka dengan isNumeric dan berat lebih dari 0
                while(!NotaGenerator.isNumeric(beratString) || Integer.parseInt(beratString) < 1){
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    beratString = in.nextLine();
                }
                int berat = Integer.parseInt(beratString);
                // kondisi jika berat kurang dari 2
                if(berat < 2){
                    System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                    berat = 2;
                }

                // apakah setrika
                System.out.print("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?\n Hanya tambah 1000 / kg :0\n[Ketik x untuk tidak mau]: ");
                String setrika = in.nextLine();
                // apakah diantar
                System.out.print("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!\n Cuma 2000 / 4kg, kemudian 500 / kg\n[Ketik x untuk tidak mau]: ");
                String antar = in.nextLine();
                // membuat tanggal
                String tanggalMasuk = NotaManager.fmt.format(NotaManager.cal.getTime());
                // membuat nota baru
                Nota notaMember = new Nota(loginMember, berat, paket, tanggalMasuk);
                // tambah services
                if(!setrika.equals("x")){
                    notaMember.addService(new SetrikaService());
                }
                if(!antar.equals("x")){
                    notaMember.addService(new AntarService());;
                }
                // menambahkan nota ke notaManager
                NotaManager.addNota(notaMember);
                // menambahkan nota ke member
                loginMember.addNota(notaMember);
                System.out.println("Nota berhasil dibuat!");
                break;

            // lihat detail nota saya
            case 2:
                // iterasi semua nota
                for (int i = 0; i < loginMember.getNotaList().length; i++) {
                    // mencetak detail nota
                    System.out.println(loginMember.getNotaList()[i]);
                    if(i < loginMember.getNotaList().length - 1){
                        System.out.println();
                    }
                }
                break;
            // logout
            case 3:
                // logout
                logout = true;
                break;
            default:
                // jika pilihan tidak ada, tampilkan pesan error
                System.out.println("Pilihan tidak tersedia");
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        // TODO
        // membuat array baru dengan ukuran array lama + 1
        Member[] memberCopy = new Member[memberList.length + 1];
        // menyalin semua isi array lama ke array baru
        for (int i = 0; i < memberList.length; i++) {
            memberCopy[i] = memberList[i];
        }
        // menambahkan member baru ke array baru
        memberCopy[memberList.length] = member;
        // mengganti array lama dengan array baru
        memberList = memberCopy;
    }
}