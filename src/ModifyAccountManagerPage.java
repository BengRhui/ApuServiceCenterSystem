import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Objects;

public class ModifyAccountManagerPage implements ComponentListener, MouseListener {

    static ModifyAccountManagerPage currentPage;
    static JFrame frame;
    JLabel backgroundPicture, backArrow, logoutButton, accountText, title, checkInputText;
    JPanel backgroundPanel;
    JScrollPane displayManagerPanel, displayTechnicianPanel, displayCustomerPanel;
    JLayeredPane confirmButton, cancelButton, saveButton, searchButton;
    JComboBox<String> selectAccount;
    InformationPaneComponent infoPane1;
    JTextField checkPrompt;

    public ModifyAccountManagerPage(Manager manager) {

        frame = new JFrame("Modify Account Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(Asset.getFrameWidth(), Asset.getFrameHeight());
        frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());
        frame.addComponentListener(this);

        infoPane1 = new InformationPaneComponent();

        backgroundPicture = new Asset().generateImage("background.jpg");

        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        backArrow = new Asset().generateImage("backArrow_icon.png");

        logoutButton = new Asset().generateImage("logout_icon.png");

        title = new JLabel("Modify Account");
        title.setFont(Asset.getTitleFont());

        accountText = new JLabel("Choose type of account:");
        accountText.setFont(Asset.getNameFont("Plain"));
        accountText.setSize(350, 50);

        String[] accountChoice = User.getTypesOfAccount();
        selectAccount = new JComboBox<>(accountChoice);
        selectAccount.setFont(Asset.getBodyFont("Plain"));
        selectAccount.setSize(accountText.getWidth(), accountText.getHeight());
        selectAccount.setBackground(Color.WHITE);

        confirmButton = new Asset().generateButtonWithoutImage("Confirm", 150, accountText.getHeight());
        confirmButton.setFocusable(true);
        confirmButton.addMouseListener(this);

        displayTechnicianPanel = new InformationPaneComponent().bookAppointmentPane(null);
        displayCustomerPanel = new JScrollPane(new InformationPaneComponent().customerInformation(null));

        cancelButton = new Asset().generateButtonWithoutImage("Cancel", confirmButton.getWidth(), confirmButton.getHeight());

        saveButton = new Asset().generateButtonWithoutImage("Save details", cancelButton.getWidth() + 100, confirmButton.getHeight());
        saveButton.addMouseListener(this);

        backgroundPanel.add(backArrow);
        backgroundPanel.add(logoutButton);
        backgroundPanel.add(title);
        backgroundPanel.add(accountText);
        backgroundPanel.add(selectAccount);
        backgroundPanel.add(confirmButton);
        backgroundPanel.add(cancelButton);
        backgroundPanel.add(saveButton);

        frame.add(backgroundPanel);
        frame.add(backgroundPicture);
        frame.setVisible(true);

    }

