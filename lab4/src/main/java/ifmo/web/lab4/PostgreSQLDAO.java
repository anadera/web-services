package ifmo.web.lab4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQLDAO {

    public List<Item> getItems()
    {
        List<Item> Items = new ArrayList<Item>();
        try {
            Connection connection = ConnectionUtil.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from \"items\"");

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String barcode = rs.getString("barcode");
                String shop = rs.getString("shop");
                String weight = rs.getString("weight");
                String price = rs.getString("price");
                Item item = new Item(id, name, barcode, shop, weight, price);
                Items.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Items;
    }

    public List<Item> findItem(String id, String name, String barcode, String shop, String weight, String price)
    {
        System.out.println("Debug findItem params: " + id + " " + name + " " + barcode + " " + shop + " " + price);

        ArrayList<String> query_where = new ArrayList<String>();
        if ((id != null) && !id.isEmpty()) query_where.add("id=" + id + "");
        if ((name != null) && !name.isEmpty()) query_where.add("name='" + name + "'");
        if ((barcode != null) && !barcode.isEmpty()) query_where.add("barcode='" + barcode + "'");
        if ((shop != null) && !shop.isEmpty()) query_where.add("shop='" + shop + "'");
        if ((weight != null) && !weight.isEmpty()) query_where.add("weight=" + weight + "");
        if ((price != null) && !price.isEmpty()) query_where.add("price=" + price + "");

        String query = new String();
        if (query_where.size() > 0)
            query = "select * from \"items\" where " + ConnectionUtil.join(query_where, " and ");
        System.out.println("Debug findItem query: " + query);

        List<Item> Items = findItem(query);
        return Items;
    }

    public List<Item> findItem(String query) {
        List<Item> Items = new ArrayList<>();
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

                Item Item = new Item(id, name, barcode, shop, weight, price);
                Items.add(Item);
                System.out.println("get Item");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Items;
    }

    public Integer insertItem(String name, String barcode, String shop, String weight, String price)
    {
        ArrayList<String> keys = new ArrayList<String>();
        ArrayList<String> values = new ArrayList<String>();
        if ((name != null) && !name.isEmpty())
        {
            keys.add("name");
            values.add("?");
        }
        if ((shop != null) && !shop.isEmpty())
        {
            keys.add("shop");
            values.add("?");
        }
        if ((barcode != null) && !barcode.isEmpty())
        {
            keys.add("barcode");
            values.add("?");
        }
        if ((weight != null ) && !weight.isEmpty())
        {
            keys.add("weight");
            values.add("?");
        }
        if ((price != null) && !price.isEmpty())
        {
            keys.add("price");
            values.add("?");
        }

        String query = "INSERT INTO \"items\"(" + ConnectionUtil.join(keys, ", ") + ") VALUES (" + ConnectionUtil.join(values, ", ") + ")";
        System.out.println(query);

        Integer id = -1;
        try (Connection connection = ConnectionUtil.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int index = 1;
            if ((name != null) && !name.isEmpty()) {
                stmt.setString(index, name);
                ++index;
            }
            if ((shop != null) && !shop.isEmpty()) {
                stmt.setInt(index, Integer.parseInt(shop));
                ++index;
            }
            if ((barcode != null) && !barcode.isEmpty()) {
                stmt.setString(index, barcode);
                ++index;
            }
            if ((weight != null ) && !weight.isEmpty()) {
                stmt.setString(index, weight);
                ++index;
            }
            if ((price != null) && !price.isEmpty()) {
                stmt.setDouble(index, Double.parseDouble(price));
            }

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
                System.out.println("Insert row with id = "+id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public String updateItem(String id, String name, String barcode, String shop, String weight, String price)
    {
        if (id.isEmpty())
            return "Wrong query: Id is empty";

        ArrayList<String> keys = new ArrayList<String>();
        if ((name != null) && !name.isEmpty())
        {
            keys.add("name=?");
        }
        if ((shop != null) && !shop.isEmpty())
        {
            keys.add("shop=?");
        }
        if ((barcode != null) && !barcode.isEmpty())
        {
            keys.add("barcode=?");
        }
        if ((weight != null) && !weight.isEmpty())
        {
            keys.add("weight=?");
        }
        if ((price != null) && !price.isEmpty())
        {
            keys.add("price=?");
        }

        String query = "UPDATE \"items\" SET " + ConnectionUtil.join(keys, ", ") + " WHERE id=?";
        System.out.println(query);

        String status;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);

            int index = 1;
            if ((name != null) && !name.isEmpty()) {
                stmt.setString(index, name);
                ++index;
            }
            if ((shop != null) && !shop.isEmpty()) {
                stmt.setInt(index, Integer.parseInt(shop));
                ++index;
            }
            if ((barcode != null) && !barcode.isEmpty()) {
                stmt.setString(index, barcode);
                ++index;
            }
            if ((weight != null) && !weight.isEmpty()) {
                stmt.setString(index, weight);
                ++index;
            }
            if ((price != null) && !price.isEmpty()) {
                stmt.setDouble(index, Double.parseDouble(price));
                ++index;
            }
            stmt.setInt(index, Integer.parseInt(id));

            int count_row = stmt.executeUpdate();
            status = (count_row > 0) ? ("Updated " + count_row + " row") : "No updated row";
        } catch (SQLException ex) {
            status = "Error:" + ex.toString();
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public String deleteItem(String id)
    {
        if (id.isEmpty())
            return "Wrong query: Id is empty";

        String status;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("DELETE from \"items\" where id=?");
            stmt.setInt(1, Integer.parseInt(id));
            int count_row = stmt.executeUpdate();
            status = (count_row > 0) ? ("Deleted " + count_row + " row") : "No deleted row";
        } catch (SQLException ex) {
            status = "Error:" + ex.toString();
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

}
