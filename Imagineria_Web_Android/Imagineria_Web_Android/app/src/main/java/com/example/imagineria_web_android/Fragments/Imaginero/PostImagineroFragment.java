package com.example.imagineria_web_android.Fragments.Imaginero;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imagineria_web_android.Model.Imagineros.Imaginero;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.ImagineroViewModel;


public class PostImagineroFragment extends Fragment {


    private EditText postNameImaginero, postLocalidadImaginero, postEdadImaginero;
    private Button btnAgregar, btnCancelar;

    private ImagineroViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_imaginero, container, false);

        postNameImaginero = view.findViewById(R.id.post_name_imaginero);
        postLocalidadImaginero = view.findViewById(R.id.post_localidad_imaginero);
        postEdadImaginero = view.findViewById(R.id.post_edad_imaginero);
        btnAgregar = view.findViewById(R.id.btn_agregar);
        btnCancelar = view.findViewById(R.id.btn_cancelar);

        viewModel = new ViewModelProvider(this).get(ImagineroViewModel.class);

        btnAgregar.setOnClickListener(v -> {
            String name = postNameImaginero.getText().toString();
            String localidad = postLocalidadImaginero.getText().toString();
            int edad = Integer.parseInt(postEdadImaginero.getText().toString());

            Imaginero imaginero = new Imaginero();
            imaginero.setName(name);
            imaginero.setLocalidad(localidad);
            imaginero.setEdad(edad);

            viewModel.createImaginero(imaginero);
            Navigation.findNavController(v).navigate(R.id.action_post_imaginero_to_navigation_imagineros);
        });


        btnCancelar.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_post_imaginero_to_navigation_imagineros);
        });

        return view;
    }
}