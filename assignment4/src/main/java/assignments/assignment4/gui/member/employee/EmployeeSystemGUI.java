package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // TODO
        return new JButton[]{
            // button pertama untuk menampilkan cucicuci
            new JButton("It's nyuci time"),
            // button kedua untuk menampilkan display
            new JButton("Display List Nota")
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {
        // TODO
        // jika tidak ada nota yang perlu dicuci
        if (NotaManager.notaList.length == 0){
            JOptionPane.showMessageDialog(this, "Belum ada nota", "Display Nota Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // mengumpulkan semua status nota yang ada pada sistem
        String notaStr = "";
        for (Nota nota : NotaManager.notaList){
            notaStr += (nota.getNotaStatus() + "\n");
        }
        // menampilkan semua status nota yang ada pada sistem
        JTextArea notaText = new JTextArea(notaStr,10,20);
        JScrollPane scrollPane = new JScrollPane(notaText);
        notaText.setLineWrap(true);
        notaText.setWrapStyleWord(true);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JOptionPane.showMessageDialog(this, scrollPane);
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        // TODO
        // pesan beginning
        JOptionPane.showMessageDialog(this, "Stand back! " + loggedInMember.getNama() + " beginning to nyuci!");
        // jika tidak ada nota yang perlu dicuci
        if (NotaManager.notaList.length == 0){
            JOptionPane.showMessageDialog(this, "Tidak ada nota yang perlu dicuci", "Cuci Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // mengumpulkan semua status nota yang ada pada sistem
        String notaStatus = "";
        for (Nota nota: NotaManager.notaList){
            notaStatus += (nota.kerjakan()+"\n");
        }
        JTextArea notaText = new JTextArea(10,20);
        JScrollPane scrollPane = new JScrollPane(notaText);
        notaText.setText(notaStatus);
        notaText.setLineWrap(true);
        notaText.setWrapStyleWord(true);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JOptionPane.showMessageDialog(this, scrollPane);
    }
}
