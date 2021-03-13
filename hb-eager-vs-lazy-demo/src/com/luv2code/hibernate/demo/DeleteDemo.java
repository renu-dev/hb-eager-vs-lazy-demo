package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure()
				                 .addAnnotatedClass(Instructor.class)
				                 .addAnnotatedClass(InstructorDetail.class)
				                 .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//begin or start transaction
			session.beginTransaction();
			
			//get instructor by primary key
			int theId=1;
			
			Instructor tInstructor = session.get(Instructor.class, theId);

		    System.out.println("Found the instructor: "+tInstructor);
			
			//delete the instructor
			if(tInstructor!=null) {
				System.out.println("deleting the instructor: "+tInstructor);
				//NOTE: also delete instructor details due to CascadeType.ALL
				session.delete(tInstructor);
			}
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
			
		}finally {
			factory.close();
		}
	}

}
