package com.example.imagineria_web_android.Fragments.Imaginero;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.imagineria_web_android.Model.Imagineros.Imaginero;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.ImagineroViewModel;

public class PutImaginerosFragment extends Fragment {

    private EditText localidadEditText;
    private EditText edadEditText;
    private Button cambiarButton;
    private Button cancelButton;
    private ImagineroViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_put_imagineros, container, false);

        localidadEditText = view.findViewById(R.id.put_localidad_imaginero);
        edadEditText = view.findViewById(R.id.put_edad_imaginero);
        cambiarButton = view.findViewById(R.id.btn_cambiar);
        cancelButton = view.findViewById(R.id.btn_cancelar);

        viewModel = new ViewModelProvider(this).get(ImagineroViewModel.class);

        cambiarButton.setOnClickListener(v -> {
            String localidad = localidadEditText.getText().toString();
            int edad = Integer.parseInt(edadEditText.getText().toString());

            Imaginero imaginero = new Imaginero();
            imaginero.setLocalidad(localidad);
            imaginero.setEdad(edad);

            // Aquí debes saber el ID del imaginero que estás actualizando.
      //      int idImaginero = ...;

          //  viewModel.updateImaginero(idImaginero, imaginero);
        });

        cancelButton.setOnClickListener(v -> {
            // Aquí puedes manejar lo que sucede cuando se hace clic en el botón de cancelar.
            // Por ejemplo, puedes volver a la pantalla anterior.
        });

        return view;
    }

}