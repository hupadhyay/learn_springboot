package in.himtech.boot.learn.service;

import org.springframework.stereotype.Service;

import in.himtech.boot.learn.model.Student;

@Service
public class StudentService {

	public Student getStudent() {
		return new Student(101, "Himanshu", "lucknow");
	}

}
