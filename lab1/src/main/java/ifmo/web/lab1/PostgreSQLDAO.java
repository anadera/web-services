package ifmo.web.lab1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQLDAO {
//    private Connection connection;
//
//    public PostgreSQLDAO(){};
//
//    public PostgreSQLDAO(Connection connection) {
//        this.connection = connection;
//    }

    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from items");
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String barcode = rs.getString("barcode");
                String shop = rs.getString("shop");
                Double weight = rs.getDouble("weight");
                Double price = rs.getDouble("price");
                Item item = new Item(id, name, barcode, shop, weight, price);
                items.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    public List<Item> findItem(String id, String name, String barcode, String shop, String weight, String price) {
        ArrayList<String> query_where = new ArrayList<String>();
        if (!id.isEmpty()) query_where.add("id='" + id + "'");
        if (!name.isEmpty()) query_where.add("name='" + name + "'");
        if (!barcode.isEmpty()) query_where.add("barcode='" + barcode + "'");
        if (!shop.isEmpty()) query_where.add("shop='" + shop + "'");
        if (!weight.isEmpty()) query_where.add("weight='" + weight + "'");
        if (!price.isEmpty()) query_where.add("price='" + price + "'");

        String query = new String();
        if (query_where.size() > 0)
            query = "select * from \"Items\" where " + join(query_where, " and ");
        List<Item> items = findItem(query);
        return items;
    }

    public List<Item> findItem(String query) {
        List<Item> items = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String barcode = rs.getString("barcode");
                String shop = rs.getString("shop");
                String weight = rs.getString("weight");
                String price = rs.getString("price");

                Item item = new Item(id, name, barcode, shop, Double.parseDouble(weight), Double.parseDouble(price));
                items.add(item);
                System.out.println("get item");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    
    public static String join(List<String> list, String delim) {
        StringBuilder sb = new StringBuilder();
        String loopDelim = "";
        for(String s : list) {
            sb.append(loopDelim);
            sb.append(s);     
            loopDelim = delim;
        }
        return sb.toString();
        }
}