package dao.generic;

import java.io.Serializable;

public class GenericPostgree2<T extends Persistente, E extends Serializable> extends GenericDAO<T,E> {

    public GenericPostgree2(Class<T> persistenteClass) {
        super(persistenteClass, "Postgree2");
    }

}
