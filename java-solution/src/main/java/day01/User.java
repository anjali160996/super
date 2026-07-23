package day01;


public class User implements Identifiable{

    public Long id;
    public String name;

    @Override
    public Long getId() {
        return id;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
