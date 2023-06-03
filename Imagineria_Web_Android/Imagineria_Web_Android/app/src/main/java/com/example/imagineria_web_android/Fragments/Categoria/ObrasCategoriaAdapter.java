package com.example.imagineria_web_android.Fragments.Categoria;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagineria_web_android.Model.Obras.Obra;
import com.example.imagineria_web_android.R;

import java.util.List;

public class ObrasCategoriaAdapter extends RecyclerView.Adapter<ObrasCategoriaAdapter.ObraCategoriaViewHolder>{

    private List<Obra> obras;

    public ObrasCategoriaAdapter(List<Obra> obras){
        this.obras = obras;
    }

    @NonNull
    @Override
    public ObraCategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.obras_categotia, parent, false);

        return new ObraCategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObraCategoriaViewHolder holder, int position) {
        Obra obra = obras.get(position);
        holder.textViewNombreObra.setText(obra.getName());
    }

    @Override
    public int getItemCount() {
        return obras.size();
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
        notifyDataSetChanged();
    }

    public class ObraCategoriaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombreObra;

        public ObraCategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombreObra = itemView.findViewById(R.id.textViewNombreObraCategoria);
        }
    }
}
