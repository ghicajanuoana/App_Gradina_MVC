package Model.Persistenta;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.UnmarshalException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import Model.Planta;
import Model.PlantaLista;

public class PersistentaPlanta {
	private PlantaLista listaPlante = new PlantaLista();
	private final File fileXML = new File("./plante.xml");

	public PersistentaPlanta() {
		listaPlante.setListaPlante(this.citireXML());
	}

	public List<Planta> citireXML() {
		PlantaLista planta = new PlantaLista();
		try {
			JAXBContext context = JAXBContext.newInstance(PlantaLista.class);
			Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
			planta = (PlantaLista) jaxbUnmarshaller.unmarshal(fileXML);
		} catch (JAXBException e) {
			System.out.println(e.getMessage());
		}

		return planta.getListaPlante();
	}

	public boolean addPlanta(Planta p) {
		boolean succes = true;

		for (int i = 0; i < listaPlante.getListaPlante().size(); i++) {
			if (listaPlante.getListaPlante().get(i).getNumePlanta().equals(p.getNumePlanta())) {
				succes = false;
			}
		}
		if (succes) {
			listaPlante.getListaPlante().add(p);
		}
		adaugarePlante();
		return succes;
	}

	public void adaugarePlante() {

		try {
			JAXBContext context = JAXBContext.newInstance(PlantaLista.class);
			Marshaller mar = context.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			mar.marshal(listaPlante, fileXML);

		} catch (JAXBException e) {
			System.out.println(e.getMessage());
		}

	}

	public Planta cautarePlanta(String nume) {
		List<Planta> p = listaPlante.getListaPlante();
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).getNumePlanta().equals(nume)) {
				return p.get(i);
			}
		}
		return null;
	}

	public boolean stergerePlanta(String name) {
		boolean succes = false;
		List<Planta> plantaList = citireXML();

		for (int i = 0; i < plantaList.size(); i++) {
			if (plantaList.get(i).getNumePlanta().equals(name)) {
				plantaList.remove(plantaList.get(i));
				succes = true;
				break;
			}
		}

		listaPlante.setListaPlante(plantaList);
		adaugarePlante();

		return succes;
	}

	public boolean actualizarePlanta(String name, Planta planta) {
		boolean succes = false;

		List<Planta> plantaList = citireXML();

		for (int i = 0; i < plantaList.size(); i++) {
			if (plantaList.get(i).getNumePlanta().equals(name)) {
				plantaList.set(i, planta);
				succes = true;
			}
		}

		listaPlante.setListaPlante(plantaList);
		adaugarePlante();

		return succes;
	}

	public List<Planta> filtruTip(String tip) {
		List<Planta> planta = new ArrayList<Planta>();
		for (Planta p : citireXML()) {
			if (p.getTip().equals(tip)) {
				planta.add(p);
			}
		}
		return planta;
	}

	public List<Planta> filtruSpecie(String specie) {
		List<Planta> planta = new ArrayList<Planta>();
		for (Planta p : citireXML()) {
			if (p.getSpecie().equals(specie)) {
				planta.add(p);
			}
		}
		return planta;
	}

	public List<Planta> filtruZona(String zona) {
		List<Planta> planta = new ArrayList<Planta>();
		for (Planta p : citireXML()) {
			if (p.getZona().equals(zona)) {
				planta.add(p);
			}
		}
		return planta;
	}

	public void writeCSV() {
		List<String[]> dataLines = new ArrayList<>();
		dataLines.add(new String[] { "Nume", "Tip", "Specie", "Zona" });

		List<Planta> plante = listaPlante.getListaPlante();
		for (int i = 0; i < plante.size(); i++) {
			dataLines.add(new String[] { plante.get(i).getNumePlanta(), plante.get(i).getTip(),
					plante.get(i).getSpecie(), plante.get(i).getZona() });
		}

		try (CSVWriter writer = new CSVWriter(new FileWriter("./plante.csv"))) {
			writer.writeAll(dataLines);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
