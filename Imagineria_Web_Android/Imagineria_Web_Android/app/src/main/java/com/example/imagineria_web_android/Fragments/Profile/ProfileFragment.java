package com.example.imagineria_web_android.Fragments.Profile;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.imagineria_web_android.API.UserApi;
import com.example.imagineria_web_android.Activity.LoginActivity;
import com.example.imagineria_web_android.Model.Auth.AvatarChangeResponse;
import com.example.imagineria_web_android.Model.Auth.User;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.RetrofitInstance;
import com.example.imagineria_web_android.ViewModel.ProfileViewModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int IMAGE_REQUEST = 100;

    private Button buttonUpload;
    private ImageView avatarImageView;
    private SharedPreferences sharedPref;
    private UserApi userService;
    private String userId;
    private Button logoutButton;
    private ProfileViewModel viewModel;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        sharedPref = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        buttonUpload = rootView.findViewById(R.id.changeAvatarButton);
        avatarImageView = rootView.findViewById(R.id.avatarImageView);
        userService = RetrofitInstance.getRetrofitInstance(requireContext()).create(UserApi.class);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_REQUEST);
            }
        });

        viewModel.getAvatarChangeResponse().observe(getViewLifecycleOwner(), response -> {
            if (response != null) {
                String avatarUrl = RetrofitInstance.BASE_URL + "download/" + response.getAvatarFilename();
                Picasso.get().load(avatarUrl).into(avatarImageView);
            } else {
                Toast.makeText(getActivity(), "Error al cambiar el avatar", Toast.LENGTH_SHORT).show();
            }
        });

        Button cambiarContrasenaButton = rootView.findViewById(R.id.changeAPassword);
        cambiarContrasenaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.navigation_put_password);
            }
        });

        logoutButton = rootView.findViewById(R.id.btn_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        Button favoritosButton = rootView.findViewById(R.id.btn_ver_fav);
        favoritosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_profile_to_listaFav); // Cambia "navigation_favoritos" al id de tu FavoritosFragment en el nav_graph
            }
        });

        userId = sharedPref.getString("user_id", "");
        getUserProfile(userId);

        String avatarUrl = sharedPref.getString("avatarUrl", "");
        if (!avatarUrl.isEmpty()) {
            Glide.with(requireContext())
                    .load(avatarUrl)
                    .into(avatarImageView);
        }

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String imagePath = cursor.getString(columnIndex);
            cursor.close();

            File file = new File(imagePath);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

            userId = sharedPref.getString("user_id", "");
            viewModel.changeAvatar(userId, body); // Asegúrate de obtener el userId correctamente
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            } else {
                // handle permission denied case
            }
        }
    }


    private void getUserProfile(String userId) {
        userService.getUserProfile(userId).enqueue(new Callback<Optional<User>>() {
            @Override
            public void onResponse(Call<Optional<User>> call, Response<Optional<User>> response) {
                if (response.isSuccessful()) {
                    Optional<User> userOptional = response.body();
                    if (userOptional.isPresent()) {
                        User user = userOptional.get();
                        String avatarUrl = user.getAvatar();

                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("avatarUrl", avatarUrl);
                        editor.apply();

                        Glide.with(requireContext())
                                .load(avatarUrl)
                                .into(avatarImageView);
                    } else {
                        // handle case when user is not present in the response
                    }
                } else {
                    // handle error response
                }
            }

            @Override
            public void onFailure(Call<Optional<User>> call, Throwable t) {
                // handle failure
            }
        });
    }

    private void logout() {
        // Eliminar el token y otros datos de sesión guardados en SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("token");
        editor.remove("user_id");
        editor.remove("password");
        editor.apply();

        // Navegar al LoginActivity
        Intent intent = new Intent(requireContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}



