package mail.tolstov.relationship.one_to_many.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "one_to_many", name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "owner",fetch = FetchType.LAZY)//FetchType.LAZY ленивая загрузка, cascade = CascadeType.PERSIST) каскадирование так или ниже
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST})//, org.hibernate.annotations.CascadeType.REFRESH})
    private List<Item> items;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item){
        if (this.items == null){
            this.items = new ArrayList<>();
        }

        this.items.add(item);
        item.setOwner(this);
    }
}
