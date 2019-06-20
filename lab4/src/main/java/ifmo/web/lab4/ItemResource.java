package ifmo.web.lab4;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import ifmo.web.lab4.ex.EmptyIdentifierException;
import ifmo.web.lab4.ex.NonKeySpecifiedException;

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
    
    @POST
    public String insertItem(@QueryParam("name") String name,
                                       @QueryParam("barcode") String barcode,
                                       @QueryParam("shop") String shop,
                                       @QueryParam("weight") String weight,
                                       @QueryParam("price") String price) throws NonKeySpecifiedException
     {
        if (isBodyFieldsEmpty(name, barcode, shop, weight, price))
        {
            throw NonKeySpecifiedException.DEFAULT_INSTANCE;
        }
        PostgreSQLDAO dao = new PostgreSQLDAO();
        Integer ItemId = dao.insertItem(name, barcode, shop, weight, price);
        return ItemId.toString();
    }

    @PUT
    public String updateItem(@QueryParam("id") String id,
                                      @QueryParam("name") String name,
                                      @QueryParam("barcode") String barcode,
                                      @QueryParam("shop") String shop,
                                      @QueryParam("weight") String weight,
                                      @QueryParam("price") String price) throws EmptyIdentifierException, NonKeySpecifiedException {
        if (id == null || id.isEmpty())
        {
            throw EmptyIdentifierException.DEFAULT_INSTANCE;
        }
        if (isBodyFieldsEmpty(name, barcode, shop, weight, price))
        {
            throw NonKeySpecifiedException.DEFAULT_INSTANCE;
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        String status = dao.updateItem(id, name, barcode, shop, weight, price) ;
        return status;
    }

    @DELETE
    public String deleteItem(@QueryParam("id") String id) throws EmptyIdentifierException
    {
        if (id == null || id.isEmpty())
        {
            throw EmptyIdentifierException.DEFAULT_INSTANCE;
        }
        PostgreSQLDAO dao = new PostgreSQLDAO();
        String status = dao.deleteItem(id);
        return status;
    }

    private Boolean isBodyFieldsEmpty(String name, String barcode, String shop, String weight, String price)
    {
        return (name == null || name.isEmpty()) &&
               (barcode == null || barcode.isEmpty()) &&
               (shop == null || shop.isEmpty()) &&
               (weight == null || weight.isEmpty())&&
               (price == null || price.isEmpty());
    }
}