package ifmo.web.lab1;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import ifmo.web.lab1.ex.EmptyIdentifierException;
import ifmo.web.lab1.ex.EmptyIdentifierExceptionBean;
import ifmo.web.lab1.ex.NonKeySpecifiedException;
import ifmo.web.lab1.ex.NonKeySpecifiedExceptionBean;


@WebService(serviceName = "ItemService")
public class ItemWebService {

    @WebMethod(operationName = "getItems")
    public List<Item> getItems() {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Item> Items = dao.getItems();
        return Items;
    }

    @WebMethod(operationName = "insertItem")
    public Integer insertItem(String name, String barcode, String shop, String weight, String price) throws NonKeySpecifiedException {
         if ((name == null || name.isEmpty()) &&
                (barcode == null || barcode.isEmpty()) &&
                (shop == null || shop.isEmpty()) &&
                (weight == null || weight.isEmpty())&&
                (price == null || price.isEmpty()))
        {
            NonKeySpecifiedExceptionBean fault = new NonKeySpecifiedExceptionBean();
            throw new NonKeySpecifiedException("No one key is specified.", fault);
        }
        PostgreSQLDAO dao = new PostgreSQLDAO();
        Integer ItemId = dao.insertItem(name, barcode, shop, weight, price);
        return ItemId;
    }

    @WebMethod(operationName = "updateItem")
    public String updateItem(String id, String name, String barcode, String shop, String weight, String price) throws EmptyIdentifierException, NonKeySpecifiedException {
        if (id == null || id.isEmpty())
        {
            EmptyIdentifierExceptionBean fault = new EmptyIdentifierExceptionBean();
            throw new EmptyIdentifierException("Product identifier is not specified.", fault);
        }
        if ((name == null || name.isEmpty()) &&
                (barcode == null || barcode.isEmpty()) &&
                (shop == null || shop.isEmpty()) &&
                (weight == null || weight.isEmpty())&&
                (price == null || price.isEmpty()))
        {
            NonKeySpecifiedExceptionBean fault = new NonKeySpecifiedExceptionBean();
            throw new NonKeySpecifiedException("No one key is specified.", fault);
        }
        PostgreSQLDAO dao = new PostgreSQLDAO();
        String status = dao.updateItem(id, name,barcode, shop, weight, price) ;
        return status;
    }

    @WebMethod(operationName = "deleteItem")
    public String deleteItem(String id) throws EmptyIdentifierException {
        if (id == null || id.isEmpty())
        {
            EmptyIdentifierExceptionBean fault = new EmptyIdentifierExceptionBean();
            throw new EmptyIdentifierException("Product identifier is not specified.", fault);
        }
        PostgreSQLDAO dao = new PostgreSQLDAO();
        String status = dao.deleteItem(id) ;
        return status;
    }

    @WebMethod(operationName = "findItem")
    public List<Item> findItem(String id, String name, String barcode, String shop, String weight, String price) throws NonKeySpecifiedException {
        if ((name == null || name.isEmpty()) &&
                (barcode == null || barcode.isEmpty()) &&
                (shop == null || shop.isEmpty()) &&
                (weight == null || weight.isEmpty())&&
                (price == null || price.isEmpty()))
        {
            NonKeySpecifiedExceptionBean fault = new NonKeySpecifiedExceptionBean();
            throw new NonKeySpecifiedException("No one key is specified.", fault);
        }
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Item> Items = dao.findItem(id, name,barcode, shop, weight, price) ;
        return Items;
    }

}