package section20;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

public class Section21ReadStudentDemo {

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
            Section20Student student = new Section20Student("Jon","Boston","boston@fghj.com");
            // start transaction
            session.beginTransaction();
            // save student object
            session.save(student);
            // commit transaction
            session.getTransaction().commit();
            System.out.println("DEBUG : Saving Student has been saved... with name "+student.getFirstName()+" with ID: "+student.getId());
            // new read
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Section20Student studentRetrived = session.get(Section20Student.class,student.getId());
            System.out.println("\nDEBUG : Retrieved Student : "+studentRetrived);
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
