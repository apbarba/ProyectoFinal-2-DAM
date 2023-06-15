package com.example.imagineria_web_android.Fragments.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.imagineria_web_android.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class HomeFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    EditText txtLatitud, txtLongitud;
    GoogleMap mMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        txtLatitud = view.findViewById(R.id.txtLatitud);
        txtLongitud = view.findViewById(R.id.txtLongitud);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng espania = new LatLng(37.3895838,-6.0142412);
        mMap.addMarker(new MarkerOptions().position(espania).title("Sevilla"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(espania));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng){
        txtLatitud.setText("" + latLng.latitude);
        txtLongitud.setText(""+ latLng.longitude);
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng){
        txtLatitud.setText("" + latLng.latitude);
        txtLongitud.setText(""+ latLng.longitude);
    }
}