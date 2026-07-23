package day01;

public class Product implements Identifiable {

    public Long id;
    public String name;

    @Override
    public Long getId() {
        return id;
    }

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

