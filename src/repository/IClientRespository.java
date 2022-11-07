package repository;

import model.Client;

public interface IClientRespository extends ICrud<Integer,Client>{

    Client findByUsername(String username);
}
