package section20;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

public class Section21CreateStudentDemo {

    public static void main(String[] args) {
       // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Section20Student.class)
                .buildSessionFactory();
      // create session
        Session session = sessionFactory.getCurrentSession();
        
        try {
            // use a session
            System.out.println("DEBUG : Creating Student Object...");
            // create student
            Section20Student student = new Section20Student("Paul","von Mises","abc@ghj.com");
            // start transaction
            session.beginTransaction();
            // save student object
            System.out.println("DEBUG : Saving Student Object...");
            session.save(student);
            // commit transaction
            session.getTransaction().commit();
            System.out.println("DEBUG : done!");
        }
        catch(Exception e ) {
            e.printStackTrace();
        }
        finally {
            sessionFactory.close();
        }

    }

}
