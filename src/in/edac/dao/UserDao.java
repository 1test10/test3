package in.edac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_DRIVER1 = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/edac";
	public static final String DB_URL1 = "jdbc:mysql://localhost:3306/edac";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "edac20";

	public void checkConnection() {
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);) {
			System.out.println("succes");
//			con.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public boolean createUser(User user) throws Exception {
		Class.forName(DB_DRIVER);
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);) {
			String sql = "INSERT INTO USER (USERNAME, EMAIL, PASSWORD, MOBILE) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getMobile());

			ps.executeUpdate();

			System.out.println("Insert Success");
			return true;

		} catch (Exception e) {

			e.printStackTrace();
//			return false;
			throw e;
		}

	}

	public boolean updateUser(User user) throws Exception {
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);) {
			String sql = "UPDATE USER SET USERNAME=?,PASSWORD=?,MOBILE=? WHERE ID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getId());

			ps.executeUpdate();

			System.out.println("update Success");
			return true;

		} catch (Exception e) {

			e.printStackTrace();
//			return false;
			throw e;
		}

	}
	
	public boolean deleteUser(User user) throws Exception {
		Class.forName(DB_DRIVER);
		try(Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);){
			String sql = "DELETE FROM USER WHERE ID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, user.getId());
			
			ps.executeUpdate();
			System.out.println("succes");
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String args[]) throws Exception {
		UserDao dao = new UserDao();
//		userdao.checkConnection();
		
		//User user = new User("shubam","myj","dfkshf","fsdkjghs");
		//dao.createUser(user);

//		User user = new User("shubam1","myj","dfkshf","fsdkjghs");
//		user.setId(7);
//		dao.updateUser(user);
		
User user = new User();
user.setId(5);
dao.deleteUser(user);

	}

}
