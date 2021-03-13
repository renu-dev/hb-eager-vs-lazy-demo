package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchLoadingJoinDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure()
				                 .addAnnotatedClass(Instructor.class)
				                 .addAnnotatedClass(InstructorDetail.class)
				                 .addAnnotatedClass(Course.class)
				                 .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//begin or start transaction
			session.beginTransaction();
			
			//get instructor from databse
			int theId=1;
			
			Query<Instructor> query = session.createQuery("select i from Instructor i "
					                                      + "JOIN FETCH i.courses where "
					                                      + "i.id=:theInstructorId",Instructor.class);
			
			query.setParameter("theInstructorId", theId);
			
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("Instructor: "+tempInstructor);			

			//option 2: Hibernate query with HQL
			
						
			//commit transaction
			session.getTransaction().commit();
			
			//close the session
			session.close();
			System.out.println("Session is now closed!!!!");
			
			//get courses for instructor
			System.out.println("Courses are: "+tempInstructor.getCourses());//courses are already in memory by line 38
			
			System.out.println("Done!!");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
