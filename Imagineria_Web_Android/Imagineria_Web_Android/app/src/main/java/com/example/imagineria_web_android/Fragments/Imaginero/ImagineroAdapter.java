package com.example.imagineria_web_android.Fragments.Imaginero;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagineria_web_android.Model.Imagineros.Imaginero;
import com.example.imagineria_web_android.R;

import java.util.List;

public class ImagineroAdapter extends RecyclerView.Adapter<ImagineroAdapter.ImagineroViewHolder> {

    private List<Imaginero> imaginero;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String id);
    }

    ImagineroAdapter(List<Imaginero> imaginero /* OnItemClickListener id*/){
        this.imaginero = imaginero;
    }

    @NonNull
    @Override
    public ImagineroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imaginero_item, parent, false);
        return new ImagineroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagineroViewHolder holder, int position){
        Imaginero imaginero1 = imaginero.get(position);
        String id = imaginero1.getId();

        holder.nombreTextViewImaginero.setText(imaginero1.getName());
        holder.localidadTextViewImaginero.setText(imaginero1.getLocalidad());

        holder.btnVerMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("id", id);

                // Navega al fragmento de detalles con el ID como argumento
                Navigation.findNavController(v)
                        .navigate(R.id.action_navigation_imagineros_to_navigation_imaginero_detalle, args);
            }
        });

    }

    @Override
    public int getItemCount(){
        return imaginero.size();
    }

    void updateData(List<Imaginero> imaginero) {
        this.imaginero = imaginero;
    }

    class ImagineroViewHolder extends RecyclerView.ViewHolder{

        TextView nombreTextViewImaginero;
        TextView localidadTextViewImaginero;
        Button btnVerMas;

        ImagineroViewHolder(@NonNull View itemView){
            super(itemView);
            nombreTextViewImaginero = itemView.findViewById(R.id.nombreTextViewImaginero);
            localidadTextViewImaginero = itemView.findViewById(R.id.localidadTextViewImaginero);
            btnVerMas = itemView.findViewById(R.id.btn_verMas);
        }
    }
}



