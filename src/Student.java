import java.util.ArrayList;

public class Student extends User {
    String tpNumber, name, gender, email;
    int contactNumber;

    private static final ArrayList<Student> overallStudentList = new ArrayList<>();

    public Student(String tpNumber, String name, String gender, String email, String contactNumber) {
        this.tpNumber = tpNumber;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.contactNumber = Integer.parseInt(contactNumber);

        overallStudentList.add(this);
    }

    public static ArrayList<Student> getOverallStudentList() {
        return overallStudentList;
    }

    public Student searchByTP(String tpNumber) {
        Student found = null;
        for (Student student: overallStudentList) {
            if (student.tpNumber.equals(tpNumber)) {
                found = student;
            }
        }
        return found;
    }

}
