package au.com.bywave.testproject;

import android.content.ContentValues;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ItemActivity extends AppCompatActivity {

    private static final String PROVIDER_NAME = "androidcontentproviderdemo.androidcontentprovider.images";
    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/images");

    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        ListView listView = (ListView) findViewById(R.id.lstViewImages);

        adapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_layout,
                null,
                new String[]{"IMAGETITLE", "IMAGEURL", "IMAGEDESC"},
                new int[]{R.id.imgTitle, R.id.imgUrl, R.id.imgDesc}, 0);

        listView.setAdapter(adapter);
        refreshValuesFromContentProvider();
    }

    private void refreshValuesFromContentProvider() {
        CursorLoader cursorLoader = new CursorLoader(getBaseContext(), CONTENT_URI,
                null, null, null, null);
        Cursor c = cursorLoader.loadInBackground();
        adapter.swapCursor(c);
    }

    public void onClickAddImage(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("IMAGETITLE", ((EditText) findViewById(R.id.edtTxtImageTitle)).getText().toString());
        contentValues.put("IMAGEURL", ((EditText) findViewById(R.id.edtImageUrl)).getText().toString());
        contentValues.put("IMAGEDESC", ((EditText) findViewById(R.id.edtImageDesc)).getText().toString());
        Uri uri = getContentResolver().insert(CONTENT_URI, contentValues);
        if (uri != null) {
            Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }
        refreshValuesFromContentProvider();
    }
}
