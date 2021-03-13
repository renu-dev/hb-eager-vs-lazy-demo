package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

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
			
			//create objects
			Instructor tInstructor = new Instructor("Susan","Public","susan@luv2code.com");
			InstructorDetail tDetail = new InstructorDetail("http://www.videogames.com/youtube","Video Games!!");
			
			Instructor tInstructor1 = new Instructor("Elena","Deo","elena@luv2code.com");
			InstructorDetail tDetail1 = new InstructorDetail("http://www.series.com/youtube/","Series!!");
			
			//associate the objects
			tInstructor.setInstructorDetail(tDetail);
			tInstructor1.setInstructorDetail(tDetail1);
			
			//begin or start transaction
			session.beginTransaction();
			
			//save instructor
			//NOTE:this will also save instructor details object
			//beacuse of CascadeType.ALL
			System.out.println("Saving the instructor");
			session.save(tInstructor);
			session.save(tInstructor1);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
			
		}finally {
			session.close();
			factory.close();
		}
	}

}
