package cis350.versiontwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegistrationActivity extends ActionBarActivity {
    Spinner genderSelection;
    Spinner practiceSettingSelection;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Add gender information
        genderSelection = (Spinner) findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSelection.setAdapter(genderAdapter);

        // Add country information
        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id
                .autocomplete_country);
        String[] countries = getResources().getStringArray(R.array.country_array);
        ArrayAdapter<String> countryAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(countryAdapter);

        // Add practice setting information
        practiceSettingSelection = (Spinner) findViewById(R.id.setting_spinner);
        ArrayAdapter<CharSequence> settingAdapter = ArrayAdapter.createFromResource(this,
                R.array.setting_array, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        practiceSettingSelection.setAdapter(settingAdapter);

        // Enable Local Datastore.
//        ParseObject.registerSubclass(ParseUser.class);
//        Parse.enableLocalDatastore(this);
//        Parse.initialize(this, "fviaFJ9B1jQdWCCnS419jkZ8dFVquHBd1lu0Y1jF",
//                "p6dYSbB0KVF7KPvstO2ui7B32RanUEj9vmS28DLi");

        register = (Button) findViewById(R.id.registerButton);
        final EditText email = (EditText) findViewById(R.id.emailLabel);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText year = (EditText) findViewById(R.id.yearBirth);
        final EditText password = (EditText) findViewById(R.id.passwordText);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gender = genderSelection.getSelectedItem().toString();
                String country = textView.getText().toString();
                String setting = practiceSettingSelection.getSelectedItem().toString();
                String username = email.getText().toString();
                String fullname = name.getText().toString();
                String yearbirth = year.getText().toString();
                String userpassword = password.getText().toString();

                if ((gender.isEmpty()) || (country.isEmpty()) || (setting
                        .isEmpty()) || (username.isEmpty()) || (fullname.isEmpty()) ||
                        (yearbirth.isEmpty()) || (userpassword.isEmpty())) {
                    Toast.makeText(getApplicationContext(), "Please fill out all fields",
                            Toast.LENGTH_SHORT).show();
                } else {
                    ParseUser newuser = new ParseUser();

                    newuser.setUsername(username);
                    newuser.setPassword(userpassword);

                    newuser.put("Gender", gender);
                    newuser.put("Country", country);
                    newuser.put("Setting", setting);
                    newuser.put("Name", fullname);
                    newuser.put("Birthyear", yearbirth);


                    newuser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                // Sign up succeeded. Proceed to Practice
                                // Settings
                                Intent intent;
                                if (practiceSettingSelection.getSelectedItem().toString().equals
                                        ("Dermatologist")) {
                                    intent = new Intent(RegistrationActivity
                                            .this,
                                            PracticeSettingActivity.class);
                                } else {
                                    intent = new Intent(RegistrationActivity
                                            .this,
                                            ViewOnlyPracticeSettingActivity
                                                    .class);
                                }

                                startActivity(intent);
                            } else {
                                // Sign up didn't succeed.
                                Toast.makeText(getApplicationContext(), "Registration failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }

            }
        });

    }


}