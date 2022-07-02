package org.groupTwo.view;

import org.groupTwo.domain.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame{
    private JPanel homePagePanel;
    private JLabel congratLabel;
    private JButton logoutButton;

    private User loggedInUser;

    private void logout(){
        dispose();
        System.out.println("Logging out");
        new Login();
    }
    public HomePage(User user) {
        setContentPane(homePagePanel);
        setVisible(true);
        setSize(600,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Homepage: BearBurger");
        this.loggedInUser = user;
        congratLabel.setText("Congratulations @"+user.getUsername()+", You are logged in!");
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
    }
}
