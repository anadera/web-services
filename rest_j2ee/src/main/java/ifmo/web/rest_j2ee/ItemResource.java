package ifmo.web.rest_j2ee;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;

@RequestScoped
@Path("/items")
@Produces({MediaType.APPLICATION_JSON})
public class ItemResource {

    @Resource(lookup = "jdbc/postgresdb")
    private DataSource dataSource;

    @GET
    public List<Item> getPersons(@QueryParam("id") String id,
                                          @QueryParam("name") String name,
                                          @QueryParam("barcode") String barcode,
                                          @QueryParam("shop") String shop,
                                          @QueryParam("weight") String weight,
                                          @QueryParam("price") String price) {
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
        price = (price == null) ? new String() : price;

        List<Item> items = new PostgreSQLDAO().findItem(id, name, barcode, shop, weight, price);
        return items;
    }

    private Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection(); }
        catch (SQLException ex) {
            Logger.getLogger(ItemResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}