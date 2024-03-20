import javax.swing.*;
import java.awt.*;

public class ViewFeedbackComponent {

    public JPanel managerOverallReview() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel systemPanel = generateSystemPanel();
        systemPanel.setPreferredSize(new Dimension(446, 220));
        systemPanel.setBackground(Color.BLUE);

        JPanel technicianPanel = generateTechnicianPanel();
        technicianPanel.setPreferredSize(new Dimension(446, 220));
        technicianPanel.setBackground(Color.RED);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(446, 30));

        panel.add(systemPanel);
        panel.add(emptyPanel);
        panel.add(technicianPanel);
        
        return panel;

    }

    public JPanel generateSystemPanel() {

        JPanel panel = new JPanel();
        return panel;

    }

    public JPanel generateTechnicianPanel() {

        JPanel panel = new JPanel();
        return panel;

    }

}
