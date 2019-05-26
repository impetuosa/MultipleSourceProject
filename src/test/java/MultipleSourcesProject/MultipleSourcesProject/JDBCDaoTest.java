package MultipleSourcesProject.MultipleSourcesProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;
import muad.dib.dao.Dao;
import muad.dib.jdbc.Building;
import muad.dib.jdbc.BuildingDao;
import muad.dib.model.Address;
import muad.dib.model.BuildingAddress;
import muad.dib.model.Gender;
import muad.dib.model.MailAddress;
import muad.dib.model.Person;

/**
 * Unit test for simple App.
 */
public class JDBCDaoTest extends TestCase {

	SessionFactory sessionFactory;
	BuildingDao dao;
	private Connection db;

	public void setUp() {
		try {
			db = DriverManager.getConnection("jdbc:postgresql://localhost/sbragagn", "sbragagn", "dummy");
			dao = new BuildingDao(db);
			dao.createTable();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void tearDown() {
		dao.dropTable();
	}

	public void testDatabaseGenteration() {
		List<Building> list = dao.findAll();
		assertEquals(list.size(), 0);
	}

	public void testDatabaseStore() {

		dao.save(this.createBuildingTest());

		List<Building> list = dao.findAll();
		assertEquals(list.size(), 1);
	}

	public void testFetchingExpectedData() {
		
	}

	public void testLogABuilding() {
		dao.save(this.createBuildingTest());
		Building building = dao.findAll().get(0);
		Logger logger = LoggerFactory.getLogger(JDBCDaoTest.class);
		logger.warn("Building: {} address: {}", building.getName(), building.getAddress().getName());

	}

	private Building createBuildingTest() {
		Building building = new Building();
		BuildingAddress address = new BuildingAddress();
		building.setId(3);
		building.setName("Chicken");
		building.setAddress(address);
		address.setName("Planet");
		address.setNumber(30);
		return building;
	}
}
