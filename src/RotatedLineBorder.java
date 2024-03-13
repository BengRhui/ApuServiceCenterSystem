import javax.swing.border.Border;
import java.awt.*;

class RotatedLineBorder extends LineBorder {
    private final double rotationAngle; // Rotation angle in degrees

    public RotatedLineBorder(Color color, double rotationAngle) {
        super(color);
        this.rotationAngle = rotationAngle;
    }
