package jsonURLLoad;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Course {

    private int id;
    private String courseNo;
    private String courseName;
    private String grade;
    private String creditHours;
    private List<Student> students;

    public Course() {
    }

    public Course(int id, String courseNo, String grade, String creditHours) {
        this.id = id;
        this.courseNo = courseNo;
        //this.courseName = courseName;
        this.grade = grade;
        this.creditHours = creditHours;
        //this.students = new ArrayList<Student>();
    }

    public Course(String courseNo, String grade, String creditHours) {
        this.courseNo = courseNo;
        this.grade = grade;
        this.creditHours = creditHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseNo() { return courseNo; }

    public void setCourseNo(String courseNo) { this.courseNo = courseNo; }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGrade() { return grade; }

    public void setGrade(String grade) { this.grade = grade; }

    public String getCreditHours() { return creditHours; }

    public void setCreditHours(String creditHours) { this.creditHours = creditHours; }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseNo='" + courseNo + '\'' +
                ", courseName='" + courseName + '\'' +
                ", grade = '" + grade + '\'' +
                ", creditHours = '" + creditHours + '\'' +
                ", students = " + students +
                '}';
    }

}