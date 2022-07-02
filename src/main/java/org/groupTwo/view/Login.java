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

public class Login extends JFrame{
    private JPanel loginPanel;
    private JTextField usernameTF;
    private JPasswordField passwordTF;
    private JButton loginButton;
    private JLabel gotoRegLabel;
    private JCheckBox seePasswordCheckBox;

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public void setLoginPanel(JPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

    public Login(){
        setContentPane(loginPanel);
        setVisible(true);
        setSize(600,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login: BearBurger");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        userDAO userDAO = applicationContext.getBean("userDao", userDAO.class);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTF.getText().trim();
                String password = String.valueOf(passwordTF.getPassword());
                if(!username.isEmpty() && !password.isEmpty()){
                User checkUser = userDAO.searchByUsername(username);
                if(checkUser!=null){
                    if (username.equals(checkUser.getUsername()) && password.equals(checkUser.getPass())){
                        System.out.println("Login successful");
                        dispose();
                        new HomePage(checkUser);
                    }
                    else{
                        System.out.println("Username/password credential error");
                    }
                }
                else{
                    System.out.println("Could not find user");
                }
                }
            }
        });
        seePasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordTF.setEchoChar(seePasswordCheckBox.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
            }
        });
        gotoRegLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Registration();
            }
        });
    }
}
