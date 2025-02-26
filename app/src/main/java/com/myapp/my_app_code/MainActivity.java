package com.myapp.my_app_code;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;

import com.myapp.my_app_code.data.model.User;
import com.myapp.my_app_code.ui.viewModel.UserViewModel;
import com.myapp.my_app_code.utils.ApiService;
import com.myapp.my_app_code.utils.RetrofitClient;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiService apiService;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initializer Retrofit
        apiService = RetrofitClient.getClient().create(ApiService.class);

        obtenerUsers();

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Insertar User
        User User = new User("Juan Pérez", "juan@example.com");
        userViewModel.insertarUser(User);

        // Obtener Users
        List<User> listaUsers = userViewModel.obtenerUsers();
        for (User u : listaUsers) {
            Log.d("ROOM", "ID: " + u.getId() + ", Nombre: " + u.getName());
        }
        
        
        
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

    }

    private void obtenerUsers() {
        Call<List<User>> call = apiService.obtenerUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body();
                    assert users != null;
                    for (User user : users) {
                        Log.d("API", "ID: " + user.getId() + ", Nombre: " + user.getName());
                    }
                } else {
                    Log.e("API", "Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                Log.e("API", "Fallo en la petición: " + t.getMessage());
            }
        });
    }

    private void crearUser() {
        User nuevoUser = new User("", "");
        nuevoUser.setName("Ana López");
        nuevoUser.setEmail("ana@example.com");

        Call<User> call = apiService.crearUser(nuevoUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.d("API", "User creado con ID: " + response.body().getId());
                } else {
                    Log.e("API", "Error al crear user: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                Log.e("API", "Error: " + t.getMessage());
            }
        });
    }

}



