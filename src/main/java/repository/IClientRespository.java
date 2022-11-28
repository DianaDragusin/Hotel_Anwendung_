package repository;

import model.Cleaner;
import model.Client;

public interface IClientRespository extends ICrud<Integer,Client>{

    Client findByUsername(String username);
}
