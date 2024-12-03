package com.example.nuevo3;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText,passwordEditText;
    private Button loginButton,registroButton,resetpasswordButton;
    private TextView statusTextView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Iniciar firebase
        mAuth=FirebaseAuth.getInstance();
        emailEditText=findViewById(R.id.correo_edit);
        passwordEditText=findViewById(R.id.contra_edit);
        loginButton=findViewById(R.id.inicia_button);
        registroButton=findViewById(R.id.registra_button);
        resetpasswordButton=findViewById(R.id.registra_contra_button);
        statusTextView=findViewById(R.id.textView_1);
        //manejar el inicio de sesion
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailEditText.getText().toString().trim();
                String password=passwordEditText.getText().toString().trim();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    statusTextView.setText("Porfavor ingresa correo o contraseña");
                    return;
                }
                loginuser(email,password);


            }




        });
        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailEditText.getText().toString().trim();
                String password=passwordEditText.getText().toString().trim();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    statusTextView.setText("Porfavor ingresa correo o contraseña");
                    return;
                }
                registrouser(email,password);


            }




        });
        resetpasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailEditText.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    statusTextView.setText("Porfavor ingresa correo o contraseña");
                    return;
                }
                resetuser(email);


            }




        });



        };
    private void loginuser(String email,String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,task->{
            if(task.isSuccessful()){
                Toast.makeText(MainActivity.this, "Inicio de Sesion Exitoso", Toast.LENGTH_SHORT).show();
                statusTextView.setText("Inicio de Sesion Exitoso");
            }
            {
                statusTextView.setText("Error: "+ task.getException().getMessage());
            }
        });

    }

    private void registrouser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,task->{
            if(task.isSuccessful()){
                Toast.makeText(MainActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                statusTextView.setText("Usuario Registrado Exitosamente");
            }
            {
                statusTextView.setText("Error: "+ task.getException().getMessage());
            }
        });

    }

    private void resetuser(String email){
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(this,task->{
            if(task.isSuccessful()){
                Toast.makeText(MainActivity.this, "Reseteado", Toast.LENGTH_SHORT).show();
                statusTextView.setText("Reseateado Exitosamente");
            }
            {
                statusTextView.setText("Error: "+ task.getException().getMessage());
            }
        });

    }
    }
