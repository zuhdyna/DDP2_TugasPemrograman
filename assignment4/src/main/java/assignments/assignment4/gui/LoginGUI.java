package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;
import assignments.assignment4.gui.member.employee.EmployeeSystemGUI;
import assignments.assignment4.gui.member.member.MemberSystemGUI;

import javax.swing.*;
import javax.swing.event.AncestorListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

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
        // TODO
        GridBagConstraints gbcons = new GridBagConstraints();
        gbcons.fill = GridBagConstraints.NONE;
        gbcons.weightx = 1;
        gbcons.weighty = 1;
        idLabel = new JLabel("Masukkan ID Anda:");
        idTextField = new JTextField();
        passwordLabel = new JLabel("Masukkan Password Anda:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                handleLogin();
            }
        });
        backButton = new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                handleBack();
            }
        });
        // membuat label dan textfield
        gbcons.gridx = 0;
        gbcons.gridy = 0;
        gbcons.gridwidth = 2;
        gbcons.anchor = GridBagConstraints.WEST;
        mainPanel.add(idLabel, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 1;
        gbcons.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(idTextField, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 2;
        gbcons.gridwidth = 2;
        gbcons.anchor = GridBagConstraints.WEST;
        mainPanel.add(passwordLabel, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 3;
        gbcons.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(passwordField, gbcons);
        // membuat button
        gbcons.fill = GridBagConstraints.NONE;
        gbcons.gridx = 0;
        gbcons.gridy = 4;
        gbcons.gridwidth = 1;
        gbcons.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 5;
        gbcons.gridwidth = 1;
        gbcons.anchor = GridBagConstraints.CENTER;
        mainPanel.add(backButton, gbcons);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // TODO
        // balikin jadi kosong semua
        idTextField.setText("");
        passwordField.setText("");
        MainFrame.getInstance().logout();
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        // TODO
        // mendapatkan password
        String password = String.valueOf(passwordField.getPassword());
        String id = idTextField.getText();
        // cek kekosongan field
        if (id.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "Semua field diatas wajib diisi!");
            return;
        }
        // kalo dah keisi, login lewat main frame. Jika return false, artinya ga jadi login
        if (!MainFrame.getInstance().login(id, password)){
            JOptionPane.showMessageDialog(this, "ID atau password salah!");
            return;
        }
        // flush field
        idTextField.setText("");
        passwordField.setText("");
    }
}
