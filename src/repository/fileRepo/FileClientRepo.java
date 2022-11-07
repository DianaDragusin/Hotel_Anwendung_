package repository.fileRepo;

import model.Client;
import repository.IClientRespository;

import java.util.List;

public class FileClientRepo implements IClientRespository {
    @Override
    public boolean add(Client client) {
        return false;
    }

    @Override
    public void delete(Integer integer) {

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
}
