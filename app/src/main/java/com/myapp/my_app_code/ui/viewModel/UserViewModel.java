package com.myapp.my_app_code.ui.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.myapp.my_app_code.data.model.User;
import com.myapp.my_app_code.data.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository repository;

    public UserViewModel(Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public void insertarUser(User User) {
        repository.insertarUser(User);
    }

    public List<User> obtenerUsers() {
        return repository.obtenerUsers();
    }

    public void actualizarUser(User User) {
        repository.actualizarUser(User);
    }

    public void eliminarUser(User User) {
        repository.eliminarUser(User);
    }
}