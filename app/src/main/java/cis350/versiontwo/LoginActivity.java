package cis350.versiontwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends ActionBarActivity {

    Button signInButton;
    Button registerButton;

    /** Display page initially */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = (Button) findViewById(R.id.signInButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        final EditText emailText = (EditText) findViewById(R.id.emailText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);

//        // Enable Parse
//        Parse.enableLocalDatastore(this);
//        Parse.initialize(this, "fviaFJ9B1jQdWCCnS419jkZ8dFVquHBd1lu0Y1jF",
//                "p6dYSbB0KVF7KPvstO2ui7B32RanUEj9vmS28DLi");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            // Sign in when sign in button is clicked
            @Override
            public void onClick(View v) {

                final String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                ParseUser.logInInBackground(email, password, new LogInCallback() {
                    // Handle behavior once app connects to Parse
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // User has logged in.
//                            ((Instaderm)getApplication()).setCurrUser(email);
                            Intent intent = new Intent(getApplicationContext(),
                                    HomeActivity.class);
                            startActivity(intent);
                        } else {
                            // Signup failed
                            Toast.makeText(getApplicationContext(),
                                "Your username or password did not match.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

}

