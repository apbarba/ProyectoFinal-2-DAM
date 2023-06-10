package com.example.imagineria_web_android.Fragments.Profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.imagineria_web_android.Model.Obras.Obra;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.ObraViewModel;

import java.util.ArrayList;
import java.util.List;

public class AdapaterFavoritos extends RecyclerView.Adapter<AdapaterFavoritos.FavoritoViewHolder> {

    private List<Obra> favoritos;


    public interface OnRemoveFavoritoListener {
        void onRemoveFavorito(String obraId);
    }

    private OnRemoveFavoritoListener listener;

    public AdapaterFavoritos(ArrayList<Obra> obras) {
        this.favoritos = obras;
    }

    // Método para asignar el listener
    public void setOnRemoveFavoritoListener(OnRemoveFavoritoListener listener) {
        this.listener = listener;
    }

    public void setFavoritos(List<Obra> favoritos) {
        this.favoritos = favoritos;
        notifyDataSetChanged();
    }

    @Override
    public FavoritoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_item, parent, false);
        return new FavoritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoritoViewHolder holder, int position) {
        Obra obra = favoritos.get(position);
        holder.nombreObra.setText(obra.getName());

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    String obraId = obra.getId();
                    listener.onRemoveFavorito(obraId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoritos != null ? favoritos.size() : 0;
    }

    public class FavoritoViewHolder extends RecyclerView.ViewHolder {

        TextView nombreObra;
        Button btnEliminar;

        public FavoritoViewHolder(View itemView) {
            super(itemView);
            nombreObra = itemView.findViewById(R.id.nombreObraFav);
            btnEliminar = itemView.findViewById(R.id.btn_eliminar_fav);
        }
    }
}
