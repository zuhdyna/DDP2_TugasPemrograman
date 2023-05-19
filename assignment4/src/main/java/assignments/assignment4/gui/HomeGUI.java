package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.toNextDay;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // constraint untuk setiap komponen yang akan ditambahkan ke mainPanel
        GridBagConstraints gbcons = new GridBagConstraints();
        gbcons.fill = GridBagConstraints.NONE;
        gbcons.weightx = 1;
        gbcons.weighty = 1;
        // membuat titleLabel
        titleLabel = new JLabel("Selamat datang di CuciCuci System!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        // membuat button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                handleToLogin();
            }
        });
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                handleToRegister();
            }
        });
        toNextDayButton = new JButton("Next Day");
        toNextDayButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNextDay();
            }
        });
        // membuat dateLabel
        dateLabel = new JLabel("Hari ini: " + NotaManager.fmt.format((cal).getTime()));
        // menambahkan komponen ke mainPanel
        gbcons.anchor = GridBagConstraints.CENTER;
        gbcons.gridx = 0;
        gbcons.gridy = 0;
        gbcons.gridwidth = 3;
        gbcons.gridheight = 1;
        mainPanel.add(titleLabel, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 1;
        gbcons.gridwidth = 1;
        gbcons.gridheight = 1;
        mainPanel.add(loginButton, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 2;
        gbcons.gridwidth = 1;
        gbcons.gridheight = 1;
        mainPanel.add(registerButton, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 3;
        gbcons.gridwidth = 1;
        gbcons.gridheight = 1;
        mainPanel.add(toNextDayButton, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 4;
        gbcons.gridwidth = 1;
        gbcons.gridheight = 1;
        gbcons.anchor = GridBagConstraints.SOUTH;
        mainPanel.add(dateLabel, gbcons);
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo(RegisterGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        // Next day notamanager
        NotaManager.toNextDay();
        // memberi pesan
        JOptionPane.showMessageDialog(this, "Kamu tidur hari ini zzz....");
        dateLabel.setText("Hari ini: " + NotaManager.fmt.format((cal).getTime()));
    }
    // method is numeric
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}
