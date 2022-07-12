package reporting.utility;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class connectDB {
    public static MongoDatabase mongoDatabase = null;

    public static Connection connect = null;
    public static Statement statement = null;
    public static PreparedStatement ps = null;
    public static ResultSet resultSet = null;


    public Connection connectToMySql() throws IOException, SQLException, ClassNotFoundException {
        Properties properties = utility.loadProperties();
        String driverClass = utility.decode(properties.getProperty("MYSQLJDBC.driver"));
        String url = properties.getProperty("MYSQLJDBC.url");
        String userName = utility.decode(properties.getProperty("MYSQLJDBC.userName"));
        String password = utility.decode(properties.getProperty("MYSQLJDBC.password"));
        Class.forName(driverClass);
        connect = DriverManager.getConnection(url,userName,password);
        System.out.println("Database is connected");
        return connect;
    }
    public MongoDatabase connectToMongoDB() {
        MongoClient mongoClient = new MongoClient();
        mongoDatabase = mongoClient.getDatabase("students");
        System.out.println("Database Connected");

        return mongoDatabase;
    }
    public List<String> readDataBase(String tableName, String columnName)throws Exception{
        List<String> data = new ArrayList<String>();

        try {
            connectToMySql();
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from " + tableName);
            data = getResultSetData(resultSet, columnName);
        } catch (ClassNotFoundException e) {
            throw e;
        }finally{
            close();
        }
        return data;
    }
    private void close() {
        try{
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connect != null){
                connect.close();
            }
        }catch(Exception e){

        }
    }
    private List<String> getResultSetData(ResultSet resultSet2, String columnName) throws SQLException {
        List<String> dataList = new ArrayList<String>();
        while(resultSet.next()){
            String itemName = resultSet.getString(columnName);
            dataList.add(itemName);
        }
        return dataList;
    }

    public List<String> readDataBase2(String tableName, String columnName1,String columnName2)throws Exception{
        List<String> data = new ArrayList<String>();
        Connection conn = connectToMySql();
        String query = "SELECT * FROM "+tableName;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String name = rs.getString(columnName1);
            String pass = rs.getString(columnName2);
        }
        return data;
    }

    public List<String> readFromMySql()throws IOException, SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<String>();

        try{
            Connection conn = connectToMySql();
            String query = "SELECT * FROM profile";
            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            // iterate through the java resultset
            while (rs.next())
            {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                //System.out.format("%s, %s\n", name, id);

            }
            st.close();
        }catch (Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return list;
    }
}
