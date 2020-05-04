package in.himtech.boot.learn.model;

public class Student {

	private String location;
	private String name;
	private int id;

	public Student(int id, String name, String location) {
		this.id = id;
		this.name = name;
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Student [location=" + location + ", name=" + name + ", id=" + id + "]";
	}
}
