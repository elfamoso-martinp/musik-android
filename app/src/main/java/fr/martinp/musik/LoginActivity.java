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

public class LoginActivity extends AppCompatActivity {

    public EditText mailEditText;
    public String mailText;

    public EditText passwordEditText;
    public String passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView goToRegisterText = findViewById(R.id.goToRegisterText);

        Button loginButton = findViewById(R.id.loginButton);

        mailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);

        goToRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mailText = mailEditText.getText().toString();
                passwordText = passwordEditText.getText().toString();
                if(validateLoginForm()){
                    loginUser(mailText, passwordText);
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

        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    passwordText = passwordEditText.getText().toString();
                    if(!FormValidator.verifyPassword(passwordText)){
                        passwordEditText.setBackgroundResource(R.drawable.login_register_input_error);
                    } else {
                        passwordEditText.setBackgroundResource(R.drawable.login_register_form);
                    }
                }
            }
        });
    }


    private boolean validateLoginForm(){
        if(!FormValidator.verifyMail(mailText)){
            return false;
        }

        if(!FormValidator.verifyPassword(passwordText)){
            return false;
        }



        return true;
    }

    public void loginUser(String mail, String motDePasse){
        RequestQueue queue = Volley.newRequestQueue(this);
        int responseCode;
        String url = "http://192.168.1.31/api_musik/server/userLogin.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        //intent.putExtra("prenom", prenom);
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
                params.put("motDePasse", motDePasse);
                ;

                return params;
            }
        };
        queue.add(postRequest);
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



}