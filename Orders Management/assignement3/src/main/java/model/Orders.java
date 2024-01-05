package model;

/**
 * clasa Orders contine id, id-ul clientului(cumparatorului), id-ul produsului cumparat
 * si in ce cantitate se doreste produsul respectiv
 * contine gettere si settere
 */
public class Orders {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;
    public Orders()
    {

    }
    public Orders(int id, int clientId, int productId, int quantity) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
