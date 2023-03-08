package Model.Persistenta;
import java.io.File;
import java.util.List;

import Model.Planta;
import Model.User;
import Model.Users;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class PersistentaUtilizator {
	private Users users = new Users();
    private final File file = new File("./users.xml");

    public PersistentaUtilizator() {
        users.setUsers(this.citireXML());
    }

    public boolean addUser(User u) {
        boolean succes = true;

        for(int i = 0; i < users.getUsers().size(); i++) {
            if(users.getUsers().get(i).getUsername().equals(u.getUsername())) {
                succes = false;
            }
        }
        if(succes) {
            users.getUsers().add(u);
        }
        adaugareUtilizatori();
        return succes;
    }

    public void adaugareUtilizatori() {

        try {
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(users, file);

        } catch (JAXBException e){
            System.out.println(e.getMessage());
        }

    }

    public boolean stergereUtilizator(String username) {
        boolean succes = false;
        List<User> usersList = citireXML();

        for(int i = 0; i < usersList.size(); i++) {
            if(usersList.get(i).getUsername().equals(username)) {
                usersList.remove(usersList.get(i));
                succes = true;
                break;
            }
        }

        users.setUsers(usersList);
        adaugareUtilizatori();

        return succes;
    }

    public boolean actualizareUtilizator(String username, User user) {
        boolean succes = false;

        List<User> usersList = citireXML();

        for(int i = 0; i < usersList.size(); i++) {
            if(usersList.get(i).getUsername().equals(username)) {
                usersList.set(i, user);
                succes = true;
            }
        }

        users.setUsers(usersList);
        adaugareUtilizatori();

        return succes;
    }

    public List<User> citireXML() {
        Users u = new Users();
        try {
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
            u = (Users) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }

        return u.getUsers();
    }
    public User cautareUtilizator(String nume) {
		List<User> u = users.getUsers();
		for (int i = 0; i < u.size(); i++) {
			if (u.get(i).getUsername().equals(nume)) {
				return u.get(i);
			}
		}
		return null;
	}
}
