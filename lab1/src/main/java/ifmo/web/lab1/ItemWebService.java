package ifmo.web.lab1;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "ItemService")
public class ItemWebService {
//    @Resource(lookup = "jdbc/postgresdb")
//    private DataSource dataSource;

    @WebMethod(operationName = "getItems")
    public List<Item> getItems() {
        PostgreSQLDAO dao = new PostgreSQLDAO(); //); getConnection());
        List<Item> items = dao.getItems();
        return items;
    }

    @WebMethod(operationName = "findItem")
    public List<Item> findItem(String name, String barcode, String shop, String weight, String price) {
        PostgreSQLDAO dao = new PostgreSQLDAO(); //getConnection());
        return dao.findItem(name, barcode, shop, weight, price);
    }

//    private Connection getConnection() {
//        Connection result = null;
//        try {
//            result = dataSource.getConnection();
//        }
//        catch (SQLException ex) {
//            Logger.getLogger(ItemWebService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
//    }
}