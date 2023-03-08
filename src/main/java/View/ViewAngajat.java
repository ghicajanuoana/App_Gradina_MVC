package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Model.Planta;
import Presenter.PresenterAngajat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewAngajat extends JFrame implements IViewAngajat {

	JLabel numeLbl = new JLabel("Nume");
	JLabel tipLbl = new JLabel("Tip");
	JLabel specieLbl = new JLabel("Specie");
	JLabel zonaLbl = new JLabel("Zona");
	JLabel plante = new JLabel("Lista Plante");

	JTextField numeTxt = new JTextField();
	JTextField tipTxt = new JTextField();
	JTextField specieTxt = new JTextField();
	JTextField zonaTxt = new JTextField();
	JTextField consultatieT = new JTextField();

	JButton btnAdd = new JButton("Add");
	JButton btnDelete = new JButton("Delete");
	JButton btnUpdate = new JButton("Update");
	JButton btnView = new JButton("View");
	JButton btnFiltru = new JButton("Filtrare");
	JButton btnSearch = new JButton("Cautare");
	JButton btnRaportCsv = new JButton("Raport CSV");
	JButton btnRaportJson = new JButton("Raport JSON");

	JComboBox comboBoxFiltru;

	JTable table = new JTable();
	DefaultTableModel model = new DefaultTableModel();

	PresenterAngajat prezentareAngajat;
	private final JButton btnBack = new JButton("Back");
	
	

	public ViewAngajat() {
		prezentareAngajat = new PresenterAngajat(this);
		// labels
		plante.setBounds(341, 26, 136, 25);
		plante.setFont(new Font("Serif", Font.BOLD, 20));
		numeLbl.setBounds(50, 75, 100, 25);
		tipLbl.setBounds(50, 100, 100, 25);
		specieLbl.setBounds(50, 125, 100, 25);
		zonaLbl.setBounds(50, 150, 100, 25);

		// textFields
		numeTxt.setBounds(150, 75, 100, 20);
		tipTxt.setBounds(150, 100, 100, 20);
		specieTxt.setBounds(150, 125, 100, 20);
		zonaTxt.setBounds(150, 150, 100, 20);

		// buttons
		btnView.setBounds(245, 265, 100, 25);
		btnView.setFocusable(false);
		btnAdd.setBounds(355, 265, 100, 25);
		btnAdd.setFocusable(false);
		btnDelete.setBounds(575, 265, 100, 25);
		btnDelete.setFocusable(false);
		btnUpdate.setBounds(465, 265, 100, 25);
		btnUpdate.setFocusable(false);
		btnFiltru.setBounds(150, 200, 100, 25);
		btnFiltru.setFocusable(false);
		btnSearch.setBounds(245, 301, 100, 25);
		btnSearch.setFocusable(false);
		btnRaportCsv.setBounds(543, 301, 100, 25);
		btnRaportCsv.setFocusable(false);
		btnRaportJson.setBounds(391, 301, 125, 25);
		btnRaportJson.setFocusable(false);

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prezentareAngajat.addPlanta();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prezentareAngajat.stergerePlanta();
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prezentareAngajat.actualizarePlanta();
			}
		});
		btnView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prezentareAngajat.viewPlante();
			}
		});
		btnFiltru.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prezentareAngajat.filtrare();
			}
		});
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prezentareAngajat.cautarePlanta();
			}
		});
		btnRaportCsv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prezentareAngajat.raportCSV();
			}
		});
		btnRaportJson.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// prezentareAngajat.raportJSON();
			}
		});

		String[] roles = { "tip", "specie", "zona" };
		comboBoxFiltru = new JComboBox(roles);
		comboBoxFiltru.setBounds(34, 200, 91, 25);

		getContentPane().add(numeLbl);
		getContentPane().add(tipLbl);
		getContentPane().add(specieLbl);
		getContentPane().add(zonaLbl);
		getContentPane().add(plante);

		getContentPane().add(numeTxt);
		getContentPane().add(tipTxt);
		getContentPane().add(specieTxt);
		getContentPane().add(zonaTxt);

		getContentPane().add(btnAdd);
		getContentPane().add(btnDelete);
		getContentPane().add(btnUpdate);
		getContentPane().add(btnView);
		getContentPane().add(btnFiltru);
		getContentPane().add(btnSearch);
		getContentPane().add(btnBack);
		getContentPane().add(btnRaportCsv);
		getContentPane().add(btnRaportJson);
		getContentPane().add(comboBoxFiltru);

		// table part
		Object[] columnsName = new Object[4];
		Object[] rowData = new Object[4];
		List<Planta> p = prezentareAngajat.getPlante();

		columnsName[0] = "Nume";
		columnsName[1] = "Tip";
		columnsName[2] = "Specie";
		columnsName[3] = "Zona";

		model.setColumnIdentifiers(columnsName);

		for (int i = 0; i < p.size(); i++) {
			rowData[0] = p.get(i).getNumePlanta();
			rowData[1] = p.get(i).getTip();
			rowData[2] = p.get(i).getSpecie();
			rowData[3] = p.get(i).getZona();

			model.addRow(rowData);
		}

		table.setModel(model);
		table.setRowSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setEnabled(true);
		table.setVisible(true);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(275, 75, 400, 150);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 400);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		btnBack.setBackground(Color.WHITE);
		btnBack.setForeground(Color.RED);
		btnBack.setBounds(34, 302, 89, 23);
		
		
		this.setTitle("Angajat");
		this.setVisible(true);
	}

	public String getNameT() {
		return numeTxt.getText();
	}

	public String getTipT() {
		return tipTxt.getText();
	}

	public String getSpecieT() {
		return specieTxt.getText();
	}

	public String getZonaT() {
		return zonaTxt.getText();
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JComboBox getComboBox() {
		return comboBoxFiltru;
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

		int numberOfComponents = getContentPane().getComponents().length - 1;
		getContentPane().remove(getContentPane().getComponent(numberOfComponents));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(275, 75, 400, 150);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		this.validate();
		this.repaint();
	}

}
