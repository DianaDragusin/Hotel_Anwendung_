package repository.inMemoryRepo;

import model.Client;
import repository.IClientRespository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryClientRepo implements IClientRespository {

    private List<Client> clients;

    public InMemoryClientRepo(List<Client> clients) {
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
    public boolean add(Client client) {
        //uberprufe, dass der username unique bleibt
        if(client_exists(client)){
            return false;
        }
        this.clients.add(client);
        return true;
    }

    private boolean client_exists(Client client){
        for(Client u:clients){
            if(u.getUsername().equals(client.getUsername())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        if(findbyID(id)!=null){
            clients.remove(clients.get(id));
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Integer id, Client client) {
        if(client_exists(client)){
            return false;
        }
        clients.get(id).setFirstName(client.getFirstName());
        clients.get(id).setLastName(client.getLastName());
        return true;
    }

    @Override
    public Client findbyID(Integer id) {
        return clients.get(id);
    }

    @Override
    public List<Client> getAll() {
        return clients;
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
}
