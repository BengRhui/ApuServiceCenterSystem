import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;

public class AddItemPage implements KeyListener, ComponentListener, WindowListener, MouseListener {
    JPanel backgroundPanel;
    JFrame frame;
    JLabel mainTitle, itemLabel, priceLabel;
    JTextField itemText, priceText;
    JLayeredPane saveButton, cancelButton;

    public AddItemPage(){
        frame = new JFrame("Add Item Page");
        frame.setSize(750, 600);
        frame.setLocation(Asset.getFramePositionX() + (Asset.getFrameWidth() - frame.getWidth()) / 2, Asset.getFramePositionY() + (Asset.getFrameHeight() - frame.getHeight()) / 2);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(null);
        frame.addComponentListener(this);
        frame.addWindowListener(this);

        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));

        mainTitle = new JLabel("Item Details");
        mainTitle.setFont(Asset.getNameFont("Bold"));

        itemLabel = new JLabel("Item name");
        itemLabel.setFont(Asset.getNameFont("Plain"));

        itemText = new Asset().generateTextField();

        priceLabel = new JLabel("Item Price (RM)");
        priceLabel.setFont(Asset.getNameFont("Plain"));

        priceText = new Asset().generateTextField();

        saveButton = new Asset().generateButtonWithoutImage("Save", 250, 50);
        saveButton.addMouseListener(this);

        cancelButton = new Asset().generateButtonWithoutImage("Cancel", 250, 50);
        cancelButton.addMouseListener(this);

        frame.add(cancelButton);
        frame.add(saveButton);
        frame.add(priceText);
        frame.add(priceLabel);
        frame.add(itemText);
        frame.add(itemLabel);
        frame.add(mainTitle);
        frame.add(backgroundPanel);
        frame.setVisible(true);


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void componentResized(ComponentEvent e) {
        int width = e.getComponent().getWidth();
        int height = e.getComponent().getHeight();

        backgroundPanel.setSize(frame.getWidth(), frame.getHeight());
        backgroundPanel.setLocation(0, 0);
        mainTitle.setBounds(width * 80 / 1100, height * 50 / 750, width * 400 / 1100, height * 100 / 750);
        itemLabel.setBounds(width * 80 / 1100, height * 160 / 750, width * 900 / 1100, height * 75 / 750);
        itemText.setBounds(width * 80 / 1100, height * 240 / 750, width * 900 / 1100, height * 75 / 750);
        priceLabel.setBounds(width * 80 / 1100, height * 340 / 750, width * 900 / 1100, height * 75 / 750);
        priceText.setBounds(width * 80 / 1100, height * 420 / 750, width * 900 / 1100, height * 75 / 750);
        saveButton.setLocation(backgroundPanel.getWidth() - saveButton.getWidth() - 80, backgroundPanel.getHeight() - saveButton.getHeight() - 100);
        cancelButton.setLocation(saveButton.getX() - cancelButton.getWidth() - 50, saveButton.getY());
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
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (e.getSource() == frame) {
            int choice = JOptionPane.showConfirmDialog(frame, "<html>Are you sure that you would like to exit the page?<br>The inputs will not be saved.</html>", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "logout_icon.png"));
            if (choice == 0) {
                Asset.setFramePosition(frame.getX(), frame.getY());
                ManagerMainPage.frame.setEnabled(true);
                frame.dispose();
            }
        }
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == saveButton) {
            try {
                TextFileOperationsComponent.readElectronicsData();
                for (ElectronicItems item : ElectronicItems.getFullItemList()) {
                    if (item.itemName.equals(itemText.getText())) {
                        throw new NumberFormatException();
                    }
                }

                if (itemText.getText().isEmpty() || priceText.getText().isEmpty()) {
                    throw new NumberFormatException();
                }

                String itemName = itemText.getText();
                double itemPrice = Double.parseDouble(priceText.getText());

                ElectronicItems item = new ElectronicItems(ElectronicItems.generateElectronicItemID(), itemName, itemPrice);
                ElectronicItems.getFullItemList().add(item);
                TextFileOperationsComponent.writeElectronicItem();

                JOptionPane.showMessageDialog(frame, "Item created successful. You will be redirected to the main page.", "Success Create Item", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "success_icon.png"));
                ManagerMainPage.frame.setEnabled(true);
                frame.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "The item is already in the system. Please try again.", "Invalid Input", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "warning_icon.png"));
            }
        } else if (e.getSource() == cancelButton) {

            int choice = JOptionPane.showConfirmDialog(frame, "<html>Do you wish to return to the main page?<br>Any inputted data will not be saved.</html>", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "return_icon.png"));
            if (choice == 0) {
                Asset.setFramePosition(frame.getX(), frame.getY());
                frame.dispose();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
