package com.oogie.view;

import com.oogie.controller.CredentialsServiceJPA;
import com.oogie.model.CredentialsEntity;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class CredentialsGui {
    private JTextField usernameTextField;
    private JPasswordField passwordPField;
    private JButton confirmButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPanel panel;
    public JFrame frame;
    private boolean left = false;

    private final MainApp mainApp;
    private EntityManager entityManager;
    private CredentialsServiceJPA credentialsServiceJPA;
    private List<CredentialsEntity> credentials;

    public CredentialsGui(final MainApp mainApp, final EntityManager entityManager) {
        this.mainApp = mainApp;
        this.entityManager = entityManager;
        credentialsServiceJPA = new CredentialsServiceJPA(entityManager);
        frame = new JFrame("Enter Your Credentials");
        frame.setSize(250, 180);
        panel = new JPanel();
        usernameTextField = new JTextField(20);
        passwordPField = new JPasswordField(20);
        frame.setContentPane(panel);
        panel.add(usernameLabel);
        panel.add(usernameTextField);
        panel.add(passwordLabel);
        panel.add(passwordPField);
        panel.add(confirmButton);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!usernameTextField.getText().isEmpty() && passwordPField.getPassword().length > 0) {
                    if (usernameTextField.getText().length() <= 6 && passwordPField.getPassword().length <= 6) {
                        CredentialsEntity credentialsEntity = createCredentialsEntity();
                        credentials = credentialsServiceJPA.retrieve(credentialsEntity);
                        if (!credentials.isEmpty()) {
                            ComicListGui comicListGui = new ComicListGui(mainApp, entityManager, credentials);
                            comicListGui.frame.setVisible(true);
                            comicListGui.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            frame.dispose();
                            left = false;
                        }
                    }
                }
            }
        });
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (left == true) {
                    mainApp.destroy();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    private CredentialsEntity createCredentialsEntity() {
        CredentialsEntity credentialsEntity = new CredentialsEntity();
        credentialsEntity.setUsername(usernameTextField.getText());
        char[] passwordStr = passwordPField.getPassword();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < passwordStr.length; i++) {
            sb.append(passwordStr[i]);
        }
        credentialsEntity.setPassword(sb.toString());
        return credentialsEntity;
    }
}
