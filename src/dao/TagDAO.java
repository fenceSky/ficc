package dao;

import java.sql.Timestamp;
import java.util.List;

import model.Tag;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TagDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TagDAO.class);
	// property constants
	public static final String ACTIVITY_ID = "activityId";
	public static final String NAME = "name";
	public static final String USER_ID = "userId";

	public void save(Tag transientInstance) {
		log.debug("saving Tag instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tag persistentInstance) {
		log.debug("deleting Tag instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tag findById(java.lang.Integer id) {
		log.debug("getting Tag instance with id: " + id);
		try {
			Tag instance = (Tag) getSession().get("model.Tag", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tag instance) {
		log.debug("finding Tag instance by example");
		try {
			List results = getSession().createCriteria("model.Tag")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Tag instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tag as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByActivityId(Object activityId) {
		return findByProperty(ACTIVITY_ID, activityId);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findAll() {
		log.debug("finding all Tag instances");
		try {
			String queryString = "from Tag";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tag merge(Tag detachedInstance) {
		log.debug("merging Tag instance");
		try {
			Tag result = (Tag) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tag instance) {
		log.debug("attaching dirty Tag instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tag instance) {
		log.debug("attaching clean Tag instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}