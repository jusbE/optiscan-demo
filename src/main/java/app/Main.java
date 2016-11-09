package app;
import java.sql.Date;
import java.time.Instant;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.database.PostgreUtil;
import app.models.EGender;
import app.models.UserForm;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Main.class, args);
		PostgreUtil util = PostgreUtil.getInstance();
		util.initializeUsersTable();

		UserForm testForm = new UserForm();
		testForm.setFirstName("etunimi");
		testForm.setLastName("sukunimi");
		testForm.setDateOfBirth(Date.from(Instant.now()));
		testForm.setGender(EGender.MALE);
		testForm.setWhyApplying("because");

		util.addUser(testForm);

		List<UserForm> users = util.getUsers();
		System.out.println(users.size());
	}

}
