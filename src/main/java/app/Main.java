package app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import app.database.PostgreUtil;

@SpringBootApplication
public class Main {
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);
		PostgreUtil pgUtil = ctx.getBean(PostgreUtil.class);
		pgUtil.initializeUsersTable();
	}
}
