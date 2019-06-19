package ifmo.web.client;

import ifmo.web.client.Item;
import ifmo.web.client.ItemService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/PersonService?wsdl");
        ItemService itemService = new ItemService(url);
        List<Item> items = itemService.getItemWebServicePort().getItems();
        for (Item  item : items) {
            System.out.println("name: " + item.getName() +
            ", barcode: " + item.getBarcode() + ", shop: " + item.getShop());
        }
        System.out.println("Total items: " + items.size());
    }
}