    @Override
    public void componentResized(ComponentEvent e) {
        backgroundPicture.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        backgroundPanel.setSize(frame.getWidth() * 9 / 10, frame.getHeight() * 9 / 10 - 30);
        backgroundPanel.setLocation((frame.getWidth() - backgroundPanel.getWidth()) / 2, (frame.getHeight() - backgroundPanel.getHeight()) / 2 - 15);
        backArrow.setLocation(backgroundPanel.getX(), backgroundPanel.getY() + 20);
        logoutButton.setLocation(backgroundPanel.getWidth() - logoutButton.getWidth() - 50, backgroundPanel.getY() - 5);
        title.setBounds(backArrow.getX() + backArrow.getWidth() + 20, logoutButton.getY(), logoutButton.getX() - backArrow.getWidth() - backArrow.getX(), logoutButton.getHeight());
        accountText.setLocation(title.getX(), title.getY() + title.getHeight() + 20);
        selectAccount.setLocation(accountText.getX() + accountText.getWidth(), accountText.getY());
        confirmButton.setLocation(selectAccount.getX() + selectAccount.getWidth() + 20, selectAccount.getY() - 3);
        saveButton.setLocation(backgroundPanel.getWidth() - saveButton.getWidth() - 40, backgroundPanel.getHeight() - saveButton.getHeight() - 40);
        cancelButton.setLocation(saveButton.getX() - 20 - cancelButton.getWidth(), saveButton.getY());
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == confirmButton) {
            if (Objects.equals(selectAccount.getSelectedItem(), "Manager")) {

                if (backgroundPanel.getComponentZOrder(checkInputText) > -1) {
                    backgroundPanel.remove(checkInputText);
                }

                if (backgroundPanel.getComponentZOrder(checkPrompt) > -1) {
                    backgroundPanel.remove(checkPrompt);
                }
                if (backgroundPanel.getComponentZOrder(searchButton) > -1) {
                    backgroundPanel.remove(searchButton);
                }
                if (backgroundPanel.getComponentZOrder(displayManagerPanel) > -1) {
                    backgroundPanel.remove(displayManagerPanel);
                }

                if (backgroundPanel.getComponentZOrder(displayTechnicianPanel) > -1) {
                    backgroundPanel.remove(displayTechnicianPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayCustomerPanel) > -1) {
                    backgroundPanel.remove(displayCustomerPanel);
                }

                checkInputText = new JLabel("Enter email for checking:");
                checkInputText.setFont(Asset.getNameFont("Plain"));
                checkInputText.setSize(accountText.getWidth(), accountText.getHeight());
                checkInputText.setLocation(accountText.getX(), accountText.getY() + accountText.getHeight() + 20);

                checkPrompt = new Asset().generateTextField();
                checkPrompt.setBounds(selectAccount.getX(), selectAccount.getY() + selectAccount.getHeight() + 20, selectAccount.getWidth(), selectAccount.getHeight());

                searchButton = new Asset().generateButtonWithoutImage("Search", confirmButton.getWidth() - 3, confirmButton.getHeight() - 3);
                searchButton.setLocation(confirmButton.getX(), checkPrompt.getY() - 3);

                backgroundPanel.add(checkInputText);
                backgroundPanel.add(checkPrompt);
                backgroundPanel.add(searchButton);

                backgroundPanel.revalidate();
                backgroundPanel.repaint();

                backgroundPanel.addComponentListener(new ComponentListener() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        checkInputText.setLocation(accountText.getX(), accountText.getY() + accountText.getHeight() + 20);
                        checkPrompt.setBounds(selectAccount.getX(), selectAccount.getY() + selectAccount.getHeight() + 20, selectAccount.getWidth(), selectAccount.getHeight());
                        searchButton.setLocation(confirmButton.getX(), checkPrompt.getY() - 3);

                    }

                    @Override
                    public void componentMoved(ComponentEvent e) {

                    }

                    @Override
                    public void componentShown(ComponentEvent e) {

                    }

                    @Override
                    public void componentHidden(ComponentEvent e) {

                    }
                });

                searchButton.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        InformationPaneComponent.positionChoice.clear();
                        InformationPaneComponent.positionChoice.addAll(Arrays.asList(Manager.getManagerPosition()));

                        if (backgroundPanel.getComponentZOrder(displayManagerPanel) > -1) {
                            backgroundPanel.remove(displayManagerPanel);
                        }

                        if (backgroundPanel.getComponentZOrder(displayTechnicianPanel) > -1) {
                            backgroundPanel.remove(displayTechnicianPanel);
                        }
                        if (backgroundPanel.getComponentZOrder(displayCustomerPanel) > -1) {
                            backgroundPanel.remove(displayCustomerPanel);
                        }

                        if (checkPrompt.getText().equals("123")) {
                            displayManagerPanel = new JScrollPane(infoPane1.modifyManagerAndTechnicianAccountOwn());
                        } else {
                            displayManagerPanel = new JScrollPane(infoPane1.modifyManagerAndTechnicianAccountNotOwn());
                        }

