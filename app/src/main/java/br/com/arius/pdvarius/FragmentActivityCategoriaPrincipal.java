package br.com.arius.pdvarius;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

/**
 * Created by Arius on 06/10/2017.
 */

public class FragmentActivityCategoriaPrincipal extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentariuscategoriaprincipal, container,false);

        AriusActivityProdutoCategoria produtoCategoria = new AriusActivityProdutoCategoria();
        produtoCategoria.montaCategorias(view, getContext());

        final AriusActivityProdutoPrincipal produtoPrincipal = new AriusActivityProdutoPrincipal();
        produtoPrincipal.montaPrincipal(view, getContext());


        TabHost host = view.findViewById(R.id.pgcContentAriusCategoriaPrincipal);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Categorias");
        spec.setContent(R.id.tbsFragmentAriusCategoriaPrincipalCategoria);
        spec.setIndicator("Categorias");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Principais");
        spec.setContent(R.id.tbsFragmentAriusCategoriaPrincipalPrincipal);
        spec.setIndicator("Principais");
        host.addTab(spec);

        host.setCurrentTab(0);

        return view;
    }
}
