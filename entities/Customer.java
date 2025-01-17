package Proyecto.TapiceriaConfort.entities;

import Proyecto.TapiceriaConfort.constants.EntityStorage;
import Proyecto.TapiceriaConfort.storage.Storable;

public class Customer extends Person implements Storable {
    private ShoppingCart shoppingCart;
    private Service service;

    public Customer() {
        this.service = new Service();
        this.shoppingCart = new ShoppingCart();
    }

    public Customer(Long id, String name, String email, Integer phoneNumber, ShoppingCart shoppingCart, Service service) {
        super(id, name, email, phoneNumber);
        this.shoppingCart = shoppingCart;
        this.service = service;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void buyProduct() {
        System.out.println("Buying product..");
    }

    public void requestService() {
        this.service.setUserId(this.getId());
        EntityStorage.serviceStorage.save(this.service);
        System.out.println("Service saved");
        System.out.println("Details about the service:" +
                "\nName: " + this.service.getName() +
                "\nId: " + getId());
    }

    public void displayDetails() {
        System.out.println("------------------------------------");
        System.out.println("The products in the cart are:");
        for (Product product : shoppingCart.getProduct()) {
            System.out.println(product.getName());
        }
        System.out.println("------------------------------------");
    }

    @Override
    public String serialize() {
        return super.serialize() + "," + this.service.getId();
    }

    @Override
    public Storable deserialize(String line) {
        String[] fields = line.split(",");
        Service dsService = EntityStorage.serviceStorage.find(new Service(), Service -> service.getId().equals(Long.valueOf(fields[4]))).orElse(null);
        return new Customer(Long.valueOf(fields[0]), fields[1], fields[2], Integer.valueOf(fields[3]), new ShoppingCart(), dsService);
    }
}
