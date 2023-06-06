package com.example.imagineria_web_android.Fragments.Obra;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imagineria_web_android.Model.Obras.Obra;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.ObraViewModel;

import org.w3c.dom.Text;


public class ObraDetalleFragment extends Fragment {

    private ObraViewModel obraViewModel;
    private ImageView heartIconFav;

    public ObraDetalleFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_obra_detalle, container, false);

        heartIconFav = view.findViewById(R.id.no_fav);


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

                    TextView estado = view.findViewById(R.id.detalle_obra_estado);
                    estado.setText(obra.getEstado());

                    TextView estilo = view.findViewById(R.id.detalle_obra_estilo);
                    estilo.setText(obra.getEstilo());
                }
            });

            Button btnEditar = view.findViewById(R.id.btn_editar_obra);
            btnEditar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Obra obra = obraViewModel.getObra().getValue();
                    if (obra != null){
                        Bundle bundle = new Bundle();
                        bundle.putString("id", obra.getId());
                        bundle.putString("nombre", obra.getNombre());
                        bundle.putString("titulo", obra.getTitulo());
                        bundle.putDouble("precio", obra.getPrecio());
                        bundle.putString("estado", obra.getEstado());
                        bundle.putString("estilo", obra.getEstilo());

                        Navigation.findNavController(v).navigate(R.id.navigation_put_obra, bundle);
                    }
                }
            });

            heartIconFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    String userId = sharedPreferences.getString("user_id", "");

                    obraViewModel.addObraToFavoritos(userId, id);

                    heartIconFav.setImageResource(R.drawable.favorite);
                }
            });
        }

        ImageButton imageButton = view.findViewById(R.id.imageButtonObra);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigation_obras);
            }
        });

        Button btnEliminar = view.findViewById(R.id.btn_eliminar_obra_put);

        btnEliminar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Obra obra = obraViewModel.getObra().getValue();
                if (obra != null) {
                    obraViewModel.deleteObra(obra.getId());

                    Navigation.findNavController(v).navigate(R.id.navigation_obras);
                }
            }
        });

        return view;
    }
}