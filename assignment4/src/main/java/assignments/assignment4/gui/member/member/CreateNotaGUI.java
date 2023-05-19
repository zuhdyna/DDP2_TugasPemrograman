package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.HomeGUI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;
    // bikin panel
    private JPanel mainPanel;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        // setup layout
        super(new BorderLayout());
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // setup panel
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set up main panel, Feel free to make any changes
        initGUI();

        // add ke mainPanel
        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
        // setup constraint
        GridBagConstraints gbcons = new GridBagConstraints();
        gbcons.fill = GridBagConstraints.NONE;
        gbcons.weightx = 1;
        gbcons.weighty = 0.1;
        // setup label
        paketLabel = new JLabel("Paket Laundry:");
        // setup combobox
        paketComboBox = new JComboBox<>(new String[]{"Express", "Fast", "Reguler"});
        // setup button
        showPaketButton = new JButton("Show Paket");
        showPaketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                showPaket();
            }
        });
        beratLabel = new JLabel("Berat Cucian (Kg):");
        beratTextField = new JTextField();
        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000/kg)");
        antarCheckBox = new JCheckBox("Tambah Antar Service(2000/4kg pertama, kemudian 500/kg)");
        createNotaButton = new JButton("Buat Nota");
        createNotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                createNota();
            }
        });
        backButton = new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                handleBack();
            }
        });
        // add ke main panel
        gbcons.gridx = 0;
        gbcons.gridy = 0;
        gbcons.anchor = GridBagConstraints.WEST;
        gbcons.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(paketLabel, gbcons);
        gbcons.gridx = 3;
        gbcons.gridy = 0;
        gbcons.insets = new Insets(0, 0, 0, 4);
        mainPanel.add(paketComboBox, gbcons);
        gbcons.gridx = 4;
        gbcons.gridy = 0;
        mainPanel.add(showPaketButton, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 1;
        mainPanel.add(beratLabel, gbcons);
        gbcons.gridx = 3;
        gbcons.gridy = 1;
        mainPanel.add(beratTextField, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 2;
        gbcons.anchor = GridBagConstraints.WEST;
        mainPanel.add(setrikaCheckBox, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 3;
        mainPanel.add(antarCheckBox, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 4;
        gbcons.gridwidth = 5;
        gbcons.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(createNotaButton, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 5;
        gbcons.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(backButton, gbcons);
    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        // TODO
        // cek input kosong
        if(beratTextField.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Input tidak boleh kosong");
            return;
        }
        // ambil input
        String paket = (String) paketComboBox.getSelectedItem();
        String beratStr = beratTextField.getText();
        boolean setrika = setrikaCheckBox.isSelected();
        boolean antar = antarCheckBox.isSelected();
        // cek input
        if (paket.equals("Express") || paket.equals("Fast") || paket.equals("Reguler")){
            if (isNumeric(beratStr)){
                // buat nota
                int berat = Integer.parseInt(beratStr);
                // jika berat kurang dari 2
                if (berat < 2){
                    berat = 2;
                    JOptionPane.showMessageDialog(this, "Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                }
                Nota nota = new Nota(memberSystemGUI.getLoggedInMember(), berat, paket, fmt.format((cal).getTime()));
                // tambahkan service
                if (setrika){
                    nota.addService(new SetrikaService());
                }
                if (antar){
                    nota.addService(new AntarService());
                }
                // tambahkan nota ke member dan notamanager
                NotaManager.addNota(nota);
                memberSystemGUI.getLoggedInMember().addNota(nota);
                // membuat pesan
                JOptionPane.showMessageDialog(this, "Berhasil membuat nota!");
                //flush input
                paketComboBox.setSelectedIndex(0);
                beratTextField.setText("");
                setrikaCheckBox.setSelected(false);
                antarCheckBox.setSelected(false);
            } else {
                JOptionPane.showMessageDialog(this, "Berat harus berupa angka", "Error", JOptionPane.ERROR_MESSAGE);
                beratTextField.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Semuanya harus diisi", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // TODO
        // flush input
        paketComboBox.setSelectedIndex(0);
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
        // kembali ke halaman login
        MainFrame.getInstance().navigateTo(memberSystemGUI.getPageName());;
    }
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}
