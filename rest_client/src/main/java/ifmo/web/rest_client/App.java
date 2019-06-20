package ifmo.web.rest_client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.ws.rs.core.MediaType;

public class App {
    private static final String URL = "http://localhost:8080/rest/items";

    public static void main(String[] args) {
        Client client = Client.create();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Choose service type: 1-standalone, 2-J2EE");

        try {
            String serviceType = br.readLine();
            switch (serviceType) {
                case "1":
                    processQueryToStandalone(client, br);
                    break;
                case "2":
                    processQueryToJ2EE(client, br);
                    break;
                default:
                    System.out.println("Wrong enter!");
                    return;
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void processQueryToJ2EE(Client client, BufferedReader br) {
        while (true) {
            System.out.println("Please, select the type of action: 0 - print all, 1 - find, 2 - quit");
            try {
                String actionType = br.readLine();

                switch (actionType) {
                    case "0":
                        getAllItems(client);
                        break;
                    case "1":
                        findItem(client, br);
                        break;
                    case "2":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong choose");
                        break;
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    private static void processQueryToStandalone(Client client, BufferedReader br){
        while (true) {
            System.out.println("Please, select the type of action: 0 - select all, 1 - select, 2 - insert, 3 - update, 4 - delete, 5 - quit");
            try {
                String actionType = br.readLine();
                try {
                    switch (actionType) {
                        case "0":
                            getAllItems(client);
                            break;
                        case "1":
                            findItem(client, br);
                            break;
                        case "2":
                            insertItem(client, br);
                            break;
                        case "3":
                            updateItem(client, br);
                            break;
                        case "4":
                            deleteItem(client, br);
                            break;
                        case "5":
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Wrong choose");
                            break;
                    }
                }
                catch (Exception ex)
                {
                    System.out.println("Received error: " + ex.getMessage());
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    private static void getAllItems(Client client) {
        WebResource webResource = client.resource(URL);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed: " + response.toString());
        }

        GenericType<List<Item>> type = new GenericType<List<Item>>() {};
        printList(response.getEntity(type));
    }

    private static void findItem(Client client, BufferedReader br) {
        System.out.println("Please, write your query: key=value, separated by comma.");
        System.out.println("Available keys: id, name, producingСountry, vendorСode, weight, price.");

        try {
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

            WebResource webResource = client.resource(URL);
            webResource = fillQueryParameters(webResource, id, name, barcode, shop, weight, price);

            ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

            if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
                throw new IllegalStateException("Request failed: " + response.toString());
            }

            GenericType<List<Item>> type = new GenericType<List<Item>>() {};
            printList(response.getEntity(type));
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    private static void insertItem(Client client, BufferedReader br) throws IOException {
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

        WebResource webResource = client.resource(URL);
        webResource = fillQueryParameters(webResource, null, name, barcode, shop, weight, price);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class);

        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed: " + response.toString());
        }

        String id = response.getEntity(String.class);
        if (Integer.parseInt(id) >= 0)
            System.out.println("Beauty product has been inserted successfully. Id = " + id);
        else
            System.out.println("Something went wrong while insertion");
    }

    private static void updateItem(Client client, BufferedReader br) throws IOException {
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

        WebResource webResource = client.resource(URL);
        webResource = fillQueryParameters(webResource, id, name, barcode, shop, weight, price);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).put(ClientResponse.class);

        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed: " + response.toString());
        }

        String status = response.getEntity(String.class);
        System.out.println("Status: " + status);
    }

    private static void deleteItem(Client client, BufferedReader br) throws IOException {
        System.out.println("Please, enter the id of the product you want to delete.");
        String id = br.readLine();
        WebResource webResource = client.resource(URL);
        if (id != null) {
            webResource = webResource.queryParam("id", id);
        }
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
        String status = response.getEntity(String.class);
        System.out.println("Status: " + status);
    }

    private static void printList(List<Item> products) {
        for (Item product : products) {
            System.out.println(product);
        }
    }

    private static WebResource fillQueryParameters(WebResource webResource, String id, String name, String barcode, String shop, String weight, String price){
        if (id != null) {
            webResource = webResource.queryParam("id", id);
        }
        if (name != null) {
            webResource = webResource.queryParam("name", name);
        }
        if (barcode != null) {
            webResource = webResource.queryParam("barcode", barcode);
        }
        if (shop != null) {
            webResource = webResource.queryParam("shop", shop);
        }
        if (weight != null) {
            webResource = webResource.queryParam("weight", weight);
        }
        if (price != null) {
            webResource = webResource.queryParam("price", price);
        }
        return webResource;
    }
}
