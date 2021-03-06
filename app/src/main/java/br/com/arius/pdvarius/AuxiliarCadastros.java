package br.com.arius.pdvarius;

import arius.pdv.base.Finalizadora;
import arius.pdv.base.FinalizadoraDao;
import arius.pdv.base.FinalizadoraTipo;
import arius.pdv.base.Historico;
import arius.pdv.base.HistoricoDao;
import arius.pdv.base.HistoricoTipo;
import arius.pdv.base.Pdv;
import arius.pdv.base.PdvDao;
import arius.pdv.base.PdvTipo;
import arius.pdv.base.Produto;
import arius.pdv.base.ProdutoCategoria;
import arius.pdv.base.ProdutoCategoriaDao;
import arius.pdv.base.ProdutoDao;
import arius.pdv.base.UnidadeMedida;
import arius.pdv.base.UnidadeMedidaDao;
import arius.pdv.base.Usuario;
import arius.pdv.base.UsuarioDao;
import arius.pdv.base.UsuarioTipo;
import arius.pdv.core.AppContext;
import arius.pdv.core.FuncionaisFilters;

/**
 * Created by Arius on 18/09/2017.
 */

public class AuxiliarCadastros {

    public void cadastraProdutos(){
        Produto produto = new Produto();

        produto.setCodigo(789123456);
        produto.setDescricao("Produto Teste 1");
        produto.setPrincipal(true);
        produto.setDescricaoReduzida("Produto Teste 1");

        AppContext.get().getDao(ProdutoDao.class).insert(produto);

        produto = new Produto();
        produto.setCodigo(789123457);
        produto.setDescricao("Produto Teste 2");
        produto.setPrincipal(false);
        produto.setDescricaoReduzida("Produto Teste 2");

        AppContext.get().getDao(ProdutoDao.class).insert(produto);

        produto = new Produto();
        produto.setCodigo(789123458);
        produto.setDescricao("Produto Teste 3");
        produto.setPrincipal(true);
        produto.setDescricaoReduzida("Produto Teste 3");

        AppContext.get().getDao(ProdutoDao.class).insert(produto);

        for (int i = 0; i < 2; i++) {
            produto = new Produto();
            produto.setCodigo(789123459);
            produto.setDescricao("Produto Teste 4");
            produto.setPrincipal(false);
            produto.setDescricaoReduzida("Produto Teste 4");

            AppContext.get().getDao(ProdutoDao.class).insert(produto);

            produto = new Produto();
            produto.setCodigo(789123460);
            produto.setDescricao("Produto Teste 5");
            produto.setPrincipal(false);
            produto.setDescricaoReduzida("Produto Teste 5");

            AppContext.get().getDao(ProdutoDao.class).insert(produto);

            produto = new Produto();
            produto.setCodigo(789123461);
            produto.setDescricao("Skol");
            produto.setPrincipal(false);
            produto.setDescricaoReduzida("Skol");
            produto.setProdutoCategoria(
                    AppContext.get().getDao(ProdutoCategoriaDao.class).find(8)
            );

            AppContext.get().getDao(ProdutoDao.class).insert(produto);

            produto = new Produto();
            produto.setCodigo(789123461);
            produto.setDescricao("Brahma");
            produto.setPrincipal(false);
            produto.setDescricaoReduzida("Brahma");
            produto.setProdutoCategoria(
                    AppContext.get().getDao(ProdutoCategoriaDao.class).find(8)
            );

            AppContext.get().getDao(ProdutoDao.class).insert(produto);

            produto = new Produto();
            produto.setCodigo(789123461);
            produto.setDescricao("Antartica");
            produto.setPrincipal(false);
            produto.setDescricaoReduzida("Laranja");
            produto.setProdutoCategoria(
                    AppContext.get().getDao(ProdutoCategoriaDao.class).find(8)
            );

            AppContext.get().getDao(ProdutoDao.class).insert(produto);

            produto = new Produto();
            produto.setCodigo(789123461);
            produto.setDescricao("Faroni Lopes");
            produto.setPrincipal(false);
            produto.setDescricaoReduzida("Faroni Lopes");
            produto.setProdutoCategoria(
                    AppContext.get().getDao(ProdutoCategoriaDao.class).find(9)
            );

            AppContext.get().getDao(ProdutoDao.class).insert(produto);

            produto = new Produto();
            produto.setCodigo(789123461);
            produto.setDescricao("Caipirinha de Vodka");
            produto.setPrincipal(false);
            produto.setDescricaoReduzida("Caipirinha de Vodka");
            produto.setProdutoCategoria(
                    AppContext.get().getDao(ProdutoCategoriaDao.class).find(10)
            );

            AppContext.get().getDao(ProdutoDao.class).insert(produto);
        }

    }

