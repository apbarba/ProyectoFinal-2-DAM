package com.example.imagineria_web_android.Fragments.Categoria;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.imagineria_web_android.Model.Categoria.Categoria;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.CategoriaViewModel;

import java.util.ArrayList;

public class CategoriaDetalleFragment extends Fragment {

    private TextView nombreCategoria, descripcionCategoria;
    private Button btnEditar, btnEliminar;
    private RecyclerView recyclerViewObras;
    private CategoriaViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categoria_detalle, container, false);

        nombreCategoria = view.findViewById(R.id.detalle_nombre_categoria);
        descripcionCategoria = view.findViewById(R.id.detalle_descripcion_categoria);
        recyclerViewObras = view.findViewById(R.id.recyclerViewObras);
        btnEditar = view.findViewById(R.id.btn_editar_categoria);
        btnEliminar = view.findViewById(R.id.btn_eliminar_categoria);

        recyclerViewObras.setLayoutManager(new LinearLayoutManager(getActivity()));
        ObrasCategoriaAdapter obrasCategoriaAdapter = new ObrasCategoriaAdapter(new ArrayList<>());
        recyclerViewObras.setAdapter(obrasCategoriaAdapter);

        if (getArguments() != null){
            String id = getArguments().getString("id");

            viewModel = new ViewModelProvider(this).get(CategoriaViewModel.class);
            viewModel.loadCategoriaByid(id);

            viewModel.getCategoria().observe(getViewLifecycleOwner(), new Observer<Categoria>() {
                @Override
                public void onChanged(Categoria categoria) {
                    nombreCategoria.setText(categoria.getNombre());
                    descripcionCategoria.setText(categoria.getDescripcion());
                    obrasCategoriaAdapter.setObras(categoria.getObras()); //Vamos a probar si funciona esto, que tiene pinta de aparecer solamente el total de obras que tiene
                }
            });

            btnEditar.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Categoria categoria = viewModel.getCategoria().getValue();
                    if (categoria != null){
                        Bundle bundle = new Bundle();
                        bundle.putString("id", categoria.getId());
                        bundle.putString("nombre", categoria.getNombre());
                        bundle.putString("descripcion", categoria.getDescripcion());

                        Navigation.findNavController(view).navigate(R.id.navigation_put_categoria,bundle);
                    }
                }
            });
        }

        ImageButton imageButton = view.findViewById(R.id.imageButtonAtrasCategoria);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigation_categorias);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Categoria categoria = viewModel.getCategoria().getValue();
                if (categoria != null){
                    viewModel.deleteCategoria(categoria.getId());

                    Navigation.findNavController(view).navigate(R.id.navigation_categorias);
                }
            }
        });

        return view;
    }
}