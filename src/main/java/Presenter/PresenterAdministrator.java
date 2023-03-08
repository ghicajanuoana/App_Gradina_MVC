package Presenter;

import Model.Planta;
import Model.User;
import Model.Persistenta.PersistentaUtilizator;
import View.IViewAdministrator;
import View.ViewAdministrator;
import View.ViewAngajat;
import View.ViewLogin;
import View.ViewVizitator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

public class PresenterAdministrator {
	private IViewAdministrator vizualizareAdministrator;
    private PersistentaUtilizator persistentaUtilizator;

    public PresenterAdministrator(IViewAdministrator viz) {
        this.vizualizareAdministrator = viz;
        persistentaUtilizator = new PersistentaUtilizator();
    }

    public List<User> getUsers() {
        List<User> users = persistentaUtilizator.citireXML();
        return users;
    }
    
    public void redirectionarePlante() {
    	ViewVizitator v = new ViewVizitator();
    	v.setVisible(true);
    }

    public void adaugareUtilizator() {
        boolean ok = false;
        String username = vizualizareAdministrator.getNameT();
        String password = vizualizareAdministrator.getPasswordT();
        String role = vizualizareAdministrator.getRoleT();
        User u = new User(username,password,role);

        ok = persistentaUtilizator.addUser(u);

        if(ok) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Adaugare efectuata cu succes!","Message",JOptionPane.INFORMATION_MESSAGE);
            vizualizareUtilizatori();
        } else {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Username deja existent!","Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void stergereUtilizator() {
        boolean ok = false;
        String username = vizualizareAdministrator.getNameT();
        ok = persistentaUtilizator.stergereUtilizator(username);
        if(ok) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Stergere user","Message",JOptionPane.INFORMATION_MESSAGE);
            vizualizareUtilizatori();
        }
    }

    public void actualizareUtilizator() {
        boolean ok = false;
        String username = vizualizareAdministrator.getNameT();
        List<User> users = getUsers();
        for(User u : users) {
            if(u.getUsername().equals(username)) {
                ok = persistentaUtilizator.actualizareUtilizator(username, new User(username,vizualizareAdministrator.getPasswordT(), vizualizareAdministrator.getRoleT()));
            }
        }

        if(ok) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Actualizare user","Message",JOptionPane.INFORMATION_MESSAGE);
            vizualizareUtilizatori();
        }
    }

    public void vizualizareUtilizatori() {
        List<User> users = persistentaUtilizator.citireXML();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();

        Object[] columnsName = new Object[3];
        Object[] rowData = new Object[3];

        columnsName[0] = "Username";
        columnsName[1] = "Password";
        columnsName[2] = "Role";

        model.setColumnIdentifiers(columnsName);

        for(int i = 0; i < users.size(); i++){
            rowData[0] = users.get(i).getUsername();
            rowData[1] = users.get(i).getPassword();
            rowData[2] = users.get(i).getRole();

            model.addRow(rowData);
        }

        vizualizareAdministrator.setModel(model);
        vizualizareAdministrator.setTable(table);
    }
    public void backButton() {
    	ViewLogin v = new ViewLogin();
    	v.setVisible(true);
    }
    public void viewUserFiltru(List<User> users) {
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		Object[] columnsName = new Object[3];
		Object[] rowData = new Object[3];

		columnsName[0] = "Username";
		columnsName[1] = "Password";
		columnsName[2] = "Role";

		model.setColumnIdentifiers(columnsName);

		for (User p : users) {
			rowData[0] = p.getUsername();
			rowData[1] = p.getPassword();
			rowData[2] = p.getRole();
			model.addRow(rowData);
		}

		vizualizareAdministrator.setModel(model);
		vizualizareAdministrator.setTable(table);
	}
    
    public void viewUsers() {
		List<User> users = persistentaUtilizator.citireXML();
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		Object[] columnsName = new Object[3];
		Object[] rowData = new Object[3];

		columnsName[0] = "Username";
		columnsName[1] = "Password";
		columnsName[2] = "Role";

		model.setColumnIdentifiers(columnsName);

		for (User p : users) {
			rowData[0] = p.getUsername();
			rowData[1] = p.getPassword();
			rowData[2] = p.getRole();
			model.addRow(rowData);
		}

		vizualizareAdministrator.setModel(model);
		vizualizareAdministrator.setTable(table);
	}

    
    public void cautareUser() {
		String nume = vizualizareAdministrator.getNameT();
		List<User> plante = new ArrayList<User>();

		User p = persistentaUtilizator.cautareUtilizator(nume);
		if (p != null) {
			plante.add(p);
			viewUserFiltru(plante);
		} else {
			JOptionPane.showMessageDialog(new JFrame("JOptionPane"), "User-ul nu exista", "Message",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
