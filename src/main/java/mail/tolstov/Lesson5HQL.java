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
public class Lesson5HQL {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        try (SessionFactory sessionfactory = configuration.buildSessionFactory()) {
            Session session = sessionfactory.getCurrentSession();
            session.beginTransaction();
//старый способ
//            List<Person> people = session.createQuery("from Person where name like 'T%'", Person.class).getResultList();
//            session.createQuery("update Person  set name = 'Test' where age < 30", Person.class).executeUpdate();

            List<Person> people = session.createSelectionQuery("from Person where name like 'T%'", Person.class).getResultList();
            session.createMutationQuery("update Person  set name = 'Test' where age < 30").executeUpdate();

            for (Person person : people){
                System.out.println(person);
            }

            session.getTransaction().commit();
        }
    }
}
