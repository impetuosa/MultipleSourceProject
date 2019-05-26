package muad.dib.jdbc;

import muad.dib.model.BuildingAddress;

public class Building {
	int id;
	String name;
	BuildingAddress address;

	public void setAddress(BuildingAddress address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BuildingAddress getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;

	}
}
