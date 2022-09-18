package section20;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

public class Section21UpdateStudentDemo {

    public static void main(String[] args) {
       // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Section20Student.class)
                .buildSessionFactory();
      // create session
        Session session = sessionFactory.getCurrentSession();
        
        try {
            int id = 1;
            // start transaction
            session.beginTransaction();
            // save student object
            Section20Student studentRetrived = session.get(Section20Student.class,id);
            studentRetrived.setFirstName("UpdatedFirstName");
            session.getTransaction().commit();
            System.out.println("PROCESSED FOR 1 Record is done!");
            // new read
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nDEBUG : Updates Students via query");
            session.createQuery("UPDATE Section20Student SET email='@gmail.com'").executeUpdate();
            session.getTransaction().commit();
            System.out.println("PROCESSING finished");
        }
        catch(Exception e ) {
            e.printStackTrace();
        }
        finally {
            sessionFactory.close();
        }

    }

}
