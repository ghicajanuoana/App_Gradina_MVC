package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Model.Planta;
import Presenter.PresenterAngajat;
import Presenter.PresenterVizitator;

public class ViewVizitator extends JFrame implements IViewVizitator{

	JLabel nameL = new JLabel("Nume");
	JLabel tipL = new JLabel("Tip");
	JLabel specieL = new JLabel("Specie");
	JLabel zonaL = new JLabel("Zona");
	JLabel plante = new JLabel("Lista Plante");

	JTextField numeTxt = new JTextField();
	JTextField tipTxt = new JTextField();
	JTextField specieTxt = new JTextField();
	JTextField zonaTxt = new JTextField();
	JTextField consultatieT = new JTextField();
	JButton btnView = new JButton("View");
	JButton btnFiltru = new JButton("Filtrare");
	JButton btnCauta = new JButton("Cautare");
	private final JButton btnBack = new JButton("Back");

	JComboBox comboBoxPlante;

	JTable table = new JTable();
	DefaultTableModel model = new DefaultTableModel();

	PresenterVizitator prezentareAngajat;
	
	

	public ViewVizitator() {
		prezentareAngajat = new PresenterVizitator(this);
		// labels
		plante.setBounds(341, 26, 136, 25);
		plante.setFont(new Font("Serif", Font.BOLD, 20));
		nameL.setBounds(50, 75, 100, 25);
		tipL.setBounds(50, 100, 100, 25);
		specieL.setBounds(50, 125, 100, 25);
		zonaL.setBounds(50, 150, 100, 25);

		// textFields
		numeTxt.setBounds(150, 75, 100, 20);
		tipTxt.setBounds(150, 100, 100, 20);
		specieTxt.setBounds(150, 125, 100, 20);
		zonaTxt.setBounds(150, 150, 100, 20);

		// buttons
		btnView.setBounds(28, 195, 100, 25);
		btnView.setFocusable(false);
		btnFiltru.setBounds(507, 253, 100, 25);
		btnFiltru.setFocusable(false);
		btnCauta.setBounds(150, 195, 100, 25);
		btnCauta.setFocusable(false);
		
		btnBack.setBackground(Color.WHITE);
		btnBack.setForeground(Color.RED);
		btnBack.setBounds(34, 302, 89, 23);

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
		btnCauta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prezentareAngajat.cautarePlanta();
			}
		});

		String[] roles = { "tip", "specie", "zona" };
		comboBoxPlante = new JComboBox(roles);
		comboBoxPlante.setBounds(358, 253, 91, 25);

		getContentPane().add(nameL);
		getContentPane().add(tipL);
		getContentPane().add(specieL);
		getContentPane().add(zonaL);
		getContentPane().add(plante);

		getContentPane().add(numeTxt);
		getContentPane().add(tipTxt);
		getContentPane().add(specieTxt);
		getContentPane().add(zonaTxt);
		getContentPane().add(btnView);
		getContentPane().add(btnBack);
		getContentPane().add(btnFiltru);
		getContentPane().add(btnCauta);
		getContentPane().add(comboBoxPlante);

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
		
		this.setTitle("Vizitator");
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
		return comboBoxPlante;
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
