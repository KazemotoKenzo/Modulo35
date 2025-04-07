package dao;

import dao.generic.GenericDAO;
import dao.generic.GenericPostgree1;
import domain.Cliente;

public class ClienteDAO extends GenericPostgree1<Cliente, Long> implements IClienteDAO {
    public ClienteDAO() {
        super(Cliente.class);
    }
}