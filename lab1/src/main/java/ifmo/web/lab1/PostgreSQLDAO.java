package ifmo.web.lab1;

import ifmo.web.lab1.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQLDAO {

    public List<Item> getItems()
    {
        List<Item> items = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
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
                items.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    public Integer insertItem(String name, String barcode, String shop, String weight, String price)
    {
        ArrayList<String> keys = new ArrayList<String>();
        ArrayList<String> values = new ArrayList<String>();
        if (!name.isEmpty())
        {
            keys.add("name");
            values.add("?");
        }
        if (!shop.isEmpty())
        {
            keys.add("shop");
            values.add("?");
        }
        if (!barcode.isEmpty())
        {
            keys.add("barcode");
            values.add("?");
        }
        if (!weight.isEmpty())
        {
            keys.add("weight");
            values.add("?");
        }
        if (!barcode.isEmpty())
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
            if (!name.isEmpty()) {
                stmt.setString(index, name);
                ++index;
            }
            if (!shop.isEmpty()) {
                stmt.setString(index, shop);
                ++index;
            }
            if (!barcode.isEmpty()) {
                stmt.setString(index, barcode);
                ++index;
            }
            if (!weight.isEmpty()) {
                stmt.setString(index, weight);
                ++index;
            }
            if (!price.isEmpty()) {
                stmt.setString(index, price);
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
        if (!name.isEmpty())
        {
            keys.add("name=?");
        }
        if (!shop.isEmpty())
        {
            keys.add("shop=?");
        }
        if (!barcode.isEmpty())
        {
            keys.add("barcode=?");
        }
        if (!weight.isEmpty())
        {
            keys.add("weight=?");
        }
        if (!barcode.isEmpty())
        {
            keys.add("price=?");
        }

        String query = "UPDATE \"items\" SET " + ConnectionUtil.join(keys, ", ") + " WHERE id=?";
        System.out.println(query);

        String status;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);

            int index = 1;
            if (!name.isEmpty()) {
                stmt.setString(index, name);
                ++index;
            }
            if (!shop.isEmpty()) {
                stmt.setString(index, shop);
                ++index;
            }
            if (!barcode.isEmpty()) {
                stmt.setString(index, barcode);
                ++index;
            }
            if (!weight.isEmpty()) {
                stmt.setString(index, weight);
                ++index;
            }
            if (!price.isEmpty()) {
                stmt.setString(index, price);
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

    public List<Item> findItem(String id, String name, String barcode, String shop, String weight, String price)
    {
        ArrayList<String> query_where = new ArrayList<String>();
        if (!id.isEmpty()) query_where.add("id='" + id + "'");
        if (!name.isEmpty()) query_where.add("name='" + name + "'");
        if (!barcode.isEmpty()) query_where.add("barcode='" + barcode + "'");
        if (!shop.isEmpty()) query_where.add("shop='" + shop + "'");
        if (!weight.isEmpty()) query_where.add("weight=" + weight + "");
        if (!price.isEmpty()) query_where.add("price=" + price + "");

        String query = new String();
        if (query_where.size() > 0)
            query = "select * from \"items\" where " + ConnectionUtil.join(query_where, " and ");

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

                Item Item = new Item(id, name, barcode, shop, weight, price);
                items.add(Item);
                System.out.println("get item");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
}