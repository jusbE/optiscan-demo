package app.models;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import app.validators.Age;

public class UserForm {

	@NotEmpty
	@Size(max=50)
	private String firstName;

	@NotEmpty
	@Size(max=50)
	private String lastName;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Age(min=15, max=100, message="Age must be between 15 and 100")
	private Date dateOfBirth;

	@NotNull()
	private EGender gender;

	@Size(max=1000)
	private String whyApplying;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public EGender getGender() {
		return gender;
	}

	public void setGender(EGender gender) {
		this.gender = gender;
	}

	public String getWhyApplying() {
		return whyApplying;
	}

	public void setWhyApplying(String whyApplying) {
		this.whyApplying = whyApplying;
	}

}
