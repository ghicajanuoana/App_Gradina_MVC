package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Model.User;
import Presenter.PresenterAdministrator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewAdministrator extends JFrame implements IViewAdministrator{
	JLabel nameL = new JLabel("Name:");
    JLabel passwordL = new JLabel("Password:");
    JLabel roleL = new JLabel("Role:");
    JLabel utilizatori = new JLabel("Lista utilizatori");

    JTextField numeTxt = new JTextField();
    JTextField passwordTxt = new JTextField();
    JTextField roleTxt = new JTextField();

    JButton btnAdd = new JButton("Adaugare");
    JButton btnDelete = new JButton("Stergere");
    JButton btnUpdate = new JButton("Actualizare");

    JTable table = new JTable();
    DefaultTableModel model = new DefaultTableModel();

    PresenterAdministrator prezentareAdministrator;
    
    JButton btnPlante = new JButton("Plante");
    private final JButton backBtn = new JButton("Back");
    private final JButton btnView = new JButton("Vizualizare");

    public ViewAdministrator() {
        prezentareAdministrator = new PresenterAdministrator(this);
        
        //labels
        utilizatori.setBounds(222,26,178,25);
        utilizatori.setFont(new Font("Arial", Font.BOLD, 20));
        nameL.setBounds(24,73,51,25);
        passwordL.setBounds(24,98,65,25);
        roleL.setBounds(24,123,51,25);

        //text field
        numeTxt.setBounds(125,75,100,20);
        passwordTxt.setBounds(125,100,100,20);
        roleTxt.setBounds(125,125,100,20);

        //buttons
        btnAdd.setBounds(200,265,100,25);
        btnAdd.setFocusable(false);
        btnDelete.setBounds(325,265,100,25);
        btnDelete.setFocusable(false);
        btnUpdate.setBounds(450,265,100,25);
        btnUpdate.setFocusable(false);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prezentareAdministrator.adaugareUtilizator();
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prezentareAdministrator.stergereUtilizator();
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prezentareAdministrator.actualizareUtilizator();
            }
        });
        btnPlante.setForeground(Color.RED);
        btnPlante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prezentareAdministrator.redirectionarePlante();
            }
        });
        getContentPane().add(nameL);
        getContentPane().add(passwordL);
        getContentPane().add(roleL);
        getContentPane().add(utilizatori);

        getContentPane().add(numeTxt);
        getContentPane().add(passwordTxt);
        getContentPane().add(roleTxt); 

        getContentPane().add(btnAdd);
        getContentPane().add(btnDelete);
        getContentPane().add(btnUpdate);

        //table part
        List<User> users = prezentareAdministrator.getUsers();
        JTable table = new JTable();
        table.setBackground(SystemColor.window);
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

        table.setModel(model);
        table.setBounds(300, 75, 300, 150);
        table.setRowSelectionAllowed(true);
        table.setFillsViewportHeight(true);
        table.setEnabled(true);
        table.setVisible(true);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(250, 75, 300, 150);
        scrollPane.setColumnHeaderView(table);
        getContentPane().add(scrollPane);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,400);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        getContentPane().setLayout(null);
        btnPlante.setBounds(36, 266, 89, 23);
        
        getContentPane().add(btnPlante);
        backBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        	}
        });
        backBtn.setBounds(36, 311, 89, 23);
        
        getContentPane().add(backBtn);
        
        JButton btnCauta = new JButton("Cautare");
        btnCauta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		prezentareAdministrator.cautareUser();
        	}
        });
        btnCauta.setBounds(125, 178, 100, 23);
        getContentPane().add(btnCauta);
        btnView.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		prezentareAdministrator.viewUsers();
        	}
        });
        btnView.setBounds(24, 177, 89, 25);
        
        getContentPane().add(btnView);
        
        String[] roles = {"administrator","angajat"};
        
        
        this.setTitle("Administrator");
        this.setVisible(true);
    }

    public String getNameT() {
        return numeTxt.getText();
    }

    public String getPasswordT() {
        return passwordTxt.getText();
    }

    public String getRoleT() {
        return roleTxt.getText();
    	//return c.getToolTipText();
    }

    public DefaultTableModel getModel(){
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;

        table.setModel(model);
        table.setBounds(250, 75, 300, 150);
        table.setRowSelectionAllowed(true);
        table.setFillsViewportHeight(true);
        table.setEnabled(true);
        table.setVisible(true);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(250, 75, 300, 150);
        scrollPane.setViewportView(table);
        getContentPane().add(scrollPane);
        this.validate();
        table.repaint();
    }
}
