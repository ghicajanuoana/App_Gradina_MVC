package Presenter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Planta;
import Model.Persistenta.PersistentaPlanta;
import View.IViewVizitator;

public class PresenterVizitator {
	private IViewVizitator vizualizareVizitator;
	private PersistentaPlanta persistentaPlanta;

	public PresenterVizitator(IViewVizitator viz) {
		this.vizualizareVizitator = viz;
		persistentaPlanta = new PersistentaPlanta();
	}

	public List<Planta> getPlante() {
		List<Planta> plante = persistentaPlanta.citireXML();
		return plante;
	}

	public void viewPlante() {
		List<Planta> plante = persistentaPlanta.citireXML();
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		Object[] columnsName = new Object[4];
		Object[] rowData = new Object[4];

		columnsName[0] = "Nume";
		columnsName[1] = "Tip";
		columnsName[2] = "Specie";
		columnsName[3] = "Zona";

		model.setColumnIdentifiers(columnsName);

		for (int i = 0; i < plante.size(); i++) {
			rowData[0] = plante.get(i).getNumePlanta();
			rowData[1] = plante.get(i).getTip();
			rowData[2] = plante.get(i).getSpecie();
			rowData[3] = plante.get(i).getZona();

			model.addRow(rowData);
		}

		vizualizareVizitator.setModel(model);
		vizualizareVizitator.setTable(table);
	}


	public void filtrare() {
		String tip = vizualizareVizitator.getTipT();
		String specie = vizualizareVizitator.getSpecieT();
		String zona = vizualizareVizitator.getZonaT();
		JComboBox c = vizualizareVizitator.getComboBox();

		List<Planta> plante;

		if (c.getSelectedIndex() == 0) {
			plante = persistentaPlanta.filtruTip(tip);
			viewPlanteFiltru(plante);
		}

		else if (c.getSelectedIndex() == 1) {
			plante = persistentaPlanta.filtruSpecie(specie);
			viewPlanteFiltru(plante);
		} else {
			plante = persistentaPlanta.filtruZona(zona);
			viewPlanteFiltru(plante);
		}

	}

	public void viewPlanteFiltru(List<Planta> plante) {
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		Object[] columnsName = new Object[4];
		Object[] rowData = new Object[4];

		columnsName[0] = "Nume";
		columnsName[1] = "Tip";
		columnsName[2] = "Specie";
		columnsName[3] = "Zona";

		model.setColumnIdentifiers(columnsName);

		for (Planta p : plante) {
			rowData[0] = p.getNumePlanta();
			rowData[1] = p.getTip();
			rowData[2] = p.getSpecie();
			rowData[3] = p.getZona();
			model.addRow(rowData);
		}

		vizualizareVizitator.setModel(model);
		vizualizareVizitator.setTable(table);

	}

	public void cautarePlanta() {
		String nume = vizualizareVizitator.getNameT();
		List<Planta> plante = new ArrayList<Planta>();

		Planta p = persistentaPlanta.cautarePlanta(nume);
		if (p != null) {
			plante.add(p);
			viewPlanteFiltru(plante);
		} else {
			JOptionPane.showMessageDialog(new JFrame("JOptionPane"), "Planta nu exista", "Message",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
