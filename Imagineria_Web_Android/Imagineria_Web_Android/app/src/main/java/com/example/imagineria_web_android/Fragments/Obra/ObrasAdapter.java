package com.example.imagineria_web_android.Fragments.Obra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagineria_web_android.Model.Obras.Obra;
import com.example.imagineria_web_android.R;

import java.util.List;

public class ObrasAdapter extends RecyclerView.Adapter<ObrasAdapter.ObraViewHolder>{
    private List<Obra> obras;

    ObrasAdapter(List<Obra> obras) {
        this.obras = obras;
    }

    @NonNull
    @Override
    public ObraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.obra_item, parent, false);
        return new ObraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObraViewHolder holder, int position) {
        Obra obra = obras.get(position);
        holder.nombreTextView.setText(obra.getNombre());
        holder.fechaTextView.setText(obra.getFecha());
    }

    @Override
    public int getItemCount() {
        return obras.size();
    }

    void updateData(List<Obra> obras) {
        this.obras = obras;
        notifyDataSetChanged();
    }

    class ObraViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView, fechaTextView;

        ObraViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            fechaTextView = itemView.findViewById(R.id.fechaTextView);
        }
    }
}



