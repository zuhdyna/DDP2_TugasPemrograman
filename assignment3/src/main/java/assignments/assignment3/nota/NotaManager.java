package assignments.assignment3.nota;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    static public Nota[] notaList = new Nota[0];

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay(){
        //TODO: implement skip hari
        // iterasi semua nota dan kurangi 1 hari pada sisa hari pengerjaan
        for (int i = 0; i < notaList.length; i++) {
            notaList[i].toNextDay();
        }
    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota){
        //TODO: implement add nota
        // membuat array baru dengan panjang array lama + 1
        Nota[] newNotaList = new Nota[notaList.length + 1];
        // menyalin semua isi array lama ke array baru
        for(int i = 0; i < notaList.length; i++){
            newNotaList[i] = notaList[i];
        }
        // menambahkan nota baru ke array baru
        newNotaList[notaList.length] = nota;
        // mengganti array lama dengan array baru
        notaList = newNotaList;
    }
}
