package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

// import Arrays
import java.util.Arrays;

import static assignments.assignment3.nota.NotaManager.notaList;

public class EmployeeSystem extends SystemCLI {

    /**
     * Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci
     */
    public EmployeeSystem() {
        memberList = new Member[]{
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };
    }

    /**
     * Memproses pilihan dari employee yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        // TODO:
        // membuat switch case untuk memproses pilihan
        switch (choice){
            // it's nyuci time
            case 1:
                System.out.println("Stand back! "+ loginMember.getNama() +" beginning to nyuci!");
                // mencetak semua nota yang ada
                for (int i = 0; i < notaList.length; i++) {
                    System.out.println(notaList[i].kerjakan());
                }
                break;
            // Display list nota
            case 2:
                // mencetak semua status nota
                for (int i = 0; i < notaList.length; i++) {
                    System.out.println(notaList[i].getNotaStatus());
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
     * Displays specific menu untuk Employee.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }
}