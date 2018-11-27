package de.tudarmstadt.informatik.ukp.athenakp.database.access.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import de.tudarmstadt.informatik.ukp.athenakp.database.HibernateUtils;
import de.tudarmstadt.informatik.ukp.athenakp.database.access.ConferenceCommonAccess;
import de.tudarmstadt.informatik.ukp.athenakp.database.models.Conference;

/**
 * @author Daniel Lehmann
 */
public class ConferenceHibernateAccess implements ConferenceCommonAccess {
	@Override
	public List<Conference> getByName(String name) {
		return getBy("name", name);
	}

	@Override
	public List<Conference> getByStartDate(Integer year, Integer month, Integer day) {
		return getBy("startDate", HibernateUtils.toTimestamp(year, month, day));
	}

	@Override
	public List<Conference> getByEndDate(Integer year, Integer month, Integer day) {
		return getBy("endDate", HibernateUtils.toTimestamp(year, month, day));
	}

	@Override
	public List<Conference> getByAuthor(String author) { //TODO: implement this
		return null;
	}

	@Override
	public List<Conference> getByPaper(String paper) { //TODO: implement this
		return null;
	}

	/**
	 * Common code used by all get methods above
	 * @param name The name of the column to restrict
	 * @param value The value to restrict the selection to
	 * @return A List of all persons with the given restriction
	 */
	private List<Conference> getBy(String name, Object value) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Conference.class);
		List<Conference> result;

		criteria.add(Restrictions.eq(name, value));
		result = criteria.list();
		session.close();
		return result;
	}

	@Override
	public void add(Conference data) {
		Session session = HibernateUtils.getSessionFactory().openSession();

		session.beginTransaction();
		session.save(data);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void update(Conference data) {
		Session session = HibernateUtils.getSessionFactory().openSession();

		session.beginTransaction();
		session.update(data);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(Conference data) {
		Session session = HibernateUtils.getSessionFactory().openSession();

		session.beginTransaction();
		session.remove(data);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Conference> get() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Conference> result = session.createCriteria(Conference.class).list();

		session.close();
		return result;
	}
}