package dao;

import java.sql.Timestamp;
import java.util.List;

import model.Message;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(MessageDAO.class);
	// property constants
	public static final String FROM_USER_ID = "fromUserId";
	public static final String TO_USER_ID = "toUserId";
	public static final String TITLE = "title";
	public static final String CONTENT = "content";
	public static final String STATE = "state";

	public void save(Message transientInstance) {
		log.debug("saving Message instance");
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

	public void delete(Message persistentInstance) {
		log.debug("deleting Message instance");
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

	public Message findById(java.lang.Integer id) {
		log.debug("getting Message instance with id: " + id);
		try {
			Message instance = (Message) getSession().get("model.Message", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Message instance) {
		log.debug("finding Message instance by example");
		try {
			List results = getSession().createCriteria("model.Message")
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
		log.debug("finding Message instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Message as model where model."
					+ propertyName + "= ? order by model.ctime desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public int findUnreadCount(int to_id) {
		try {
			String queryString = "from Message as model where model.toUserId = " + to_id + 
					" and model.state = " + Message.STATE_NO_READ;
			Query queryObject = getSession().createQuery(queryString);
			return (Integer)queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFromUserId(Object fromUserId) {
		return findByProperty(FROM_USER_ID, fromUserId);
	}

	public List findByToUserId(Object toUserId) {
		return findByProperty(TO_USER_ID, toUserId);
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findAll() {
		log.debug("finding all Message instances");
		try {
			String queryString = "from Message";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Message merge(Message detachedInstance) {
		log.debug("merging Message instance");
		try {
			Message result = (Message) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Message instance) {
		log.debug("attaching dirty Message instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Message instance) {
		log.debug("attaching clean Message instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}