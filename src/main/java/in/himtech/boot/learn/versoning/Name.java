package in.himtech.boot.learn.versoning;

public class Name {
	private String fisrtName;

	private String lastName;

	public Name(String fisrtName, String lastName) {
		super();
		this.fisrtName = fisrtName;
		this.lastName = lastName;
	}

	public String getFisrtName() {
		return fisrtName;
	}

	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Name [fisrtName=" + fisrtName + ", lastName=" + lastName + "]";
	}

}
