package muad.dib.model;


public abstract class Address {

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	protected Person owner;

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public Person getOwner() {
		return owner;
	}

	}
