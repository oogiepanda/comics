package com.oogie.view;

import com.oogie.model.CredentialsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    private static EntityManager entityManager;
    private static EntityManagerFactory emfactory;
    List<CredentialsEntity> credentials = new ArrayList<>();

    public static void main(String[] args)
    {
        MainApp mainApp = new MainApp();
        mainApp.run();
    }

    public void run() {
        final MainApp mainApp = this;
        final JFrame frame = new JFrame("User or Guest");
        FlowLayout flowLayout = new FlowLayout();
        frame.setLayout(flowLayout);
        frame.setSize(300,100);
        JButton userButton = new JButton("User");
        JButton guestButton = new JButton("Guest");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(userButton);
        frame.add(guestButton);
        frame.setVisible(true);
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                config();
                CredentialsGui credentialsGui = new CredentialsGui(mainApp, entityManager);
                credentialsGui.frame.setVisible(true);
                credentialsGui.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.dispose();
            }
        });
        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                config();
                CredentialsEntity credentialsEntity = new CredentialsEntity();
                credentialsEntity.setAffiliation(0);
                credentials.add(credentialsEntity);
                ComicListGui comicListGui = new ComicListGui(mainApp,entityManager,credentials);
                comicListGui.frame.setVisible(true);
                comicListGui.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.dispose();
            }
        });
    }

    public void config() {
        emfactory = Persistence.createEntityManagerFactory("comics");
        entityManager = emfactory.createEntityManager();
    }

    public void destroy() {
        entityManager.close();
        emfactory.close();
    }
}
