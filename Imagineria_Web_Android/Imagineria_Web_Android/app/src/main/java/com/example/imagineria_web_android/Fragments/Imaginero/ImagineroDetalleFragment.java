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
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.imagineria_web_android.Model.Imagineros.Imaginero;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.ImagineroViewModel;


public class ImagineroDetalleFragment extends Fragment {
    private ImagineroViewModel imagineroViewModel;

    public ImagineroDetalleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_imaginero_detalle, container, false);

        if (getArguments() != null) {
            String id = getArguments().getString("id");

            imagineroViewModel = new ViewModelProvider(this).get(ImagineroViewModel.class);
            imagineroViewModel.loadImagineroById(id);
            imagineroViewModel.getImaginero().observe(getViewLifecycleOwner(), new Observer<Imaginero>() {
                @Override
                public void onChanged(Imaginero imaginero) {
                    TextView nombre = view.findViewById(R.id.nameImaginero);
                    nombre.setText(imaginero.getName());

                    TextView edad = view.findViewById(R.id.edadImaginero);
                    edad.setText(String.valueOf(imaginero.getEdad()));

                    TextView localidad = view.findViewById(R.id.localidadImaginero);
                    localidad.setText(imaginero.getLocalidad());
                }
            });

            Button btnEditar = view.findViewById(R.id.btn_editar);
            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Imaginero imaginero = imagineroViewModel.getImaginero().getValue();
                    if (imaginero != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", imaginero.getId());
                        bundle.putString("localidad", imaginero.getLocalidad());
                        bundle.putInt("edad", imaginero.getEdad());

                        // Aquí debes reemplazar "action_id" por la acción real que lleva al fragmento de edición
                        Navigation.findNavController(v).navigate(R.id.navigation_put_imaginero, bundle);
                    }
                }
            });
        }

        ImageButton imageButton = view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí redirige al fragmento "ImaginerosFragment"
                Navigation.findNavController(v).navigate(R.id.navigation_imagineros);
            }
        });

        return view;
    }

}