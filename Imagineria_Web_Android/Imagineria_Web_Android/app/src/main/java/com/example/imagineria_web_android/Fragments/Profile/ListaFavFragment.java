package com.example.imagineria_web_android.Fragments.Profile;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.ImageButton;

import com.example.imagineria_web_android.Model.Obras.Obra;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.ObraViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListaFavFragment extends Fragment {

    private RecyclerView favoritosRecyclerView;
    private AdapaterFavoritos favoritosAdapter; // Este es un adaptador personalizado que tendrías que crear
    private ObraViewModel obraViewModel;


    public ListaFavFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_fav, container, false);

        favoritosRecyclerView = view.findViewById(R.id.listaFavRecyclerView);

        favoritosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        favoritosAdapter = new AdapaterFavoritos(new ArrayList<>());
        favoritosRecyclerView.setAdapter(favoritosAdapter);

        obraViewModel = new ViewModelProvider(this).get(ObraViewModel.class);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", "");

        obraViewModel.loadUserFavoritos(userId);

        obraViewModel.getFavoritos().observe(getViewLifecycleOwner(), new Observer<List<Obra>>() {
            @Override
            public void onChanged(List<Obra> favoritos) {
                favoritosAdapter.setFavoritos(favoritos);
            }
        });

       // Button btnElimar = view.findViewById(R.id.btn_eliminar_fav);

        ImageButton imageButton = view.findViewById(R.id.atras_profile);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigation_profile);
            }
        });

        return view;
    }
}