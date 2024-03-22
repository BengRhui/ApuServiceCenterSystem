import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

public class InformationPaneComponent extends JPanel {

    private final static int scrollPaneWidth = 900;
    JPanel panelBA, panel1BA, panel2BA, panel3BA;
    // Overall pane - book appointment
    InformationPaneComponent bookPane;
    public JScrollPane bookAppointmentPane(Student student) {

        bookPane = new InformationPaneComponent();

        panelBA = new JPanel(null);
        panelBA.setBackground(Color.WHITE);

        panel1BA = bookPane.customerInformation(student);
        panel2BA = bookPane.divider();
        panel3BA = bookPane.appointmentDetails();

        panel1BA.setLocation(0, 0);
        panel2BA.setLocation(0, panel1BA.getHeight());
        panel3BA.setLocation(0, panel1BA.getHeight() + panel2BA.getHeight());

        panelBA.add(panel1BA);
        panelBA.add(panel2BA);
        panelBA.add(panel3BA);

        int sumOfHeight = panel1BA.getHeight() + panel2BA.getHeight() + panel3BA.getHeight();

        panelBA.setPreferredSize(new Dimension(panel1BA.getWidth(), sumOfHeight));

        JScrollPane finalPane = new JScrollPane(panelBA);
        finalPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return finalPane;
    }

    JPanel panel1;
    InformationPaneComponent createAdminAccount;
    static ArrayList<String> positionChoice = new ArrayList<>();
    public JPanel createManagerAndTechnicianAccount() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        createAdminAccount = new InformationPaneComponent();
        JPanel panel1 = createAdminAccount.managerAndTechnicianPersonalInformation(null, null);
        JPanel panel2 = createAdminAccount.divider();
        JPanel panel3 = createAdminAccount.jobDetails(null, null);
        JPanel panel4 = createAdminAccount.divider();
        JPanel panel5 = createAdminAccount.loginDetails();

