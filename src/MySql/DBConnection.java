package MySql;

import Journal.OpenForm;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


/**
 * Created by Nika on 01.03.2017.
 */
public class DBConnection {

    public static Properties propeties = new Properties();
    private static Connection mysqlConnect;

    public String host;
    public String root;
    public String password;
    public String nameDB;
    public static String url;

    public static ResultSet rs;
    public static Statement stat;



    public DBConnection(String host, String root, String password, String nameDB) // polya classa
    {
        this.host = host;
        this.root = root;
        this.password = password;
        this.nameDB = nameDB;

        initProperties();
        init();
    }

    // создаем подключение

    public void initProperties() //dly us puti k BD i user i parol
    {

        url = "jdbc:mysql://" + host + ":3306/" + nameDB; //+dbName, User, Password);

        propeties.setProperty("user", "root");
        propeties.setProperty("password", "");
        propeties.setProperty("characterEncoding", "UTF-8"); // dlya otobrajeniya simvolov normal'nuh
        propeties.setProperty("useUnocode", "true");

        System.out.println("URL: " + url);
    }

    public void init() {

        if (mysqlConnect == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");  // нужен для подключения драйвера
                mysqlConnect = DriverManager.getConnection(url, propeties); // возвращает наш юрл, что бы создать соединение
                stat = mysqlConnect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                System.out.println("Connect!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // подпограмма для получения результата с таблици
    public static ResultSet query(String query) {
        // ResultSet rs=null;
        try {
            rs = stat.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    // подпограмма для операций с данными в таблице (Delete, Insert)
    public static void updateQuery(String query) {
        try {
            //Statement stmt = mysqlConnect.createStatement();
            stat.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Добавления студентов к группе из файла пользователя
    public static void ADDFailu(File file, String nametableADD) {

        try {
            String put = file.getPath();
            String str = "";

            FileReader reader = new FileReader(put);
            BufferedReader buffer = new BufferedReader(reader);
            str = buffer.readLine();
            String fail = "";

            while (!(str.equals(null)))
            //  while (!(str=" "))
            {
                fail = str + "\n";
                updateQuery("INSERT INTO " + nametableADD + "(student)VALUES('" + fail + "')");

                str = buffer.readLine();

            }

            System.out.println("Dannue zapisannu!");
            reader.close();
            // write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // создание таблиц студентов
    public static void CreateTableRB(String tablename) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tablename + "(id int(100) NOT NULL auto_increment, student varchar(100) NOT NULL, PRIMARY KEY (id))";

        try {
            Statement stmt = mysqlConnect.createStatement();
            stmt.execute(createTableSQL);
            System.out.println("Table " + tablename + " is created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //закрываем потоки
    public void finale() //zakrutie potokov
    {
        try {
            mysqlConnect.close();
            stat.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}