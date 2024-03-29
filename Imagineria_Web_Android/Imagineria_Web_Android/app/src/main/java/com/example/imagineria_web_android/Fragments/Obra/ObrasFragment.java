package com.example.imagineria_web_android.Fragments.Obra;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.ObraViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ObrasFragment extends Fragment {

    private ObraViewModel obraViewModel;
    private RecyclerView obraRecyclerView;
    private ObrasAdapter obraAdapter;
    private EditText searchEditText;
    private Button searchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_obras, container, false);

        obraViewModel = new ViewModelProvider(this).get(ObraViewModel.class);

        obraRecyclerView = view.findViewById(R.id.obrasRecyclerView);
        obraAdapter = new ObrasAdapter(new ArrayList<>(), obraViewModel);
        obraRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        obraRecyclerView.setAdapter(obraAdapter);
        searchEditText = view.findViewById(R.id.searchObra);
        searchButton = view.findViewById(R.id.searchButton);

        obraRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();

                if (lastVisible >= totalItemCount - 1) {
                    obraViewModel.loadObras();
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String obraName = searchEditText.getText().toString();
                obraViewModel.loadObrasByName(obraName);
            }
        });

        obraViewModel.getObrasList().observe(getViewLifecycleOwner(), obras -> {
            obraAdapter.replaceData(obras);
        });

        obraViewModel.loadObras();

        obraViewModel.getObrasList().observe(getViewLifecycleOwner(), obras -> {
            if (obraViewModel.getSearchResultsList().getValue() == null || obraViewModel.getSearchResultsList().getValue().isEmpty()) {
                obraAdapter.replaceData(obras);
            }
        });

        obraViewModel.getSearchResultsList().observe(getViewLifecycleOwner(), searchResults -> {
            if (!searchResults.isEmpty()) {
                obraAdapter.replaceData(searchResults);
            }
        });

        FloatingActionButton fabNavigationToPostObra = view.findViewById(R.id.add_obra);
        fabNavigationToPostObra.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(ObrasFragment.this);
            navController.navigateUp();
            navController.navigate(R.id.action_navigation_obras_to_post_obra);
        });

        return view;
    }
}