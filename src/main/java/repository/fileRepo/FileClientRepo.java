package repository.fileRepo;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Client;
import repository.IClientRespository;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileClientRepo implements IClientRespository {

    private String filePath;
    private List<Client> clients;
    public FileClientRepo() {
        this.filePath = "D:\\Andreea\\Facultate\\Anul 2\\MAP\\HotelApp\\src\\main\\resources\\clients.json";
        this.clients = new ArrayList<>();
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
            clients.add(client1);
            clients.add(client2);
            clients.add(client3);
            mapper.writeValue(Paths.get(filePath).toFile(),clients);
            // print book
            //System.out.println(book);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void add(Client client) {

    }

    @Override
    public void delete(String username) {

    }

    @Override
    public void update(String username, Client client) {

    }

    @Override
    public List<Client> getAll() {
        return null;
    }

    @Override
    public Client findbyusername(String username) {
        return null;
    }
}
