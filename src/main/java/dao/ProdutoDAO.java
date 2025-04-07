package dao;

import dao.generic.GenericDAO;
import dao.generic.GenericPostgree1;
import domain.Produto;

public class ProdutoDAO extends GenericPostgree1<Produto, Long> implements IProdutoDAO {
    public ProdutoDAO() {
        super(Produto.class);
    }
}