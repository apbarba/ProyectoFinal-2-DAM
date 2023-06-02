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

    private EditText putNombre, putTitulo, putPrecio, putEstado, putEstilo, putImg;
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
        putTitulo = view.findViewById(R.id.put_titulo_obra);
        putPrecio = view.findViewById(R.id.put_precio_obra);
        putEstado = view.findViewById(R.id.put_estado_obra);
        putEstilo = view.findViewById(R.id.put_estilo_obra);
        putImg = view.findViewById(R.id.put_img_obra);
        btn_editar = view.findViewById(R.id.btn_editar_put_obra);
        btn_cancelar = view.findViewById(R.id.btn_cancelar_put_obra);

        obraViewModel = new ViewModelProvider(this).get(ObraViewModel.class);

        String id = getArguments().getString("id");

        obraViewModel.loadImagineroById(id);

        obraViewModel.getObra().observe(getViewLifecycleOwner(), obra -> {
            obraActual = obra;

            putNombre.setText(obra.getNombre());
            putTitulo.setText(obra.getTitulo());
          //  Double.parseDouble(putPrecio.setText(obra.getPrecio()));
            putEstado.setText(obra.getEstado());
            putEstilo.setText(obra.getEstilo());
            putImg.setText(obra.getImg());

        });

        btn_editar.setOnClickListener(v -> {
            String nuevoNombre = putNombre.getText().toString();
            String nuevoTitulo = putTitulo.getText().toString();
            double nuevoPrecio = Double.parseDouble(putPrecio.getText().toString());
            String nuevoEstado = putEstado.getText().toString();
            String nuevoEstilo = putEstilo.getText().toString();
            String nuevaImg = putImg.getText().toString(); //ESTO SE DEBE DE REALIZAR CON UN MULTIPART

            obraActual.setName(nuevoNombre);
            obraActual.setTitulo(nuevoTitulo);
            obraActual.setPrecio(nuevoPrecio);
            obraActual.setEstado(nuevoEstado);
            obraActual.setEstilo(nuevoEstilo);
            obraActual.setImg(nuevaImg);

            obraViewModel.updateObra(obraActual.getId(), obraActual);
            Navigation.findNavController(v).navigate(R.id.action_put_obra_to_navigation_obraDetalles);
        });

        btn_cancelar.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_put_obra_to_navigation_obraDetalles);
        });

        return view;
    }
}