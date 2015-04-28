package cis350.versiontwo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SubmitActivity extends ActionBarActivity {

    ImageButton cameraButton;
    ImageButton galleryButton;
    private static final int captureImageRequestCode = 100;
    private static final int chooseImageRequestCode = 101;
    public static final int mediaTypeImage = 1;
    private Uri fileUri;

    /** Display page initially */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        cameraButton = (ImageButton) findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // handle behavior when user clicks camera button
            public void onClick(View v) {
                if (hasCamera()) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    fileUri = getOutputMediaFileUri(mediaTypeImage); // create a file to save the image
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, captureImageRequestCode);
                } else {
                    String temp = "No camera available. Please select from gallery.";
                    Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();
                }
            }
        });

        galleryButton = (ImageButton) findViewById(R.id.galleryButton);
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, chooseImageRequestCode);
            }
        });
    }

    /** Go to Enter Diagnosis page after capture image */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == chooseImageRequestCode && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {

            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Intent intent = new Intent(getApplicationContext(), EnterDiagnosisActivity.class);
                intent.putExtra("URI", uri);
                startActivity(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** Inflate the menu; this adds items to the action bar if it is present */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_submit, menu);
        return true;
    }

    /** Handle behaviors when menu item is selected */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homePage:
                goHome();
                return true;
            case R.id.search:
                goToSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Go to home page */
    private void goHome() {
        Intent intent = new Intent(getApplicationContext(), cis350.versiontwo.HomeActivity.class);
        startActivity(intent);
    }

    /** Go to search page */
    private void goToSearch() {
        Intent intent = new Intent(getApplicationContext(), cis350.versiontwo.SearchActivity.class);
        startActivity(intent);
    }

    /** Helper method to determine if device has a camera */
    private boolean hasCamera() {
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    // Parts of the following methods are taken from developer.android.com
    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // Check that the SDCard is mounted
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == mediaTypeImage){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }
}
