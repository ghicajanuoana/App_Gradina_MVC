package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public interface IViewAdministrator {
	String getNameT();
    String getPasswordT();
    String getRoleT();
    JTable getTable();
    void setTable(JTable table);
    DefaultTableModel getModel();
    void setModel(DefaultTableModel model);

}
