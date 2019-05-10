package muad.dib.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Person {

	@Id
	@GeneratedValue
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	Gender gender;

	String name;
	Date birthdate;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	List<Address> addresses = new ArrayList<Address>();

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Gender getGender() {
		return gender;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void addAddress(Address address) {
		this.addresses.add(address);
		address.owner = this;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

}
