package com.example.imagineria_web_android.Fragments.Profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.ViewModel.ProfileViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class CambiarContrasenaFragment extends Fragment {
    private EditText oldPasswordEditText;
    private EditText newPasswordEditText;
    private EditText verifyPasswordEditText;

    private ProfileViewModel profileViewModel;
    private String storedPassword;

    public CambiarContrasenaFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cambiar_contrasena, container, false);

        oldPasswordEditText = view.findViewById(R.id.oldPassword);
        newPasswordEditText = view.findViewById(R.id.newPassword);
        verifyPasswordEditText = view.findViewById(R.id.verifyPasswordChange);

        Button editarPasswordButton = view.findViewById(R.id.editarPassword);
        editarPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = oldPasswordEditText.getText().toString();
                String newPassword = newPasswordEditText.getText().toString();
                String verifyPassword = verifyPasswordEditText.getText().toString();

                if (verifyCurrentPassword(oldPassword)) {
                    profileViewModel.changePassword(oldPassword, newPassword, verifyPassword).observe(getViewLifecycleOwner(), user -> {
                        if (user != null) {
                            Toast.makeText(getContext(), "Contraseña cambiada exitosamente", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(v).navigate(R.id.action_put_password_to_navigate_profile);
                        } else {
                            Toast.makeText(getContext(), "Error al cambiar la contraseña", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Contraseña actual incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button cancelarButton = view.findViewById(R.id.bnt_cancelar_change);
        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_put_password_to_navigate_profile);

            }
        });

        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);

        storedPassword = getStoredPasswordFromPreferences();

        return view;
    }

    private boolean verifyCurrentPassword(String currentPassword) {
        return currentPassword.equals(storedPassword);
    }

    private String getStoredPasswordFromPreferences() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        return sharedPreferences.getString("password", "");
    }
}
