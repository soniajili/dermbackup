package cis350.versiontwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class ViewOnlyPracticeSettingActivity extends ActionBarActivity {

    Spinner practiceSettingSpinner;
    Spinner secondarySpinner;
    Button proceedButton;

    String pracSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_only_practice_setting);

        // Add practice settings to the drop-down
        practiceSettingSpinner = (Spinner) findViewById(R.id.view_only_practice_setting_spinner);
        ArrayAdapter<CharSequence> pracSettingAdapter = ArrayAdapter.createFromResource(this,
                R.array.view_only_practice_setting_array, android.R.layout.simple_spinner_dropdown_item);
        pracSettingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        practiceSettingSpinner.setAdapter(pracSettingAdapter);
        practiceSettingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (practiceSettingSpinner.getSelectedItem().toString().equals("Physician, Primary Care")) {
                    secondarySpinner = (Spinner) findViewById(R.id.secondary_setting_spinner);
                    ArrayAdapter<CharSequence> secondarySpinnerAdapter = ArrayAdapter.createFromResource(ViewOnlyPracticeSettingActivity.this,
                            R.array.physician_primary_care_array, android.R.layout.simple_spinner_dropdown_item);
                    secondarySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    secondarySpinner.setAdapter(secondarySpinnerAdapter);
                } else if (practiceSettingSpinner.getSelectedItem().toString().equals("Physician, Specialist/Subspecialist")) {
                    secondarySpinner = (Spinner) findViewById(R.id.secondary_setting_spinner);
                    ArrayAdapter<CharSequence> secondarySpinnerAdapter = ArrayAdapter.createFromResource(ViewOnlyPracticeSettingActivity.this,
                            R.array.physician_specialist_array, android.R.layout.simple_spinner_dropdown_item);
                    secondarySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    secondarySpinner.setAdapter(secondarySpinnerAdapter);
                } else if (practiceSettingSpinner.getSelectedItem().toString().equals("Physician, Surgery")) {
                    secondarySpinner = (Spinner) findViewById(R.id.secondary_setting_spinner);
                    ArrayAdapter<CharSequence> secondarySpinnerAdapter = ArrayAdapter.createFromResource(ViewOnlyPracticeSettingActivity.this,
                            R.array.physician_surgery_array, android.R.layout.simple_spinner_dropdown_item);
                    secondarySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    secondarySpinner.setAdapter(secondarySpinnerAdapter);
                } else if (practiceSettingSpinner.getSelectedItem().toString().equals("Nurse Practitioner/Physician Assistant, Primary Care")) {
                    secondarySpinner = (Spinner) findViewById(R.id.secondary_setting_spinner);
                    ArrayAdapter<CharSequence> secondarySpinnerAdapter = ArrayAdapter.createFromResource(ViewOnlyPracticeSettingActivity.this,
                            R.array.np_primary_care_array, android.R.layout.simple_spinner_dropdown_item);
                    secondarySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    secondarySpinner.setAdapter(secondarySpinnerAdapter);
                } else if (practiceSettingSpinner.getSelectedItem().toString().equals("Nurse Practitioner/Physician Assistant, Specialist/Subspecialist")) {
                    secondarySpinner = (Spinner) findViewById(R.id.secondary_setting_spinner);
                    ArrayAdapter<CharSequence> secondarySpinnerAdapter = ArrayAdapter.createFromResource(ViewOnlyPracticeSettingActivity.this,
                            R.array.np_specialist_array, android.R.layout.simple_spinner_dropdown_item);
                    secondarySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    secondarySpinner.setAdapter(secondarySpinnerAdapter);
                } else {
                    secondarySpinner = (Spinner) findViewById(R.id.secondary_setting_spinner);
                    ArrayAdapter<CharSequence> secondarySpinnerAdapter = ArrayAdapter.createFromResource(ViewOnlyPracticeSettingActivity.this,
                            R.array.empty_array, android.R.layout.simple_spinner_dropdown_item);
                    secondarySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    secondarySpinner.setAdapter(secondarySpinnerAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       /*
        if (pracSetting.equals("Physician, Primary Care")) {
            secondarySpinner = (Spinner) findViewById(R.id.secondary_setting_spinner);
            ArrayAdapter<CharSequence> secondarySettingAdapter = ArrayAdapter.createFromResource(this,
                    R.array.physician_primary_care_array, android.R.layout.simple_spinner_dropdown_item);
        }*/

        proceedButton = (Button) findViewById(R.id.proceed_button);
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the practice setting that was selected as a String
                final String pracSetting = practiceSettingSpinner.getSelectedItem().toString();
                Intent intent = new Intent(getApplicationContext(), ViewOnlyDisclaimerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_only_practice_setting, menu);
        return true;
    }

    /** Handle action bar item clicks here */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.homePage) {
            goHome();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /** Go to home page */
    private void goHome() {
        Intent intent = new Intent(getApplicationContext(),
                cis350.versiontwo.HomeActivity.class);
        startActivity(intent);
    }
}
