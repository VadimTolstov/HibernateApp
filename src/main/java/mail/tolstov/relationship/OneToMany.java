package mail.tolstov.relationship;

import mail.tolstov.relationship.one_to_many.model.Item;
import mail.tolstov.relationship.one_to_many.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OneToMany {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        try (SessionFactory sessionfactory = configuration.buildSessionFactory()) {
            Session session = sessionfactory.getCurrentSession();
            session.beginTransaction();

            Item item = session.get(Item.class, 1);
            System.out.println("Предмет найден " + item);

            Person person1 = item.getOwner();
            System.out.println("Человек у которого эта вещь " + person1);

            Person person2 = session.get(Person.class, 3);
            System.out.println("Человек найден " + person1);

            List<Item> items = person2.getItems();

            System.out.println("Список вещей человека " + items);

            System.out.println("Добавим товар для человека");
            Person person3 = session.get(Person.class, 2);
            Item newItem = new Item("Item from Hibernate", person3);
            person3.getItems().add(newItem);//эта строка нужна для установки двусторонней связи, Hibernate кэширует данные и при новом запросе без этой строки мы можем получать старые данные без newItem, она не создает sql запрос просто добавляет в кэш Hibernate новые данные в
            session.persist(newItem);

            System.out.println("Создаем нового человека и новый заказ ");
            Person person4 = new Person("Test person", 30);
            Item item1 = new Item("Item from Hibernate 2 ", person4);
            person4.setItems(new ArrayList<>(Collections.singletonList(item1)));
            session.persist(person4);
            session.persist(item1);

            System.out.println("Удаляем товары у человека ");
            List<Item> items2 = person3.getItems();
            for (Item item2 : items2) {
                session.remove(item2);
            }
            person3.getItems().clear();//все так же для кэша стр39

            System.out.println("Удаляем человека ");
            session.remove(person4);
            person4.getItems().forEach(i -> i.setOwner(null));//для кэша стр39

            System.out.println("Меняем владельца у существующего товара ");
            Person person5 = session.get(Person.class, 1);
            Item item3 = session.get(Item.class, 6);
            item3.getOwner().getItems().remove(item3);//для кэша стр39
            item3.setOwner(person5);
            person5.getItems().add(item3);

            session.getTransaction().commit();
        }
    }
}
