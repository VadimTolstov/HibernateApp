package mail.tolstov.one_to_one.v2;

import mail.tolstov.one_to_one.v2.model.Passport;
import mail.tolstov.one_to_one.v2.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToOne {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            System.out.println("Создаем человека и паспорт");
            Person person = new Person("Test person4", 50);
            Passport passport = new Passport(12345);
            person.setPassport(passport);
            session.persist(person);

//            System.out.println("Получаем данные из базы");
//            Person person1 = session.get(Person.class, 1);
//            System.out.println(person1.getPassport().getPassportNumber());

//            System.out.println("Изменяем данные паспорта у человека");
//            Person person2 = session.get(Person.class, 1);
//            person2.getPassport().setPassportNumber(77777);
//            System.out.println(person2.getPassport().getPassportNumber());

//            System.out.println("Удаляем человека и паспорт (включено каскадирование и паспорт удалится за одно с человеком");
//            Person person3 = session.get(Person.class, 1);
//            session.remove(person3);

            session.getTransaction().commit();
        }
    }
}