    public void cadastrarOperador(){
        Usuario operador = new Usuario();
        operador.setNome("Arius");
        operador.setLogin("Arius");
        operador.setSenha("automa");
        operador.setTipo(UsuarioTipo.OPERADOR);

        AppContext.get().getDao(UsuarioDao.class).insert(operador);

    }

    public void cadastrarFinalizadora(){
        for(Finalizadora litem : AppContext.get().getDao(FinalizadoraDao.class).listCache(new FuncionaisFilters<Finalizadora>() {
            @Override
            public boolean test(Finalizadora p) {
                return true;
            }
        })){
            AppContext.get().getDao(FinalizadoraDao.class).delete(litem);
        }

        Finalizadora finalizadora = new Finalizadora();

        finalizadora.setDescricao("Dinheiro");
        finalizadora.setTipo(FinalizadoraTipo.DINHEIRO);
        finalizadora.setPermiteTroco(true);
        finalizadora.setAceitaSangria(true);

        AppContext.get().getDao(FinalizadoraDao.class).insert(finalizadora);

        Finalizadora finalizadora2 = new Finalizadora();
        finalizadora2.setDescricao("Cartão Crédito");
        finalizadora2.setTipo(FinalizadoraTipo.CARTAO_CREDITO);
        finalizadora2.setPermiteTroco(false);
        finalizadora2.setAceitaSangria(false);

        AppContext.get().getDao(FinalizadoraDao.class).insert(finalizadora2);

        Finalizadora finalizadora3 = new Finalizadora();
        finalizadora3.setDescricao("Cartão Débito");
        finalizadora3.setTipo(FinalizadoraTipo.CARTAO_DEBITO);
        finalizadora3.setPermiteTroco(false);
        finalizadora3.setAceitaSangria(false);

        AppContext.get().getDao(FinalizadoraDao.class).insert(finalizadora3);
    }

    public void cadastrarPDV(){
        Pdv pdv = new Pdv();
        pdv.setStatus(PdvTipo.FECHADO);
        pdv.setCodigo_pdv(1);
        AppContext.get().getDao(PdvDao.class).insert(pdv);
    }

    public void cadastroUnidadeMedida(){
        UnidadeMedida unidadeMedida = new UnidadeMedida();
        unidadeMedida.setDescricao("Unidade");
        unidadeMedida.setFracionada(false);
        unidadeMedida.setSigla("UN");

        AppContext.get().getDao(UnidadeMedidaDao.class).insert(unidadeMedida);

    }

    public void cadastroeHistorico(){
        Historico historico = new Historico();
        historico.setDescricao("Pagamento Fornecedor");
        historico.setTipo(HistoricoTipo.RETIRADA);

        AppContext.get().getDao(HistoricoDao.class).insert(historico);

        historico.setDescricao("Vale");
        historico.setTipo(HistoricoTipo.RETIRADA);

        AppContext.get().getDao(HistoricoDao.class).insert(historico);

    }

    public void cadastroProdutoClassificacao(){
        ProdutoCategoria produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Lanches");

        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

        produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Bebidas");

        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

        produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Sobremesas");

        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);


        produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Sem Alcool");
        produtoClassificacao.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(2)
        );
        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

        produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Alcoolica");
        produtoClassificacao.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(2)
        );
        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

        produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Refrigerantes");
        produtoClassificacao.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(4)
        );
        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

        produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Sucos");
        produtoClassificacao.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(4)
        );
        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

        produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Cervejas");
        produtoClassificacao.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(5)
        );
        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

        produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Vinhos");
        produtoClassificacao.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(5)
        );
        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

        produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Destilados");
        produtoClassificacao.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(5)
        );
        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

    }

    public void alteraProdutoClassificacao(){
        ProdutoCategoria produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Sem Alcool");
        produtoClassificacao.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(2)
        );
        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

        produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Alcoolica");
        produtoClassificacao.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(2)
        );
        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

        produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Refrigerantes");
        produtoClassificacao.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(4)
        );
        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

        produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Sucos");
        produtoClassificacao.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(4)
        );
        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

        produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Cervejas");
        produtoClassificacao.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(5)
        );
        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

        produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Vinhos");
        produtoClassificacao.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(5)
        );
        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

        produtoClassificacao = new ProdutoCategoria();
        produtoClassificacao.setDescricao("Destilados");
        produtoClassificacao.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(5)
        );
        AppContext.get().getDao(ProdutoCategoriaDao.class).insert(produtoClassificacao);

    }

    public void alteraProduto(){
        Produto produto = new Produto();
//        produto.setCodigo(789123461);
//        produto.setDescricao("Coca Cola");
//        produto.setPrincipal(false);
//        produto.setDescricaoReduzida("Coca Cola");
//        produto.setProdutoCategoria(
//                AppContext.get().getDao(ProdutoCategoriaDao.class).find(6)
//        );
//
//        AppContext.get().getDao(ProdutoDao.class).insert(produto);
//
//        produto = new Produto();
//        produto.setCodigo(789123461);
//        produto.setDescricao("Guaraná Antartica");
//        produto.setPrincipal(false);
//        produto.setDescricaoReduzida("Guaraná Antartica");
//        produto.setProdutoCategoria(
//                AppContext.get().getDao(ProdutoCategoriaDao.class).find(6)
//        );
//
//        AppContext.get().getDao(ProdutoDao.class).insert(produto);
//
//        produto = new Produto();
//        produto.setCodigo(789123461);
//        produto.setDescricao("Laranja");
//        produto.setPrincipal(false);
//        produto.setDescricaoReduzida("Laranja");
//        produto.setProdutoCategoria(
//                AppContext.get().getDao(ProdutoCategoriaDao.class).find(7)
//        );
//
//        AppContext.get().getDao(ProdutoDao.class).insert(produto);

        produto = new Produto();
        produto.setCodigo(789123461);
        produto.setDescricao("Skol");
        produto.setPrincipal(false);
        produto.setDescricaoReduzida("Skol");
        produto.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(8)
        );

        AppContext.get().getDao(ProdutoDao.class).insert(produto);

        produto = new Produto();
        produto.setCodigo(789123461);
        produto.setDescricao("Brahma");
        produto.setPrincipal(false);
        produto.setDescricaoReduzida("Brahma");
        produto.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(8)
        );

        AppContext.get().getDao(ProdutoDao.class).insert(produto);

        produto = new Produto();
        produto.setCodigo(789123461);
        produto.setDescricao("Antartica");
        produto.setPrincipal(false);
        produto.setDescricaoReduzida("Laranja");
        produto.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(8)
        );

        AppContext.get().getDao(ProdutoDao.class).insert(produto);

        produto = new Produto();
        produto.setCodigo(789123461);
        produto.setDescricao("Faroni Lopes");
        produto.setPrincipal(false);
        produto.setDescricaoReduzida("Faroni Lopes");
        produto.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(9)
        );

        AppContext.get().getDao(ProdutoDao.class).insert(produto);

        produto = new Produto();
        produto.setCodigo(789123461);
        produto.setDescricao("Caipirinha de Vodka");
        produto.setPrincipal(false);
        produto.setDescricaoReduzida("Caipirinha de Vodka");
        produto.setProdutoCategoria(
                AppContext.get().getDao(ProdutoCategoriaDao.class).find(10)
        );

        AppContext.get().getDao(ProdutoDao.class).insert(produto);


    }

    public void performanceProduto(){
        Produto produto;
        for (int i = 0; i <= 10000; i++){
            produto = new Produto();
            produto.setCodigo(i);
            produto.setDescricao("Produto = " + i);
            produto.setDescricaoReduzida(produto.getDescricao());

            AppContext.get().getDao(ProdutoDao.class).insert(produto);
        }
    }

}
