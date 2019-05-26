package muad.dib.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reporting {
	Connection db;

	protected ResultSet query(String query) {

		try {
			db = DriverManager.getConnection("jdbc:postgresql://[::1]:5740/", "sbragagn", "root");
			return db.createStatement().executeQuery(query);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<String> getPersonsLines() {
		try {
			
			List<String> value = new ArrayList<String>();
			ResultSet rs = this.query(" SELECT * FROM Person ");
			while (rs.next()) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("ID ").append(rs.getInt(0)).append("Name").append(rs.getString(3));
				value.add(buffer.toString());
			}
			 db.close(); 
			return value;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}  
	}
	
	public ResultSet selectLinesFromAt(String table, String columns, String database, String user, String secret) {
		try {
			db = DriverManager.getConnection("jdbc:postgresql://[::1]:5740/"+database, user, secret);
			return db.createStatement().executeQuery("select " + columns + " from " + table);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<String> getBuildingAddressLines() {
		try {
			
			List<String> value = new ArrayList<String>();
			ResultSet rs =	this.selectLinesFromAt("buildingaddress", "DISTINCT name, number", "", "sbragagn", "root");
			while (rs.next()) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("Number").append(rs.getInt(1)).append("Street").append(rs.getString(0));
				value.add(buffer.toString());
			}
			return value;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
