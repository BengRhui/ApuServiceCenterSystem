import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class InformationPane extends JPanel {

    private final static int scrollPaneWidth = 900;

    // Overall pane - book appointment
    public JScrollPane bookAppointmentPane() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        JPanel panel1 = new InformationPane().customerInformation();
        JPanel panel2 = new InformationPane().divider();
        JPanel panel3 = new InformationPane().appointmentDetails();

        panel1.setLocation(0, 0);
        panel2.setLocation(0, panel1.getHeight());
        panel3.setLocation(0, panel1.getHeight() + panel2.getHeight());

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);

        int sumOfHeight = panel1.getHeight() + panel2.getHeight() + panel3.getHeight();

        panel.setPreferredSize(new Dimension(panel1.getWidth(), sumOfHeight));

        JScrollPane finalPane = new JScrollPane(panel);
        finalPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return finalPane;
    }

    public JScrollPane createManagerAndTechnicianAccount() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        JPanel panel1 = new InformationPane().managerAndTechnicianPersonalDetails();
        JPanel panel2 = new InformationPane().divider();
        JPanel panel3 = new InformationPane().customerInformation();

        panel1.setLocation(0, 0);
        panel2.setLocation(0, panel1.getHeight());
        panel3.setLocation(0, panel1.getHeight() + panel2.getHeight());

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);

        int sumOfHeight = panel1.getHeight() + panel2.getHeight() + panel3.getHeight();

        panel.setPreferredSize(new Dimension(panel1.getWidth(), sumOfHeight));

        JScrollPane finalPane = new JScrollPane(panel);
        finalPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return finalPane;
    }

    // Sub-pane
    public JPanel customerInformation() {

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setSize(scrollPaneWidth, 270);

        JLabel title = new JLabel("<html>Customer<br>Information</html>");
        title.setFont(Asset.getNameFont("Bold"));
        title.setBounds(30, 0, panel.getWidth() / 5, 100);

        JLabel namePlaceholder = new JLabel("Name");
        namePlaceholder.setFont(Asset.getBodyFont("Plain"));
        namePlaceholder.setBounds(title.getWidth() + title.getX(), title.getY() + 10, panel.getWidth() - title.getWidth() - title.getX(), 50);

        JTextField name = new Asset().generateTextField();
        name.setBounds(namePlaceholder.getX(), namePlaceholder.getY() + namePlaceholder.getHeight(), namePlaceholder.getWidth() - 20, namePlaceholder.getHeight());

        JLabel emailText = new JLabel("Email");
        emailText.setFont(Asset.getBodyFont("Plain"));
        emailText.setBounds(namePlaceholder.getX(), name.getY() + name.getHeight() + 20, namePlaceholder.getWidth() / 2 - 20, name.getHeight());

        JTextField email = new Asset().generateTextField();
        email.setBounds(emailText.getX(), emailText.getY() + emailText.getHeight(), emailText.getWidth(), emailText.getHeight());

        JLabel contactText = new JLabel("Contact Number");
        contactText.setFont(Asset.getBodyFont("Plain"));
        contactText.setBounds(emailText.getX() + emailText.getWidth() + 20, emailText.getY(), emailText.getWidth(), emailText.getHeight());

        JTextField contact = new Asset().generateTextField();
        contact.setBounds(contactText.getX(), contactText.getY() + contactText.getHeight(), contactText.getWidth(), contactText.getHeight());

        panel.add(title);
        panel.add(namePlaceholder);
        panel.add(name);
        panel.add(emailText);
        panel.add(email);
        panel.add(contactText);
        panel.add(contact);

        return panel;

    }

    public JPanel appointmentDetails() {

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setSize(scrollPaneWidth, 390);

        JLabel title = new JLabel("<html>Appointment<br>Details</html>");
        title.setFont(Asset.getNameFont("Bold"));
        title.setBounds(30, 0, panel.getWidth() / 5, 100);

        JLabel technicianText = new JLabel("Technician-in-charge");
        technicianText.setFont(Asset.getBodyFont("Plain"));
        technicianText.setBounds(title.getWidth() + title.getX(), title.getY() + 10, (panel.getWidth() - title.getWidth() - title.getX()) / 2 - 20, 50);

        String[] technicianChoices = {"Adam", "Ali", "Muthu"};
        JComboBox<String> appointmentChoice = new JComboBox<>(technicianChoices);
        appointmentChoice.setFont(Asset.getBodyFont("Plain"));
        appointmentChoice.setBounds(technicianText.getX(), technicianText.getY() + technicianText.getHeight(), technicianText.getWidth(), technicianText.getHeight());
        appointmentChoice.setBackground(Color.WHITE);

        JLabel itemsText = new JLabel("Items to be Serviced");
        itemsText.setFont(Asset.getBodyFont("Plain"));
        itemsText.setBounds(technicianText.getX() + technicianText.getWidth() + 20, technicianText.getY(), technicianText.getWidth(), technicianText.getHeight());

        TextFileOperations.readElectronicsData();
        String[] itemsChoices = new String[ElectronicItems.fullItemList.size()];
        int i = 0;
        for (ElectronicItems item: ElectronicItems.fullItemList) {
            itemsChoices[i] = item.itemName;
            i ++;
        }

        JComboBox<String> serviceItemList = new JComboBox<>(itemsChoices);
        serviceItemList.setFont(Asset.getBodyFont("Plain"));
        serviceItemList.setBounds(itemsText.getX(), itemsText.getY() + itemsText.getHeight(), itemsText.getWidth(), itemsText.getHeight());
        serviceItemList.setBackground(Color.WHITE);

        JLabel appointmentDateText = new JLabel("Appointment Date");
        appointmentDateText.setFont(Asset.getBodyFont("Plain"));
        appointmentDateText.setBounds(technicianText.getX(), appointmentChoice.getY() + appointmentChoice.getHeight() + 20, technicianText.getWidth(), technicianText.getHeight());

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, new Properties());
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        datePanel.setFont(Asset.getBodyFont("Plain"));
        datePicker.setBackground(Color.WHITE);
        datePicker.setLocation(appointmentDateText.getX(), appointmentDateText.getY() + appointmentDateText.getHeight());
        datePicker.setSize(new Dimension(appointmentDateText.getWidth(), appointmentDateText.getHeight()));
        datePicker.getComponent(0).setPreferredSize(new Dimension(appointmentDateText.getWidth() - appointmentDateText.getHeight(),appointmentDateText.getHeight()));
        datePicker.getComponent(1).setPreferredSize(new Dimension(appointmentDateText.getHeight(),appointmentDateText.getHeight()));
        datePicker.getJFormattedTextField().setFont(Asset.getBodyFont("Plain"));
        datePicker.getJFormattedTextField().setBackground(Color.WHITE);
        datePicker.getComponent(1).setBackground(Color.WHITE);

        JLayeredPane checkAppointment = new Asset().generateButtonWithoutImage("Click to View Schedule", serviceItemList.getWidth(), serviceItemList.getHeight());
        checkAppointment.setLocation(serviceItemList.getX(), datePicker.getY() - 3);

        JLabel appointmentStartTimeText = new JLabel("Appointment Start Time");
        appointmentStartTimeText.setFont(Asset.getBodyFont("Plain"));
        appointmentStartTimeText.setBounds(datePicker.getX(), datePicker.getY() + datePicker.getHeight() + 20, datePicker.getWidth(), datePicker.getHeight());

        String[] appointmentTimeSlot = Appointment.getAppointmentTime();

        JComboBox<String> appointmentStart = new JComboBox<>(appointmentTimeSlot);
        appointmentStart.setFont(Asset.getBodyFont("Plain"));
        appointmentStart.setBounds(appointmentStartTimeText.getX(), appointmentStartTimeText.getY() + appointmentStartTimeText.getHeight(), appointmentStartTimeText.getWidth(), appointmentStartTimeText.getHeight());
        appointmentStart.setBackground(Color.WHITE);

        JLabel appointmentEndTimeText = new JLabel("Appointment End Time");
        appointmentEndTimeText.setFont(Asset.getBodyFont("Plain"));
        appointmentEndTimeText.setBounds(itemsText.getX(), checkAppointment.getY() + 3 + checkAppointment.getHeight() + 20, itemsText.getWidth(), itemsText.getHeight());

        JComboBox<String> appointmentEnd = new JComboBox<>(appointmentTimeSlot);
        appointmentEnd.setBackground(Color.WHITE);
        appointmentEnd.setFont(Asset.getBodyFont("Plain"));
        appointmentEnd.setBounds(appointmentEndTimeText.getX(), appointmentStart.getY(), appointmentStart.getWidth(), appointmentStart.getHeight());

        panel.add(title);
        panel.add(technicianText);
        panel.add(appointmentChoice);
        panel.add(itemsText);
        panel.add(serviceItemList);
        panel.add(appointmentDateText);
        panel.add(datePicker);
        panel.add(checkAppointment);
        panel.add(appointmentStartTimeText);
        panel.add(appointmentStart);
        panel.add(appointmentEndTimeText);
        panel.add(appointmentEnd);

        return panel;
    }

    public JPanel managerAndTechnicianPersonalDetails() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setSize(scrollPaneWidth, 800);

        JLabel title = new JLabel("<html>Personal<br>Information</html>");
        title.setFont(Asset.getNameFont("Bold"));
        title.setBounds(30, 0, panel.getWidth() / 5, 100);

        JLabel namePlaceholder = new JLabel("Name");
        namePlaceholder.setFont(Asset.getBodyFont("Plain"));
        namePlaceholder.setBounds(title.getWidth() + title.getX(), title.getY() + 10, panel.getWidth() - title.getWidth() - title.getX(), 50);

        JTextField name = new Asset().generateTextField();
        name.setBounds(namePlaceholder.getX(), namePlaceholder.getY() + namePlaceholder.getHeight(), namePlaceholder.getWidth() - 20, namePlaceholder.getHeight());

        JLabel genderText = new JLabel("Gender");
        genderText.setFont(Asset.getBodyFont("Plain"));
        genderText.setBounds(namePlaceholder.getX(), name.getY() + name.getHeight() + 20, namePlaceholder.getWidth() / 2 - 20, name.getHeight());

        JComboBox<String> genderChoice = new JComboBox<>(User.getGenderTypes());
        genderChoice.setBounds(genderText.getX(), genderText.getY() + genderText.getHeight(), genderText.getWidth(), genderText.getHeight());
        genderChoice.setFont(Asset.getBodyFont("Plain"));

        JLabel maritalStatusText = new JLabel("Marital Status");
        maritalStatusText.setFont(Asset.getBodyFont("Plain"));
        maritalStatusText.setBounds(genderText.getX() + genderText.getWidth() + 20, genderText.getY(), genderText.getWidth(), genderText.getHeight());

        JComboBox<String> maritalStatusChoice = new JComboBox<>(User.getMaritalStatus());
        maritalStatusChoice.setFont(Asset.getBodyFont("Plain"));
        maritalStatusChoice.setBounds(maritalStatusText.getX(), maritalStatusText.getY() + maritalStatusText.getHeight(), maritalStatusText.getWidth(), maritalStatusText.getHeight());

        JLabel addressText = new JLabel("Address");
        addressText.setFont(Asset.getBodyFont("Plain"));
        addressText.setBounds(genderChoice.getX(), genderChoice.getY() + genderChoice.getHeight() + 20, genderChoice.getWidth(), genderChoice.getHeight());

        JTextField addressLine1 = new Asset().generateTextField();
        addressLine1.setBounds(addressText.getX(), addressText.getY() + addressText.getHeight(), name.getWidth(), addressText.getHeight());

        JTextField addressLine2 = new Asset().generateTextField();
        addressLine2.setBounds(addressLine1.getX(), addressLine1.getY() + addressLine1.getHeight() + 10, addressLine1.getWidth(), addressLine1.getHeight());

        JTextField addressLine3 = new Asset().generateTextField();
        addressLine3.setBounds(addressLine2.getX(), addressLine2.getY() + addressLine2.getHeight() + 10, addressLine2.getWidth(), addressLine2.getHeight());

        JLabel postcodeText = new JLabel("Postcode");
        postcodeText.setFont(Asset.getBodyFont("Plain"));
        postcodeText.setBounds(addressLine3.getX(), addressLine3.getY() + addressLine3.getHeight() + 20, genderText.getWidth(), genderText.getHeight());

        

        panel.add(title);
        panel.add(namePlaceholder);
        panel.add(name);
        panel.add(genderText);
        panel.add(genderChoice);
        panel.add(maritalStatusText);
        panel.add(maritalStatusChoice);
        panel.add(addressText);
        panel.add(addressLine1);
        panel.add(addressLine2);
        panel.add(addressLine3);
        panel.add(postcodeText);

        return panel;
    }

    // Line pane
    public JPanel divider() {
        JPanel panel = new Asset().drawLine(0, 0, 2000, 0, 1);
        panel.setBackground(Asset.getTransparentColour());
        return panel;
    }

}
