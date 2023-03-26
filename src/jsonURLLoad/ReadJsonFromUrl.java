package jsonURLLoad;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ReadJsonFromUrl implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

@Autowired
Student id;

@Autowired
Course courseNo;

	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext context = SpringApplication.run(ReadJsonFromUrl.class, args);

		String url = "https://hccs-advancejava.s3.amazonaws.com/student_course.json";

		RestTemplate restTemplate = new RestTemplate();
		String jsonString = restTemplate.getForObject(url, String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		List<Student> students = objectMapper.readValue(jsonString, TypeFactory.defaultInstance().constructCollectionType(List.class, Student.class));

		/*
		 I tried all these combinations are TONS more and could not get the courses to map!!!
		 List<Course> courses = objectMapper.readValue(json, new TypeReference<List<Student>>() {}
		 List<Course> courses = objectMapper.readValue(json, new TypeReference<List<Course>>() {}
		 TypeReference<List<Course>> () {});
		 List<Course> courses = (List<Course>) objectMapper.readValue(json, Course.class);
		 Course[] courses = objectMapper.readValue(json, Course[].class);
		*/

		// I found that I was missing the @JsonProperty on the students "Courses"
		// Just to make this work, I manually added the fields to the courses objects
		//courseSetter(jsonString, students, courses);

		try (Scanner userInput = new Scanner(System.in)) {
			System.out.println("Student + Course Number Search System\n");
			System.out.println("Enter Student name to search for: ");
			String studentName = userInput.nextLine();
			System.out.println("Enter Course Number to search for: ");
			String courseNumber = userInput.nextLine();

			courseSearch(students, studentName, courseNumber);

		}

	}

	private static void courseSearch(List<Student> students, String studentName,
			String courseNumber) {
		int gpa = 0;
		String gradeLetter = "";
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getFirst_name().equalsIgnoreCase(studentName)) {
				System.out.print("FOUND: " + students.get(i).getFirst_name()
						+ " with a " + courseNumber + " gpa of ");
				for (Course cours : students.get(i).getCourses()) {
					String grade = cours.getGrade();
					if(cours.getCourseNo() == null) { break; }
					if (cours.getCourseNo().equalsIgnoreCase(courseNumber)) {
						gradeLetter = cours.getGrade();
						if (gradeLetter.equalsIgnoreCase("A")) {
							gpa = 4;
							break;
						}
						if (gradeLetter.equalsIgnoreCase("B")) {
							gpa = 3;
							break;
						}
						if (gradeLetter.equalsIgnoreCase("C")) {
							gpa = 2;
							break;
						}
						if (gradeLetter.equalsIgnoreCase("D")) {
							gpa = 1;
							break;
						}

					} else {
						gpa = 0;
					}

				}
				
			}

		}
		if(gpa == 0) {
			System.out.print("null\n");
		} else {
			System.out.print(gpa + "\n");
		}
		
	}
/*
	private static <Students> void courseSetter(String json, List<Students> students, List<Course> courses) {

		Course student11 = new Course(1, "", "", "");
		Course student12 = new Course(1, "", "", "");
		Course student21 = new Course(2, "CS", "B", "4");
		Course student22 = new Course(2, "CS12", "A", "4");
		Course student31 = new Course(3, "BS", "B", "3");
		Course student32 = new Course(3, "Hist", "A", "3");
		Course student41 = new Course(4, "ENG", "A", "4");
		Course student42 = new Course(4, "CS12", "A", "4");

		courses.add(student11);
		courses.add(student12);

		courses.add(student21);
		courses.add(student22);

		courses.add(student31);
		courses.add(student32);

		courses.add(student41);
		courses.add(student42);

		System.out.println(courses.toString());

	}
*/

}
