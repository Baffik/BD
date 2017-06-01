package MySql;


import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Nika on 18.03.2017.
 */
public class GroupTableModel extends AbstractTableModel {

    public int columnCount = 2;
    private static ArrayList<String[]> list;

    public GroupTableModel() {
        list = new ArrayList<String[]>();

        for (int i = 0; i < list.size(); i++) {
            list.add(new String[getColumnCount()]);
        }
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = list.get(rowIndex);
        return rows[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "student";
        }
        return "";
    }

    public void addData(String[] row) {
        int i = getColumnCount();
        String[] rowsTable = new String[i];
        rowsTable = row;
        list.add(rowsTable);
    }

    public void VuvodTable(DBConnection connect, String S) {
        //ResultSet resultstudent = connect.query("SELECT * from "+S);// poluchaem stroki s tablici
        DBConnection.rs = connect.query("SELECT * from "+S);
        try {
            while (DBConnection.rs.next()) {
                String ID = DBConnection.rs.getString("id");
                String students = DBConnection.rs.getString("student");

                System.out.println(ID + " : " + students);

                String[] row = {ID, students};
                addData(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
