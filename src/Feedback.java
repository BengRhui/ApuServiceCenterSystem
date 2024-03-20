import java.util.ArrayList;

public class Feedback extends Appointment {

    int systemRating, technicianRating;
    String comment;

    private static ArrayList<Feedback> overallFeedbackList = new ArrayList<>();
    public Feedback(String appointmentID, String technicianID, String studentTP, String item, String date, String startingTime, String endingTime, int price, String status, int systemRating, int technicianRating, String comment) {
        super(appointmentID, technicianID, studentTP, item, date, startingTime, endingTime, price, status);
        this.systemRating = systemRating;
        this.technicianRating = technicianRating;
        this.comment = comment;
    }

    public static ArrayList<Feedback> getOverallFeedbackList() {
        return overallFeedbackList;
    }

    public static ArrayList<Feedback> getFilteredFeedbackList(String tpNumber) {
        ArrayList<Feedback> filter = new ArrayList<>();
        for (Feedback feedback: overallFeedbackList) {
            if (feedback.studentTP.equals(tpNumber)) {
                filter.add(feedback);
            }
        }
        return filter;
    }

}
