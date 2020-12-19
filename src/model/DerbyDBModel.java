package model;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Properties;

public class DerbyDBModel implements IModel {
    private final String framework = "embedded";
    private final String protocol = "jdbc:derby:";
    private final String dbName = "costManagerDB";
    private PreparedStatement psInsertCost;
    private PreparedStatement psInsertCategory;
    private Connection conn = null;
    private Properties props = null;
    private ResultSet rs = null;
    private Statement st = null;

    public DerbyDBModel() throws CostManagerException {
        setUpDerby();
    }

    public void shutDown() throws CostManagerException {
        try {
            // the shutdown=true attribute shuts down Derby
            DriverManager.getConnection("jdbc:derby:;shutdown=true");

        } catch (SQLException se) {
            if (((se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState())))) {
                System.out.println("Derby shut down normally");
            } else {
                System.out.println("Derby did not shut down normally");
            }
        }

        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException sqle) {
            System.out.println("problem when closing conn");
        }
    }

    public void setUpDerby() throws CostManagerException {
        System.out.println("Cost manager starting in " + framework + " mode");

        try {
            props = new Properties(); // connection properties
            conn = DriverManager.getConnection(protocol + dbName + ";create=true", props);

            System.out.println("Connected to and created database " + dbName);

            conn.setAutoCommit(false);
            st = conn.createStatement();

            // block of crating our tables
            try {
                st.execute("create table costs(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), date_ DATE, category varchar(40), currency varchar(5), amount double, description varchar(100))");
                st.execute("create table category(name varchar(20))");
            } catch (SQLException sqle) {
                if (sqle.getErrorCode() == 20000) {
                    System.out.println("tables exist - ok");
                } else
                    throw new CostManagerException("problem setting up derbyDB", sqle);
            }

            psInsertCost = conn.prepareStatement("insert into costs(date_,category,currency,amount,description) values (?, ?, ?, ?,?)");
            psInsertCategory = conn.prepareStatement("insert into category values(?)");
            conn.commit();
            System.out.println("Committed the transaction");

        } catch (SQLException sqle) {
            throw new CostManagerException("problem setting up derbyDB", sqle);
        }
    }

    @Override
    public boolean addCost(CostItem cost) throws CostManagerException {
        try {
            psInsertCost.setDate(1, cost.getDate());
            psInsertCost.setString(2, cost.getCategory());
            psInsertCost.setString(3, cost.getCurrency());
            psInsertCost.setDouble(4, cost.getSum());
            psInsertCost.setString(5, cost.getDescription());

            psInsertCost.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            throw new CostManagerException("problem with adding cost item", e);
        }

        return true;
    }

    @Override
    public boolean deleteCost(int id) throws CostManagerException {
        String query = "DELETE FROM costs WHERE id =" + id;

        try {
            st.executeUpdate(query);
            conn.commit();
        } catch (SQLException e) {
            throw new CostManagerException("problem with deleting cost item", e);
        }

        return true;
    }


    @Override
    public ArrayList<CostItem> getCosts(Date start, Date end) throws CostManagerException {

        String query = "SELECT * FROM costs WHERE date_ BETWEEN DATE('" + start.toLocalDate() + "') and DATE('" + end.toLocalDate() + "')";
        ArrayList<CostItem> ans = this.getCosts(query);

        return ans;
    }

    @Override
    public ArrayList<CostItem> getCosts(String query) throws CostManagerException {

        if (query == "") {
            query = "SELECT * FROM costs";
        }

        ArrayList<CostItem> ans = new ArrayList<CostItem>();
        try {
            rs = st.executeQuery(query);
            while (rs.next()) {
                ans.add(new CostItem(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getString(6)));
            }
        } catch (SQLException sqle) {
            throw new CostManagerException("problem getting all costs from derbyDB", sqle);
        }

        return ans;
    }

    @Override
    public boolean addCategory(Category c) throws CostManagerException {
        try {
            psInsertCategory.setString(1, c.toString());

            psInsertCategory.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            throw new CostManagerException("problem with adding category", e);
        }

        return true;
    }
    
    @Override
    public ArrayList<Category> getCategories() throws CostManagerException {

        ArrayList<Category> ans = new ArrayList<Category>();
        try {
            rs = st.executeQuery("SELECT * FROM category");
            while (rs.next()) {
                ans.add(new Category(rs.getString(1)));
            }
        } catch (SQLException sqle) {
            throw new CostManagerException("problem getting all categories from derbyDB", sqle);
        }

        return ans;
    }

    @Override
    public boolean checkIfCategoryExist(Category c) throws CostManagerException {
        try {
            // categories saved at database in upper
            String query = "SELECT * FROM category WHERE name ='"+c.toString().toUpperCase()+"'" ;
            rs = st.executeQuery(query);

            if(!rs.next())
                return true;
            else
                return false;
        } catch (SQLException sqle) {
            throw new CostManagerException("problem fetching categories from derbyDB", sqle);
        }
    }
}