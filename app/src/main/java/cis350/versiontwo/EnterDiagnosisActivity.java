package cis350.versiontwo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;


public class EnterDiagnosisActivity extends ActionBarActivity {
    Spinner locationSelection;
    Button previewButton;
    ImageButton image;
    String email;

    /** Display page initially */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_diagnosis);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        final Uri uri = intent.getParcelableExtra("URI");

        image = (ImageButton) findViewById(R.id.reselectPhoto);
        image.setImageURI(uri);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Add location information
        locationSelection = (Spinner) findViewById(R.id.location_spinner);
        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter
                .createFromResource(this,
                        R.array.location_array, android.R.layout.simple_spinner_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSelection.setAdapter(locationAdapter);

        previewButton = (Button) findViewById(R.id.previewButton);

        // Get user data
        final EditText diagnosis = (EditText) findViewById(R.id.diagnosis);
        final EditText tags = (EditText) findViewById(R.id.tags);
        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String location = locationSelection.getSelectedItem().toString();
                String diagnosisText = diagnosis.getText().toString();

                String tagText = tags.getText().toString();

                Intent intent = new Intent(getApplicationContext(), PreviewActivity.class);
                intent.putExtra("diagnosis", diagnosisText);
                intent.putExtra("tags", tagText);
                intent.putExtra("location", location);
                intent.putExtra("image", uri);
                startActivity(intent);
            }
        });
    }

    /** Inflate the menu; this adds items to the action bar if it is present */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_enter_diagnosis, menu);
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
