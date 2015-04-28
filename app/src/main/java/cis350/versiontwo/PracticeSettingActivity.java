package cis350.versiontwo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class PracticeSettingActivity extends ActionBarActivity {
    Spinner practiceSettingSpinner;
    Button proceedButton;

    /** Display page initially */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_setting);

        // Add practice settings to the drop-down
        practiceSettingSpinner = (Spinner) findViewById(R.id.practice_setting_spinner);
        ArrayAdapter<CharSequence> pracSettingAdapter = ArrayAdapter.createFromResource(this,
                R.array.practice_setting_array, android.R.layout.simple_spinner_dropdown_item);
        pracSettingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        practiceSettingSpinner.setAdapter(pracSettingAdapter);

        proceedButton = (Button) findViewById(R.id.proceed_button);
        proceedButton.setOnClickListener(new View.OnClickListener() {
            // Go to Disclaimer
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DisclaimerActivity.class);
                startActivity(intent);
            }
        });
    }

    /** Inflate the menu; this adds items to the action bar if it is present.*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_practice_setting, menu);
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
        Intent intent = new Intent(getApplicationContext(), cis350.versiontwo.HomeActivity.class);
        startActivity(intent);
    }
}
