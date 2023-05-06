package assignments.assignment3.user;

public class Employee extends Member {
    public static int employeeCount;
    public Employee(String nama, String password) {
        super(nama, generateId(nama), password);
    }

    /**
     * Membuat ID employee dari nama employee dengan format
     * NAMA_DEPAN-[jumlah employee, mulai dari 0]
     * Contoh: Dek Depe adalah employee pertama yang dibuat, sehingga IDnya adalah DEK-0
     *
     * @param nama -> Nama lengkap dari employee
     */
    private static String generateId(String nama) {
        // TODO
        // mengambil nama depan dari nama lengkap
        String[] namaSplitted = nama.split(" ");
        String namaDepan = namaSplitted[0];
        // mengambil jumlah employee
        int jumlahEmployee = employeeCount;
        // membuat id
        String id = namaDepan.toUpperCase() + "-" + jumlahEmployee;
        // menambah jumlah employee
        employeeCount++;
        return id;
    }
}
