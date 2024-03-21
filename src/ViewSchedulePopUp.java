import javax.swing.*;
import java.awt.*;

public class ViewSchedulePopUp extends JFrame {
    JFrame frame;
    public ViewSchedulePopUp(String technicianName, String date) {

        frame = new JFrame("View Schedule");
        frame.setSize(1100, 700);
        frame.setBackground(Color.WHITE);
        frame.setLayout(null);
        JLayeredPane referencePane = new JLayeredPane();
        referencePane.setSize(frame.getWidth() - 100, frame.getHeight() - 100);
        ViewScheduleComponent schedule = new ViewScheduleComponent(referencePane, "T001", date);

        schedule.setBounds(50, 30, frame.getWidth() - 200, frame.getHeight() - 200);
        frame.add(schedule);

        frame.setVisible(true);

    }

}
