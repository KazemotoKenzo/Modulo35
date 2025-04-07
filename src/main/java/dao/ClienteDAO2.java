package dao;

import dao.generic.GenericPostgree2;
import domain.Cliente;

public class ClienteDAO2 extends GenericPostgree2<Cliente, Long> implements IClienteDAO {
    public ClienteDAO2() {
        super(Cliente.class);
    }
}