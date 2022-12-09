package repository.inMemoryRepo;

import model.Client;
import model.Coupon;
import repository.IClientRespository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryClientRepo implements IClientRespository {

    private List<Client> clients;
    private int clientId;
    private int coupon_id;

    public InMemoryClientRepo( ) {
        this.clients = new ArrayList<>();
        populate_clients();
        clientId = 0;
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
        clientId++;
        client.setId(clientId);
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
    public void addCoupon(Coupon c, int clientId)
    {

        coupon_id++;
        c.setCode(coupon_id);
        findById(clientId).addCoupon(c);
       // clients.get(clientId).addCoupon(c);
    }
    public void removeCoupon(Coupon coupon, int clientId)
    {

        findById(clientId).removeCoupon(coupon);

    }
    public Coupon findCouponById(int couponId, int clientId)
    {
        for (Client client : clients)
        {
            if (client.getId() == clientId)
            {
                List<Coupon> coupons = client.getCouponList();
                for (Coupon coupon : coupons)
                {
                    if (coupon.getCode() == couponId)
                    {
                       return coupon;
                    }
                }
            }

        }
      return null;

    }

}
