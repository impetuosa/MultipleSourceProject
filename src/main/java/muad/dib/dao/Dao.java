package muad.dib.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

public class Dao<T> {
	SessionFactory factory;
	final private Class<T> persistentClass;

	public Dao(SessionFactory factory, Class<T> type) {

		this.persistentClass = type;
		this.factory = factory;

	}

	public List<T> findAll() {
		List<T> list;
		this.factory.getCurrentSession().beginTransaction();
		list = (List<T>) (this.factory.getCurrentSession().createCriteria(this.persistentClass)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		this.factory.getCurrentSession().getTransaction().commit();
		return list;

	}

	public void save(T object) {
		this.factory.getCurrentSession().beginTransaction();
		this.factory.getCurrentSession().save(object);
		this.factory.getCurrentSession().getTransaction().commit();
	}

	public void delete(T object) {
		this.factory.getCurrentSession().beginTransaction();
		this.factory.getCurrentSession().delete(object);
		this.factory.getCurrentSession().getTransaction().commit();
	}
}
