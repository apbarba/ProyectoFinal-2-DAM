package com.example.imagineria_web_android.Fragments.Categoria;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.imagineria_web_android.R;


public class PutCategoriaFragment extends Fragment {

    private EditText putNombre, putDescripcion;
    private Button btnEditar, btnCancelar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_put_categoria, container, false);

        putNombre = view.findViewById(R.id.nombre_categoria_put);
        putDescripcion = view.findViewById(R.id.descripcion_categoria_put);
        btnEditar = view.findViewById(R.id.btn_editar_put_categoria);
        btnCancelar = view.findViewById(R.id.btn_cancelar_put_categoria);

        return view;
    }
}