package dao;

import dao.generic.GenericPostgree2;
import domain.Cliente;
import domain.Produto;
import domain.Venda;
import exceptions.DAOException;
import exceptions.TipoChaveNaoEncontradaException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class VendaDAO2 extends GenericPostgree2<Venda, Long> implements IVendaDAO {

    public VendaDAO2() {
        super(Venda.class);
    }

    @Override
    public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException {
        super.alterar(venda);
    }

    @Override
    public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException {
        super.alterar(venda);
    }

    @Override
    public void excluir(Venda entity) throws DAOException {
        throw new UnsupportedOperationException("operação não permitida");
    }

    @Override
    public Venda cadastrar(Venda entity) throws TipoChaveNaoEncontradaException, DAOException {
        try {
            openConnection();
            entity.getProdutos().forEach(prod -> {
                Produto prodJpa = entityManager.merge(prod.getProduto());
                prod.setProduto(prodJpa);
            });
            Cliente cliente = entityManager.merge(entity.getCliente());
            entity.setCliente(cliente);
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            throw new DAOException("erro salvando venda ", e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Venda consultarComCollection(Long id) {
        try {
            openConnection();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Venda> query = builder.createQuery(Venda.class);
            Root<Venda> root = query.from(Venda.class);
            root.fetch("cliente");
            root.fetch("produtos");
            query.select(root).where(builder.equal(root.get("id"), id));
            TypedQuery<Venda> tpQuery = entityManager.createQuery(query);
            Venda venda = tpQuery.getSingleResult();

            venda.getProdutos().size();

            return venda;
        } finally {
            closeConnection();
        }
    }
}
