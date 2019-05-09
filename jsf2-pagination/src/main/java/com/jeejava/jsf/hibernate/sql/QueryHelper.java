package com.jeejava.jsf.hibernate.sql;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.jeejava.jsf.hibernate.domain.Cds;
import com.jeejava.jsf.hibernate.util.HibernateUtil;

public class QueryHelper {

	Session session = null;

	public QueryHelper() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	/**
	 * this method should not be used in real application
	 */
	public void insertRecords() {
		try {
			session.beginTransaction();

			Cds cd = new Cds();
			cd.setId(1l);
			cd.setInterpret("Singham");
			cd.setTitle("Rohit Setty");
			session.save(cd);

			cd = new Cds();
			cd.setId(2l);
			cd.setInterpret("Singham Returns");
			cd.setTitle("Rohit Setty");
			session.save(cd);

			cd = new Cds();
			cd.setId(3l);
			cd.setInterpret("Golmal");
			cd.setTitle("Rohit Setty");
			session.save(cd);

			cd = new Cds();
			cd.setId(4l);
			cd.setInterpret("Golmal Returns");
			cd.setTitle("Rohit Setty");
			session.save(cd);

			cd = new Cds();
			cd.setId(5l);
			cd.setInterpret("Golmal Retuens 2");
			cd.setTitle("Rohit Setty");
			session.save(cd);

			cd = new Cds();
			cd.setId(6l);
			cd.setInterpret("Welcome");
			cd.setTitle("Rohit Setty");
			session.save(cd);

			cd = new Cds();
			cd.setId(7l);
			cd.setInterpret("Toofan");
			cd.setTitle("RGV");
			session.save(cd);

			cd = new Cds();
			cd.setId(8l);
			cd.setInterpret("Alag Alag");
			cd.setTitle("Kishore Kumar");
			session.save(cd);

			cd = new Cds();
			cd.setId(9l);
			cd.setInterpret("Sholay");
			cd.setTitle("Amitav Bacchan");
			session.save(cd);

			cd = new Cds();
			cd.setId(10l);
			cd.setInterpret("Khiladi");
			cd.setTitle("Akshay Kumar");
			session.save(cd);

			cd = new Cds();
			cd.setId(11l);
			cd.setInterpret("Taal Songs");
			cd.setTitle("A R Reheman");
			session.save(cd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cds> getListOfCds(int firstRow, int rowCount) {
		List<Cds> cdList = null;
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Cds.class);
			criteria.setFirstResult(firstRow);
			criteria.setMaxResults(rowCount);
			if (criteria != null) {
				cdList = (List<Cds>) criteria.list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cdList;
	}

	public int countRows() {
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Cds.class);
			if (criteria != null) {
				return criteria.list().size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
