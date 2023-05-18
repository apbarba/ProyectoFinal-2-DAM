package com.example.imagineria_web_android.Fragments.Imaginero;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagineria_web_android.Model.Imagineros.Imaginero;
import com.example.imagineria_web_android.Model.Imagineros.ImagineroResponse;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.ImagineroViewModel;

import java.util.List;

public class ImagineroAdapter extends RecyclerView.Adapter<ImagineroAdapter.ImagineroViewHolder> {

    private List<Imaginero> imaginero;

    ImagineroAdapter(List<Imaginero> imaginero){
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
        holder.nombreTextViewImaginero.setText(imaginero1.getName());
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

        ImagineroViewHolder(@NonNull View itemView){
            super(itemView);
            nombreTextViewImaginero = itemView.findViewById(R.id.nombreTextViewImaginero);
        }
    }

}


