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
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.sincronizarUsuariosDesdeAPI();

        userViewModel.obtenerUsuarios().observe(this, usuarios -> {
            for (User u : usuarios) {
                Log.d("ROOM", "ID: " + u.getId() + ", Nombre: " + u.getName());
            }
        });
        
        
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

    }

}



