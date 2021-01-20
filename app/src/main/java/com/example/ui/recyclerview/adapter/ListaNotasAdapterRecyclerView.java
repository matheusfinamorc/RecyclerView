package com.example.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceep.Nota;
import com.example.ceep.R;
import com.example.ui.recyclerview.adapter.listener.OnItemClickListener;

import java.util.Collections;
import java.util.List;

public class ListaNotasAdapterRecyclerView extends RecyclerView.Adapter<ListaNotasAdapterRecyclerView.NotaViewHolder> {

    private final List<Nota> notas;
    private final Context context;
    private OnItemClickListener onItemClickListener;


    public ListaNotasAdapterRecyclerView(Context context, List<Nota> notas){
        this.context = context;
        this.notas = notas;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override

    // Responsavel por criar as views do ViewHolder
    public ListaNotasAdapterRecyclerView.NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // responsavel por criar os layouts

        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_nota, parent, false);

        return new NotaViewHolder(viewCriada);
    }


    //Cria o objeto e começa a vincular com o ViewHolder (colocando as informações dentro da View)
    @Override
    public void onBindViewHolder(ListaNotasAdapterRecyclerView.NotaViewHolder holder, int position) {
        Nota nota = notas.get(position);
        holder.vincula(nota);

    }



    // tem que atribuir o tamanho da lista ( usando size() )
    @Override
    public int getItemCount() {
        return notas.size();
    }

    public void altera(int posicao, Nota nota) {
        notas.set(posicao, nota);
        notifyDataSetChanged();
    }

    public void remove(int posicao) {
        notas.remove(posicao);
        notifyItemRemoved(posicao);
    }

    public void troca(int posicaoInicial, int posicaoFinal) {
        Collections.swap(notas, posicaoInicial, posicaoFinal);
        notifyItemMoved(posicaoInicial, posicaoFinal);
    }

    class NotaViewHolder extends RecyclerView.ViewHolder {

        private final TextView  titulo;
        private final TextView descricao;
        private Nota nota;

        public NotaViewHolder(@NonNull View itemView) {

            super(itemView);
            titulo = itemView.findViewById(R.id.item_nota_titulo);
            descricao = itemView.findViewById(R.id.item_nota_descricao);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(nota, getAdapterPosition());
                }
            });

        }
        //com o Holder recebido de parametro, se consegue pegar a view criada

        public void vincula(Nota nota){
            this.nota = nota;
            titulo.setText(nota.getTitulo());
            descricao.setText(nota.getDescricao());
        }

    }

    public void adiciona(Nota nota){
        notas.add(nota);
        notifyDataSetChanged();
    }

}
