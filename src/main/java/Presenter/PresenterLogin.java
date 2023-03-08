package Presenter;

import javax.swing.JComboBox;

import java.util.List;

import javax.swing.*;

import Model.User;
import Model.Persistenta.PersistentaUtilizator;
import View.IViewLogin;
import View.ViewAdministrator;
import View.ViewAngajat;
import View.ViewLogin;
import View.ViewVizitator;

public class PresenterLogin {
	private IViewLogin viz;
    private PersistentaUtilizator persistenta;

    public PresenterLogin(IViewLogin vizualizareLogin) {
        this.viz = vizualizareLogin;
        persistenta = new PersistentaUtilizator();
    }

    public void login() {
        JComboBox c = viz.getComboBox();
        String username = viz.getUsername();
        String password = viz.getPassword();
        List<User> users = persistenta.citireXML();

        for(User user : users) {
        	if(!user.getUsername().equals(username) && user.getRole().equals("administrator")) {
        		viz.setFailMessageLabel("Wrong username");
        	}
        	else
            if(user.getUsername().equals(username) && c.getSelectedIndex() == 0 && user.getRole().equals("administrator")){
                if(user.getPassword().equals(password)){
                    viz.setSuccessMessageLabel("Login successful!");
                    ViewAdministrator adminView = new ViewAdministrator();
                    adminView.setVisible(true);
                    break;
                } else {
                    viz.setFailMessageLabel("Wrong password");
                }
            }
        	if(!user.getUsername().equals(username) && user.getRole().equals("angajat")) {
        		viz.setFailMessageLabel("Wrong username");
        	}
            if(user.getUsername().equals(username) && c.getSelectedIndex() == 1 && user.getRole().equals("angajat")){
                if(user.getPassword().equals(password)){
                    viz.setSuccessMessageLabel("Login successful!");
                    ViewAngajat angajatView = new ViewAngajat();
                    angajatView.setVisible(true);
                    break;
                } else {
                    viz.setFailMessageLabel("Wrong password");
                }
            }
        }
        
    }
    
    public void vizitatorButton() {
    	ViewVizitator v = new ViewVizitator();
        v.setVisible(true);
    }
    
}
