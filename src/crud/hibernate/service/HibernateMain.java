package crud.hibernate.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import crud.hibernate.entity.User;

public class HibernateMain {

	private static SessionFactory sessionFactory;

	public static void main(String[] args) {

		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();

			// Create operation
			for (int i = 1; i <= 5; i++) {
				User user = new User();
				user.setUserName("User " + i);
				session.save(user);
			}

			// Read operation
			User user = session.get(User.class, 2);
			System.out.println("User name: " + user.getUserName());

			// Update operation
			user.setUserName("User two");
			session.update(user);
			System.out.println("User name 2 after update: " + user.getUserName());

			// Delete operation
			session.delete(user);

			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}
