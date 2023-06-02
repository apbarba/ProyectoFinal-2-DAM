package com.example.imagineria_web_android.Fragments.Categoria;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.imagineria_web_android.R;

public class CategoriaDetalleFragment extends Fragment {

    private EditText nombreCategoria, descripcionCategoria, obrasCategoria;
    private Button btnEditar, btnEliminar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categoria_detalle, container, false);

        nombreCategoria = view.findViewById(R.id.detalle_nombre_categoria);
        descripcionCategoria = view.findViewById(R.id.detalle_descripcion_categoria);
        obrasCategoria = view.findViewById(R.id.detalle_obras_categoria);
        btnEditar = view.findViewById(R.id.btn_editar_categoria);
        btnEliminar = view.findViewById(R.id.btn_eliminar_categoria);

        return view;
    }
}