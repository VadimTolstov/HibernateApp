package mail.tolstov;

import mail.tolstov.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

//получение данных
public class Test3 {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        try (SessionFactory sessionfactory = configuration.buildSessionFactory()) {
            Session session = sessionfactory.getCurrentSession();
            session.beginTransaction();

            List<Person> personList = session.createQuery("from Person", Person.class).getResultList();

            for (Person p: personList)
                System.out.println(p);
            session.getTransaction().commit();
        }
    }
}
