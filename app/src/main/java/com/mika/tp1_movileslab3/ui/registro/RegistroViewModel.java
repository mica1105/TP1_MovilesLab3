package com.mika.tp1_movileslab3.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mika.tp1_movileslab3.model.Usuario;
import com.mika.tp1_movileslab3.request.ApiClient;
import com.mika.tp1_movileslab3.ui.login.MainActivity;


public class RegistroViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuario;
    private Context context;
    private ApiClient apiClient;

    public RegistroViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
        apiClient= new ApiClient();
    }

    public LiveData<Usuario> getUsuario() {
        if (usuario==null){
            usuario=new MutableLiveData<>();
        }
        return usuario;
    }

    public void registrar(String dni, String apellido,String nombre,String email,String pass){
        int dni1=Integer.parseInt(dni);
        Usuario u= new Usuario(dni1,apellido,nombre,email,pass);
        apiClient.guardar(context,u);
        Intent i= new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public void mostrar(){
        Usuario u= apiClient.leer(context);
        usuario.setValue(u);
    }
}
