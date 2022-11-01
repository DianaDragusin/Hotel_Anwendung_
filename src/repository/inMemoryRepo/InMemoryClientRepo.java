package repository.inMemoryRepo;

import model.Client;
import repository.IClientRespository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryClientRepo implements IClientRespository {

    private List<Client> clients;

    public InMemoryClientRepo(List<Client> clients) {
        this.clients = new ArrayList<>();
    }

    @Override
    public void add(Client client) {
        clients.add(client);
    }

    @Override
    public void delete(Integer id) {
       clients.remove(clients.get(id));
    }

    @Override
    public void update(Integer id, Client client) {
        clients.get(id).setFirstName(client.getFirstName());
        clients.get(id).setFirstName(client.getLastName());
    }

    @Override
    public Client findbyID(Integer id) {
        return clients.get(id);
    }
}
