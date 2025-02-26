package com.myapp.my_app_code.data.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import java.util.List;

@Dao
public interface UserDao {

    // Insertar User (si ya existe, lo reemplaza)
    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    void insertarUser(User User);

    // Obtener todos los Users
    @Query("SELECT * FROM Users")
    List<User> obtenerUsers();

    // Actualizar User
    @Update
    void actualizarUser(User User);

    // Eliminar User
    @Delete
    void eliminarUser(User User);
}
