package org.groupTwo.view;

import org.groupTwo.dao.userDAO;
import org.groupTwo.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateProfile extends JFrame{
    private JPanel updateProfilePanel;
    private JTextField phoneTF;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JRadioButton otherRadioButton;
    private JButton saveButton;
    private JLabel usernameLabel;
    private JPasswordField passwordTF;
    private JLabel cancelUpdateLabel;
    private JLabel registerDateLabel;
    private JLabel emailLabel;
    private ButtonGroup gendersButton;

    String selectedGender = null;
    private User user;
    public UpdateProfile(int userId) {
        setContentPane(updateProfilePanel);
        setVisible(true);
        setSize(600,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Update profile: BearBurger");
        gendersButton = new ButtonGroup();
        gendersButton.add(maleRadioButton);
        gendersButton.add(femaleRadioButton);
        gendersButton.add(otherRadioButton);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        userDAO userDAO = applicationContext.getBean("userDao", userDAO.class);

        user = userDAO.searchById(userId);

        usernameLabel.setText(user.getUsername());
        phoneTF.setText(user.getPhone());
        emailLabel.setText(user.getEmail());
        passwordTF.setText(user.getPass());
        registerDateLabel.setText(String.valueOf(user.getReg_date()));

        maleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedGender = maleRadioButton.getText();
                System.out.println("Selected button: "+selectedGender);
            }
        });

        femaleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedGender = femaleRadioButton.getText();
                System.out.println("Selected button: "+selectedGender);
            }
        });

        otherRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedGender = otherRadioButton.getText();
                System.out.println("Selected button: "+selectedGender);
            }
        });

        selectedGender = user.getGender();
        switch (user.getGender()){
            case "Male":
                maleRadioButton.setSelected(true);
                break;
            case "Female":
                femaleRadioButton.setSelected(true);
                break;
            case "Other":
                otherRadioButton.setSelected(true);
                break;
        }

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phone = phoneTF.getText();
                String pass = String.valueOf(passwordTF.getPassword());
                userDAO.updateUser(user.getUserId(),pass,phone,selectedGender);
                dispose();
                new HomePage(userId);
            }
        });
        cancelUpdateLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new HomePage(userId);
            }
        });
    }
}
