package Journal;

/**
 * Created by Nika on 07.05.2017.
 */
import MySql.DBConnection;
import com.sun.media.jfxmedia.logging.Logger;

import java.awt.Color;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.sql.RowSetMetaData;
import javax.swing.table.AbstractTableModel;

public class DataTable extends AbstractTableModel{

    public static int numCols=0;
    public static int numRows=0;

    static String currenttable;

    public static ArrayList<String> tableHeaders = new ArrayList<String>();

    public static HashMap<String, ArrayList<Rows>> hashofTables = new HashMap<String, ArrayList<Rows>>();

    public static HashMap<String, Integer> hashofNumCols = new HashMap<String, Integer>();
    public static ArrayList<Rows> tableData = new ArrayList<Rows>();

    public static HashMap<String, Integer> hashofNumRows = new HashMap<String, Integer>();

    public DataTable(String tableName) {
        numCols= 0;
        numRows = 0;
        currenttable = tableName;
        System.out.println("Selected table: " + currenttable);
        getHeaders(currenttable);
        fillData(currenttable);
    }

    public void getHeaders(String tableName)
    {
        System.out.println( "Get headers from " + currenttable + " table.");

        ///DBView.table.setEnabled(true);

        tableHeaders.clear();

        String sql = "SELECT * FROM " + tableName;

        try {


            DBConnection.rs = DBConnection.stat.executeQuery(sql);

            ResultSetMetaData meta = DBConnection.rs.getMetaData();
            numCols = meta.getColumnCount();
            System.out.println( "Num Cols: " + numCols);
            for(int i=0; i < numCols; i++) {
                tableHeaders.add(meta.getColumnName(i+1));
            }
            hashofNumCols.put(tableName, numCols);

            //DBConnect.Close();
            //DBConnect.connected = false;
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void fillData(String tableName) {
        System.out.println( "Get rows from " + currenttable + " table.");
        if (!hashofTables.containsKey(tableName)) {

            String sql = "SELECT * FROM " + tableName;



            try {

                DBConnection.rs = DBConnection.stat.executeQuery(sql);
                ResultSetMetaData md = DBConnection.rs.getMetaData();
                while (DBConnection.rs.next()) {
                    Rows row = new Rows();
                    for (int i = 1; i <= numCols; i++) {
                        Object item = DBConnection.rs.getObject(i);
                        //System.out.println(md.getColumnName(i) + " header :  " + tableHeaders.get(i-1) + ": " + item);
                        row.Add(item);
                    }
                    tableData.add(row);
                    numRows++;
                }

                //System.out.println(Arrays.deepToString(tableData.get(0).cols.toArray()));
                hashofNumRows.put(tableName, numRows);

                hashofTables.put(tableName, tableData);

                //DBConnect.Close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

    @Override
    public String getColumnName(int column) {
        return tableHeaders.get(column);
    }

    @Override
    public int getRowCount() {
        //JDBC.textArea.append("numRows: " + Integer.toString(numRows) + "\n");
        return numRows = hashofNumRows.get(currenttable);
    }

    @Override
    public int getColumnCount() {
        //JDBC.textArea.append("numCols: " + Integer.toString(numCols) + "\n");
        return numCols = hashofNumCols.get(currenttable);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        ArrayList<Rows> tableObj = hashofTables.get(currenttable);

        Object cell = tableObj.get(rowIndex).Get(columnIndex);
        //Logger.log(Logger.INFO, "Selected cell- " + " row: " + rowIndex + " col: " + columnIndex);
        return cell;
    }



    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        ArrayList<Rows> tableObj = hashofTables.get(currenttable);

        Rows row = tableObj.get(rowIndex);

        row.Set(columnIndex, aValue);

        try {
            DBConnection.rs.absolute(rowIndex+1);
            DBConnection.rs.updateObject(columnIndex+1, aValue);
            DBConnection.rs.updateRow();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}
