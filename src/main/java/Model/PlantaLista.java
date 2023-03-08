package Model;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "plante")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlantaLista{
	@XmlElement(name = "planta")
	private List<Planta> listaPlante = null;
	
	public PlantaLista() {
		listaPlante = new ArrayList<>();
	}
	public PlantaLista(List<Planta> listaPlante) {
		this.listaPlante = listaPlante;
	}
	public List<Planta> getListaPlante() {
        return listaPlante;
    }

    public void setListaPlante(List<Planta> listaPlante) {
        this.listaPlante = listaPlante;
    }
    
}
