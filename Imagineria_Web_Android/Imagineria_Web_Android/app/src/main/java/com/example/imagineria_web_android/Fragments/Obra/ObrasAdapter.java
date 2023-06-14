package com.example.imagineria_web_android.Fragments.Obra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imagineria_web_android.GlideApp;
import com.example.imagineria_web_android.Model.Obras.Obra;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.RetrofitInstance;
import com.example.imagineria_web_android.ViewModel.ObraViewModel;

import java.util.List;

public class ObrasAdapter extends RecyclerView.Adapter<ObrasAdapter.ObraViewHolder>{
    private List<Obra> obras;
    private ObraViewModel obraViewModel;
    private boolean isLoading = false;


    ObrasAdapter(List<Obra> obras, ObraViewModel obraViewModel) {
        this.obras = obras;
        this.obraViewModel = obraViewModel;
    }

    @NonNull
    @Override
    public ObraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.obra_item, parent, false);
        return new ObraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObraViewHolder holder, int position) {
        if (position == obras.size() - 1 && !isLoading) {
            // Se ha llegado al final de la lista y no se está cargando
            // más obras, así que cargamos las siguientes.
            isLoading = true;
            obraViewModel.loadObras();
        }

        Obra obra = obras.get(position);
        String id = obra.getId();

        holder.nombreTextView.setText(obra.getName());
        holder.fechaTextView.setText(obra.getFecha());

        String imageUrl = RetrofitInstance.BASE_URL + obra.getImg();
        GlideApp.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.obrasImg);
        holder.btnVerMas.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("id", id);
                args.putString("imagen", imageUrl);

                Navigation.findNavController(view)
                        .navigate(R.id.action_navigation_obras_to_navigation_obra_detalle, args);
            }
        });
    }

    @Override
    public int getItemCount() {
        return obras.size();
    }

    void updateData(List<Obra> obras) {
        this.obras.addAll(obras);
        notifyDataSetChanged();
    }

    void replaceData(List<Obra> obras) {
        this.obras.clear();
        this.obras.addAll(obras);
        notifyDataSetChanged();
    }


    class ObraViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView, fechaTextView;
        Button btnVerMas;
        ImageView obrasImg;

        ObraViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            fechaTextView = itemView.findViewById(R.id.fechaTextView);
            obrasImg = itemView.findViewById(R.id.obrasImg);
            btnVerMas = itemView.findViewById(R.id.btn_eliminar_fav);

        }
    }
}



