package com.mika.tp1_movileslab3.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mika.tp1_movileslab3.R;
import com.mika.tp1_movileslab3.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private EditText dni,apellido,nombre,email,password;
    private Button guardar;
    private RegistroViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        inicializar();
    }

    public void inicializar(){
        dni= findViewById(R.id.etDni);
        apellido=findViewById(R.id.etApellido);
        nombre=findViewById(R.id.etNombre);
        email=findViewById(R.id.etEmail);
        password=findViewById(R.id.etClave);
        guardar=findViewById(R.id.btGuardar);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);
        vm.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                dni.setText(usuario.getDni() + "");
                apellido.setText(usuario.getApellido());
                nombre.setText(usuario.getNombre());
                email.setText(usuario.getEmail());
                password.setText(usuario.getPassword());
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vm.registrar(dni.getText().toString(), apellido.getText().toString(), nombre.getText().toString(), email.getText().toString(), password.getText().toString());
                }
        });
         if(getIntent().getSerializableExtra("usuario") != null) {
            vm.mostrar();
        }
    }
}