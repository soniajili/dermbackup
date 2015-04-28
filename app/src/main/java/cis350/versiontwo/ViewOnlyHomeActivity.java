package cis350.versiontwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ViewOnlyHomeActivity extends ActionBarActivity {

    Button viewCollectionButton;
    Button searchButton;
    Button logoutButton;

    /** Display the page initially */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_only_home);
        viewCollectionButton = (Button) findViewById(R.id.viewCollectionButton2);
        viewCollectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // Go to View Collection page
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CollectionActivity.class);
                startActivity(intent);
            }
        });

        searchButton = (Button) findViewById(R.id.searchButton2);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // Go to search page
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        logoutButton = (Button) findViewById(R.id.logoutButton2);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // Go to log in page
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    /** Inflate the menu; this adds items to the action bar if it is present */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_only_home, menu);
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