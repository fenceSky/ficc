package dao;

import java.sql.Timestamp;
import java.util.List;

import model.Signup;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignupDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SignupDAO.class);
	// property constants
	public static final String ACTIVITY_ID = "activityId";
	public static final String USER_ID = "userId";
	public static final String STATE = "state";

	public void save(Signup transientInstance) {
		log.debug("saving Signup instance");
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

	public void delete(Signup persistentInstance) {
		log.debug("deleting Signup instance");
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

	public Signup findById(java.lang.Integer id) {
		log.debug("getting Signup instance with id: " + id);
		try {
			Signup instance = (Signup) getSession().get("model.Signup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByAidandUid(Integer aid, Integer uid) {
		try {
			String queryString = "from Signup as model where model.activityId = " 
					+ aid + " and model.userId = " + uid;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByExample(Signup instance) {
		log.debug("finding Signup instance by example");
		try {
			List results = getSession().createCriteria("model.Signup")
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
		log.debug("finding Signup instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Signup as model where model."
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

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findAll() {
		log.debug("finding all Signup instances");
		try {
			String queryString = "from Signup";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Signup merge(Signup detachedInstance) {
		log.debug("merging Signup instance");
		try {
			Signup result = (Signup) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Signup instance) {
		log.debug("attaching dirty Signup instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Signup instance) {
		log.debug("attaching clean Signup instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}