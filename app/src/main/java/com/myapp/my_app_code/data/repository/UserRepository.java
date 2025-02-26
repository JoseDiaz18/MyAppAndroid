package com.myapp.my_app_code.data.repository;

import android.content.Context;

import com.myapp.my_app_code.data.model.*;
import com.myapp.my_app_code.utils.AppDataBase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private UserDao UserDao;
    private ExecutorService executorService;

    public UserRepository(Context context) {
        AppDataBase db = AppDataBase.getDatabase(context);
        UserDao = db.UserDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insertarUser(User User) {
        executorService.execute(() -> UserDao.insertarUser(User));
    }

    public List<User> obtenerUsers() {
        return UserDao.obtenerUsers();
    }

    public void actualizarUser(User User) {
        executorService.execute(() -> UserDao.actualizarUser(User));
    }

    public void eliminarUser(User User) {
        executorService.execute(() -> UserDao.eliminarUser(User));
    }
}
