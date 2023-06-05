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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.imagineria_web_android.API.UserApi;
import com.example.imagineria_web_android.Model.Auth.User;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.RetrofitInstance;

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

    private Button buttonUpload;
    private ImageView avatarImageView;
    private SharedPreferences sharedPref;
    private UserApi userService;

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

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
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

        String userId = sharedPref.getString("user_id", "");
        getUserProfile(userId);

        String avatarUrl = sharedPref.getString("avatarUrl", "");
        if (!avatarUrl.isEmpty()) {
            Glide.with(requireContext())
                    .load(avatarUrl)
                    .into(avatarImageView);
        }

        return rootView;
    }

    private void openFileChooser() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            String userId = sharedPref.getString("user_id", "");
            changeAvatar(userId, imageUri);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imageUri);
                avatarImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void changeAvatar(String userId, Uri imageUri) {
        try {
            ContentResolver contentResolver = requireActivity().getContentResolver();
            AssetFileDescriptor assetFileDescriptor = contentResolver.openAssetFileDescriptor(imageUri, "r");
            if (assetFileDescriptor != null) {
                FileInputStream inputStream = assetFileDescriptor.createInputStream();
                File file = inputStreamToFile(inputStream);
                if (file != null && file.exists()) {
                    // Rest of the avatar change code...
                } else {
                    // Handle the case where the temporary file cannot be created
                }
                assetFileDescriptor.close();
            } else {
                // Handle the case where the AssetFileDescriptor cannot be obtained
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the file not found error
        }
    }

    private File inputStreamToFile(InputStream inputStream) throws IOException {
        if (inputStream == null) return null;
        File file = File.createTempFile("avatar", "tmp");
        OutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.close();
        inputStream.close();
        return file;
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
}



