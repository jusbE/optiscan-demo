package app;


import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import app.database.PostgreUtil;

@Configuration
public class AppConfiguration {

	@Autowired
	public Environment env;

	@Bean
	public PostgreUtil postgreUtil(){
		Driver driver = new Driver();
		SimpleDriverDataSource ds = new SimpleDriverDataSource(driver,
				env.getProperty("postgres.url"),
				env.getProperty("postgres.username"),
				env.getProperty("postgres.password"));
		ds.setDriverClass(org.postgresql.Driver.class);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		return new PostgreUtil(jdbcTemplate);
	}
}
