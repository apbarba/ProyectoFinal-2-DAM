package com.example.imagineria_web_android.Fragments.Categoria;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.imagineria_web_android.Model.Categoria.Categoria;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.CategoriaViewModel;


public class PutCategoriaFragment extends Fragment {

    private EditText putNombre, putDescripcion;
    private Button btnEditar, btnCancelar;
    private CategoriaViewModel categoriaViewModel;
    private Categoria categoriaActual;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_put_categoria, container, false);

        putNombre = view.findViewById(R.id.nombre_categoria_put);
        putDescripcion = view.findViewById(R.id.descripcion_categoria_put);
        btnEditar = view.findViewById(R.id.btn_editar_put_categoria);
        btnCancelar = view.findViewById(R.id.btn_cancelar_put_categoria);

        categoriaViewModel = new ViewModelProvider(this).get(CategoriaViewModel.class);

        String id = getArguments().getString("id");
        categoriaViewModel.loadCategoriaByid(id);

        categoriaViewModel.getCategoria().observe(getViewLifecycleOwner(), categoria -> {
            categoriaActual = categoria;

            putNombre.setText(categoria.getNombre());
            putDescripcion.setText(categoria.getDescripcion());
        });

        btnEditar.setOnClickListener(v -> {
            String nuevoNombre = putNombre.getText().toString();
            String nuevaDescripcion = putDescripcion.getText().toString();

            categoriaActual.setNombre(nuevoNombre);
            categoriaActual.setDescripcion(nuevaDescripcion);

            categoriaViewModel.updateCategoria(categoriaActual.getId(), categoriaActual);
            Navigation.findNavController(v).navigate(R.id.navigation_categorias);
        });

        btnCancelar.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_put_categoria_to_navigation_categoriaDetalle);
        });

        return view;
    }
}