package dao;

import java.util.List;

import model.User;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	// property constants
	public static final String WEIXIN_OPENID = "weixinOpenid";
	public static final String WEIXIN_UNIONID = "weixinUnionid";
	public static final String NICKNAME = "nickname";
	public static final String SEX = "sex";
	public static final String PROVINCE = "province";
	public static final String CITY = "city";
	public static final String COUNTRY = "country";
	public static final String WEIXIN_HEADIMG = "weixinHeadimg";
	public static final String EMAIL = "email";
	public static final String WEIXIN_ID = "weixinId";
	public static final String REALNAME = "realname";
	public static final String CORP = "corp";
	public static final String CELLPHONE = "cellphone";
	public static final String WORKPHONE = "workphone";
	public static final String POSITION = "position";
	public static final String ADDRESS = "address";
	public static final String VERIFIED = "verified";
	public static final String PERMISSION = "permission";
	public static final String QQ = "qq";
	public static final String PEMAIL = "pemail";
	public static final String CORPDETAIL = "corpdetail";
	public static final String DEPARTMENT = "department";
	public static final String REMARK1 = "remark1";
	public static final String REMARK2 = "remark2";
	public static final String REMARK3 = "remark3";

	public void save(User transientInstance) {
		log.debug("saving User instance");
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
	
	public int modify(User user){
		Session session = HibernateSessionFactory.getSession();
		Transaction tran=session.beginTransaction();
		session.update(user);
		tran.commit();
		session.close();
		return user.getId().intValue();
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getSession().get("model.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List results = getSession().createCriteria("model.User")
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
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByWeixinOpenid(Object weixinOpenid) {
		return findByProperty(WEIXIN_OPENID, weixinOpenid);
	}

	public List findByWeixinUnionid(Object weixinUnionid) {
		return findByProperty(WEIXIN_UNIONID, weixinUnionid);
	}

	public List findByNickname(Object nickname) {
		return findByProperty(NICKNAME, nickname);
	}

	public List findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	public List findByProvince(Object province) {
		return findByProperty(PROVINCE, province);
	}

	public List findByCity(Object city) {
		return findByProperty(CITY, city);
	}

	public List findByCountry(Object country) {
		return findByProperty(COUNTRY, country);
	}

	public List findByWeixinHeadimg(Object weixinHeadimg) {
		return findByProperty(WEIXIN_HEADIMG, weixinHeadimg);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByWeixinId(Object weixinId) {
		return findByProperty(WEIXIN_ID, weixinId);
	}

	public List findByRealname(Object realname) {
		return findByProperty(REALNAME, realname);
	}

	public List findByCorp(Object corp) {
		return findByProperty(CORP, corp);
	}

	public List findByCellphone(Object cellphone) {
		return findByProperty(CELLPHONE, cellphone);
	}

	public List findByWorkphone(Object workphone) {
		return findByProperty(WORKPHONE, workphone);
	}

	public List findByPosition(Object position) {
		return findByProperty(POSITION, position);
	}

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findByVerified(Object verified) {
		return findByProperty(VERIFIED, verified);
	}

	public List findByPermission(Object permission) {
		return findByProperty(PERMISSION, permission);
	}

	public List findByQq(Object qq) {
		return findByProperty(QQ, qq);
	}

	public List findByPemail(Object pemail) {
		return findByProperty(PEMAIL, pemail);
	}

	public List findByCorpdetail(Object corpdetail) {
		return findByProperty(CORPDETAIL, corpdetail);
	}

	public List findByDepartment(Object department) {
		return findByProperty(DEPARTMENT, department);
	}

	public List findByRemark1(Object remark1) {
		return findByProperty(REMARK1, remark1);
	}

	public List findByRemark2(Object remark2) {
		return findByProperty(REMARK2, remark2);
	}

	public List findByRemark3(Object remark3) {
		return findByProperty(REMARK3, remark3);
	}

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllOrderBy(String orderby, int offset, int count) {
		log.debug("finding all User instances");
		try {
			String queryString = "from User order by " + orderby + " desc limit " + offset + "," + count;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}