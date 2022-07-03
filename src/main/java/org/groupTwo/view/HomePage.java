package org.groupTwo.view;

import org.groupTwo.dao.userDAO;
import org.groupTwo.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame{
    private JPanel homePagePanel;
    private JLabel congratLabel;
    private JButton logoutButton;
    private JButton updateProfileButton;

    private User loggedInUser;
    private void logout(){
        dispose();
        System.out.println("Logging out");
        new Login();
    }
    public HomePage(int userId) {
        setContentPane(homePagePanel);
        setVisible(true);
        setSize(600,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Homepage: BearBurger");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        userDAO userDAO = applicationContext.getBean("userDao", userDAO.class);
        loggedInUser = userDAO.searchById(userId);
        congratLabel.setText("Congratulations @"+loggedInUser.getUsername()+", You are logged in!");
        if(loggedInUser == null){
            logout();
        }
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loggedInUser = null;
                logout();
            }
        });
        updateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UpdateProfile(userId);
            }
        });
    }
}
