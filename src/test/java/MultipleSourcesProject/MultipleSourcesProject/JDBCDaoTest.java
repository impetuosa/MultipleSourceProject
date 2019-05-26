package MultipleSourcesProject.MultipleSourcesProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;
import muad.dib.jdbc.Building;
import muad.dib.jdbc.BuildingDao;
import muad.dib.model.BuildingAddress;

/**
 * Unit test for simple App.
 */
public class JDBCDaoTest extends TestCase {

	BuildingDao dao;
	private Connection db;

	public void setUp() {
		try {
			db = DriverManager.getConnection("jdbc:postgresql://localhost/dummyx", "sbragagn", "dummy");
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


	public void testLogABuilding() {
		dao.save(this.createBuildingTest());
		Building building = dao.findAll().get(0);
		Logger logger = LoggerFactory.getLogger(JDBCDaoTest.class);
		logger.warn("Building: {} address: {}", 
				building.getName(), 
				building.getAddress().getName());
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
