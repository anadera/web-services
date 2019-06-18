package ifmo.web.lab1;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(serviceName = "ItemService")
public class ItemWebService {
//    @Resource(lookup = "jdbc/postgresdb")
//    private DataSource dataSource;

    @WebMethod(operationName = "getItems")
    public List<Item> getItems() {
        PostgreSQLDAO dao = new PostgreSQLDAO(); //); getConnection());
        return dao.getItems();
    }

    @WebMethod(operationName = "findItem")
    public List<Item> findItem(String id, String name, String barcode, String shop, String weight, String price) {
        PostgreSQLDAO dao = new PostgreSQLDAO(); //getConnection());
        return dao.findItem(id, name, barcode, shop, weight, price);
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