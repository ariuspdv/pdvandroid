package br.com.arius.pdvarius;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arius.pdv.base.PdvDao;
import arius.pdv.base.PdvService;
import arius.pdv.base.Produto;
import arius.pdv.base.ProdutoCategoria;
import arius.pdv.base.ProdutoDao;
import arius.pdv.base.Venda;
import arius.pdv.base.VendaItem;
import arius.pdv.base.VendaSituacao;
import arius.pdv.core.AppContext;
import arius.pdv.core.FuncionaisFilters;
import arius.pdv.db.AndroidUtils;
import arius.pdv.db.AriusCursorAdapter;

/**
 * Created by Arius on 09/10/2017.
 */

public class AriusActivityProdutoPrincipal extends ActivityPadrao {

    private GridView grdProdPrincipal;
    private Context context;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contentariusprodprincipal);

        montaPrincipal(null, getApplicationContext());

    }

    public void montaPrincipal(View view, Context context){
        this.context = context;

        if (view == null)
            grdProdPrincipal = (GridView) findViewById(R.id.grdProduto_Principal);
        else
            grdProdPrincipal = view.findViewById(R.id.grdProduto_Principal);

        pesquisaPrincipa(0);

        grdProdPrincipal.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                inserirItemVenda((Produto) adapterView.getItemAtPosition(i));
            }
        });

        btnVoltar = view.findViewById(R.id.btnProPrincipalVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesquisaPrincipa(0);
            }
        });
    }

    public void pesquisaPrincipa(final long categoria_id){
        AriusCursorAdapter adapter_item = null;
        FuncionaisFilters<Produto> filter = new FuncionaisFilters<Produto>() {
            @Override
            public boolean test(Produto p) {
                if (categoria_id == 0)
                    return p.getPrincipal();
                else
                    return p.getProdutoCategoria() == null ? false : p.getProdutoCategoria().getId() == categoria_id;
            }
        };

        List<Produto> lproduto;

        lproduto = AppContext.get().getDao(ProdutoDao.class).listCache(filter);
        Map<Integer, String> campos = new HashMap<>();
        campos.put(R.id.combobox_codigo,"produto.codigo");
        campos.put(R.id.combobox_descricao,"produto.descricao");

        if (lproduto.size() > 0) {
            adapter_item = new AriusCursorAdapter(context,
                    R.layout.layoutprodprincipal,
                    R.layout.layoutprodprincipal,
                    campos,
                    lproduto);

            adapter_item.setMontarCamposTela(new AriusCursorAdapter.MontarCamposTela() {
                @Override
                public void montarCamposTela(Object p, View v) {
                    TextView edtaux = v.findViewById(R.id.edtlayoutProdPrincipal);
                    if (edtaux != null)
                        edtaux.setText(((Produto) p).getDescricao());
                    ImageView imgaux = v.findViewById(R.id.imglayoutProdPrincipal);
                    imgaux.setImageResource(R.drawable.semimagem);
                }
            });

            grdProdPrincipal.setAdapter(adapter_item);
        } else
            AndroidUtils.toast(this.context,"Nenhum produto encontrado para a categoria!");
    }

    public void inserirItemVenda(Produto produto){
        if (PdvService.get().getVendaAtiva() == null){
            Venda vnd = new Venda();
            vnd.setDataHora(new Date());
            vnd.setSituacao(VendaSituacao.ABERTA);
            vnd.setValorTroco(0);

            PdvService.get().insereVenda(vnd);

            PdvService.get().getPdv().setVendaAtiva(vnd);
            AppContext.get().getDao(PdvDao.class).update(PdvService.get().getPdv());
        }

        VendaItem vndItem = new VendaItem();
        vndItem.setVenda(PdvService.get().getVendaAtiva());
        vndItem.setProduto(produto);
        vndItem.setQtde(1);
        vndItem.setValorTotal(10);

        PdvService.get().insereVendaItem(vndItem);

        AndroidUtils.toast(context,vndItem.getProduto().getDescricao() + " \n Incluido na venda!");

        progressBar(false);
    }

}