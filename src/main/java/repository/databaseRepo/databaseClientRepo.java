package repository.databaseRepo;

import model.Client;
import model.Coupon;
import repository.IClientRespository;

import java.util.ArrayList;
import java.util.List;

public class databaseClientRepo implements IClientRespository {

    private List<Client> clients;
    private int client_id;
    private int coupon_id;

    public databaseClientRepo( ) {
        this.clients = new ArrayList<>();
        populate_clients();
        client_id = 0;
        coupon_id = 0;
    }

    private void populate_clients(){

        Client client1 = new Client("Bob", "Pop","bobpop","00bob");
        Client client2 = new Client("Laura", "Georgescu","laurgeor","22laura");
        Client client3 = new Client("Catalin", "Olariu","cataola","24catalin");
        this.add(client1);
        this.add(client2);
        this.add(client3);

    }

    @Override
    public void add(Client client) {
        //uberprufe, dass der username unique bleibt
        client_id++;
        client.setId(client_id);
        clients.add(client);

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
    public void addCoupon(Coupon c, int client_id)
    {
        coupon_id++;
        c.setCode(coupon_id);
        clients.get(client_id).addCoupon(c);
    }

}
