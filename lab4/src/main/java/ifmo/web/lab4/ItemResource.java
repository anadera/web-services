package ifmo.web.lab4;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/items")
@Produces({MediaType.APPLICATION_JSON})
public class ItemResource {
    @GET
    public List<Item> getItems(@QueryParam("id") String id,
    @QueryParam("name") String name,
    @QueryParam("barcode") String barcode,
    @QueryParam("shop") String shop,
    @QueryParam("weight") String weight,
    @QueryParam("price") String price)
    {
        if ((id == null || id.isEmpty()) &&
                (name == null || name.isEmpty()) &&
                (barcode == null || barcode.isEmpty()) &&
                (shop == null || shop.isEmpty()) &&
                (weight == null || weight.isEmpty())&&
                (price == null || price.isEmpty())) {
                List<Item> items = new PostgreSQLDAO().getItems();
                return items;
        }

        id = (id == null) ? new String() : id;
        name = (name == null) ? new String() : name;
        barcode = (barcode == null) ? new String() : barcode;
        shop = (shop == null) ? new String() : shop;
        weight = (weight == null) ? new String() : weight;
        price = (price == null) ? new String() : price;

        List<Item> items = new PostgreSQLDAO().findItem(id, name, barcode, shop, weight, price);
        return items;
    }
}