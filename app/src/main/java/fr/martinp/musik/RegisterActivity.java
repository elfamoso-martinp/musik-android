package fr.martinp.musik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText mailEditText;
    String mailText;

    EditText lastNameEditText;
    String lastNameText;

    EditText firstNameEditText;
    String firstNameText;

    EditText addressEditText;
    String addressText;

    EditText editTextTextPostalCode;
    String postalCodeText;

    EditText editTextPassword;
    String passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerButton = findViewById(R.id.registerButton);
        TextView goToLoginText = findViewById(R.id.goToLoginText);

        mailEditText = findViewById(R.id.editTextTextEmailAddress);

        lastNameEditText = findViewById(R.id.editTextTextLastName);

        firstNameEditText = findViewById(R.id.editTextTextFirstName);

        addressEditText = findViewById(R.id.editTextAddress);

        editTextTextPostalCode = findViewById(R.id.editTextTextPostalCode);

        editTextPassword = findViewById(R.id.editTextTextPassword);

        goToLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mailText = mailEditText.getText().toString();
                lastNameText = lastNameEditText.getText().toString();
                firstNameText = firstNameEditText.getText().toString();
                addressText = addressEditText.getText().toString();
                postalCodeText = editTextTextPostalCode.getText().toString();
                passwordText = editTextPassword.getText().toString();
                if(validateRegisterForm()){
                    registerUser(mailText, lastNameText, firstNameText, addressText, Integer.parseInt(postalCodeText), passwordText);
                }
            }
        });

        mailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    mailText = mailEditText.getText().toString();
                    if(!FormValidator.verifyMail(mailText)){
                        mailEditText.setBackgroundResource(R.drawable.login_register_input_error);
                    } else {
                        mailEditText.setBackgroundResource(R.drawable.login_register_form);
                    }
                }
            }
        });

        firstNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    firstNameText = firstNameEditText.getText().toString();
                    if(!FormValidator.verifyNames(firstNameText)){
                        firstNameEditText.setBackgroundResource(R.drawable.login_register_input_error);
                    } else {
                        firstNameEditText.setBackgroundResource(R.drawable.login_register_form);
                    }
                }
            }
        });

        lastNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    lastNameText = lastNameEditText.getText().toString();
                    if(!FormValidator.verifyNames(lastNameText)){
                        lastNameEditText.setBackgroundResource(R.drawable.login_register_input_error);
                    } else {
                        lastNameEditText.setBackgroundResource(R.drawable.login_register_form);
                    }
                }
            }
        });

        addressEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    addressText = addressEditText.getText().toString();
                    if(!FormValidator.verifyAddress(addressText)){
                        addressEditText.setBackgroundResource(R.drawable.login_register_input_error);
                    } else {
                        addressEditText.setBackgroundResource(R.drawable.login_register_form);
                    }
                }
            }
        });

        editTextTextPostalCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    postalCodeText = editTextTextPostalCode.getText().toString();
                    try {
                        if(!FormValidator.verifyPostalCode(Integer.parseInt(postalCodeText))){
                            editTextTextPostalCode.setBackgroundResource(R.drawable.login_register_input_error);
                        } else {
                            editTextTextPostalCode.setBackgroundResource(R.drawable.login_register_form);
                        }
                    } catch (NumberFormatException e){
                        editTextTextPostalCode.setBackgroundResource(R.drawable.login_register_input_error);
                    }


                    }
                }
        });

        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    passwordText = editTextPassword.getText().toString();
                    if(!FormValidator.verifyPassword(passwordText)){
                        editTextPassword.setBackgroundResource(R.drawable.login_register_input_error);
                    } else {
                        editTextPassword.setBackgroundResource(R.drawable.login_register_form);
                    }
                }
            }
        });



    }

    private boolean validateRegisterForm(){
        if(!FormValidator.verifyMail(mailText)){
            return false;
        }
        if(!FormValidator.verifyNames(lastNameText)){
            return false;
        }

        if(!FormValidator.verifyNames(firstNameText)){
            return false;
        }

        if(!FormValidator.verifyAddress(addressText)){
            return false;
        }

        if(!FormValidator.verifyPostalCode(Integer.parseInt(postalCodeText))){
            return false;
        }

        if(!FormValidator.verifyPassword(passwordText)){
            return false;
        }



        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    public void registerUser(String mail, String nom, String prenom, String adresse, int codePostal, String motDePasse){
        RequestQueue queue = Volley.newRequestQueue(this);
        int responseCode;
        String url = "http://192.168.1.31/api_musik/server/userRegister.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("prenom", prenom);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Erreur", Toast.LENGTH_SHORT).show();
                        System.out.println(error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", mail);
                params.put("nom", nom);
                params.put("prenom", prenom);
                params.put("adresse", adresse);
                params.put("codePostal", Integer.toString(codePostal));
                params.put("motDePasse", motDePasse);
                ;

                return params;
            }
        };
        queue.add(postRequest);
    }
}