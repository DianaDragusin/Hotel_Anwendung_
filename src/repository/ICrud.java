package repository;

public interface ICrud <ID, E>{
    void add(E e); //fuge ein Element von Typ E hinzu
    void delete(ID id); //losche nach ID
    void update(ID id, E e); //erstelle Update des Elementes mit einem neuen Element e
    E findbyID(ID id); //suche nach ID
}



