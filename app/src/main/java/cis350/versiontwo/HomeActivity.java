package cis350.versiontwo;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends ActionBarActivity {

    Button submitButton;
    Button viewCollectionButton;
    Button searchButton;
    Button logoutButton;

    /** Display page initially */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        submitButton = (Button) findViewById(R.id.submitCaseButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            // Submit the activity when submit button is clicked
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubmitActivity.class);
                startActivity(intent);
            }
        });

        viewCollectionButton = (Button) findViewById(R.id.viewCollectionButton);
        viewCollectionButton.setOnClickListener(new View.OnClickListener() {
            // View the collection when view collection button is clicked
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CollectionActivity.class);
                startActivity(intent);
            }
        });

        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            // search for an image when search button is clicked
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    /** Inflate the menu; this adds items to the action bar if it is
     * present */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    /** Handle action bar item clicks here */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
