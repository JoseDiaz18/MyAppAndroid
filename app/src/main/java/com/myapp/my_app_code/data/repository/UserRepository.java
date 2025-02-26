package com.myapp.my_app_code.data.repository;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.myapp.my_app_code.utils.*;
import com.myapp.my_app_code.data.model.*;

public class UserRepository {
    private UserDao UserDao;
    private ExecutorService executorService;
    private ApiService apiService;


    public UserRepository(Context context) {
        AppDataBase db = AppDataBase.getDatabase(context);
        UserDao = db.UserDao();
        executorService = Executors.newSingleThreadExecutor();
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public void insertarUser(User User) {
        executorService.execute(() -> UserDao.insertarUser(User));
    }

    public LiveData<List<User>> obtenerUsers() {
        return UserDao.obtenerUsersLive();
    }

    public void actualizarUser(User User) {
        executorService.execute(() -> UserDao.actualizarUser(User));
    }

    public void eliminarUser(User User) {
        executorService.execute(() -> UserDao.eliminarUser(User));
    }

    public void sincronizarUsuariosDesdeAPI() {
        apiService.obtenerUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    executorService.execute(() -> {
                        UserDao.eliminarTodos();  // Limpiar la tabla para evitar duplicados
                        UserDao.insertarUsuarios(response.body());  // Insertar los nuevos datos
                    });
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("API ERROR", "Error al obtener usuarios", t);
            }
        });
    }
}
