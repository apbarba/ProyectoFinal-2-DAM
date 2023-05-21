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

    public PutImaginerosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_put_imagineros, container, false);

        put_localidad_imaginero = view.findViewById(R.id.put_localidad_imaginero);
        put_edad_imaginero = view.findViewById(R.id.put_edad_imaginero);
        btn_cambiar = view.findViewById(R.id.btn_cambiar);
        btn_cancelar = view.findViewById(R.id.btn_cancelar);

        imagineroViewModel = new ViewModelProvider(this).get(ImagineroViewModel.class);

        // Supongamos que estás pasando el ID del Imaginero a editar a este Fragmento
        String id = getArguments().getString("id");

        // Carga el Imaginero con el ID especificado
        imagineroViewModel.loadImagineroById(id);

        imagineroViewModel.getImaginero().observe(getViewLifecycleOwner(), imaginero -> {
            // Guarda la referencia al Imaginero actual
            imagineroActual = imaginero;

            // Configura los campos de texto con los valores actuales del Imaginero
            put_localidad_imaginero.setText(imaginero.getLocalidad());
            put_edad_imaginero.setText(String.valueOf(imaginero.getEdad()));
        });

        btn_cambiar.setOnClickListener(v -> {
            // Recoge los nuevos valores
            String nuevaLocalidad = put_localidad_imaginero.getText().toString();
            int nuevaEdad = Integer.parseInt(put_edad_imaginero.getText().toString());

            // Actualiza el Imaginero con los nuevos valores
            imagineroActual.setEdad(nuevaEdad);
            imagineroActual.setLocalidad(nuevaLocalidad);

            // Actualiza el Imaginero en la base de datos
            imagineroViewModel.updateImaginero(imagineroActual.getId(), imagineroActual);
            Navigation.findNavController(v).navigate(R.id.action_put_imaginero_to_navigation_imaginerosDetalles);
        });

        btn_cancelar.setOnClickListener(v -> {
            // Aquí puedes manejar lo que quieres hacer cuando se cancela la edición.
            // Puedes simplemente cerrar este fragmento, por ejemplo.
            Navigation.findNavController(v).navigate(R.id.action_put_imaginero_to_navigation_imaginerosDetalles);
          //  getActivity().getSupportFragmentManager().popBackStack();
        });

        return view;
    }

    }