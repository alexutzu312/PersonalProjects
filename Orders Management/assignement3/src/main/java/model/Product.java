package model;
/**
 * plasa Produs contine id-ul produsului, greutatea acestuia, cantitatea din depozit si denumirea lui
 * contine gettere si settere
 */
public class Product {
    private int id;
    private int weight;
    private int quantity;
    private String name;
    public Product()
    {

    }
    public Product(int id, int weight, int quantity, String name) {
        this.id = id;
        this.weight = weight;
        this.quantity = quantity;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