        panel1.setLocation(0, 0);
        panel2.setLocation(0, panel1.getHeight());
        panel3.setLocation(0, panel1.getHeight() + panel2.getHeight());
        panel4.setLocation(0, panel1.getHeight() + panel2.getHeight() + panel3.getHeight());
        panel5.setLocation(0, panel1.getHeight() + panel2.getHeight() + panel3.getHeight() + panel4.getHeight());

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);

        int sumOfHeight = panel1.getHeight() + panel2.getHeight() + panel3.getHeight() + panel4.getHeight() + panel5.getHeight();

        panel.setPreferredSize(new Dimension(panel1.getWidth(), sumOfHeight));

        return panel;
    }

    InformationPaneComponent modifyOtherAdminAccount;
    public JPanel modifyManagerAndTechnicianAccountNotOwn(Manager manager, Technician technician) {

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        modifyOtherAdminAccount = new InformationPaneComponent();
        JPanel panel1 = modifyOtherAdminAccount.managerAndTechnicianPersonalInformation(manager, technician);
        JPanel panel2 = new InformationPaneComponent().divider();
        JPanel panel3 = modifyOtherAdminAccount.accountDetails(manager, technician);
        JPanel panel4 = new InformationPaneComponent().divider();
        JPanel panel5 = modifyOtherAdminAccount.jobDetails(manager, technician);

        panel1.setLocation(0, 0);
        panel2.setLocation(0, panel1.getHeight());
        panel3.setLocation(0, panel1.getHeight() + panel2.getHeight());
        panel4.setLocation(0, panel1.getHeight() + panel2.getHeight() + panel3.getHeight());
        panel5.setLocation(0, panel1.getHeight() + panel2.getHeight() + panel3.getHeight() + panel4.getHeight());

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);

        int sumOfHeight = panel1.getHeight() + panel2.getHeight() + panel3.getHeight() + panel4.getHeight() + panel5.getHeight();

        panel.setPreferredSize(new Dimension(panel1.getWidth(), sumOfHeight));

        return panel;
    }

    InformationPaneComponent modifyOwnAccount;
    public JPanel modifyManagerAndTechnicianAccountOwn(Manager manager) {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        modifyOwnAccount = new InformationPaneComponent();
        JPanel panel1 = modifyOwnAccount.managerAndTechnicianPersonalInformation(manager, null);
        JPanel panel2 = new InformationPaneComponent().divider();
        JPanel panel3 = modifyOwnAccount.accountDetails(manager, null);
        JPanel panel4 = new InformationPaneComponent().divider();
        JPanel panel5 = modifyOwnAccount.jobDetails(manager, null);
        JPanel panel6 = new InformationPaneComponent().divider();
        JPanel panel7 = modifyOwnAccount.loginDetails();

        panel1.setLocation(0, 0);
        panel2.setLocation(0, panel1.getHeight());
        panel3.setLocation(0, panel2.getY() + panel2.getHeight());
        panel4.setLocation(0, panel3.getY() + panel3.getHeight());
        panel5.setLocation(0, panel4.getY() + panel4.getHeight());
        panel6.setLocation(0, panel5.getY() + panel5.getHeight());
        panel7.setLocation(0, panel6.getY() + panel6.getHeight());

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        panel.add(panel6);
        panel.add(panel7);

        int sumOfHeight = panel1.getHeight() + panel2.getHeight() + panel3.getHeight() + panel4.getHeight() + panel5.getHeight() + panel6.getHeight() + panel7.getHeight();

        panel.setPreferredSize(new Dimension(panel1.getWidth(), sumOfHeight));

        return panel;
    }

    InformationPaneComponent technicianModifyOwn;
    public JPanel modifyAccountTechnician(Technician technician) {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        technicianModifyOwn = new InformationPaneComponent();
        panel1 = technicianModifyOwn.managerAndTechnicianPersonalInformation(null, technician);
        JPanel panel2 = new InformationPaneComponent().divider();
        JPanel panel3 = new InformationPaneComponent().loginDetails();

        panel1.setLocation(0, 0);
        panel2.setLocation(0, panel1.getHeight());
        panel3.setLocation(0, panel2.getY() + panel2.getHeight());

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);


        int sumOfHeight = panel1.getHeight() + panel2.getHeight() + panel3.getHeight();

        panel.setPreferredSize(new Dimension(panel1.getWidth(), sumOfHeight));

        return panel;
    }

    // Sub-pane
    public JPanel customerInformation(Student student) {

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
        name.setText(student.name);
        name.setEditable(false);
        name.setBounds(namePlaceholder.getX(), namePlaceholder.getY() + namePlaceholder.getHeight(), namePlaceholder.getWidth() - 20, namePlaceholder.getHeight());

        JLabel emailText = new JLabel("Email");
        emailText.setFont(Asset.getBodyFont("Plain"));
        emailText.setBounds(namePlaceholder.getX(), name.getY() + name.getHeight() + 20, namePlaceholder.getWidth() / 2 - 20, name.getHeight());

        JTextField email = new Asset().generateTextField();
        email.setText(student.email);
        email.setEditable(false);
        email.setBounds(emailText.getX(), emailText.getY() + emailText.getHeight(), emailText.getWidth(), emailText.getHeight());

        JLabel contactText = new JLabel("Contact Number");
        contactText.setFont(Asset.getBodyFont("Plain"));
        contactText.setBounds(emailText.getX() + emailText.getWidth() + 20, emailText.getY(), emailText.getWidth(), emailText.getHeight());

        JTextField contact = new Asset().generateTextField();
        contact.setText(student.contactNumber);
        contact.setEditable(false);
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

    JComboBox<String> appointmentChoiceAD, serviceItemListAD, appointmentStartAD, appointmentEndAD;
    JDatePickerImpl datePickerAD;
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

        String[] technicianChoices = Technician.generateTechnicianNames();
        appointmentChoiceAD = new JComboBox<>(technicianChoices);
        appointmentChoiceAD.setFont(Asset.getBodyFont("Plain"));
        appointmentChoiceAD.setBounds(technicianText.getX(), technicianText.getY() + technicianText.getHeight(), technicianText.getWidth(), technicianText.getHeight());
        appointmentChoiceAD.setBackground(Color.WHITE);

        JLabel itemsText = new JLabel("Items to be Serviced");
        itemsText.setFont(Asset.getBodyFont("Plain"));
        itemsText.setBounds(technicianText.getX() + technicianText.getWidth() + 20, technicianText.getY(), technicianText.getWidth(), technicianText.getHeight());

        TextFileOperationsComponent.readElectronicsData();
        String[] itemsChoices = new String[ElectronicItems.getFullItemList().size()];
        int i = 0;
        for (ElectronicItems item: ElectronicItems.getFullItemList()) {
            itemsChoices[i] = item.itemName;
            i ++;
        }

        serviceItemListAD = new JComboBox<>(itemsChoices);
        serviceItemListAD.setFont(Asset.getBodyFont("Plain"));
        serviceItemListAD.setBounds(itemsText.getX(), itemsText.getY() + itemsText.getHeight(), itemsText.getWidth(), itemsText.getHeight());
        serviceItemListAD.setBackground(Color.WHITE);

        JLabel appointmentDateText = new JLabel("Appointment Date");
        appointmentDateText.setFont(Asset.getBodyFont("Plain"));
        appointmentDateText.setBounds(technicianText.getX(), appointmentChoiceAD.getY() + appointmentChoiceAD.getHeight() + 20, technicianText.getWidth(), technicianText.getHeight());

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, new Properties());
        datePickerAD = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        datePanel.setFont(Asset.getBodyFont("Plain"));
        datePickerAD.setBackground(Color.WHITE);
        datePickerAD.setLocation(appointmentDateText.getX(), appointmentDateText.getY() + appointmentDateText.getHeight());
        datePickerAD.setSize(new Dimension(appointmentDateText.getWidth(), appointmentDateText.getHeight()));
        datePickerAD.getComponent(0).setPreferredSize(new Dimension(appointmentDateText.getWidth() - appointmentDateText.getHeight(),appointmentDateText.getHeight()));
        datePickerAD.getComponent(1).setPreferredSize(new Dimension(appointmentDateText.getHeight(),appointmentDateText.getHeight()));
        datePickerAD.getJFormattedTextField().setFont(Asset.getBodyFont("Plain"));
        datePickerAD.getJFormattedTextField().setBackground(Color.WHITE);
        datePickerAD.getComponent(1).setBackground(Color.WHITE);

        JLayeredPane checkAppointment = new Asset().generateButtonWithoutImage("Click to View Schedule", serviceItemListAD.getWidth(), serviceItemListAD.getHeight());
        checkAppointment.setLocation(serviceItemListAD.getX(), datePickerAD.getY() - 3);

        JLabel appointmentStartTimeText = new JLabel("Appointment Start Time");
        appointmentStartTimeText.setFont(Asset.getBodyFont("Plain"));
        appointmentStartTimeText.setBounds(datePickerAD.getX(), datePickerAD.getY() + datePickerAD.getHeight() + 20, datePickerAD.getWidth(), datePickerAD.getHeight());

        String[] appointmentTimeSlot = Appointment.getAppointmentTime();

        appointmentStartAD = new JComboBox<>(appointmentTimeSlot);
        appointmentStartAD.setFont(Asset.getBodyFont("Plain"));
        appointmentStartAD.setBounds(appointmentStartTimeText.getX(), appointmentStartTimeText.getY() + appointmentStartTimeText.getHeight(), appointmentStartTimeText.getWidth(), appointmentStartTimeText.getHeight());
        appointmentStartAD.setBackground(Color.WHITE);

        JLabel appointmentEndTimeText = new JLabel("Appointment End Time");
        appointmentEndTimeText.setFont(Asset.getBodyFont("Plain"));
        appointmentEndTimeText.setBounds(itemsText.getX(), checkAppointment.getY() + 3 + checkAppointment.getHeight() + 20, itemsText.getWidth(), itemsText.getHeight());

        appointmentEndAD = new JComboBox<>(appointmentTimeSlot);
        appointmentEndAD.setBackground(Color.WHITE);
        appointmentEndAD.setFont(Asset.getBodyFont("Plain"));
        appointmentEndAD.setBounds(appointmentEndTimeText.getX(), appointmentStartAD.getY(), appointmentStartAD.getWidth(), appointmentStartAD.getHeight());
        checkAppointment.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    if (LocalDate.of(datePickerAD.getModel().getYear(), datePickerAD.getModel().getMonth() + 1, datePickerAD.getModel().getDay()).isBefore(LocalDate.now())) {
                        throw new NullPointerException();
                    }

                    String day = String.format("%02d", datePickerAD.getModel().getDay());
                    String month = String.format("%02d", datePickerAD.getModel().getMonth() + 1);
                    JFrame popUp = new ViewSchedulePopUp(Objects.requireNonNull(appointmentChoiceAD.getSelectedItem()).toString(), day + "/" + month + "/" + datePickerAD.getModel().getYear());
                    popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    popUp.addWindowListener(new WindowListener() {
                        @Override
                        public void windowOpened(WindowEvent e) {
                            if (e.getSource() == popUp) {
                                checkAppointment.setEnabled(false);
                            }
                        }

                        @Override
                        public void windowClosing(WindowEvent e) {

                        }

                        @Override
                        public void windowClosed(WindowEvent e) {
                            if (e.getSource() == popUp) {
                                checkAppointment.setEnabled(true);
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
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(panel, "Please select a valid technician and date to view available time.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        panel.add(title);
        panel.add(technicianText);
        panel.add(appointmentChoiceAD);
        panel.add(itemsText);
        panel.add(serviceItemListAD);
        panel.add(appointmentDateText);
        panel.add(datePickerAD);
        panel.add(checkAppointment);
        panel.add(appointmentStartTimeText);
        panel.add(appointmentStartAD);
        panel.add(appointmentEndTimeText);
        panel.add(appointmentEndAD);

        return panel;
    }

    public String getAppointmentChoiceAD() {
        return Objects.requireNonNull(bookPane.appointmentChoiceAD.getSelectedItem()).toString();
    }

    public String getServiceItemListAD() {
        return Objects.requireNonNull(bookPane.serviceItemListAD.getSelectedItem()).toString();
    }

    public LocalTime getAppointmentStartAD() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(Objects.requireNonNull(bookPane.appointmentStartAD.getSelectedItem()).toString(), format);
    }

    public LocalTime getAppointmentEndAD() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(Objects.requireNonNull(bookPane.appointmentEndAD.getSelectedItem()).toString(), format);
    }

    public LocalDate getDatePickerAD() {
        return LocalDate.of(bookPane.datePickerAD.getModel().getYear(), bookPane.datePickerAD.getModel().getMonth() + 1, bookPane.datePickerAD.getModel().getDay());
    }

    private JTextField addressLine1MPI, addressLine2MPI, addressLine3MPI, postcodeMPI, stateMPI, cityMPI;
    JTextField nameMPI, nationalityMPI, emailMPI, contactNoMPI;
    JComboBox<String> genderChoiceMPI, maritalStatusChoiceMPI;
    public JPanel managerAndTechnicianPersonalInformation(Manager manager, Technician technician) {

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setSize(scrollPaneWidth, 870);

        JLabel title = new JLabel("<html>Personal<br>Information</html>");
        title.setFont(Asset.getNameFont("Bold"));
        title.setBounds(30, 0, panel.getWidth() / 5, 100);

        JLabel namePlaceholder = new JLabel("Name");
        namePlaceholder.setFont(Asset.getBodyFont("Plain"));
        namePlaceholder.setBounds(title.getWidth() + title.getX(), title.getY() + 10, panel.getWidth() - title.getWidth() - title.getX(), 50);

        nameMPI = new Asset().generateTextField();
        nameMPI.setBounds(namePlaceholder.getX(), namePlaceholder.getY() + namePlaceholder.getHeight(), namePlaceholder.getWidth() - 20, namePlaceholder.getHeight());

        JLabel genderText = new JLabel("Gender");
        genderText.setFont(Asset.getBodyFont("Plain"));
        genderText.setBounds(namePlaceholder.getX(), nameMPI.getY() + nameMPI.getHeight() + 20, namePlaceholder.getWidth() / 2 - 20, nameMPI.getHeight());

        genderChoiceMPI = new JComboBox<>(User.getGenderTypes());
        genderChoiceMPI.setBounds(genderText.getX(), genderText.getY() + genderText.getHeight(), genderText.getWidth(), genderText.getHeight());
        genderChoiceMPI.setFont(Asset.getBodyFont("Plain"));
        genderChoiceMPI.setBackground(Color.WHITE);

        JLabel maritalStatusText = new JLabel("Marital Status");
        maritalStatusText.setFont(Asset.getBodyFont("Plain"));
        maritalStatusText.setBounds(genderText.getX() + genderText.getWidth() + 20, genderText.getY(), genderText.getWidth(), genderText.getHeight());

        maritalStatusChoiceMPI = new JComboBox<>(User.getMaritalStatus());
        maritalStatusChoiceMPI.setFont(Asset.getBodyFont("Plain"));
        maritalStatusChoiceMPI.setBounds(maritalStatusText.getX(), maritalStatusText.getY() + maritalStatusText.getHeight(), maritalStatusText.getWidth(), maritalStatusText.getHeight());
        maritalStatusChoiceMPI.setBackground(Color.WHITE);

        JLabel addressText = new JLabel("Address");
        addressText.setFont(Asset.getBodyFont("Plain"));
        addressText.setBounds(genderChoiceMPI.getX(), genderChoiceMPI.getY() + genderChoiceMPI.getHeight() + 20, genderChoiceMPI.getWidth(), genderChoiceMPI.getHeight());

        addressLine1MPI = new Asset().generateTextField();
        addressLine1MPI.setBounds(addressText.getX(), addressText.getY() + addressText.getHeight(), nameMPI.getWidth(), addressText.getHeight());

        addressLine2MPI = new Asset().generateTextField();
        addressLine2MPI.setBounds(addressLine1MPI.getX(), addressLine1MPI.getY() + addressLine1MPI.getHeight() + 10, addressLine1MPI.getWidth(), addressLine1MPI.getHeight());

        addressLine3MPI = new Asset().generateTextField();
        addressLine3MPI.setBounds(addressLine2MPI.getX(), addressLine2MPI.getY() + addressLine2MPI.getHeight() + 10, addressLine2MPI.getWidth(), addressLine2MPI.getHeight());

        JLabel postcodeText = new JLabel("Postcode");
        postcodeText.setFont(Asset.getBodyFont("Plain"));
        postcodeText.setBounds(addressLine3MPI.getX(), addressLine3MPI.getY() + addressLine3MPI.getHeight() + 20, genderText.getWidth(), genderText.getHeight());

        postcodeMPI = new Asset().generateTextField();
        postcodeMPI.setFont(Asset.getBodyFont("Plain"));
        postcodeMPI.setBounds(postcodeText.getX(), postcodeText.getY() + postcodeText.getHeight(), postcodeText.getWidth(), postcodeText.getHeight());

        JLabel stateText = new JLabel("State");
        stateText.setFont(Asset.getBodyFont("Plain"));
        stateText.setBounds(postcodeMPI.getX(), postcodeMPI.getY() + postcodeMPI.getHeight() + 20, postcodeMPI.getWidth(), postcodeMPI.getHeight());

        JLabel cityText = new JLabel("City");
        cityText.setFont(Asset.getBodyFont("Plain"));
        cityText.setBounds(maritalStatusText.getX(), postcodeText.getY(), postcodeMPI.getWidth(), postcodeMPI.getHeight());

        stateMPI = new Asset().generateTextField();
        stateMPI.setFont(Asset.getBodyFont("Plain"));
        stateMPI.setBounds(cityText.getX(), cityText.getY() + cityText.getHeight(), cityText.getWidth(), cityText.getHeight());

        cityMPI = new Asset().generateTextField();
        cityMPI.setFont(Asset.getBodyFont("Plain"));
        cityMPI.setBounds(stateText.getX(), stateText.getY() + stateText.getHeight(), stateText.getWidth(), stateText.getHeight());

        JLabel nationalityText = new JLabel("Nationality");
        nationalityText.setFont(Asset.getBodyFont("Plain"));
        nationalityText.setBounds(cityText.getX(), stateText.getY(), stateText.getWidth(), stateText.getHeight());

        nationalityMPI = new Asset().generateTextField();
        nationalityMPI.setFont(Asset.getBodyFont("Plain"));
        nationalityMPI.setBounds(nationalityText.getX(), nationalityText.getY() + nationalityText.getHeight(), cityMPI.getWidth(), cityMPI.getHeight());

        JLabel emailText = new JLabel("Email");
        emailText.setFont(Asset.getBodyFont("Plain"));
        emailText.setBounds(stateText.getX(), nationalityMPI.getY() + nationalityMPI.getHeight() + 20, stateMPI.getWidth(), stateMPI.getHeight());

        emailMPI = new Asset().generateTextField();
        emailMPI.setFont(Asset.getBodyFont("Plain"));
        emailMPI.setBounds(emailText.getX(), emailText.getY() + emailText.getHeight(), emailText.getWidth(), emailText.getHeight());

        JLabel contactNoText = new JLabel("Contact No");
        contactNoText.setFont(Asset.getBodyFont("Plain"));
        contactNoText.setBounds(nationalityText.getX(), emailText.getY(), emailText.getWidth(), emailText.getHeight());

        contactNoMPI = new Asset().generateTextField();
        contactNoMPI.setFont(Asset.getBodyFont("Plain"));
        contactNoMPI.setBounds(contactNoText.getX(), contactNoText.getY() + contactNoText.getHeight(), contactNoText.getWidth(), contactNoText.getHeight());

        if (manager != null) {
            nameMPI.setText(manager.name);
            genderChoiceMPI.setSelectedItem(manager.gender);
            maritalStatusChoiceMPI.setSelectedItem(manager.maritalStatus);
            addressLine1MPI.setText(manager.addressLine1);
            addressLine2MPI.setText(manager.addressLine2);
            addressLine3MPI.setText(manager.addressLine3);
            postcodeMPI.setText(manager.postcode);
            stateMPI.setText(manager.state);
            cityMPI.setText(manager.city);
            nationalityMPI.setText(manager.nationality);
            emailMPI.setText(manager.email);
            contactNoMPI.setText(manager.contactNumber);
        } else if (technician != null) {
            nameMPI.setText(technician.name);
            genderChoiceMPI.setSelectedItem(technician.gender);
            maritalStatusChoiceMPI.setSelectedItem(technician.maritalStatus);
            addressLine1MPI.setText(technician.addressLine1);
            addressLine2MPI.setText(technician.addressLine2);
            addressLine3MPI.setText(technician.addressLine3);
            postcodeMPI.setText(technician.postcode);
            stateMPI.setText(technician.state);
            cityMPI.setText(technician.city);
            nationalityMPI.setText(technician.nationality);
            emailMPI.setText(technician.email);
            contactNoMPI.setText(technician.contactNumber);
        }

        panel.add(title);
        panel.add(namePlaceholder);
        panel.add(nameMPI);
        panel.add(genderText);
        panel.add(genderChoiceMPI);
        panel.add(maritalStatusText);
        panel.add(maritalStatusChoiceMPI);
        panel.add(addressText);
        panel.add(addressLine1MPI);
        panel.add(addressLine2MPI);
        panel.add(addressLine3MPI);
        panel.add(postcodeText);
        panel.add(postcodeMPI);
        panel.add(stateText);
        panel.add(stateMPI);
        panel.add(cityText);
        panel.add(cityMPI);
        panel.add(nationalityText);
        panel.add(nationalityMPI);
        panel.add(emailText);
        panel.add(emailMPI);
        panel.add(contactNoText);
        panel.add(contactNoMPI);

        return panel;
    }

    public String getAddressLine1MPI() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.addressLine1MPI.getText() != null) {
            text = createAdminAccount.addressLine1MPI.getText();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.addressLine1MPI.getText() != null) {
            text = modifyOtherAdminAccount.addressLine1MPI.getText();
        } else if (modifyOwnAccount != null && modifyOwnAccount.addressLine1MPI.getText() != null) {
            text = modifyOwnAccount.addressLine1MPI.getText();
        } else if (technicianModifyOwn != null && technicianModifyOwn.addressLine1MPI.getText() != null) {
            text = technicianModifyOwn.addressLine1MPI.getText();
        }
        return text;
    }

    public String getAddressLine2MPI() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.addressLine2MPI.getText() != null) {
            text = createAdminAccount.addressLine2MPI.getText();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.addressLine2MPI.getText() != null) {
            text = modifyOtherAdminAccount.addressLine2MPI.getText();
        } else if (modifyOwnAccount != null && modifyOwnAccount.addressLine2MPI.getText() != null) {
            text = modifyOwnAccount.addressLine2MPI.getText();
        } else if (technicianModifyOwn != null && technicianModifyOwn.addressLine2MPI.getText() != null) {
            text = technicianModifyOwn.addressLine2MPI.getText();
        }
        return text;
    }

    public String getAddressLine3MPI() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.addressLine3MPI.getText() != null) {
            text = createAdminAccount.addressLine3MPI.getText();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.addressLine3MPI.getText() != null) {
            text = modifyOtherAdminAccount.addressLine3MPI.getText();
        } else if (modifyOwnAccount != null && modifyOwnAccount.addressLine3MPI.getText() != null) {
            text = modifyOwnAccount.addressLine3MPI.getText();
        } else if (technicianModifyOwn != null && technicianModifyOwn.addressLine3MPI.getText() != null) {
            text = technicianModifyOwn.addressLine3MPI.getText();
        }
        return text;
    }

    public String getPostcodeMPI() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.postcodeMPI.getText() != null) {
            text = createAdminAccount.postcodeMPI.getText();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.postcodeMPI.getText() != null) {
            text = modifyOtherAdminAccount.postcodeMPI.getText();
        } else if (modifyOwnAccount != null && modifyOwnAccount.postcodeMPI.getText() != null) {
            text = modifyOwnAccount.postcodeMPI.getText();
        } else if (technicianModifyOwn != null && technicianModifyOwn.postcodeMPI.getText() != null) {
            text = technicianModifyOwn.postcodeMPI.getText();
        }
        return text;
    }
    public String getStateMPI() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.stateMPI.getText() != null) {
            text = createAdminAccount.stateMPI.getText();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.stateMPI.getText() != null) {
            text = modifyOtherAdminAccount.stateMPI.getText();
        } else if (modifyOwnAccount != null && modifyOwnAccount.stateMPI.getText() != null) {
            text = modifyOwnAccount.stateMPI.getText();
        } else if (technicianModifyOwn != null && technicianModifyOwn.stateMPI.getText() != null) {
            text = technicianModifyOwn.stateMPI.getText();
        }
        return text;
    }

    public String getCityMPI() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.cityMPI.getText() != null) {
            text = createAdminAccount.cityMPI.getText();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.cityMPI.getText() != null) {
            text = modifyOtherAdminAccount.cityMPI.getText();
        } else if (modifyOwnAccount != null && modifyOwnAccount.cityMPI.getText() != null) {
            text = modifyOwnAccount.cityMPI.getText();
        } else if (technicianModifyOwn != null && technicianModifyOwn.cityMPI.getText() != null) {
            text = technicianModifyOwn.cityMPI.getText();
        }
        return text;
    }

    public String getNameMPI() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.nameMPI.getText() != null) {
            text = createAdminAccount.nameMPI.getText();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.nameMPI.getText() != null) {
            text = modifyOtherAdminAccount.nameMPI.getText();
        } else if (modifyOwnAccount != null && modifyOwnAccount.nameMPI.getText() != null) {
            text = modifyOwnAccount.nameMPI.getText();
        } else if (technicianModifyOwn != null && technicianModifyOwn.nameMPI.getText() != null) {
            text = technicianModifyOwn.nameMPI.getText();
        }
        return text;
    }
    public String getNationalityMPI() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.nationalityMPI.getText() != null) {
            text = createAdminAccount.nationalityMPI.getText();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.nationalityMPI.getText() != null) {
            text = modifyOtherAdminAccount.nationalityMPI.getText();
        } else if (modifyOwnAccount != null && modifyOwnAccount.nationalityMPI.getText() != null) {
            text = modifyOwnAccount.nationalityMPI.getText();
        } else if (technicianModifyOwn != null && technicianModifyOwn.nationalityMPI.getText() != null) {
            text = technicianModifyOwn.nationalityMPI.getText();
        }
        return text;
    }
    public String getEmailMPI() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.emailMPI.getText() != null) {
            text = createAdminAccount.emailMPI.getText();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.emailMPI.getText() != null) {
            text = modifyOtherAdminAccount.emailMPI.getText();
        } else if (modifyOwnAccount != null && modifyOwnAccount.emailMPI.getText() != null) {
            text = modifyOwnAccount.emailMPI.getText();
        } else if (technicianModifyOwn != null && technicianModifyOwn.emailMPI.getText() != null) {
            text = technicianModifyOwn.emailMPI.getText();
        }
        return text;
    }
    public String getContactNoMPI() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.contactNoMPI.getText() != null) {
            text = createAdminAccount.contactNoMPI.getText();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.contactNoMPI.getText() != null) {
            text = modifyOtherAdminAccount.contactNoMPI.getText();
        } else if (modifyOwnAccount != null && modifyOwnAccount.contactNoMPI.getText() != null) {
            text = modifyOwnAccount.contactNoMPI.getText();
        } else if (technicianModifyOwn != null && technicianModifyOwn.contactNoMPI.getText() != null) {
            text = technicianModifyOwn.contactNoMPI.getText();
        }
        return text;
    }
    public String getGenderMPI() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.genderChoiceMPI.getSelectedItem() != null) {
            text = Objects.requireNonNull(createAdminAccount.genderChoiceMPI.getSelectedItem()).toString();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.genderChoiceMPI.getSelectedItem() != null) {
            text = Objects.requireNonNull(modifyOtherAdminAccount.genderChoiceMPI.getSelectedItem()).toString();
        } else if (modifyOwnAccount != null && modifyOwnAccount.genderChoiceMPI.getSelectedItem() != null) {
            text = Objects.requireNonNull(modifyOwnAccount.genderChoiceMPI.getSelectedItem()).toString();
        } else if (technicianModifyOwn != null && technicianModifyOwn.genderChoiceMPI.getSelectedItem() != null) {
            text = Objects.requireNonNull(technicianModifyOwn.genderChoiceMPI.getSelectedItem()).toString();
        }
        return text;
    }

    public String getMaritalStatusMPI() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.maritalStatusChoiceMPI.getSelectedItem() != null) {
            text = Objects.requireNonNull(createAdminAccount.maritalStatusChoiceMPI.getSelectedItem()).toString();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.maritalStatusChoiceMPI.getSelectedItem() != null) {
            text = Objects.requireNonNull(modifyOtherAdminAccount.maritalStatusChoiceMPI.getSelectedItem()).toString();
        } else if (modifyOwnAccount != null && modifyOwnAccount.maritalStatusChoiceMPI.getSelectedItem() != null) {
            text = Objects.requireNonNull(modifyOwnAccount.maritalStatusChoiceMPI.getSelectedItem()).toString();
        } else if (technicianModifyOwn != null && technicianModifyOwn.maritalStatusChoiceMPI.getSelectedItem() != null) {
            text = Objects.requireNonNull(technicianModifyOwn.maritalStatusChoiceMPI.getSelectedItem()).toString();
        }
        return text;
    }


    JComboBox<String> positionMPI = new JComboBox<>();
    JDatePickerImpl datePickerMPI;
    public JPanel jobDetails(Manager manager, Technician technician) {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setSize(scrollPaneWidth, 150);

        JLabel title = new JLabel("<html>Job<br>Details</html>");
        title.setFont(Asset.getNameFont("Bold"));
        title.setBounds(30, 0, panel.getWidth() / 5, 100);

        JLabel positionText = new JLabel("Position");
        positionText.setFont(Asset.getBodyFont("Plain"));
        positionText.setBounds(title.getWidth() + title.getX(), title.getY() + 10, (panel.getWidth() - title.getWidth() - title.getX()) / 2 - 20, 50);

        positionMPI.setFont(Asset.getBodyFont("Plain"));
        positionMPI.setBounds(positionText.getX(), positionText.getY() + positionText.getHeight(), positionText.getWidth(), positionText.getHeight());
        positionMPI.setBackground(Color.WHITE);

        updatePositionCombo(Objects.requireNonNull(accountType.getSelectedItem()).toString());

        JLabel dateJoined = new JLabel("Date Joined");
        dateJoined.setFont(Asset.getBodyFont("Plain"));
        dateJoined.setBounds(positionText.getX() + positionText.getWidth() + 20, positionText.getY(), positionText.getWidth(), positionText.getHeight());

        UtilDateModel model = new UtilDateModel();

        JDatePanelImpl datePanel = new JDatePanelImpl(model, new Properties());
        datePickerMPI = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        datePanel.setFont(Asset.getBodyFont("Plain"));
        datePickerMPI.setBackground(Color.WHITE);
        datePickerMPI.setLocation(dateJoined.getX(), dateJoined.getY() + dateJoined.getHeight());
        datePickerMPI.setSize(new Dimension(dateJoined.getWidth(), dateJoined.getHeight()));
        datePickerMPI.getComponent(0).setPreferredSize(new Dimension(dateJoined.getWidth() - dateJoined.getHeight(),dateJoined.getHeight()));
        datePickerMPI.getComponent(1).setPreferredSize(new Dimension(dateJoined.getHeight(),dateJoined.getHeight()));
        datePickerMPI.getJFormattedTextField().setFont(Asset.getBodyFont("Plain"));
        datePickerMPI.getJFormattedTextField().setBackground(Color.WHITE);
        datePickerMPI.getComponent(1).setBackground(Color.WHITE);

        if (manager != null) {
            positionMPI.setSelectedItem(manager.position);
            model.setDate(manager.dateJoined.getYear(), manager.dateJoined.getMonthValue() - 1, manager.dateJoined.getDayOfMonth());
            model.setSelected(true);
        } else if (technician != null) {
            positionMPI.setSelectedItem(technician.position);
            model.setDate(technician.dateJoined.getYear(), technician.dateJoined.getMonthValue() - 1, technician.dateJoined.getDayOfMonth());
            model.setSelected(true);
        }

        panel.add(title);
        panel.add(positionText);
        panel.add(positionMPI);
        panel.add(dateJoined);
        panel.add(datePickerMPI);
        return panel;
    }

    public String getPositionMPI() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.positionMPI.getSelectedItem() != null) {
            text = Objects.requireNonNull(createAdminAccount.positionMPI.getSelectedItem()).toString();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.positionMPI.getSelectedItem() != null) {
            text = Objects.requireNonNull(modifyOtherAdminAccount.positionMPI.getSelectedItem()).toString();
        } else if (modifyOwnAccount != null && modifyOwnAccount.positionMPI.getSelectedItem() != null) {
            text = Objects.requireNonNull(modifyOwnAccount.positionMPI.getSelectedItem()).toString();
        } else if (technicianModifyOwn != null && technicianModifyOwn.positionMPI.getSelectedItem() != null) {
            text = Objects.requireNonNull(technicianModifyOwn.positionMPI.getSelectedItem()).toString();
        }
        return text;
    }

    public LocalDate getDateJoinedMPI() {
        LocalDate text = null;
        if (createAdminAccount != null && createAdminAccount.datePickerMPI.getModel() != null) {
            text = LocalDate.of(createAdminAccount.datePickerMPI.getModel().getYear(), createAdminAccount.datePickerMPI.getModel().getMonth() + 1, createAdminAccount.datePickerMPI.getModel().getDay());
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.datePickerMPI.getModel() != null) {
            text = LocalDate.of(modifyOtherAdminAccount.datePickerMPI.getModel().getYear(), modifyOtherAdminAccount.datePickerMPI.getModel().getMonth() + 1, modifyOtherAdminAccount.datePickerMPI.getModel().getDay());
        } else if (modifyOwnAccount != null && modifyOwnAccount.datePickerMPI.getModel() != null) {
            text = LocalDate.of(modifyOwnAccount.datePickerMPI.getModel().getYear(), modifyOwnAccount.datePickerMPI.getModel().getMonth() + 1, modifyOwnAccount.datePickerMPI.getModel().getDay());
        } else if (technicianModifyOwn != null && technicianModifyOwn.datePickerMPI.getModel() != null) {
            text = LocalDate.of(technicianModifyOwn.datePickerMPI.getModel().getYear(), technicianModifyOwn.datePickerMPI.getModel().getMonth() + 1, technicianModifyOwn.datePickerMPI.getModel().getDay());
        }
        return text;
    }

    public void updatePositionCombo(String position) {

        System.out.println(position);

        positionMPI.removeAllItems();

        positionChoice.clear();
        if (position.equals("Manager")) {
            positionChoice.addAll(Arrays.asList(Manager.getManagerPosition()));
        } else if (position.equals("Technician")) {
            positionChoice.addAll(Arrays.asList(Technician.getTechnicianPosition()));
        }

        for (String positionName: positionChoice) {
            positionMPI.addItem(positionName);
        }

    }

    JPasswordField passwordMPI, reEnterPasswordMPI;
    public JPanel loginDetails() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setSize(scrollPaneWidth, 290);

        JLabel title = new JLabel("<html>Login<br>Details</html>");
        title.setFont(Asset.getNameFont("Bold"));
        title.setBounds(30, 0, panel.getWidth() / 5, 100);

        JLabel passwordText = new JLabel("Password");
        passwordText.setFont(Asset.getBodyFont("Plain"));
        passwordText.setBounds(title.getWidth() + title.getX(), title.getY() + 10, (panel.getWidth() - title.getWidth() - title.getX()) / 2 - 20, 50);

        passwordMPI = new JPasswordField();
        passwordMPI.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(5, 15, 5, 5)));
        passwordMPI.setFont(Asset.getBodyFont("Plain"));
        passwordMPI.setBounds(passwordText.getX(), passwordText.getY() + passwordText.getHeight(), passwordText.getWidth(), passwordText.getHeight());

        JLabel reEnterPasswordText = new JLabel("Re-enter Password");
        reEnterPasswordText.setFont(Asset.getBodyFont("Plain"));
        reEnterPasswordText.setBounds(passwordMPI.getX(), passwordMPI.getY() + passwordMPI.getHeight() + 49, passwordMPI.getWidth(), passwordMPI.getHeight());

        reEnterPasswordMPI = new JPasswordField();
        reEnterPasswordMPI.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(5, 15, 5, 5)));
        reEnterPasswordMPI.setFont(Asset.getBodyFont("Plain"));
        reEnterPasswordMPI.setBounds(reEnterPasswordText.getX(), reEnterPasswordText.getY() + reEnterPasswordText.getHeight(), reEnterPasswordText.getWidth(), reEnterPasswordText.getHeight());

        JLabel note = new JLabel("<html>Note:<br><br>" +
                "The password must fulfil all requirements:<br><br>" +
                "• Consists between 4 to 20 characters<br><br>" +
                "• Consists at least a lower case and <br><br>" +
                "&nbsp; upper case alphabet<br><br>" +
                "• Consists at least a digit</html>");
        note.setFont(new Font("Arial", Font.PLAIN, 15));
        note.setBackground(Color.BLACK);
        note.setForeground(Color.WHITE);
        note.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        note.setBounds(passwordText.getX() + 10 + passwordText.getWidth() + 20, passwordText.getY() + 10, passwordText.getWidth(), panel.getHeight() - 50);
        note.setOpaque(true);

        panel.add(title);
        panel.add(passwordText);
        panel.add(passwordMPI);
        panel.add(reEnterPasswordText);
        panel.add(reEnterPasswordMPI);
        panel.add(note);

        return panel;
    }

    public String getPasswordMPI() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.passwordMPI.getPassword() != null) {
            StringBuilder password = new StringBuilder();
            for (char character: createAdminAccount.passwordMPI.getPassword()) {
                password.append(character);
            }
            text = password.toString();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.passwordMPI.getPassword() != null) {
            StringBuilder password = new StringBuilder();
            for (char character: modifyOtherAdminAccount.passwordMPI.getPassword()) {
                password.append(character);
            }
            text = password.toString();
        } else if (modifyOwnAccount != null && modifyOwnAccount.passwordMPI.getPassword() != null) {
            StringBuilder password = new StringBuilder();
            for (char character: modifyOwnAccount.passwordMPI.getPassword()) {
                password.append(character);
            }
            text = password.toString();
        } else if (technicianModifyOwn != null && technicianModifyOwn.passwordMPI.getPassword() != null) {
            StringBuilder password = new StringBuilder();
            for (char character: technicianModifyOwn.passwordMPI.getPassword()) {
                password.append(character);
            }
            text = password.toString();
        }
        return text;
    }

    public String getReEnterPasswordMPI() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.reEnterPasswordMPI.getPassword() != null) {
            StringBuilder password = new StringBuilder();
            for (char character: createAdminAccount.reEnterPasswordMPI.getPassword()) {
                password.append(character);
            }
            text = password.toString();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.reEnterPasswordMPI.getPassword() != null) {
            StringBuilder password = new StringBuilder();
            for (char character: modifyOtherAdminAccount.reEnterPasswordMPI.getPassword()) {
                password.append(character);
            }
            text = password.toString();
        } else if (modifyOwnAccount != null && modifyOwnAccount.reEnterPasswordMPI.getPassword() != null) {
            StringBuilder password = new StringBuilder();
            for (char character: modifyOwnAccount.reEnterPasswordMPI.getPassword()) {
                password.append(character);
            }
            text = password.toString();
        } else if (technicianModifyOwn != null && technicianModifyOwn.reEnterPasswordMPI.getPassword() != null) {
            StringBuilder password = new StringBuilder();
            for (char character: technicianModifyOwn.reEnterPasswordMPI.getPassword()) {
                password.append(character);
            }
            text = password.toString();
        }
        return text;
    }

    JTextField nameCPI, tpNumberCPI, contactNoCPI, emailCPI;
    JComboBox<String> genderCPI;
    public JPanel customerCreatePersonalInformation() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setSize(scrollPaneWidth, 290);

        JLabel title = new JLabel("<html>Personal<br>Information</html>");
        title.setFont(Asset.getNameFont("Bold"));
        title.setBounds(30, 0, panel.getWidth() / 5, 100);

        JLabel nameText = new JLabel("Name");
        nameText.setFont(Asset.getBodyFont("Plain"));
        nameText.setBounds(title.getWidth() + title.getX(), title.getY() + 10, panel.getWidth() - title.getWidth() - title.getX() - 20, 50);

        nameCPI = new Asset().generateTextField();
        nameCPI.setFont(Asset.getBodyFont("Plain"));
        nameCPI.setBounds(nameText.getX(), nameText.getY() + nameText.getHeight(), nameText.getWidth(), nameText.getHeight());

        JLabel tpNumberText = new JLabel("TP Number");
        tpNumberText.setFont(Asset.getBodyFont("Plain"));
        tpNumberText.setBounds(nameCPI.getX(), nameCPI.getY() + nameCPI.getHeight() + 20, nameCPI.getWidth() / 3 * 2 - 10, nameCPI.getHeight());

        tpNumberCPI = new Asset().generateTextField();
        tpNumberCPI.setFont(Asset.getBodyFont("Plain"));
        tpNumberCPI.setBounds(tpNumberText.getX(), tpNumberText.getY() + tpNumberText.getHeight(), tpNumberText.getWidth(), tpNumberText.getHeight());

        JLabel genderText = new JLabel("Gender");
        genderText.setFont(Asset.getBodyFont("Plain"));
        genderText.setBounds(tpNumberText.getX() + tpNumberText.getWidth() + 20, tpNumberText.getY(), nameCPI.getWidth() / 3 - 10, tpNumberText.getHeight());

        String[] genderChoice = User.getGenderTypes();
        genderCPI = new JComboBox<>(genderChoice);
        genderCPI.setFont(Asset.getBodyFont("Plain"));
        genderCPI.setBounds(genderText.getX(), genderText.getY() + genderText.getHeight(), genderText.getWidth(), genderText.getHeight());
        genderCPI.setBackground(Color.WHITE);

        JLabel emailText = new JLabel("Email");
        emailText.setFont(Asset.getBodyFont("Plain"));
        emailText.setBounds(tpNumberText.getX(), tpNumberCPI.getY() + tpNumberCPI.getHeight() + 20, tpNumberText.getWidth(), tpNumberText.getHeight());

        emailCPI = new Asset().generateTextField();
        emailCPI.setBounds(emailText.getX(), emailText.getY() + emailText.getHeight(), emailText.getWidth(), emailText.getHeight());

        JLabel contactNoText = new JLabel("Contact No");
        contactNoText.setFont(Asset.getBodyFont("Plain"));
        contactNoText.setBounds(genderCPI.getX(), genderCPI.getY() + genderCPI.getHeight() + 20, genderCPI.getWidth(), genderCPI.getHeight());

        contactNoCPI = new Asset().generateTextField();
        contactNoCPI.setFont(Asset.getBodyFont("Plain"));
        contactNoCPI.setBounds(contactNoText.getX(), contactNoText.getY() + contactNoText.getHeight(), contactNoText.getWidth(), contactNoText.getHeight());

        panel.add(title);
        panel.add(nameText);
        panel.add(nameCPI);
        panel.add(tpNumberText);
        panel.add(tpNumberCPI);
        panel.add(genderText);
        panel.add(genderCPI);
        panel.add(emailText);
        panel.add(emailCPI);
        panel.add(contactNoText);
        panel.add(contactNoCPI);

        return panel;
    }

    public String getNameCPI() {
        return nameCPI.getText();
    }

    public String getTpNumberCPI() {
        return tpNumberCPI.getText();
    }

    public String getContactNoCPI() {
        return contactNoCPI.getText();
    }

    public String getGenderCPI() {
        return Objects.requireNonNull(genderCPI.getSelectedItem()).toString();
    }

    public String getEmailCPI() {
        return emailCPI.getText();
    }

    JTextField nameSPI, emailSPI, contactNoSPI;
    JComboBox<String> genderSPI;
    public JPanel customerModifyPersonalInformation(Student student) {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setSize(scrollPaneWidth, 290);

        JLabel title = new JLabel("<html>Personal<br>Information</html>");
        title.setFont(Asset.getNameFont("Bold"));
        title.setBounds(30, 0, panel.getWidth() / 5, 100);

        JLabel nameText = new JLabel("Name");
        nameText.setFont(Asset.getBodyFont("Plain"));
        nameText.setBounds(title.getWidth() + title.getX(), title.getY() + 10, (panel.getWidth() - title.getWidth() - title.getX()) * 2 / 3 - 10 , 50);

        nameSPI = new Asset().generateTextField();
        nameSPI.setText(student.name);
        nameSPI.setFont(Asset.getBodyFont("Plain"));
        nameSPI.setBounds(nameText.getX(), nameText.getY() + nameText.getHeight(), nameText.getWidth(), nameText.getHeight());

        JLabel genderText = new JLabel("Gender");
        genderText.setFont(Asset.getBodyFont("Plain"));
        genderText.setBounds(nameText.getX() + nameText.getWidth() + 20, nameText.getY(), nameText.getWidth() / 2, nameText.getHeight());

        String[] genderChoice = User.getGenderTypes();
        genderSPI = new JComboBox<>(genderChoice);
        genderSPI.setSelectedItem(student.gender);
        genderSPI.setFont(Asset.getBodyFont("Plain"));
        genderSPI.setBounds(genderText.getX(), genderText.getY() + genderText.getHeight(), genderText.getWidth(), genderText.getHeight());
        genderSPI.setBackground(Color.WHITE);

        JLabel emailText = new JLabel("Email");
        emailText.setFont(Asset.getBodyFont("Plain"));
        emailText.setBounds(nameText.getX(), nameSPI.getY() + nameSPI.getHeight() + 20, nameText.getWidth(), nameText.getHeight());

        emailSPI = new Asset().generateTextField();
        emailSPI.setText(student.email);
        emailSPI.setBounds(emailText.getX(), emailText.getY() + emailText.getHeight(), emailText.getWidth(), emailText.getHeight());

        JLabel contactNoText = new JLabel("Contact No");
        contactNoText.setFont(Asset.getBodyFont("Plain"));
        contactNoText.setBounds(genderSPI.getX(), genderSPI.getY() + genderSPI.getHeight() + 20, genderSPI.getWidth(), genderSPI.getHeight());

        contactNoSPI = new Asset().generateTextField();
        contactNoSPI.setText(student.contactNumber);
        contactNoSPI.setFont(Asset.getBodyFont("Plain"));
        contactNoSPI.setBounds(contactNoText.getX(), contactNoText.getY() + contactNoText.getHeight(), contactNoText.getWidth(), contactNoText.getHeight());

        panel.add(title);
        panel.add(nameText);
        panel.add(nameSPI);
        panel.add(genderText);
        panel.add(genderSPI);
        panel.add(emailText);
        panel.add(emailSPI);
        panel.add(contactNoText);
        panel.add(contactNoSPI);

        return panel;
    }
    //JTextField nameSPI, emailSPI, contactNoSPI;
    //    JComboBox<String> genderSPI;

    public String getNameSPI() {
        return nameSPI.getText();
    }

    public String getEmailSPI() {
        return emailSPI.getText();
    }

    public String getContactNoSPI() {
        return contactNoSPI.getText();
    }

    public String getGenderSPI() {
        return Objects.requireNonNull(genderSPI.getSelectedItem()).toString();
    }

    JComboBox<String> accountType;

    public JPanel accountDetails(Manager manager, Technician technician) {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setSize(scrollPaneWidth, 150);

        JLabel title = new JLabel("<html>Account<br>Details</html>");
        title.setFont(Asset.getNameFont("Bold"));
        title.setBounds(30, 0, panel.getWidth() / 5, 100);

        JLabel accountText = new JLabel("Account type");
        accountText.setFont(Asset.getBodyFont("Plain"));
        accountText.setBounds(title.getWidth() + title.getX(), title.getY() + 10, (panel.getWidth() - title.getWidth() - title.getX()) / 2 - 20, 50);

        String[] accountChoice = {"Manager", "Technician"};
        accountType = new JComboBox<>(accountChoice);
        accountType.setFont(Asset.getBodyFont("Plain"));
        accountType.setBounds(accountText.getX(), accountText.getY() + accountText.getHeight(), accountText.getWidth(), accountText.getHeight());
        accountType.setBackground(Color.WHITE);

        if (manager != null) {
            accountType.setSelectedItem("Manager");
        } else if (technician != null) {
            accountType.setSelectedItem("Technician");
        }

        JLayeredPane confirm = new Asset().generateButtonWithoutImage("Confirm", accountType.getWidth(), accountType.getHeight());
        confirm.setLocation(accountType.getX() + accountType.getWidth() + 20, accountType.getY());

        confirm.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                String type = Objects.requireNonNull(accountType.getSelectedItem()).toString();
                if (type.equals("Manager")) {
                    updatePositionCombo("Manager");
                } else if (type.equals("Technician")) {
                    updatePositionCombo("Technician");
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        panel.add(title);
        panel.add(accountText);
        panel.add(accountType);
        panel.add(confirm);

        return panel;
    }

    public String getAccountType() {
        String text = null;
        if (createAdminAccount != null && createAdminAccount.accountType.getSelectedItem() != null) {
            text = Objects.requireNonNull(createAdminAccount.accountType.getSelectedItem()).toString();
        } else if (modifyOtherAdminAccount != null && modifyOtherAdminAccount.accountType.getSelectedItem() != null) {
            text = Objects.requireNonNull(modifyOtherAdminAccount.accountType.getSelectedItem()).toString();
        } else if (modifyOwnAccount != null && modifyOwnAccount.accountType.getSelectedItem() != null) {
            text = Objects.requireNonNull(modifyOwnAccount.accountType.getSelectedItem()).toString();
        } else if (technicianModifyOwn != null && technicianModifyOwn.accountType.getSelectedItem() != null) {
            text = Objects.requireNonNull(technicianModifyOwn.accountType.getSelectedItem()).toString();
        }
        return text;
    }

    // Line pane
    public JPanel divider() {
        JPanel panel = new Asset().drawLine(0, 0, 2000, 0, 1);
        panel.setBackground(Asset.getTransparentColour());
        return panel;
    }

}
