package Presenter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Planta;
import Model.PlantaLista;
import Model.User;
import Model.Persistenta.PersistentaPlanta;
import View.IViewAngajat;
import View.ViewLogin;
import View.ViewVizitator;

public class PresenterAngajat {
	private IViewAngajat vizualizareAngajat;
	private PersistentaPlanta persistentaPlanta;

	public PresenterAngajat(IViewAngajat angajat) {
		this.vizualizareAngajat = angajat;
		persistentaPlanta = new PersistentaPlanta();
	}

	public List<Planta> getPlante() {
		List<Planta> plante = persistentaPlanta.citireXML();
		return plante;
	}

	public void addPlanta() {
		boolean ok = false;
		String nume = vizualizareAngajat.getNameT();
		String tip = vizualizareAngajat.getTipT();
		String specie = vizualizareAngajat.getSpecieT();
		String zona = vizualizareAngajat.getZonaT();
		Planta p = new Planta(nume, tip, specie, zona);

		ok = persistentaPlanta.addPlanta(p);

		if (ok) {
			JOptionPane.showMessageDialog(new JFrame("JOptionPane"), "Adaugare efectuata cu succes!", "Message",
					JOptionPane.INFORMATION_MESSAGE);
			viewPlante();
		} else {
			JOptionPane.showMessageDialog(new JFrame("JOptionPane"), "Nume deja existent!", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
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

		vizualizareAngajat.setModel(model);
		vizualizareAngajat.setTable(table);
	}

	public void stergerePlanta() {
		boolean ok = false;
		String nume = vizualizareAngajat.getNameT();
		ok = persistentaPlanta.stergerePlanta(nume);
		if (ok) {
			JOptionPane.showMessageDialog(new JFrame("JOptionPane"), "Sters", "Message",
					JOptionPane.INFORMATION_MESSAGE);
			viewPlante();
		}
	}

	public void actualizarePlanta() {
		boolean ok = false;
		String nume = vizualizareAngajat.getNameT();
		List<Planta> plante = getPlante();
		for (Planta p : plante) {
			if (p.getNumePlanta().equals(nume)) {
				ok = persistentaPlanta.actualizarePlanta(nume, new Planta(nume, vizualizareAngajat.getTipT(),
						vizualizareAngajat.getSpecieT(), vizualizareAngajat.getZonaT()));
			}
		}

		if (ok) {
			JOptionPane.showMessageDialog(new JFrame("JOptionPane"), "Actualizare efectuata cu succes!", "Message",
					JOptionPane.INFORMATION_MESSAGE);
			viewPlante();
		}
	}

	public void filtrare() {
		String tip = vizualizareAngajat.getTipT();
		String specie = vizualizareAngajat.getSpecieT();
		String zona = vizualizareAngajat.getZonaT();
		JComboBox c = vizualizareAngajat.getComboBox();

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

		vizualizareAngajat.setModel(model);
		vizualizareAngajat.setTable(table);

	}

	public void cautarePlanta() {
		String nume = vizualizareAngajat.getNameT();
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

	public void raportCSV() {
		persistentaPlanta.writeCSV();
	}
	
	

}
