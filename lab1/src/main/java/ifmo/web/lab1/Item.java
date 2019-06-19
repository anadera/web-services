package ifmo.web.lab1;

public class Item {
    
    //private Integer id;
    private String name;
    private String barcode;
    private String shop; 
    private Double weight;
    private Double price;

    public Item() {
    }

    public Item(String name, String barcode, String shop, Double weight, Double price) {
        //this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.shop = shop;
        this.weight = weight;
        this.price = price;
    }

    //public Integer getId() { return id; }
    public String getName() { return name;}
    public String getBarcode() { return barcode; }
    public String getShop() { return shop; }
    public double getWeight() { return weight; }
    public double getPrice() { return price; }

    //public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBarcode(String barcode) { this.barcode = barcode; }
    public void setShop(String shop) { this.shop = shop; }
    public void setWeight(Double weight) { this.weight = weight; }  
    public void setPrice(Double price) { this.price = price; }   
}
