package com.example.ui.recyclerview.helper.callback;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceep.NotaDAO;
import com.example.ui.recyclerview.adapter.ListaNotasAdapterRecyclerView;

public class NotaItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ListaNotasAdapterRecyclerView adapter;

    public NotaItemTouchHelperCallback(ListaNotasAdapterRecyclerView adapter) {
        this.adapter = adapter;
    }


    @Override
    // define oq permite de animaçao (deslize esquerda ou direita)
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        // deslize tanto esquerda qnt direita
        int marcacoesDeDeslize = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        // dragFlags é arrastar, marcacoesDeDeslize para esquerda e direita
        int marcacoesDeArrastar = ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;

        return makeMovementFlags(marcacoesDeArrastar, marcacoesDeDeslize);
    }

    @Override
    // chamada para quando o elemento for arrastado
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int posicaoInicial = viewHolder.getAdapterPosition();
        int posicaoFinal = target.getAdapterPosition();
        trocaNotas(posicaoInicial, posicaoFinal);
        return true;
    }

    private void trocaNotas(int posicaoInicial, int posicaoFinal) {
        new NotaDAO().troca(posicaoInicial,posicaoFinal);
        adapter.troca(posicaoInicial, posicaoFinal);
    }

    @Override
    // quando o movimento é de deslize ele é chamado e faz alguma açao
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        removeNota(viewHolder);
    }

    private void removeNota(RecyclerView.ViewHolder posicao) {
        int posicaoDaNotaDesejada = posicao.getAdapterPosition();
        new NotaDAO().remove(posicaoDaNotaDesejada);
        adapter.remove(posicaoDaNotaDesejada);
    }
}
