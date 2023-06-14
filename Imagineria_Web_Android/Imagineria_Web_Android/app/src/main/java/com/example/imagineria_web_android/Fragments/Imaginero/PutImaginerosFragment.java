package com.example.imagineria_web_android.Fragments.Imaginero;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.imagineria_web_android.Model.Imagineros.Imaginero;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.ImagineroViewModel;

public class PutImaginerosFragment extends Fragment {

    private EditText put_localidad_imaginero, put_edad_imaginero;
    private Button btn_cambiar, btn_cancelar;
    private ImagineroViewModel imagineroViewModel;
    private Imaginero imagineroActual;

    public PutImaginerosFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_put_imagineros, container, false);

        put_localidad_imaginero = view.findViewById(R.id.put_localidad_imaginero);
        put_edad_imaginero = view.findViewById(R.id.put_edad_imaginero);
        btn_cambiar = view.findViewById(R.id.btn_cambiar);
        btn_cancelar = view.findViewById(R.id.btn_cancelar);

        imagineroViewModel = new ViewModelProvider(this).get(ImagineroViewModel.class);

        String id = getArguments().getString("id");

        imagineroViewModel.loadImagineroById(id);

        imagineroViewModel.getImaginero().observe(getViewLifecycleOwner(), imaginero -> {
            imagineroActual = imaginero;

            put_localidad_imaginero.setText(imaginero.getLocalidad());
            put_edad_imaginero.setText(String.valueOf(imaginero.getEdad()));
        });

        btn_cambiar.setOnClickListener(v -> {
            String nuevaLocalidad = put_localidad_imaginero.getText().toString();
            int nuevaEdad = Integer.parseInt(put_edad_imaginero.getText().toString());

            imagineroActual.setEdad(nuevaEdad);
            imagineroActual.setLocalidad(nuevaLocalidad);

            imagineroViewModel.updateImaginero(imagineroActual.getId(), imagineroActual);
            Navigation.findNavController(v).navigate(R.id.navigation_imagineros);
        });

        btn_cancelar.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_put_imaginero_to_navigation_imaginerosDetalles);
        });

        return view;
    }

    }