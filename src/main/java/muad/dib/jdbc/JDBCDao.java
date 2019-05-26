package muad.dib.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class JDBCDao<T> {

	Connection db;

	public JDBCDao(Connection db) {

		this.db = db;

	}

	public void dropTable() {
		try {
			db.createStatement().execute("DROP TABLE " + this.getTableName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void createTable() {
		try {
			db.createStatement().execute(this.createTableString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	protected abstract String getColumns();

	protected abstract String createTableString();

	protected abstract String getTableName();

	protected abstract T inflateInstance(ResultSet set);

	protected abstract String getIdColumn();

	protected abstract String serializeId(T object);

	protected abstract String serializeValues(T object);

	public List<T> findAll() {
		List<T> result = new ArrayList<T>();
		ResultSet set;
		try {
			set = db.createStatement().executeQuery("select " + this.getColumns() + " from " + this.getTableName());
			
			while (set.next()) {
				result.add(this.inflateInstance(set));
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

	public void save(T object) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO ").append(this.getTableName()).append("(").append(this.getColumns()).append(')')
				.append("VALUES (").append(this.serializeValues(object)).append(')');
		try {
			db.createStatement().execute(buffer.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void delete(T object) {
		try {
			PreparedStatement statement = this.db
					.prepareStatement(" DELETE FROM " + this.getTableName() + " WHERE" + this.getIdColumn() + "= ?");
			statement.setString(1, this.serializeId(object));
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
