package dao;

import dao.generic.GenericPostgree2;
import domain.Produto;

public class ProdutoDAO2 extends GenericPostgree2<Produto, Long> implements IProdutoDAO {
    public ProdutoDAO2() {
        super(Produto.class);
    }
}