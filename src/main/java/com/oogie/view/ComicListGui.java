package com.oogie.view;

import com.oogie.controller.ComicServiceJPA;
import com.oogie.model.ComiclistEntity;
import com.oogie.model.CredentialsEntity;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.Scanner;

public class ComicListGui {
    private JTextField idTextField;
    private JTextField comicNameTextField;
    private JTextField issueTextField;
    private JTextField writerTextField;
    private JTextField artistTextField;
    private JTextField publisherTextField;
    private JTextField yearTextField;
    private JTextField genreTextField;
    private JButton createButton;
    private JButton retrieveButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextArea retrieveTextArea;
    private JLabel idLabel;
    private JLabel comicNameLabel;
    private JLabel issueNumberLabel;
    private JLabel writerLabel;
    private JLabel artistLabel;
    private JLabel publisherLabel;
    private JLabel yearLabel;
    private JLabel genreLabel;
    private JPanel panel;
    public JFrame frame;

    private ComicServiceJPA comicServiceJPA;
    private List<CredentialsEntity> credentials;
    private int id = 0;

    public ComicListGui(final MainApp mainApp, final EntityManager entityManager, final List<CredentialsEntity> credentials) {
        this.credentials = credentials;
        final ComicServiceJPA comicServiceJPA = new ComicServiceJPA(entityManager);
        frame = new JFrame("Enter Comic Info");
        frame.setSize(300, 400);
        panel = new JPanel();
        idTextField = new JTextField(20);
        comicNameTextField = new JTextField(20);
        issueTextField = new JTextField(20);
        writerTextField = new JTextField(20);
        artistTextField = new JTextField(20);
        publisherTextField = new JTextField(20);
        yearTextField = new JTextField(20);
        genreTextField = new JTextField(20);
        retrieveTextArea = new JTextArea(5, 25);
        retrieveTextArea.setEditable(false);
        frame.setContentPane(panel);

        if (credentials.get(0).getAffiliation() == 1) {
            panel.add(idLabel);
            panel.add(idTextField);
        }
        panel.add(comicNameLabel);
        panel.add(comicNameTextField);
        panel.add(issueNumberLabel);
        panel.add(issueTextField);
        panel.add(writerLabel);
        panel.add(writerTextField);
        panel.add(artistLabel);
        panel.add(artistTextField);
        panel.add(publisherLabel);
        panel.add(publisherTextField);
        panel.add(yearLabel);
        panel.add(yearTextField);
        panel.add(genreLabel);
        panel.add(genreTextField);
        panel.add(createButton);
        panel.add(retrieveButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(retrieveTextArea);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ComiclistEntity comiclistEntity = createComiclistEntity();
                    id = comicServiceJPA.create(comiclistEntity);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ComiclistEntity comiclistEntity = createComiclistEntity();
                    List<ComiclistEntity> comics = comicServiceJPA.retrieve(comiclistEntity);
                    StringBuilder sb = new StringBuilder();
                    for (ComiclistEntity c : comics) {
                        sb.append("ID: ").append(c.getId()).append(", Comic Name: ").append(c.getComicname()).append(", Writer: ").append(c.getWriter())
                                .append(", Artist: ").append(c.getArtist()).append(", Publisher: ").append(c.getPublisher()).append(", Release Year: ")
                                .append(c.getYear()).append(", Genre: ").append(c.getGenre()).append("\n");
                    }
                    retrieveTextArea.setText(sb.toString());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ComiclistEntity comiclistEntity = createComiclistEntity();
                    comicServiceJPA.update(comiclistEntity, id);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!idTextField.getText().isEmpty()) {
                        Scanner s = new Scanner(idTextField.getText());
                        boolean check = true;
                        if (!s.hasNextInt()) {
                            check = false;
                        }
                        if (check == true) {
                            id = Integer.parseInt(idTextField.getText());
                            comicServiceJPA.delete(id);
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                updateButton.setVisible(false);
                deleteButton.setVisible(false);
                CredentialsEntity credentialsEntity = credentials.get(0);
                if (credentialsEntity.getAffiliation() == 1) {
                    updateButton.setVisible(true);
                    deleteButton.setVisible(true);
                } else if (credentialsEntity.getAffiliation() == 2) {
                    updateButton.setVisible(true);
                } else {
                    createButton.setVisible(false);
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                mainApp.destroy();
            }

            @Override
            public void windowClosed(WindowEvent e) {
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

    private ComiclistEntity createComiclistEntity() {
        ComiclistEntity comiclistEntity = new ComiclistEntity();
        if (!comicNameTextField.getText().isEmpty()) {
            comiclistEntity.setComicname(comicNameTextField.getText());
        }
        if (!issueTextField.getText().isEmpty()) {
            Integer issueInt = Integer.parseInt(issueTextField.getText());
            comiclistEntity.setIssue(issueInt);
        }
        if (!writerTextField.getText().isEmpty()) {
            comiclistEntity.setWriter(writerTextField.getText());
        }
        if (!artistTextField.getText().isEmpty()) {
            comiclistEntity.setArtist(artistTextField.getText());
        }
        if (!publisherTextField.getText().isEmpty()) {
            comiclistEntity.setPublisher(publisherTextField.getText());
        }
        if (!yearTextField.getText().isEmpty()) {
            Integer yearInt = Integer.parseInt(yearTextField.getText());
            comiclistEntity.setYear(yearInt);
        }
        if (!genreTextField.getText().isEmpty()) {
            comiclistEntity.setGenre(genreTextField.getText());
        }
        return comiclistEntity;
    }
}
