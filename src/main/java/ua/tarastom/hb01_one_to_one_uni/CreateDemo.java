package ua.tarastom.hb01_one_to_one_uni;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.Instructor;
import ua.tarastom.entity.InstructorDetail;

public class CreateDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Instructor.class)
                .buildSessionFactory();

        Session currentSession = sessionFactory.getCurrentSession();

        try{
            currentSession.beginTransaction();
//            InstructorDetail instructorDetail = new InstructorDetail("http://love2code.com/youtube", "JavaCoding");
//            Instructor instructor = new Instructor("Chad", "Darby", "chad@gmail.com");
            InstructorDetail instructorDetail = new InstructorDetail("http://love3code.com/youtube", "JavaCoding");
            Instructor instructor = new Instructor("Tim", "Bushalka", "tim@gmail.com");
            instructor.setInstructorDetail(instructorDetail);

//             Note: this will ALSO save the details object because of CascadeType.ALL
            currentSession.save(instructor);

            currentSession.getTransaction().commit();

        }finally {
            sessionFactory.close();
        }

    }
}
