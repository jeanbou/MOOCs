package section20;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

import org.hibernate.Session;

public class Section21HQLStudentDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
       // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Section20Student.class)
                .buildSessionFactory();
      // create session
        Session session = sessionFactory.getCurrentSession();
        
        try {
            session.beginTransaction();
            System.out.println("DEBUG : All student ");
            List<Section20Student> retrievedListOfStudents = session.createQuery("FROM Section20Student").list();
            displayStudent(retrievedListOfStudents);
            System.out.println("DEBUG : All student with %on% in First name ");
            retrievedListOfStudents = session.createQuery("FROM Section20Student s WHERE s.firstName LIKE '%on%'").list();
            displayStudent(retrievedListOfStudents);
            // new read
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

    private static void displayStudent(
            List<Section20Student> retrievedListOfStudents) {
        for (Section20Student aStudent : retrievedListOfStudents) {
            System.out.println("Student ID : "+aStudent.getId()+ " First Name : "+aStudent.getFirstName());
        }
    }

}
