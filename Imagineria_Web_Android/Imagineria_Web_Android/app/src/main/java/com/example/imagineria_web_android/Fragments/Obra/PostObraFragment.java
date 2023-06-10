package com.example.imagineria_web_android.Fragments.Obra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.imagineria_web_android.Model.Categoria.Categoria;
import com.example.imagineria_web_android.Model.Obras.Obra;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.ObraViewModel;

import java.util.ArrayList;
import java.util.List;

public class PostObraFragment extends Fragment {

    private EditText postNameObra, postTitleObra,postPriceObra, postStadeObra, postStileObra;
    private Button btnAgregar, btnCancelar;
    private List<Categoria> categorias;
    private Categoria categoriaSeleccionada;
    private List<String> nombresCategorias;
    private Obra obra;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_obra, container, false);

        obra = new Obra();
        postNameObra = view.findViewById(R.id.post_name_obra);
        postTitleObra = view.findViewById(R.id.post_title_obra);
        postPriceObra = view.findViewById(R.id.post_precio_obra);
        postStadeObra = view.findViewById(R.id.post_estado_obra);
        postStileObra = view.findViewById(R.id.post_estilo_obra);
        btnAgregar = view.findViewById(R.id.btn_agregar_obra);
        btnCancelar = view.findViewById(R.id.btn_cancelar_obra);
        Spinner spinnerCategorias = view.findViewById(R.id.spinner_categorias);

      //  ViewModelProvider.Factory factory = (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory();

        ObraViewModel viewModel = new ViewModelProvider(this).get(ObraViewModel.class);

        viewModel.getCategoriasList().observe(getViewLifecycleOwner(), categorias -> {
           this.categorias = categorias;

            nombresCategorias = new ArrayList<>();
            for (Categoria categoria : categorias) {
                nombresCategorias.add(categoria.getNombre());
            }

           ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, nombresCategorias);
           adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           spinnerCategorias.setAdapter(adapter);
        });

        spinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriaSeleccionada = categorias.get(position);
                obra.setCategoria(categoriaSeleccionada.getId().toString()); // Aquí asigna el ID como una cadena
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //REALIZAR AQUI ALGO CUANDO LA CATEGORIA NO ES SELECCIONADA
                categoriaSeleccionada = null;
            }
        });


        viewModel.getCategorias();

        btnAgregar.setOnClickListener(v -> {
            String name = postNameObra.getText().toString();
            String title = postTitleObra.getText().toString();
            double price = Double.parseDouble(postPriceObra.getText().toString());
            String stade = postStadeObra.getText().toString();
            String stile = postStileObra.getText().toString();

            Obra obra = new Obra();
            obra.setName(name);
            obra.setTitulo(title);
            obra.setPrecio(price);
            obra.setEstado(stade);
            obra.setEstilo(stile);
            if (categoriaSeleccionada != null){
                obra.setCategoria(categoriaSeleccionada.getId());
                Log.d("PostObraFragment", "ID de categoría seleccionada: " + categoriaSeleccionada.getId().toString());
            } else {
                Log.d("PostObraFragment", "Ninguna categoría seleccionada.");
            }

            viewModel.createObra(obra);
            Navigation.findNavController(v).navigate(R.id.action_post_obra_to_navigation_obras);

        });

        btnCancelar.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_post_obra_to_navigation_obras);
        });

        return view;
    }
}