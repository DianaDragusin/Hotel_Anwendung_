package repository.fileRepo;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Client;
import repository.IClientRespository;

import java.nio.file.Paths;
import java.util.List;

public class FileClientRepo implements IClientRespository {

    private String filePath;
    public FileClientRepo() {
        this.filePath = "D:\\Andreea\\Facultate\\Anul 2\\FortgeschritteneProgrammierungssysteme\\Seminar\\Seminar5\\src\\main\\resources\\users.json";
        this.populate();
    }

    public void populate(){
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();
            // convert JSON string to Book object
            Client client1 = new Client("Bob", "Pop","bobpop","00bob");
            Client client2 = new Client("Laura", "Georgescu","laurgeor","22laura");
            Client client3 = new Client("Catalin", "Olariu","cataola","24catalin");
            mapper.writeValue(Paths.get(filePath).toFile(), client1);
            // print book
            //System.out.println(book);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public boolean add(Client client) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public boolean update(Integer integer, Client client) {
        return false;
    }

    @Override
    public Client findbyID(Integer integer) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        return null;
    }

    @Override
    public Client findByUsername(String username) {
        return null;
    }
}
