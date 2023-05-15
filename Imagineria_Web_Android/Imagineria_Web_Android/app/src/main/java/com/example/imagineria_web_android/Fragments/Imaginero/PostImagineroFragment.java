package com.example.imagineria_web_android.Fragments.Imaginero;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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


    public PostImagineroFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        return inflater.inflate(R.layout.fragment_post_imaginero, container, false);
    }

   /* private ImagineroViewModel imagineroViewModel;
    private EditText nameEditText, edadEditText, localidadEditText;
    private Button createButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_post_imaginero, container, false);

        // Inicializar ViewModel
        imagineroViewModel = new ViewModelProvider(this).get(ImagineroViewModel.class);

        // Inicializar UI
        nameEditText = view.findViewById(R.id.post_name_imaginero);
        edadEditText = view.findViewById(R.id.post_edad_imaginero);
        localidadEditText = view.findViewById(R.id.post_localidad_imaginero);
        createButton = view.findViewById(R.id.btn_agregar);

        createButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            int edad = Integer.parseInt(edadEditText.getText().toString().trim());
            String localidad = localidadEditText.getText().toString().trim();

            Imaginero newImaginero = new Imaginero();
            newImaginero.setName(name);
            newImaginero.setEdad(edad);
            newImaginero.setLocalidad(localidad);

            imagineroViewModel.createImaginero(newImaginero);
        });

        imagineroViewModel.getCreatedImaginero().observe(getViewLifecycleOwner(), imaginero -> {
            if (imaginero != null) {
                // Imaginero creado exitosamente, navegar de regreso al fragmento Imaginero
                Toast.makeText(getContext(), "Imaginero creado exitosamente", Toast.LENGTH_SHORT).show();
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            } else {
                // Mostrar error
                Toast.makeText(getContext(), "Error al crear el imaginero", Toast.LENGTH_SHORT).show();
            }
        });

        return view; // Retornar la vista inflada
    }*/
}