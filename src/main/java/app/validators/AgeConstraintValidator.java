package app.validators;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeConstraintValidator implements ConstraintValidator<Age, Date>{

	private int min;
	private int max;

	@Override
	public void initialize(Age constraintAnnotation) {
		min = constraintAnnotation.min();
		max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(Date dateOfBirth, ConstraintValidatorContext context) {
		if(dateOfBirth == null){
			return false;
		}
		LocalDate birthDay = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int age = Period.between(birthDay, LocalDate.now()).getYears();
		return min <= age && age <= max;
	}

}
