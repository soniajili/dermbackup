package cis350.versiontwo;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class PreviewActivity extends ActionBarActivity {

    ImageView image;
    TextView diagnosisText;
    TextView tagText;
    TextView locationText;
    Button editButton;
    Button submitButton;

    /** Display page initially */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

//        // Enable Parse
//        Parse.enableLocalDatastore(getApplicationContext());
//        Parse.initialize(getApplicationContext(),
//                "fviaFJ9B1jQdWCCnS419jkZ8dFVquHBd1lu0Y1jF",
//                "p6dYSbB0KVF7KPvstO2ui7B32RanUEj9vmS28DLi");

        image = (ImageView) findViewById(R.id.image);
        diagnosisText = (TextView) findViewById(R.id.diagnosisText);
        tagText = (TextView) findViewById(R.id.tagText);
        locationText = (TextView) findViewById(R.id.locationText);

        // Set the image
        Intent intent = getIntent();
        final Uri uri = intent.getParcelableExtra("image");
        final String uriString = uri.toString();
        image = (ImageView) findViewById(R.id.image);
        image.setImageURI(uri);

        // Set the text
        final String diagnosis = intent.getStringExtra("diagnosis");
        diagnosisText.setText(diagnosis);

        final String tags = intent.getStringExtra("tags");
        tagText.setText(tags);

        final String location = intent.getStringExtra("location");
        locationText.setText(location);

        editButton = (Button) findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
                // upload image and data when submit button is clicked
                public void onClick(View v) {

                final ParseObject imageSubmission = new ParseObject
                    ("ImageUpload");
                final ParseFile imageFile = new ParseFile("img",
                convertImageToBytes(Uri.parse(uriString)));
//                final HashMap<String, Integer> voters = new HashMap<String,
//                        Integer>();
                imageFile.saveInBackground(new SaveCallback() {

                @Override
                public void done(ParseException e) {
                    if (e == null) {

                         imageSubmission.put("file", imageFile);
                         imageSubmission.put("objectType", "image");
                         imageSubmission.put("diagnosis", diagnosis);
                         imageSubmission.put("tags", tags);
                         imageSubmission.put("location", location);
                         imageSubmission.put("upvotes", "0");
                         imageSubmission.put("downvotes", "0");
//                         imageSubmission.put("voters", voters);
                         imageSubmission.saveInBackground();
                         Log.d("save", "saved in background");
                         Intent intent = new Intent(PreviewActivity.this,
                                CollectionActivity.class);
                        startActivity(intent);

                     } else {
                         Toast.makeText(getApplicationContext(),
                                 "Failed to save image",
                                 Toast.LENGTH_SHORT).show();

                     }
                }
                });
            }
        });
    }

    /** change image format from Uri to Bytes */
    private byte[] convertImageToBytes(Uri uri) {
        byte[] data = null;
        try {
            ContentResolver cr = getBaseContext().getContentResolver();
            InputStream inputStream = cr.openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            data = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    /** Inflate the menu; this adds items to the action bar if it is present.*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_preview, menu);
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

