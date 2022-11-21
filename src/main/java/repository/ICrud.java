package repository;

import java.util.List;

public interface ICrud <username, E>{
    boolean add(E e); //fuge ein Element von Typ E hinzu
    boolean delete(username username); //losche nach username
    boolean update(username username, E e); //erstelle Update des Elementes mit einem neuen Element e
    E findbyusername(username username); //suche nach username
    List<E> getAll(); //gibt die Liste

}