                        displayManagerPanel.setBounds(title.getX(), checkInputText.getY() + checkInputText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - checkInputText.getY() - checkInputText.getHeight()) * 9 / 10 - 100);
                        displayManagerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                        backgroundPanel.add(displayManagerPanel);
                        displayManagerPanel.setVisible(true);
                        displayManagerPanel.revalidate();
                        displayManagerPanel.repaint();
                        backgroundPanel.addComponentListener(new ComponentListener() {
                            @Override
                            public void componentResized(ComponentEvent e) {
                                displayManagerPanel.setBounds(title.getX(), checkInputText.getY() + checkInputText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - checkInputText.getY() - checkInputText.getHeight()) * 9 / 10 - 100);
                                displayManagerPanel.revalidate();
                            }

                            @Override
                            public void componentMoved(ComponentEvent e) {

                            }

                            @Override
                            public void componentShown(ComponentEvent e) {

                            }

                            @Override
                            public void componentHidden(ComponentEvent e) {

                            }
                        });
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });



            } else if (Objects.equals(selectAccount.getSelectedItem(), "Technician")) {

                if (backgroundPanel.getComponentZOrder(checkInputText) > -1) {
                    backgroundPanel.remove(checkInputText);
                }

                if (backgroundPanel.getComponentZOrder(checkPrompt) > -1) {
                    backgroundPanel.remove(checkPrompt);
                }
                if (backgroundPanel.getComponentZOrder(searchButton) > -1) {
                    backgroundPanel.remove(searchButton);
                }

                if (backgroundPanel.getComponentZOrder(displayTechnicianPanel) > -1) {
                    backgroundPanel.remove(displayTechnicianPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayManagerPanel) > -1) {
                    backgroundPanel.remove(displayManagerPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayCustomerPanel) > -1) {
                    backgroundPanel.remove(displayCustomerPanel);
                }

                checkInputText = new JLabel("Enter email for checking:");
                checkInputText.setFont(Asset.getNameFont("Plain"));
                checkInputText.setSize(accountText.getWidth(), accountText.getHeight());
                checkInputText.setLocation(accountText.getX(), accountText.getY() + accountText.getHeight() + 20);

                checkPrompt = new Asset().generateTextField();
                checkPrompt.setBounds(selectAccount.getX(), selectAccount.getY() + selectAccount.getHeight() + 20, selectAccount.getWidth(), selectAccount.getHeight());

                searchButton = new Asset().generateButtonWithoutImage("Search", confirmButton.getWidth() - 3, confirmButton.getHeight() - 3);
                searchButton.setLocation(confirmButton.getX(), checkPrompt.getY() - 3);

                backgroundPanel.add(checkInputText);
                backgroundPanel.add(checkPrompt);
                backgroundPanel.add(searchButton);

                backgroundPanel.revalidate();
                backgroundPanel.repaint();

                searchButton.addComponentListener(new ComponentListener() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        checkInputText.setLocation(accountText.getX(), accountText.getY() + accountText.getHeight() + 20);
                        checkPrompt.setBounds(selectAccount.getX(), selectAccount.getY() + selectAccount.getHeight() + 20, selectAccount.getWidth(), selectAccount.getHeight());
                        searchButton.setLocation(confirmButton.getX(), checkPrompt.getY() - 3);
                    }

                    @Override
                    public void componentMoved(ComponentEvent e) {

                    }

                    @Override
                    public void componentShown(ComponentEvent e) {

                    }

                    @Override
                    public void componentHidden(ComponentEvent e) {

                    }
                });

                searchButton.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        InformationPaneComponent.positionChoice.clear();
                        InformationPaneComponent.positionChoice.addAll(Arrays.asList(Technician.getTechnicianPosition()));

                        if (backgroundPanel.getComponentZOrder(displayManagerPanel) > -1) {
                            backgroundPanel.remove(displayManagerPanel);
                        }

                        if (backgroundPanel.getComponentZOrder(displayTechnicianPanel) > -1) {
                            backgroundPanel.remove(displayTechnicianPanel);
                        }
                        if (backgroundPanel.getComponentZOrder(displayCustomerPanel) > -1) {
                            backgroundPanel.remove(displayCustomerPanel);
                        }

                        if (checkPrompt.getText().equals("456")) {
                            displayTechnicianPanel = new JScrollPane(new InformationPaneComponent().modifyManagerAndTechnicianAccountOwn());
                        } else {
                            displayTechnicianPanel = new JScrollPane(new InformationPaneComponent().modifyManagerAndTechnicianAccountNotOwn());
                        }
                        displayTechnicianPanel.setBounds(title.getX(), checkInputText.getY() + checkInputText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - checkInputText.getY() - checkInputText.getHeight()) * 9 / 10 - 100);
                        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                        backgroundPanel.add(displayTechnicianPanel);
                        displayTechnicianPanel.setVisible(true);
                        displayTechnicianPanel.revalidate();
                        backgroundPanel.addComponentListener(new ComponentListener() {
                            @Override
                            public void componentResized(ComponentEvent e) {
                                displayTechnicianPanel.setBounds(title.getX(), checkInputText.getY() + checkInputText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - checkInputText.getY() - checkInputText.getHeight()) * 9 / 10 - 100);
                                displayTechnicianPanel.revalidate();
                            }

                            @Override
                            public void componentMoved(ComponentEvent e) {

                            }

                            @Override
                            public void componentShown(ComponentEvent e) {

                            }

                            @Override
                            public void componentHidden(ComponentEvent e) {

                            }
                        });
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });




            } else if (Objects.equals(selectAccount.getSelectedItem(), "Customer")) {

                if (backgroundPanel.getComponentZOrder(checkInputText) > -1) {
                    backgroundPanel.remove(checkInputText);
                }
                if (backgroundPanel.getComponentZOrder(checkPrompt) > -1) {
                    backgroundPanel.remove(checkPrompt);
                }
                if (backgroundPanel.getComponentZOrder(searchButton) > -1) {
                    backgroundPanel.remove(searchButton);
                }

                if (backgroundPanel.getComponentZOrder(displayCustomerPanel) > -1) {
                    backgroundPanel.remove(displayCustomerPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayTechnicianPanel) > -1) {
                    backgroundPanel.remove(displayTechnicianPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayManagerPanel) > -1) {
                    backgroundPanel.remove(displayManagerPanel);
                }

                checkInputText = new JLabel("Enter TP number:");
                checkInputText.setFont(Asset.getNameFont("Plain"));
                checkInputText.setSize(accountText.getWidth(), accountText.getHeight());
                checkInputText.setLocation(accountText.getX(), accountText.getY() + accountText.getHeight() + 20);

                checkPrompt = new Asset().generateTextField();
                checkPrompt.setBounds(selectAccount.getX(), selectAccount.getY() + selectAccount.getHeight() + 20, selectAccount.getWidth(), selectAccount.getHeight());

                searchButton = new Asset().generateButtonWithoutImage("Search", confirmButton.getWidth() - 3, confirmButton.getHeight() - 3);
                searchButton.setLocation(confirmButton.getX(), checkPrompt.getY() - 3);

                backgroundPanel.add(checkInputText);
                backgroundPanel.add(checkPrompt);
                backgroundPanel.add(searchButton)
;
                backgroundPanel.repaint();
                backgroundPanel.revalidate();

                backgroundPanel.addComponentListener(new ComponentListener() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        checkInputText.setLocation(accountText.getX(), accountText.getY() + accountText.getHeight() + 20);
                        checkPrompt.setBounds(selectAccount.getX(), selectAccount.getY() + selectAccount.getHeight() + 20, selectAccount.getWidth(), selectAccount.getHeight());
                        searchButton.setLocation(confirmButton.getX(), checkPrompt.getY() - 3);
                    }

                    @Override
                    public void componentMoved(ComponentEvent e) {

                    }

                    @Override
                    public void componentShown(ComponentEvent e) {

                    }

                    @Override
                    public void componentHidden(ComponentEvent e) {

                    }
                });

                searchButton.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if (backgroundPanel.getComponentZOrder(displayManagerPanel) > -1) {
                            backgroundPanel.remove(displayManagerPanel);
                        }

                        if (backgroundPanel.getComponentZOrder(displayTechnicianPanel) > -1) {
                            backgroundPanel.remove(displayTechnicianPanel);
                        }
                        if (backgroundPanel.getComponentZOrder(displayCustomerPanel) > -1) {
                            backgroundPanel.remove(displayCustomerPanel);
                        }

                        displayCustomerPanel = new JScrollPane(new InformationPaneComponent().customerModifyPersonalInformation());
                        displayCustomerPanel.setBounds(title.getX(), checkInputText.getY() + checkInputText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - checkInputText.getY() - checkInputText.getHeight()) * 9 / 10 - 100);
                        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

                        backgroundPanel.add(displayCustomerPanel);
                        displayCustomerPanel.setVisible(true);
                        displayCustomerPanel.revalidate();

                        backgroundPanel.addComponentListener(new ComponentListener() {
                            @Override
                            public void componentResized(ComponentEvent e) {
                                displayCustomerPanel.setBounds(title.getX(), checkInputText.getY() + checkInputText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - checkInputText.getY() - checkInputText.getHeight()) * 9 / 10 - 100);
                                displayCustomerPanel.revalidate();
                            }

                            @Override
                            public void componentMoved(ComponentEvent e) {

                            }

                            @Override
                            public void componentShown(ComponentEvent e) {

                            }

                            @Override
                            public void componentHidden(ComponentEvent e) {

                            }
                        });
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            } else {
                System.out.println("Error with selecting accounts.");
            }
        } else if (e.getSource() == saveButton) {
            System.out.println(infoPane1.getAddressLine1());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
