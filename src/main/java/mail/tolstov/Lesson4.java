package mail.tolstov;


import mail.tolstov.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */

//обновление и удаление по одному
public class Lesson4 {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        try (SessionFactory sessionfactory = configuration.buildSessionFactory()) {
            Session session = sessionfactory.getCurrentSession();
            session.beginTransaction();

            Person person2 = session.get(Person.class, 2);
            //обновляем данные в таблице
            person2.setName("New name");
            //удаление
            session.delete(person2);
            //   session.delete(person3);
            session.getTransaction().commit();
        }
    }
}
