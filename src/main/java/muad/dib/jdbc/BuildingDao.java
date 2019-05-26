package muad.dib.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import muad.dib.model.BuildingAddress;

public class BuildingDao extends JDBCDao<Building> {

	public BuildingDao(Connection db) {
		super(db);
	}

	@Override
	protected String getColumns() {
		return " id, name, streetName, number ";
	}

	@Override
	protected String getTableName() {
		return " building ";
	}

	@Override
	protected Building inflateInstance(ResultSet set) {
		Building building = new Building();
		try {
			building.id = set.getInt(1);

			building.name = set.getString(2);
			building.address = new BuildingAddress();
			building.address.setName(set.getString(3));
			building.address.setNumber(set.getInt(4));
			return building;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	protected String getIdColumn() {
		return "id";
	}

	@Override
	protected String serializeId(Building object) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(object.id);
		return buffer.toString();
	}

	@Override
	protected String serializeValues(Building object) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(object.id).append(",'").append(object.getName()).append("','").append(object.address.getName())
				.append("',").append(object.address.getNumber());
		return buffer.toString();
	}

	@Override
	protected String createTableString() {

		return "CREATE TABLE building ( id INTEGER, name TEXT, streetName TEXT, number INTEGER, PRIMARY KEY(id)  );";
	}

}
