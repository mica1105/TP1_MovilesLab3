package com.mika.tp1_movileslab3.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mika.tp1_movileslab3.model.Usuario;
import com.mika.tp1_movileslab3.request.ApiClient;
import com.mika.tp1_movileslab3.ui.registro.RegistroActivity;


public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<String> error;
    private ApiClient apiClient;
    private Context context;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
        apiClient= new ApiClient();
    }

    public LiveData<String> getError() {
        if (error==null){
            error=new MutableLiveData<>();
        }
        return error;
    }

    public void autenticar(String mail, String pass){
        Usuario usuario= apiClient.login(context,mail,pass);
        if (usuario != null){
            Intent intent= new Intent(context, RegistroActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("usuario",usuario);
            context.startActivity(intent);
        }
        else {
            error.setValue("*El email o password son incorrectos");
        }
    }

    public void activityRegistar(){
        Intent intent= new Intent(context, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
