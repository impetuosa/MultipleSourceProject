package MultipleSourcesProject.MultipleSourcesProject;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;
import muad.dib.dao.Dao;
import muad.dib.model.Address;
import muad.dib.model.BuildingAddress;
import muad.dib.model.Gender;
import muad.dib.model.MailAddress;
import muad.dib.model.Person;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	SessionFactory sessionFactory;
	Dao<Person> personDao;
	Dao<Address> addressDao;

	public void setUp() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		sessionFactory.openSession();
		personDao = new Dao<Person>(sessionFactory, Person.class);
		addressDao = new Dao<Address>(sessionFactory, Address.class);

	}

	public void tearDown() {
		sessionFactory.getCurrentSession().close();
	}

	public void testDatabaseGenteration() {
		Person person = this.createPersonTest();
		int lastAmount = personDao.findAll().size();

		personDao.save(person);
		assertEquals(lastAmount + 1, personDao.findAll().size());
	}

	public void testFetchingExpectedData() {
		Person person = personDao.findAll().get(0);
		assertEquals(person.getGender(), Gender.Pan);
		assertEquals(person.getAddresses().size(), 2);
		assertEquals(person.getAddresses().get(0).getClass(), MailAddress.class);
		assertEquals(person.getAddresses().get(1).getClass(), BuildingAddress.class);
		assertEquals(person.getAddresses().get(0).getOwner().getId(), person.getId());
		assertEquals(person.getAddresses().get(1).getOwner().getId(), person.getId());
	}

	public void testLogAPerson() {
		Person person = personDao.findAll().get(0);
		Logger logger = LoggerFactory.getLogger(AppTest.class);
		logger.warn("Person: {} address: {}", person.getName(),
				((BuildingAddress) person.getAddresses().get(1)).getName());

	}

	private Person createPersonTest() {
		Person person;
		MailAddress mail;
		BuildingAddress address;

		person = new Person();
		person.setGender(Gender.Pan);
		person.setBirthdate(new Date());
		person.setName("Pablo Tesone");

		mail = new MailAddress();
		mail.setHost("univ-lille.fr");
		mail.setUsername("sbragagnolo");

		address = new BuildingAddress();
		address.setComplement("");
		address.setName("rue tamere");
		address.setNumber(20);

		person.addAddress(mail);
		person.addAddress(address);

		return person;

	}
}
