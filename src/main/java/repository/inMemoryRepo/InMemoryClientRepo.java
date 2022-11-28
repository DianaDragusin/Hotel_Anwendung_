package repository.inMemoryRepo;

import model.Client;
import repository.IClientRespository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryClientRepo implements IClientRespository {

    private List<Client> clients;

    public InMemoryClientRepo(  List<Client> clients) {
        this.clients = populate_clients();
    }

    private List<Client> populate_clients(){
        List<Client> clients = new ArrayList<>();
        Client client1 = new Client("Bob", "Pop","bobpop","00bob");
        Client client2 = new Client("Laura", "Georgescu","laurgeor","22laura");
        Client client3 = new Client("Catalin", "Olariu","cataola","24catalin");
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        return clients;
    }

    @Override
    public void add(Client client) {
        //uberprufe, dass der username unique bleibt

        this.clients.add(client);

    }
    /*
    private boolean client_exists(Client client){
        for(Client u:clients){
            if(u.getUsername().equals(client.getUsername())){
                return true;
            }
        }
        return false;
    }
    */
    @Override
    public void delete(Integer id) {

        clients.remove(findById(id));

    }

    @Override
    public void update(Integer id, Client client) {

        findById(id).setFirstName(client.getFirstName());
        findById(id).setLastName(client.getLastName());

    }

    @Override
    public Client findByUsername(String username) {
        for(Client c : clients){
            if(c.getUsername().equals(username)){
                return c;
            }
        }
        return null;
    }
    @Override
    public Client findById(Integer id){
        for(Client c : clients){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Client> getAll() {
        return clients;
    }

}
