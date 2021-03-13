package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// begin or start transaction
			session.beginTransaction();

			// get instructor detail object
			int theId = 2;
			InstructorDetail tDetail = session.get(InstructorDetail.class, theId);

			// print the object
			System.out.println("Instructor Detail is: " + tDetail);

			// get associated instructor and print it
			System.out.println("Instructor is: " + tDetail.getInstructor());

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
