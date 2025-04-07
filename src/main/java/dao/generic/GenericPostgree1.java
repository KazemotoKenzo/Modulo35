package dao.generic;

import java.io.Serializable;

public class GenericPostgree1<T extends Persistente, E extends Serializable> extends GenericDAO<T,E> {

    public GenericPostgree1(Class<T> persistenteClass) {
        super(persistenteClass, "Postgree1");
    }

}
