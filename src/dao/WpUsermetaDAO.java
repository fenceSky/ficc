package dao;

import model.*;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * WpUsermeta entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.WpUsermeta
 * @author MyEclipse Persistence Tools
 */

public class WpUsermetaDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(WpUsermetaDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String META_KEY = "metaKey";
	public static final String META_VALUE = "metaValue";

	public void save(WpUsermeta transientInstance) {
		log.debug("saving WpUsermeta instance");
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
	
	public void update(WpUsermeta transientInstance) {
		log.debug("updating Game instance");
		try {
			Session session = getSession();
        	Transaction tran=session.beginTransaction();
        	session.update(transientInstance);
        	tran.commit();
        	session.close();
			
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(WpUsermeta persistentInstance) {
		log.debug("deleting WpUsermeta instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public WpUsermeta findById(java.lang.Long id) {
		log.debug("getting WpUsermeta instance with id: " + id);
		try {
			WpUsermeta instance = (WpUsermeta) getSession().get(
					"model.WpUsermeta", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(WpUsermeta instance) {
		log.debug("finding WpUsermeta instance by example");
		try {
			List results = getSession().createCriteria("model.WpUsermeta")
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
		log.debug("finding WpUsermeta instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from WpUsermeta as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByMetaKey(Object metaKey) {
		return findByProperty(META_KEY, metaKey);
	}

	public List findByMetaValue(Object metaValue) {
		return findByProperty(META_VALUE, metaValue);
	}

	public List findAll() {
		log.debug("finding all WpUsermeta instances");
		try {
			String queryString = "from WpUsermeta";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public WpUsermeta merge(WpUsermeta detachedInstance) {
		log.debug("merging WpUsermeta instance");
		try {
			WpUsermeta result = (WpUsermeta) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(WpUsermeta instance) {
		log.debug("attaching dirty WpUsermeta instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(WpUsermeta instance) {
		log.debug("attaching clean WpUsermeta instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}