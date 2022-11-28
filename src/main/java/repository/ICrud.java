package repository;

import java.util.List;

public interface ICrud <username, E>{
    void add(E e); //fuge ein Element von Typ E hinzu
    void delete(username username); //losche nach username
    void update(username username, E e); //erstelle Update des Elementes mit einem neuen Element e
    E findbyusername(username username); //suche nach username
    List<E> getAll(); //gibt die Liste

}



