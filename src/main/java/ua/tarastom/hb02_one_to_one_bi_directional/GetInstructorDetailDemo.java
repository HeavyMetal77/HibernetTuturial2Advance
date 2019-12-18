package ua.tarastom.hb02_one_to_one_bi_directional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.Instructor;
import ua.tarastom.entity.InstructorDetail;

public class GetInstructorDetailDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        try {
            currentSession.beginTransaction();
            int theId = 5;
            InstructorDetail instructorDetail = currentSession.get(InstructorDetail.class, theId);
            System.out.println(instructorDetail);
            Instructor instructor = instructorDetail.getInstructor();
            System.out.println(instructor);

            //для удаления без каскада - меняем мапинг
            // @OneToOne(cascade = CascadeType.ALL, mappedBy = "instructorDetail")
            // на
            // @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            //              mappedBy = "instructorDetail")

            instructorDetail.getInstructor().setInstructorDetail(null); // и разрываем связывание таблиц
            currentSession.delete(instructorDetail);
            currentSession.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            currentSession.close();
            sessionFactory.close();
        }
    }
}
