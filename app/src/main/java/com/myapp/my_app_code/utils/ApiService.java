package com.myapp.my_app_code.utils;

import com.myapp.my_app_code.data.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

import java.util.List;

public interface ApiService {

    // Obtener todos los users (GET)
    @GET("users")
    Call<List<User>> obtenerUsers();

    // Obtener un user por ID (GET)
    @GET("users/{id}")
    Call<User> obtenerUserPorId(@Path("id") int id);

    // Crear un nuevo user (POST)
    @POST("users")
    Call<User> crearUser(@Body User user);

    // Actualizar un user (PUT)
    @PUT("users/{id}")
    Call<User> actualizarUser(@Path("id") int id, @Body User user);

    // Eliminar un user (DELETE)
    @DELETE("users/{id}")
    Call<Void> eliminarUser(@Path("id") int id);
}
