package com.ashugupta.pjp.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ashugupta.pjp.models.Transaction;

public class DBManager {
	
	SessionFactory sessionFactory;
	
	public DBManager() {
		Configuration config = new Configuration();
        config.addAnnotatedClass(Transaction.class);
        config.addAnnotatedClass(com.ashugupta.pjp.models.Session.class);
        config.configure();
        sessionFactory = config.buildSessionFactory();
	}
	
	public void addTransactionToDB(Transaction transaction) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( transaction );
		session.getTransaction().commit();
		session.close();
	}

	public void addSessionToDB(com.ashugupta.pjp.models.Session currentSession) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(currentSession);
		session.getTransaction().commit();
		session.close();
	}
}
