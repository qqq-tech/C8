package example;

import java.util.Comparator;

public class sort {
	class Student {
		int rollno;
		String name, address;

		// Constructor
		public Student(int rollno, String name, String address) {
			this.rollno = rollno;
			this.name = name;
			this.address = address;
		}

		// Used to print student details in main()
		public String toString() {
			return this.rollno + " " + this.name + " " + this.address;
		}
	}

	class Sortbyroll implements Comparator<Student> {
		// Used for sorting in ascending order of
		// roll number
		public int compare(Student a, Student b) {
			return a.rollno - b.rollno;
		}
	}

}