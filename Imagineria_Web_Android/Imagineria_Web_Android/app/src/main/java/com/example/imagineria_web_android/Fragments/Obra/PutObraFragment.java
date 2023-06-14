package com.example.imagineria_web_android.Fragments.Obra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.imagineria_web_android.Model.Obras.Obra;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.ObraViewModel;

public class PutObraFragment extends Fragment {

    private EditText putNombre, putPrecio, putEstado, putEstilo;
    private Button btn_editar, btn_cancelar;
    private ObraViewModel obraViewModel;
    private Obra obraActual;

    public PutObraFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_put_obra, container, false);

        putNombre = view.findViewById(R.id.put_nombre_obra);
        putPrecio = view.findViewById(R.id.put_precio_obra);
        putEstado = view.findViewById(R.id.put_estado_obra);
        putEstilo = view.findViewById(R.id.put_estilo_obra);
        btn_editar = view.findViewById(R.id.btn_editar_put_obra);
        btn_cancelar = view.findViewById(R.id.btn_cancelar_put_obra);

        obraViewModel = new ViewModelProvider(this).get(ObraViewModel.class);

        String id = getArguments().getString("id");

        obraViewModel.loadImagineroById(id);

        obraViewModel.getObra().observe(getViewLifecycleOwner(), obra -> {
            obraActual = obra;

            putNombre.setText(obra.getName());
            putPrecio.setText(String.valueOf(obra.getPrecio()));
            putEstado.setText(obra.getEstado());
            putEstilo.setText(obra.getEstilo());

        });

        btn_editar.setOnClickListener(v -> {
            String nuevoNombre = putNombre.getText().toString();
            String nuevoPrecioPor = putPrecio.getText().toString();

            double nuevoPrecio = Double.parseDouble(putPrecio.getText().toString());
            if (!nuevoPrecioPor.isEmpty()) {
                try {
                    nuevoPrecio = Double.parseDouble(nuevoPrecioPor);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            String nuevoEstado = putEstado.getText().toString();
            String nuevoEstilo = putEstilo.getText().toString();

            obraActual.setName(nuevoNombre);
            obraActual.setPrecio(nuevoPrecio);
            obraActual.setEstado(nuevoEstado);
            obraActual.setEstilo(nuevoEstilo);

            obraViewModel.updateObra(obraActual.getId(), obraActual);
            Navigation.findNavController(v).navigate(R.id.navigation_obras);
        });

        btn_cancelar.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_put_obra_to_navigation_obraDetalles);
        });

        return view;
    }
}