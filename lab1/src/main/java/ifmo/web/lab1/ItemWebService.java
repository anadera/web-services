package ifmo.web.lab1;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "ItemService")
public class ItemWebService {

    @WebMethod(operationName = "getItems")
    public List<Item> getItems() {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Item> Items = dao.getItems();
        return Items;
    }

    @WebMethod(operationName = "insertItem")
    public Integer insertItem(String name, String barcode, String shop, String weight, String price) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        Integer ItemId = dao.insertItem(name, barcode, shop, weight, price);
        return ItemId;
    }

    @WebMethod(operationName = "updateItem")
    public String updateItem(String id, String name, String barcode, String shop, String weight, String price) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        String status = dao.updateItem(id, name,barcode, shop, weight, price) ;
        return status;
    }

    @WebMethod(operationName = "deleteItem")
    public String deleteItem(String id) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        String status = dao.deleteItem(id) ;
        return status;
    }

    @WebMethod(operationName = "findItem")
    public List<Item> findItem(String id, String name, String barcode, String shop, String weight, String price) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Item> Items = dao.findItem(id, name,barcode, shop, weight, price) ;
        return Items;
    }

}