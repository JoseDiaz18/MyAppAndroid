package com.myapp.my_app_code.data.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import java.util.List;

@Dao
public interface UserDao {

    // Insertar User (si ya existe, lo reemplaza)
    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    void insertarUser(User User);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarUsuarios(List<User> Users);

    // Obtener todos los Users
    @Query("SELECT * FROM Users")
    List<User> obtenerUsers();

    @Query("SELECT * FROM Users")
    LiveData<List<User>> obtenerUsersLive();

    // Actualizar User
    @Update
    void actualizarUser(User User);

    // Eliminar User
    @Delete
    void eliminarUser(User User);

    @Query("DELETE FROM Users")
    void eliminarTodos();
}
