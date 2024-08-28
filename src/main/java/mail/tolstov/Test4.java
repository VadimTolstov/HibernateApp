package mail.tolstov;

import mail.tolstov.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

//createSelectionQuery для неизменяющих обращений к БД и createMutationQuery для изменяющих.
// Ранее интерфейсы SelectionQuery и MutationQuery реализовывал один интерфейс Query,
// но для более явного разделения типов обращений они используются по-отдельности,
// плюс это позволяет обрабатывать некоторые исключения связанные с неверным типом обращения к базе.
public class Test4 {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        try (SessionFactory sessionfactory = configuration.buildSessionFactory()) {
            Session session = sessionfactory.getCurrentSession();
            session.beginTransaction();


            session.createMutationQuery("update Person set name = 'Alex2' where id =5").executeUpdate();

            List<Person> personList = session.createSelectionQuery("from Person", Person.class).getResultList();

            for (Person p : personList)
                System.out.println(p);
            session.getTransaction().commit();

            for (Person p : personList)
                System.out.println(p);
        }
    }
}
