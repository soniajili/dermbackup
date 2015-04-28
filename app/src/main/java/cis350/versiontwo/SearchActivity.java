package cis350.versiontwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SearchActivity extends ActionBarActivity {

    Button search;
    /** Display page initially */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search = (Button) findViewById(R.id.searchButton);
        final EditText searchText = (EditText) findViewById(R.id.searchTerm);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTerm = searchText.getText().toString();
//                ParseQuery query = new ParseQuery("Search Results");
//
//                query.whereEqualTo("diagnosis", termToSearch);

                Intent intent = new Intent(getApplicationContext(),
                        SearchResultsActivity.class);
                intent.putExtra("searchTerm", searchTerm);
                startActivity(intent);
            }
        });
    }

    /** Inflate the menu; this adds items to the action bar if it is present */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
