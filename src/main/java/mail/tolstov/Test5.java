package mail.tolstov;

import mail.tolstov.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

//удаление объекта
public class Test5 {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        try (SessionFactory sessionfactory = configuration.buildSessionFactory()) {
            Session session = sessionfactory.getCurrentSession();
            session.beginTransaction();

            session.persist(new Person("Anatolii", 77));
            // удалить работника по id
            //  session.remove(1);
            session.getTransaction().commit();
        }

        Configuration configuration2 = new Configuration().addAnnotatedClass(Person.class);

        try (SessionFactory sessionfactory = configuration2.buildSessionFactory()) {
            Session session = sessionfactory.getCurrentSession();
            session.beginTransaction();
            //Удалить с помощью createMutationQuery
            session.createMutationQuery("delete Person WHERE name = 'Anatolii'").executeUpdate();

            session.getTransaction().commit();
        }
    }
}
