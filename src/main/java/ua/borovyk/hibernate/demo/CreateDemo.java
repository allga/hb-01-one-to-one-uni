package ua.borovyk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.hibernate.demo.entity.Instructor;
import ua.borovyk.hibernate.demo.entity.InstructorDetail;

import java.text.ParseException;

public class CreateDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            Instructor instructor = new Instructor("Mad", "Part", "part@gmail.com");

            InstructorDetail instructorDetail =
                    new InstructorDetail(
                            "http://www.part.com/youtube",
                            "Cats"
                    );

            instructor.setInstructorDetail(instructorDetail);

            session.beginTransaction();

            System.out.println("Saving " + instructor);
            session.save(instructor);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
