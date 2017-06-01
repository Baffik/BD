package MySql;

import java.util.ArrayList;
        import javax.swing.table.AbstractTableModel;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;

/**
 * Created by Nika on 13.04.2017.
 */

public class TableADDPR extends AbstractTableModel {


    public int columnCount = 1;
    private static ArrayList<String[]> list;

    public TableADDPR() {
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
    public Object getValueAt(int rowIndex, int columnIndex) // poluchaet znachenie s tablici

    {
        String[] rows = list.get(rowIndex);
        return rows[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "predmet";
            // case 1:
            // return "predmetu";
        }
        return "";
    }

    public void addData(String[] row) {
        int i = getColumnCount();
        String[] rowsTable = new String[i];
        rowsTable = row;
        list.add(rowsTable);
    }

    //делаем запрос на таблицу и заносим в тайбл все значения из таблици
    public void ADDPR(DBConnection connect)
    {
        ResultSet resultPredmetu = connect.query("SELECT * from PR");// poluchaem stroki s tablici
        try {
            while (resultPredmetu.next()) {
                String ID = resultPredmetu.getString("ID_PR");
                String predmetu = resultPredmetu.getString("predmet_PR");

                System.out.println(ID +" : " + predmetu);

                String [] row = {predmetu};
                addData(row);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}

