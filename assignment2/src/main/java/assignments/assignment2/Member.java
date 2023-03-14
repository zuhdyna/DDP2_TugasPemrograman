package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String nama, noHp, id; 
    private int bonusCounter;
    public Member(String nama, String noHp) {
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.noHp = noHp;
        this.id = NotaGenerator.generateId(nama, noHp);
        this.bonusCounter = 0;
    }
    // TODO: tambahkan methods yang diperlukan untuk class ini

    // method-method setter
    public void setBonusCounter(int bonusCounter) {
        this.bonusCounter = bonusCounter;
    }
    //method-method getter
    public String getNama() {
        return nama;
    }
    public String getNoHp() {
        return noHp;
    }
    public String getId() {
        return id;
    }
    public int getBonusCounter() {
        return bonusCounter;
    }
}
