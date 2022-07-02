package org.groupTwo.view;

import org.groupTwo.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import org.groupTwo.dao.userDAO;

public class SampleDataView extends JFrame{
    private JPanel sampleDataPanel;
    private JTextField nameTF;
    private JTextField emailTF;
    private JButton insertButton;
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
    userDAO userDAO = applicationContext.getBean("userDao", userDAO.class);

    public SampleDataView(){
        setContentPane(sampleDataPanel);
        setVisible(true);
        setSize(600,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sample dataview: Data input frame");
        insertButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                 String name = nameTF.getText().trim();
                 String email = emailTF.getText().trim();
                 if(!nameTF.getText().isEmpty() && !emailTF.getText().isEmpty()) {
                     //userDAO.insertRow(name,email);
                     System.out.println("Insertion complete");
                     nameTF.setText("");
                     emailTF.setText("");
                 }
            }
        });

    }
}
