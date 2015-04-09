package dao;

import java.sql.Timestamp;
import java.util.List;

import model.Activity;
import model.User;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ActivityDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String LAST_TIME = "lastTime";
	public static final String PROVINCE = "province";
	public static final String CITY = "city";
	public static final String SHORT_ADDR = "shortAddr";
	public static final String DETAIL_ADDR = "detailAddr";
	public static final String PUBLISHER_ID = "publisherId";
	public static final String TOTAL_PEOPLE = "totalPeople";
	public static final String FEE = "fee";
	public static final String CONTENT = "content";
	public static final String STATE = "state";

	public void save(Activity transientInstance) {
		log.debug("saving Activity instance");
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
	
	public int modify(Activity activity){
		Session session = HibernateSessionFactory.getSession();
		Transaction tran=session.beginTransaction();
		session.update(activity);
		tran.commit();
		session.close();
		return activity.getId().intValue();
	}

	public void delete(Activity persistentInstance) {
		log.debug("deleting Activity instance");
		try {
			Session session = HibernateSessionFactory.getSession();
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

	public Activity findById(java.lang.Integer id) {
		log.debug("getting Activity instance with id: " + id);
		try {
			Activity instance = (Activity) getSession().get("model.Activity", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Activity instance) {
		log.debug("finding Activity instance by example");
		try {
			List results = getSession().createCriteria("model.Activity")
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
		log.debug("finding Activity instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Activity as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List search(String search_word) {
		try {
			String queryString = "from Activity as model where model.title like '%"+search_word+"%' or model.province like '%"+search_word+
					"%'" + " or model.detailAddr like '%"+search_word+"%' order by model.publishTime desc";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByLastTime(Object lastTime) {
		return findByProperty(LAST_TIME, lastTime);
	}

	public List findByProvince(Object province) {
		return findByProperty(PROVINCE, province);
	}

	public List findByCity(Object city) {
		return findByProperty(CITY, city);
	}

	public List findByShortAddr(Object shortAddr) {
		return findByProperty(SHORT_ADDR, shortAddr);
	}

	public List findByDetailAddr(Object detailAddr) {
		return findByProperty(DETAIL_ADDR, detailAddr);
	}

	public List findByPublisherId(Object publisherId) {
		return findByProperty(PUBLISHER_ID, publisherId);
	}

	public List findByTotalPeople(Object totalPeople) {
		return findByProperty(TOTAL_PEOPLE, totalPeople);
	}

	public List findByFee(Object fee) {
		return findByProperty(FEE, fee);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findAll() {
		log.debug("finding all Activity instances");
		try {
			String queryString = "from Activity";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAll(int count) {
		log.debug("finding all Activity instances");
		try {
			String queryString = "from Activity";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setMaxResults(count);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findOrderbyStartTime(int count, Timestamp t) {
		log.debug("finding all Activity instances");
		try {
			String queryString = "from Activity as model where model.startTime > '"+t.toString()+"' order by model.startTime";
			
			//System.out.println(queryString);
			Query queryObject = getSession().createQuery(queryString);
			//queryObject.setTimestamp(0, t);
			queryObject.setMaxResults(count);
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	public Activity merge(Activity detachedInstance) {
		log.debug("merging Activity instance");
		try {
			Activity result = (Activity) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Activity instance) {
		log.debug("attaching dirty Activity instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Activity instance) {
		log.debug("attaching clean Activity instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}