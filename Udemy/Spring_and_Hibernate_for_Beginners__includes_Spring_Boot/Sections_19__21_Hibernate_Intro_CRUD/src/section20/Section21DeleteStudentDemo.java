package section20;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

public class Section21DeleteStudentDemo {

    public static void main(String[] args) {
       // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Section20Student.class)
                .buildSessionFactory();
      // create session
        Session session = sessionFactory.getCurrentSession();
        
        try {
            int id = 3;
            // start transaction
            session.beginTransaction();
            Section20Student student = session.get(Section20Student.class, id);
            session.delete(student);
            session.getTransaction().commit();
            System.out.println("DELETING FOR 3 Record is done!");
            // new read
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nDEBUG : Delete Student via query");
            session.createQuery("DELETE FROM Section20Student WHERE id=2").executeUpdate();
            session.getTransaction().commit();
            System.out.println("PROCESSING deleting via query has finished");
        }
        catch(Exception e ) {
            e.printStackTrace();
        }
        finally {
            sessionFactory.close();
        }

    }

}
