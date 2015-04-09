package dao;

import java.sql.Timestamp;
import java.util.List;

import model.Attachment;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AttachmentDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AttachmentDAO.class);
	// property constants
	public static final String ACTIVITY_ID = "activityId";
	public static final String USER_ID = "userId";
	public static final String FILE_TYPE = "fileType";
	public static final String FILE_NAME = "fileName";
	public static final String FILE_URL = "fileUrl";
	public static final String FILE_SIZE = "fileSize";
	public static final String STATE = "state";

	public void save(Attachment transientInstance) {
		log.debug("saving Attachment instance");
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
	
	public void modify(Attachment persistentInstance) {
		log.debug("update Attachment instance");
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

	public void delete(Attachment persistentInstance) {
		log.debug("deleting Attachment instance");
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

	public Attachment findById(java.lang.Integer id) {
		log.debug("getting Attachment instance with id: " + id);
		try {
			Attachment instance = (Attachment) getSession().get(
					"model.Attachment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Attachment instance) {
		log.debug("finding Attachment instance by example");
		try {
			List results = getSession().createCriteria("model.Attachment")
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
		log.debug("finding Attachment instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Attachment as model where model."
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

	public List findByFileType(Object fileType) {
		return findByProperty(FILE_TYPE, fileType);
	}

	public List findByFileName(Object fileName) {
		return findByProperty(FILE_NAME, fileName);
	}

	public List findByFileUrl(Object fileUrl) {
		return findByProperty(FILE_URL, fileUrl);
	}

	public List findByFileSize(Object fileSize) {
		return findByProperty(FILE_SIZE, fileSize);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findAll() {
		log.debug("finding all Attachment instances");
		try {
			String queryString = "from Attachment";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Attachment merge(Attachment detachedInstance) {
		log.debug("merging Attachment instance");
		try {
			Attachment result = (Attachment) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Attachment instance) {
		log.debug("attaching dirty Attachment instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Attachment instance) {
		log.debug("attaching clean Attachment instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}