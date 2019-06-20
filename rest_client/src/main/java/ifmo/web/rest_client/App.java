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
        processQueryToStandalone(client);
    }
	
    private static void processQueryToStandalone(Client client) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
			System.out.println("Wrong choice");
			break;
		}
            }
            catch (IOException e) {
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
		
        GenericType<List<Item>> type = new GenericType<List<Item>>(){};
        printList(response.getEntity(type));
	}
	
    private static void findItem(Client client, BufferedReader br)
    {
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
	
    private static void printList(List<Item> items) {
        for (Item item : items) {
            System.out.println(item);
	}
    }
}