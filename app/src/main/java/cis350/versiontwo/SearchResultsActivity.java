package cis350.versiontwo;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends ActionBarActivity {
    GridView gridView;
    List<ParseObject> obj;
    ArrayList<Pair<String, ParseObject>> pairArrayList;
    ArrayList<String> imageArrayList;
    ImageAdapter adapter;
    String searchTerm;

    /** Display the page initially */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Intent intent = getIntent();
        searchTerm = intent.getStringExtra("searchTerm");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        new RemoteDataTask().execute();
    }
    /**  perform background operations and publish results on the UI thread */
    class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /** get image data */
        @Override
        protected Void doInBackground(Void... params) {
            pairArrayList = new ArrayList<Pair<String, ParseObject>>();
            imageArrayList = new ArrayList<String>();

            try {
                ParseQuery query = ParseQuery.getQuery("ImageUpload");

                obj = query.find();

                for (ParseObject o : obj) {
                    ParseFile image = null;
                    String diagnosis = (String) o.get("diagnosis");

                    if (searchTerm.equalsIgnoreCase(diagnosis)) {
                        image = (ParseFile) o.get("file");
                        Log.d("diagnosisLog", "diagnosis:" + diagnosis);
                    } else {
                        String tagText = (String) o.get("tags");
                        String[] tagArray;
                        if (tagText != null) {
                            tagArray = tagText.split(", ");
                        } else {
                            continue;
                        }

                        for (String tag : tagArray) {
                            Log.d("tag", "tag: " + tag);
                            if (tag.equalsIgnoreCase(searchTerm)) {
                                image = (ParseFile) o.get("file");
                            }
                        }
                    }
                    if (image != null) {
                        Pair<String, ParseObject> imageToAdd = new Pair<String,
                                ParseObject>(image.getUrl(), o);
                        pairArrayList.add(imageToAdd);
                        imageArrayList.add(image.getUrl());
                        Log.d("URL: ", image.getUrl());
                    }
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        /** sets up grid */
        @Override
        protected void onPostExecute(Void result) {
            gridView = (GridView) findViewById(R.id.grid_view);
            adapter = new ImageAdapter(SearchResultsActivity.this, imageArrayList);
            gridView.setAdapter(adapter);
        }
    }

    /** Inflate the menu; this adds items to the action bar if it is present */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_results, menu);
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

    /** set up ImageAdapter which helps display the image */
    class ImageAdapter extends BaseAdapter {
        private Context context;
        ArrayList imageArrayList;

        public ImageAdapter(Context c, ArrayList<String> imageArray) {
            context = c;
            imageArrayList = imageArray;
        }

        /** get the number of images to be displayed */
        public int getCount() {
            return imageArrayList.size();
        }

        /** get the item to be displayed */
        public Object getItem(int position) {
            return imageArrayList.get(position);
        }

        /** get item's position */
        public long getItemId(int position) {
            return position;
        }

        /** create a new ImageView for each item referenced by the Adapter */
        public View getView(int position, View view, ViewGroup parent) {
            Log.d("ArrayList size: ", Integer.toString(imageArrayList.size()));
            View row = view;
            ViewHolder holder;
            final int objectPosition = position;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                        .LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.gridview_item, parent, false);
                holder = new ViewHolder(row);
                row.setTag(holder);

            } else {
                holder = (ViewHolder) row.getTag();


            }
            try {
                InputStream in = new URL((String) imageArrayList.get(position))
                        .openStream();
                final Bitmap bmp = BitmapFactory.decodeStream(in);
                holder.image.setImageBitmap(bmp);

                holder.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),
                                EnlargedImageActivity.class);
                        Pair<String, ParseObject> pairImage = pairArrayList.get
                                (objectPosition);
                        ParseObject image = pairImage.second;
                        String diagnosis = (String) image.get("diagnosis");
                        Log.d("diagnosis: ", diagnosis);
                        String tagText = (String) image.get("tags");
                        Log.d("tags: ", tagText);
                        String location = (String) image.get("location");
                        Log.d("location: ", location);
                        String upvotes = String.valueOf(image.get("upvotes"));
                        String downvotes = String.valueOf(image.get("downvotes"));
                        String id = image.getObjectId();
                        Log.d("id: ", id);
                        ParseFile file = (ParseFile) image.get("file");
                        String url = (String) file.getUrl();
                        intent.putExtra("diagnosis", diagnosis);
                        intent.putExtra("tags", tagText);
                        intent.putExtra("location", location);
                        intent.putExtra("url", url);
                        intent.putExtra("upvotes", upvotes);
                        intent.putExtra("downvotes", downvotes);
                        intent.putExtra("id", id);
                        Log.d("searchResultsActivity: ", String.valueOf(id));

                        startActivity(intent);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            return row;
        }
    }

    /** place image */
    class ViewHolder {
        ImageView image;
        ViewHolder(View v) {
            image = (ImageView) v.findViewById(R.id.image_view);
        }
    }
}