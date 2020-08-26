package com.ashugupta.pjp.database;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import com.ashugupta.pjp.constants.Operation;
import com.ashugupta.pjp.models.Transaction;

public class hibernate {
	
	SessionFactory sessionFactory;
	
	@Before
	public void setup() {
		
		Configuration config = new Configuration();
        config.addAnnotatedClass(Transaction.class);
        config.configure();
        
        sessionFactory = config.buildSessionFactory();
//		// A SessionFactory is set up once for an application!
//		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//				.configure() // configures settings from hibernate.cfg.xml
//				.build();
//		try {
//			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
//		}
//		catch (Exception e) {
//			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
//			// so destroy it manually.
//			StandardServiceRegistryBuilder.destroy( registry );
//		}
//		
	}
	
	@Test
	public void shouldAddATransaction() {
		com.ashugupta.pjp.models.Session localSession = new com.ashugupta.pjp.models.Session();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Transaction transaction = new Transaction();
		transaction.setInput(List.of("Ashu"));
		transaction.setOperation(Operation.ADD_DAYS);
		localSession.addTransaction(transaction);
		transaction.setSession(localSession);
		session.save(localSession);
		session.save( transaction );
		session.getTransaction().commit();
		session.close();
	}

}
