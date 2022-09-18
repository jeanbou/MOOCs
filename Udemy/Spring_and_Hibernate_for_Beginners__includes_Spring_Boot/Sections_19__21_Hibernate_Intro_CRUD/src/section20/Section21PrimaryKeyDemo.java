package section20;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Section21PrimaryKeyDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Section20Student.class)
                .buildSessionFactory();
      // create session
        Session session = sessionFactory.getCurrentSession();
        
        try {
            // use a session
            System.out.println("DEBUG : Creating Student Object...");
            // create 3 students
            Section20Student student1 = new Section20Student("Tom","Volks","a1bc@ghj.com");
            Section20Student student2 = new Section20Student("Donald","Trump","a2bc@ghj.com");
            Section20Student student3 = new Section20Student("Hugo","Boss","a3bc@ghj.com");
            // start transaction
            session.beginTransaction();
            // save student object
            System.out.println("DEBUG : Saving Students ...");
            session.save(student1);
            session.save(student2);
            session.save(student3);
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
