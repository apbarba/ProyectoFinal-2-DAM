package com.example.imagineria_web_android.Fragments.Profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.imagineria_web_android.Model.Obras.Obra;
import com.example.imagineria_web_android.R;

import java.util.ArrayList;
import java.util.List;

public class AdapaterFavoritos extends RecyclerView.Adapter<AdapaterFavoritos.FavoritoViewHolder> {

    private List<Obra> favoritos;

    public <E> AdapaterFavoritos(ArrayList<E> es) {
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
    }

    @Override
    public int getItemCount() {
        return favoritos != null ? favoritos.size() : 0;
    }

    public class FavoritoViewHolder extends RecyclerView.ViewHolder {

        TextView nombreObra;

        public FavoritoViewHolder(View itemView) {
            super(itemView);
            nombreObra = itemView.findViewById(R.id.nombreObraFav);
        }
    }
}
