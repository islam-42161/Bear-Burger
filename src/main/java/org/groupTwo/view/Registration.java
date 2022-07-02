package org.groupTwo.view;

import org.groupTwo.dao.userDAO;
import org.groupTwo.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class Registration extends JFrame {
    private JPanel regPanel;
    private JTextField usernameTF;
    private JTextField emailTF;
    private JPasswordField passwordTF;
    private JPasswordField confirmPasswordTF;
    private JButton signupButton;
    private JLabel gotoLogin;
    private JTextField phoneTF;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JRadioButton otherRadioButton;
    private ButtonGroup genders;
    String selectedGender = null;

    public Registration(){
        setContentPane(regPanel);
        setVisible(true);
        setSize(600,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login: BearBurger");
        genders = new ButtonGroup();
        genders.add(maleRadioButton);
        genders.add(femaleRadioButton);
        genders.add(otherRadioButton);



        gotoLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Login();
            }
        });
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        userDAO userDAO = applicationContext.getBean("userDao", userDAO.class);

        for (User user:
                userDAO.getAllUsers() ) {
            System.out.println(user.getUsername()+", "+user.getEmail());
        }


        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTF.getText().trim();
                String email = emailTF.getText().trim();
                String phone = phoneTF.getText().trim();
                String password = String.valueOf(passwordTF.getPassword());
                String confirmPassoword = String.valueOf(confirmPasswordTF.getPassword());
                if(!username.isEmpty() && !email.isEmpty() && phone.length()>=11 && password.equals(confirmPassoword)) {

                    if(userDAO.insertRow(username,email,password,phone,selectedGender)){
                    System.out.println("New user registered successfully!");
                    dispose();
                    new Login();
                    }
                }
            }
        });

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

    }
}
