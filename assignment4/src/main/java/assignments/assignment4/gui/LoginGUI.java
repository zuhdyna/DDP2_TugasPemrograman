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
    // checkbox show password
    private JCheckBox showPassword;

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
        // constraint untuk setiap komponen yang akan ditambahkan ke mainPanel
        GridBagConstraints gbcons = new GridBagConstraints();
        gbcons.fill = GridBagConstraints.NONE;
        gbcons.weightx = 1;
        gbcons.weighty = 1;
        // membuat label dan textfield
        idLabel = new JLabel("Masukkan ID Anda:");
        idTextField = new JTextField();
        passwordLabel = new JLabel("Masukkan Password Anda:");
        passwordField = new JPasswordField();
        // membuat button-button
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
        // membuat checkbox show password
        showPassword = new JCheckBox("Show Password");
        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (showPassword.isSelected()){
                    passwordField.setEchoChar((char)0);
                } else {
                    passwordField.setEchoChar('•');
                }
            }
        });
        // meletakkan label dan textfield
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
        // membuat panel baru untuk passwordField dan showPassword
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.add(passwordField, BorderLayout.CENTER);
        passwordPanel.add(showPassword, BorderLayout.EAST);
        mainPanel.add(passwordPanel, gbcons);
        // meletakkan button
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
        // show password di set ke false
        showPassword.setSelected(false);
        passwordField.setEchoChar('•');
        // balik ke home
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
        // kalo dah keisi, login lewat main frame. Jika return false, artinya salah
        if (!MainFrame.getInstance().login(id, password)){
            JOptionPane.showMessageDialog(this, "ID atau password salah!");
            return;
        }
        // flush field
        idTextField.setText("");
        passwordField.setText("");
        // show password di set ke false
        showPassword.setSelected(false);
        passwordField.setEchoChar('•');
    }
}
