package com.example.tecomca.mylogin_seccion05.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tecomca.mylogin_seccion05.Model.User;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Sql.DatabaseHelper;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEmailField, mPassField, mNameField;

    private Button mRegisterBtn;
    private Button mBackBtn;

    private DatabaseHelper databaseHelper;
    private User user;

//    private FirebaseAuth mAuth;
//
//    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       // mAuth = FirebaseAuth.getInstance();

//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if (firebaseAuth.getCurrentUser() != null ){
//                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
//                }
//            }
//        };

//        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                startSingUp();
//                Toast.makeText(RegisterActivity.this, "Algun dia funcionara XD", Toast.LENGTH_SHORT).show();
//            }
//        });

//        mBackBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//            }
//        });

        initViews();
        initListeners();
        initObjects();

    }

//    protected void onStart(){
//        super.onStart();
//
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    private void startSingUp(){
//        String email = mEmailField.getText().toString();
//        String password = mPassField.getText().toString();
//
//        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
//            Toast.makeText(RegisterActivity.this, "Campos estan vacios", Toast.LENGTH_SHORT).show();
//        } else {
//            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (!task.isSuccessful()) {
//                        Toast.makeText(RegisterActivity.this, "Problemas al registrar", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }

    private void initViews(){
        mEmailField = (EditText) findViewById(R.id.editTextEmail);
        mPassField = (EditText) findViewById(R.id.editTextPass);
        mNameField = (EditText) findViewById(R.id.editTextName);

        mRegisterBtn = (Button) findViewById(R.id.btnRegister);
        mBackBtn = (Button) findViewById(R.id.btnBack);
    }
    private void initListeners(){
        mRegisterBtn.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
    }

    private void initObjects(){
        databaseHelper = new DatabaseHelper(RegisterActivity.this);
        user = new User();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnRegister:
                //Toast.makeText(RegisterActivity.this, "Algun dia funcionara XD", Toast.LENGTH_SHORT).show();
                postDataToSQLite();
                break;
            case R.id.btnBack: // los onclick de los listener como estan arriba
                Intent intentRegister = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    private boolean login(String email, String password) {
        if  (!isValidEmail(email)){
            Toast.makeText(this, "Email is not valid, please try again", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(password)){
            Toast.makeText(this, "Password is not valid, 4 characters or more, please try again", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword (String password) {
        return password.length() >= 4;
    }

    private void postDataToSQLite(){
        String email = mEmailField.getText().toString().trim();
        String password = mPassField.getText().toString().trim();
        String name = mNameField.getText().toString().trim();

        if (login(email, password)) {
//            goToMain();
            if (!databaseHelper.checkUser(email)){

                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);

                databaseHelper.addUser(user);

                Toast.makeText(this, "El registro de usuario satisfactorio", Toast.LENGTH_LONG).show();
                emptyInputEditText();
            }else{
                Toast.makeText(this, "El email ya existe", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void emptyInputEditText(){
        mEmailField.setText(null);
        mPassField.setText(null);
        mNameField.setText(null);
    }
}
