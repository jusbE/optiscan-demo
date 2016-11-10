package app.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import app.models.EGender;
import app.models.UserForm;

public class PostgreUtil {

	private  final JdbcTemplate jdbcTemplate;

	public PostgreUtil(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Creates new table called Users. Drops old table if found.
	 * @param tableName
	 */
	public  void initializeUsersTable(){
		jdbcTemplate.execute("DROP TABLE IF EXISTS Users");
		jdbcTemplate.execute("CREATE TABLE Users ("
				+ "id SERIAL, "
				+ "first_name VARCHAR(50), "
				+ "last_name VARCHAR(50),"
				+ "date_of_birth BIGINT,"
				+ "gender VARCHAR(6),"
				+ "why_applying VARCHAR(1000))");
	}

	/**
	 * Add new user to Users table.
	 * @param user
	 */
	public void addUser(UserForm user){
		jdbcTemplate.execute(String.format("INSERT INTO Users ("
				+ "first_name, "
				+ "last_name,"
				+ "date_of_birth,"
				+ "gender,"
				+ "why_applying) VALUES ("
				+ "'%s', '%s', %d, '%s', '%s')",
				user.getFirstName(),
				user.getLastName(),
				user.getDateOfBirth().toInstant().toEpochMilli(),
				user.getGender().name(),
				user.getWhyApplying()));
	}

	/**
	 * Fetched every row from Users table.
	 * @return
	 */
	public List<UserForm> getUsers(){
		String query = "SELECT * FROM Users";
		return jdbcTemplate.query(query, new UserRowMapper());
	}

	private class UserRowMapper implements RowMapper<UserForm> {

		@Override
		public UserForm mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserForm result = new UserForm();
			result.setFirstName(rs.getString("first_name"));
			result.setLastName(rs.getString("last_name"));
			result.setDateOfBirth(Date.from(Instant.ofEpochMilli(rs.getLong("date_of_birth"))));
			if(rs.getString("gender").equals(EGender.MALE.name())){
				result.setGender(EGender.MALE);
			}else{
				result.setGender(EGender.FEMALE);
			}
			result.setWhyApplying(rs.getString("why_applying"));
			return result;
		}
	}
}
