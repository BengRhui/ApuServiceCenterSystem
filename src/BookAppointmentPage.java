import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class BookAppointmentPage implements ComponentListener {
    public static void main(String[] args) {
        new BookAppointmentPage();
    }

    static JFrame frame;
    JLabel backgroundPicture;

    public BookAppointmentPage() {
        frame = new JFrame("Appointment Booking Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(Asset.getFrameWidth(), Asset.getFrameHeight());
        frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());
        frame.addComponentListener(this);

        backgroundPicture = new Asset().generateImage("4689.jpg");


        frame.add(backgroundPicture);
        frame.setVisible(true);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        backgroundPicture.setBounds(0, 0, frame.getWidth(), frame.getHeight());
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
}
