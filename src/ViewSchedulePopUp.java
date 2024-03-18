import javax.swing.*;
import java.util.Date;

public class ViewSchedulePopUp extends JFrame {
    JFrame frame;
    public ViewSchedulePopUp(String technicianName, String date) {

        System.out.println("Hello");

        frame = new JFrame("View Schedule");
        frame.setSize(1500, 1000);
        frame.setLayout(null);
        JLayeredPane referencePane = new JLayeredPane();
        referencePane.setSize(frame.getWidth(), frame.getHeight());
        ScheduleView schedule = new ScheduleView(referencePane, technicianName, date);

        schedule.setBounds(30, 30, frame.getWidth() - 60, frame.getHeight() - 60);
        frame.add(schedule);

        frame.setVisible(true);

    }

}
