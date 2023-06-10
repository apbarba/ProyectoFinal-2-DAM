package com.example.imagineria_web_android.Fragments.Categoria;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.CategoriaViewModel;

import java.util.ArrayList;

public class CategoriaFragment extends Fragment {

    private CategoriaViewModel categoriaViewModel;
    private RecyclerView categoriaRecyclerView;
    private CategoriaAdapter categoriaAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categoria, container, false);

        // Inicializar ViewModel
        categoriaViewModel = new ViewModelProvider(this).get(CategoriaViewModel.class);

        // Inicializar RecyclerView y Adapter
        categoriaRecyclerView = view.findViewById(R.id.categoriaRecyclerView);
        categoriaAdapter = new CategoriaAdapter(new ArrayList<>());
        categoriaRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        categoriaRecyclerView.setAdapter(categoriaAdapter);

        // Cargar obras
        categoriaViewModel.loadCategoria();

        // Observar cambios
        categoriaViewModel.getCategoriaList().observe(getViewLifecycleOwner(), categorias -> {
            // Actualizar UI
            categoriaAdapter.updateData(categorias);
        });

        return view;


    }
}