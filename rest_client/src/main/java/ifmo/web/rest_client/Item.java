package ifmo.web.rest_client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item {
    
    private Integer id;
    private String name;
    private String barcode;
    private String shop; 
    private String weight;
    private String price;

    public Item() {
    }

    public Item(Integer id, String name, String barcode, String shop, String weight, String price) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.shop = shop;
        this.weight = weight;
        this.price = price;
    }

    public Integer getId() { return id; }
    public String getName() { return name;}
    public String getBarcode() { return barcode; }
    public String getShop() { return shop; }
    public String getWeight() { return weight; }
    public String getPrice() { return price; }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBarcode(String barcode) { this.barcode = barcode; }
    public void setShop(String shop) { this.shop = shop; }
    public void setWeight(String weight) { this.weight = weight; }  
    public void setPrice(String price) { this.price = price; }   

    @Override
    public String toString() {
    return "Item{" + "name=" + name + ", barcode=" + barcode + ", shop=" + shop + ", weight=" + weight + ", price=" + price + '}';
}
}
