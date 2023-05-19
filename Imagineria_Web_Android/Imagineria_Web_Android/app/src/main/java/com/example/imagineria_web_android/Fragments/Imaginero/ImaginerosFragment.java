package com.example.imagineria_web_android.Fragments.Imaginero;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.ImagineroViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ImaginerosFragment extends Fragment {

    private ImagineroViewModel imagineroViewModel;
    private RecyclerView imagineroRecyclerView;
    private ImagineroAdapter imagineroAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_imagineros, container, false);

        imagineroViewModel = new ViewModelProvider(this).get(ImagineroViewModel.class);
        imagineroRecyclerView = view.findViewById(R.id.imaginerosRecyclerView);
        imagineroAdapter = new ImagineroAdapter(new ArrayList<>());
        imagineroRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        imagineroRecyclerView.setAdapter(imagineroAdapter);

        imagineroViewModel.loadImaginero();

        imagineroViewModel.getImaginero().observe(getViewLifecycleOwner(), imagineros -> {
            imagineroAdapter.updateData(imagineros);
            imagineroAdapter.notifyDataSetChanged();
        });

        FloatingActionButton fabNavigateToPostImaginero = view.findViewById(R.id.add_imaginero);
        fabNavigateToPostImaginero.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(ImaginerosFragment.this);
            navController.navigateUp();
            navController.navigate(R.id.action_navigation_imagineros_to_post_imaginero);
        });

        return view;
    }
}