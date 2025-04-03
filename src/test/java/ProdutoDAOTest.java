import dao.IProdutoDAO;
import dao.ProdutoDAO;
import domain.Produto;
import exceptions.DAOException;
import exceptions.MaisDeUmRegistroException;
import exceptions.TableException;
import exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Random;

public class ProdutoDAOTest {
    private IProdutoDAO produtoDao;

    public ProdutoDAOTest() {
        produtoDao = new ProdutoDAO();
    }

    @Test
    public void cadastrarProdutoTest() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        Produto produto = gerarProduto("A1");
        Produto retorno = produtoDao.cadastrar(produto);
        Assert.assertNotNull(retorno);

        Produto produtoConsultado = produtoDao.consultar(produto.getId());
        Assert.assertNotNull(produtoConsultado);

        produtoDao.excluir(produto);
        produtoConsultado = produtoDao.consultar(produto.getId());
        Assert.assertNull(produtoConsultado);
    }

    @Test
    public void consultarProdutoTest() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        Produto produto = gerarProduto("A1");
        Produto retorno = produtoDao.cadastrar(produto);
        Assert.assertNotNull(retorno);

        Produto produtoConsultado = produtoDao.consultar(produto.getId());
        Assert.assertNotNull(produtoConsultado);

        produtoDao.excluir(produto);
        produtoConsultado = produtoDao.consultar(produto.getId());
        Assert.assertNull(produtoConsultado);
    }
    @Test

    public void excluirProdutoTest() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        Produto produto = gerarProduto("A1");
        Produto retorno = produtoDao.cadastrar(produto);
        Assert.assertNotNull(retorno);

        Produto produtoConsultado = produtoDao.consultar(produto.getId());
        Assert.assertNotNull(produtoConsultado);

        produtoDao.excluir(produto);
        produtoConsultado = produtoDao.consultar(produto.getId());
        Assert.assertNull(produtoConsultado);
    }

    @Test
    public void alterarProdutoTest() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        Produto produto = gerarProduto("A1");
        Produto retorno = produtoDao.cadastrar(produto);
        Assert.assertNotNull(retorno);

        Produto produtoConsultado = produtoDao.consultar(produto.getId());
        Assert.assertNotNull(produtoConsultado);

        produtoConsultado.setNome("Produto Novo");
        produtoDao.alterar(produtoConsultado);

        Produto produtoAlterado = produtoDao.consultar(produtoConsultado.getId());
        Assert.assertNotNull(produtoAlterado);
        Assert.assertEquals("Produto Novo", produtoAlterado.getNome());

        produtoDao.excluir(produto);
        produtoConsultado = produtoDao.consultar(produto.getId());
        Assert.assertNull(produtoConsultado);
    }

    @Test
    public void buscarTodosTest() throws DAOException, TipoChaveNaoEncontradaException {
        Produto produto = gerarProduto("A1");
        Produto retorno = produtoDao.cadastrar(produto);
        Assert.assertNotNull(retorno);


        Produto produto1 = gerarProduto("A2");
        Produto retorno1 = produtoDao.cadastrar(produto1);
        Assert.assertNotNull(retorno1);

        Collection<Produto> list = produtoDao.buscarTodos();
        Assert.assertTrue(list != null);
        Assert.assertTrue(list.size() == 2);

        list.forEach(cli -> {
            try {
                produtoDao.excluir(cli);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });

        Collection<Produto> list1 = produtoDao.buscarTodos();
        Assert.assertTrue(list1 != null);
        Assert.assertTrue(list1.size() == 0);
    }

    public Produto gerarProduto(String codigo) {
        Produto produto = new Produto();
        produto.setCodigo(codigo);
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produto.setValor(BigDecimal.TEN);
        return produto;
    }
}
