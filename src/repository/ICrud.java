package repository;

import java.util.List;

public interface ICrud <ID, E>{
    boolean add(E e); //fuge ein Element von Typ E hinzu
    void delete(ID id); //losche nach ID
    boolean update(ID id, E e); //erstelle Update des Elementes mit einem neuen Element e
    E findbyID(ID id); //suche nach ID
    List<E> getAll(); //gibt die Liste

}



