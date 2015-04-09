package dao;

import java.util.List;

import model.Speaker;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpeakerDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SpeakerDAO.class);
	// property constants
	public static final String ACTIVITY_ID = "activityId";
	public static final String NAME = "name";
	public static final String INTRODUCTION = "introduction";
	public static final String USER_ID = "userId";

	public void save(Speaker transientInstance) {
		log.debug("saving Speaker instance");
		try {
			Session session = getSession();
        	Transaction tran=session.beginTransaction();
        	session.save(transientInstance);
        	tran.commit();
        	session.close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void modify(Speaker persistentInstance) {
		log.debug("deleting Speaker instance");
		try {
			Session session = getSession();
        	Transaction tran=session.beginTransaction();
        	session.update(persistentInstance);
        	tran.commit();
        	session.close();
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(Speaker persistentInstance) {
		log.debug("deleting Speaker instance");
		try {
			Session session = getSession();
        	Transaction tran=session.beginTransaction();
        	session.delete(persistentInstance);
        	tran.commit();
        	session.close();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Speaker findById(java.lang.Integer id) {
		log.debug("getting Speaker instance with id: " + id);
		try {
			Speaker instance = (Speaker) getSession().get("model.Speaker", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Speaker instance) {
		log.debug("finding Speaker instance by example");
		try {
			List results = getSession().createCriteria("model.Speaker")
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
		log.debug("finding Speaker instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Speaker as model where model."
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

	public List findByIntroduction(Object introduction) {
		return findByProperty(INTRODUCTION, introduction);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findAll() {
		log.debug("finding all Speaker instances");
		try {
			String queryString = "from Speaker";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Speaker merge(Speaker detachedInstance) {
		log.debug("merging Speaker instance");
		try {
			Speaker result = (Speaker) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Speaker instance) {
		log.debug("attaching dirty Speaker instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Speaker instance) {
		log.debug("attaching clean Speaker instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}