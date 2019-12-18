package ua.tarastom.hb01_one_to_one_uni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.Instructor;
import ua.tarastom.entity.InstructorDetail;

public class DeleteDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        try{
            currentSession.beginTransaction();
            int theId = 3;
            Instructor instructor = currentSession.get(Instructor.class, theId);
            if (instructor != null) {
                // Note: this will ALSO delete the details object because of CascadeType.ALL
                currentSession.delete(instructor);
            }
            currentSession.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }
    }
}
