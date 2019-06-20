package ifmo.web.client;

import ifmo.web.client.Item;
import ifmo.web.client.ItemService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {

    private static ItemService ItemService;
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/ItemService?wsdl");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Choose service type: 1-standalone, 2-J2EE");

        try {
            String serviceType = br.readLine();
            switch (serviceType) {
                case "1":
                    ItemService = new ItemService(url);
                    processQueryToStandalone();
                    break;
                case "2":
                    ItemService = new ItemService(new URL("http://localhost:8080/WebServiceJ2EEServer_war_exploded/ItemService?wsdl"));
                    processQueryToJ2EE();
                    break;
                default:
                    System.out.println("Wrong enter!");
                    return;
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    private static void processQueryToStandalone()
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Please, select the type of action: 0 - select all, 1 - select, 2 - insert, 3 - update, 4 - delete, 5 - quit");
            try {
                String actionType = br.readLine();
                try {
                    switch (actionType) {
                        case "0":
                            getAllItems();
                            break;
                        case "1":
                            findItem(br);
                            break;
                        case "2":
                            insertItem(br);
                            break;
                        case "3":
                            updateItem(br);
                            break;
                        case "4":
                            deleteItem(br);
                            break;
                        case "5":
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Wrong choose");
                            break;
                    }
                }
                catch (Exception ex){
                        System.out.println("Received error: " + ex.getMessage());
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    private static void processQueryToJ2EE() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            findItem(br);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    private static void getAllItems()
    {
        List<Item> Items = ItemService.getItemWebServicePort().getItems();
        printItems(Items);
    }

    private static void findItem(BufferedReader br) throws IOException
    {
        System.out.println("Please, write your query: key=value, separated by comma.");
        System.out.println("Available keys: id, name, barcode, shop, weight, price.");

        String userQuery = br.readLine();
        String keysValue[] = userQuery.split(",");
        String id = "", name = "", barcode = "", shop = "", weight = "", price = "";
        for (int j = 0; j < keysValue.length; j++) {
            String temp[] = keysValue[j].split("=");
            switch (temp[0]) {
                case "id":
                    id = temp[1];
                    break;
                case "name":
                    name = temp[1];
                    break;
                case "barcode":
                    barcode = temp[1];
                    break;
                case "shop":
                    shop = temp[1];
                    break;
                case "weight":
                    weight = temp[1];
                    break;
                case "price":
                    price = temp[1];
                    break;
            }
        }

        List<Item> Items = ItemService.getItemWebServicePort().findItem(name, barcode, shop, weight, price);
        printItems(Items);
    }

    private static void insertItem(BufferedReader br) throws IOException
    {
        System.out.println("Please, write insert query options: key=value, separated by comma.");
        System.out.println("Option keys: name, barcode, shop, weight, price.");

        String userQuery = br.readLine();
        String keysValue[] = userQuery.split(",");
        String name = "", barcode = "", shop = "", weight = "", price = "";
        for (int j = 0; j < keysValue.length; j++) {
            String temp[] = keysValue[j].split("=");
            switch (temp[0]) {
                case "name":
                    name = temp[1];
                    break;
                case "barcode":
                    barcode = temp[1];
                    break;
                case "shop":
                    shop = temp[1];
                    break;
                case "weight":
                    weight = temp[1];
                    break;
                case "price":
                    price = temp[1];
                    break;
                default:
                    System.out.println("Wrong key");
                    break;
            }
        }

        Integer id = ItemService.getItemWebServicePort().insertItem(name, barcode, shop, weight, price);
        if (id >= 0)
            System.out.println("Item has been inserted successfully. Id = " + id);
        else
            System.out.println("Something went wrong while insertion");
    }

    private static void updateItem(BufferedReader br) throws IOException
    {
        System.out.println("Please, enter new fields values and the id of the updated product: key=value, separated by comma.");
        System.out.println("Available keys for update: name, barcode, shop, weight, price.");

        String userQuery = br.readLine();
        String keysValue[] = userQuery.split(",");
        String id = "", name = "", barcode = "", shop = "", weight = "", price = "";
        for (int j = 0; j < keysValue.length; j++) {
            String temp[] = keysValue[j].split("=");
            switch (temp[0]) {
                case "id":
                    id = temp[1];
                    break;
                case "name":
                    name = temp[1];
                    break;
                case "barcode":
                    barcode = temp[1];
                    break;
                case "shop":
                    shop = temp[1];
                    break;
                case "weight":
                    weight = temp[1];
                    break;
                case "price":
                    price = temp[1];
                    break;
            }
        }

        String status = ItemService.getItemWebServicePort().updateItem(id, name, barcode, shop, weight, price);
        System.out.println("Status: " + status);
    }

    private static void deleteItem(BufferedReader br) throws IOException
    {
        System.out.println("Please, enter the id of the product you want to delete.");
        String id = br.readLine();
        String status = ItemService.getItemWebServicePort().deleteItem(id);
        System.out.println("Status: " + status);
    }

    private static void printItems(List<Item> Items)
    {
        for (Item Item : Items) {
            System.out.println("Item{id = " + Item.getId() +
                    ", name = " + Item.getName() +
                    ", barcode = " + Item.getBarcode() +
                    ", shop = " + Item.getShop() +
                    ", weight = " + Item.getWeight() +
                    ", price = " + Item.getPrice() + "}");
        }
        System.out.println("Total Items: " + Items.size());
    }
}