package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
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
        nameLabel = new JLabel("Masukkan nama Anda:");
        nameTextField = new JTextField();
        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");
        phoneTextField = new JTextField();
        passwordLabel = new JLabel("Masukkan Password Anda:");
        passwordField = new JPasswordField();
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                handleRegister();
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
        mainPanel.add(nameLabel, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 1;
        gbcons.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(nameTextField, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 2;
        gbcons.gridwidth = 2;
        gbcons.anchor = GridBagConstraints.WEST;
        mainPanel.add(phoneLabel, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 3;
        gbcons.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(phoneTextField, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 4;
        gbcons.gridwidth = 2;
        gbcons.anchor = GridBagConstraints.WEST;
        mainPanel.add(passwordLabel, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 5;
        gbcons.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(passwordField, gbcons);
        // membuat button
        gbcons.fill = GridBagConstraints.NONE;
        gbcons.gridx = 0;
        gbcons.gridy = 6;
        gbcons.gridwidth = 1;
        gbcons.anchor = GridBagConstraints.CENTER;
        mainPanel.add(registerButton, gbcons);
        gbcons.gridx = 0;
        gbcons.gridy = 7;
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
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
        MainFrame.getInstance().logout();
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // TODO
        String enteredPassword = String.valueOf(passwordField.getPassword());
        // Jika salah satu field masih kosong, munculkan pesan
        if (nameTextField.getText().equals("") || phoneTextField.getText().equals("") || enteredPassword.equals("")) {
            JOptionPane.showMessageDialog(this, "Semua field diatas wajib diisi!");
            return;
        }
        // cek nomor hape
        if (isNumeric(phoneTextField.getText()) == false) {
            JOptionPane.showMessageDialog(this, "Nomor handphone harus berupa angka!");
            return;
        }
        // add member, jika belum ada ID terkait
        Member registeredMember = loginManager.register(nameTextField.getText(), phoneTextField.getText(), enteredPassword);
        if (registeredMember == null) {
            JOptionPane.showMessageDialog(this, "User dengan nama "+ nameTextField.getText() +" dan nomor hp "+ phoneTextField.getText() +" sudah ada!");
        } else {
            JOptionPane.showMessageDialog(this, "Berhasil membuat user dengan ID " + registeredMember.getId() + "!");
            MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        }
        //flush input
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
    }
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}
