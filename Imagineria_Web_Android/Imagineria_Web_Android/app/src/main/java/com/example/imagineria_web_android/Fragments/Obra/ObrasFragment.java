package com.example.imagineria_web_android.Fragments.Obra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.ObraViewModel;

import java.util.ArrayList;

public class ObrasFragment extends Fragment {

    private ObraViewModel obraViewModel;
    private RecyclerView obraRecyclerView;
    private ObrasAdapter obraAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar la vista del fragmento
        View view = inflater.inflate(R.layout.fragment_obras, container, false);

        // Inicializar ViewModel
        obraViewModel = new ViewModelProvider(this).get(ObraViewModel.class);

        // Inicializar RecyclerView y Adapter
        obraRecyclerView = view.findViewById(R.id.obrasRecyclerView);
        obraAdapter = new ObrasAdapter(new ArrayList<>());
        obraRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        obraRecyclerView.setAdapter(obraAdapter);

        // Cargar obras
        obraViewModel.loadObras();

        // Observar cambios
        obraViewModel.getObras().observe(getViewLifecycleOwner(), obras -> {
            // Actualizar UI
            obraAdapter.updateData(obras);
        });

        return view;
    }
}