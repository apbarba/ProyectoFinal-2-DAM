package com.example.imagineria_web_android.Fragments.Obra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.imagineria_web_android.Model.Obras.Obra;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.ObraViewModel;

import org.w3c.dom.Text;


public class ObraDetalleFragment extends Fragment {

    private ObraViewModel obraViewModel;

    public ObraDetalleFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_obra_detalle, container, false);

        if (getArguments() != null){
            String id = getArguments().getString("id");

            obraViewModel = new ViewModelProvider(this).get(ObraViewModel.class);
            obraViewModel.loadImagineroById(id);
            obraViewModel.getObra().observe(getViewLifecycleOwner(), new Observer<Obra>() {
                @Override
                public void onChanged(Obra obra) {
                    TextView nombre = view.findViewById(R.id.detalle_obra_nombre);
                    nombre.setText(obra.getNombre());

                    TextView titulo = view.findViewById(R.id.detalle_obra_titulo);
                    titulo.setText(obra.getTitulo());

                    TextView precio = view.findViewById(R.id.detalle_obra_precio);
                    precio.setText(String.valueOf(obra.getPrecio()));
                }
            });
        }

        return view;
    }
}