package View;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public interface IViewVizitator {
	String getNameT();
    String getTipT();
    String getSpecieT();
    String getZonaT();
    void setModel(DefaultTableModel model);
    JComboBox getComboBox();
    JTable getTable();
    void setTable(JTable table);
}
