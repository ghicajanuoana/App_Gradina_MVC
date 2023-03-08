package Model;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "planta")
@XmlAccessorType(XmlAccessType.FIELD)

public class Planta {
	private String nume;
	private String tip;
	private String specie;
	private String zona;
	
	public Planta() {}
	
	public Planta(String nume,String tip, String specie, String zona) {
		this.nume=nume;
		this.specie = specie;
		this.tip = tip;
		this.zona=zona;
	}
	public String getNumePlanta() {
		return nume;
	}
	public void setNumePlanta(String nume) {
		this.nume=nume;
	}
	public String getTip() {
		return tip;
	}
	public String getSpecie() {
		return specie;
	}
	public String getZona() {
		return zona;
	}
	
	public void setTip(String tip) {
		this.tip=tip;
	}
	public void setSpecie(String specie) {
		this.specie=specie;
	}
	public void setZona(String zona) {
		this.zona=zona;
	}
	
	
	public String toString() {
		return "Planta: " + getNumePlanta() + "este de tipul " + getTip() + "si specia " + getSpecie();
	}
}
