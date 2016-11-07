package optiscan.models;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserForm {
	private static final Date FIFTEEN_YEARS_AGO = Date.from(Instant.now().minus(Duration.of(15, ChronoUnit.YEARS)));
	private static final Date HUNDRED_YEARS_AGO = Date.from(Instant.now().minus(Duration.of(100, ChronoUnit.YEARS)));

	@NotNull()
	@Size(max=50)
	private String firstName;

	@NotNull()
	@Size(max=50)
	private String lastName;

	@NotNull()
	private Date dateOfBirth;

	@NotNull()
	private EGender gender;

	@NotNull()
	@Size(max=1000)
	private String whyApplying;

	@AssertTrue()
	public boolean isAgeAccepted(){
		return dateOfBirth.before(FIFTEEN_YEARS_AGO) && dateOfBirth.after(HUNDRED_YEARS_AGO);
	}

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
