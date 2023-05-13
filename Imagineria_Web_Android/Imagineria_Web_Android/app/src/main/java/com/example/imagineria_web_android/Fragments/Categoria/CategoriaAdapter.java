package com.example.imagineria_web_android.Fragments.Categoria;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagineria_web_android.Model.Categoria.Categoria;
import com.example.imagineria_web_android.Model.Categoria.CategoriaResponse;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.CategoriaViewModel;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>{

    private List<Categoria> categorias;

    CategoriaAdapter(List<Categoria> categorias){
        this.categorias = categorias;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoria_item, parent, false);
        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position){
        Categoria categoria = categorias.get(position);
        holder.nombreTextViewCategoria.setText(categoria.getNombre());
        holder.descripcionTextView.setText(categoria.getDescripcion());
    }

    @Override
    public int getItemCount(){
        return categorias.size();
    }

    void updateData(List<Categoria> categorias){
        this.categorias = categorias;
        notifyDataSetChanged();
    }


    class CategoriaViewHolder extends RecyclerView.ViewHolder{
        TextView nombreTextViewCategoria, descripcionTextView;

        CategoriaViewHolder(@NonNull View itemView){
            super(itemView);
            nombreTextViewCategoria = itemView.findViewById(R.id.nombreTextViewCategoria);
            descripcionTextView = itemView.findViewById(R.id.descripcionTextView);
        }
    }
}
