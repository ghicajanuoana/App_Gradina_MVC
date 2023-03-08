package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public interface IViewAngajat {
	String getNameT();
    String getTipT();
    String getSpecieT();
    String getZonaT();
    void setModel(DefaultTableModel model);
    JComboBox getComboBox();
    JTable getTable();
    void setTable(JTable table);
}
