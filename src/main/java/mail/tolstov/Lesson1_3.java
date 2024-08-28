package mail.tolstov;


import mail.tolstov.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

/**
 * Hello world!
 */
public class Lesson1_3 {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        try (SessionFactory sessionfactory = configuration.buildSessionFactory()) {
            Session session = sessionfactory.getCurrentSession();
            session.beginTransaction();
            Person person1 = new Person("Test1", 30);
            Person person2 = new Person("Test2", 40);
            Person person3 = new Person("Test2", 50);

            session.persist(person1);
            session.persist(person2);
            session.persist(person3);

            session.getTransaction().commit();
        }
    }
}
