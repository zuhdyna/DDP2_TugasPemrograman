package assignments.assignment3;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;

import java.util.Scanner;

import static assignments.assignment1.NotaGenerator.isNumeric;
import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class MainMenu {
    private final Scanner in;
    private final LoginManager loginManager;

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu(new Scanner(System.in), new LoginManager(new EmployeeSystem(), new MemberSystem()));
        mainMenu.run();
    }

    public MainMenu(Scanner in, LoginManager loginManager) {
        this.in = in;
        this.loginManager = loginManager;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1 -> login();
                case 2 -> register();
                case 3 -> toNextDay();
                case 4 -> exit = true;
                default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }

        in.close();
    }

    private void toNextDay() {
        System.out.println("Kamu tidur hari ini... zzz...");
        NotaManager.toNextDay();
    }

    void register() {
        System.out.println("Masukan nama Anda: ");
        String nama = in.nextLine();
        System.out.println("Masukan nomor handphone Anda: ");
        String noHp = in.nextLine();
        System.out.println("Masukan password Anda: ");
        String password = in.nextLine();

        Member registeredMember = loginManager.register(nama, noHp, password);
        if(registeredMember == null){
            System.out.printf("User dengan nama %s dan nomor hp %s sudah ada!\n", nama, noHp);
            return;
        }
        System.out.printf("Berhasil membuat user dengan ID %s!\n", registeredMember.getId());
    }

    private void login() {
        System.out.print("Masukan ID Anda: ");
        String inputId = in.nextLine();
        System.out.print("Masukan password Anda: ");
        String inputPassword = in.nextLine();
        SystemCLI systemCLI = loginManager.getSystem(inputId);
        if(systemCLI == null){
            System.out.println("ID atau password invalid.");
            return;
        }
        systemCLI.login(in, inputId, inputPassword);
    }



    private void displayMenu() {
        System.out.println("Selamat datang di CuciCuci System!");
        System.out.printf("Sekarang tanggal %s\n", fmt.format(cal.getTime()));
        System.out.println("1. Login");
        System.out.println("2. Register Member");
        System.out.println("3. Tidur (Skip hari)");
        System.out.println("4. Exit");
        System.out.print("Apa yang ingin Anda lakukan hari ini? ");
    }

}
