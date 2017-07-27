package au.com.bywave.testproject;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

public class ImagesProvider extends ContentProvider {

    private static final String PROVIDER_NAME = "androidcontentproviderdemo.androidcontentprovider.images";
    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/images");
    private static final int IMAGES = 1;
    private static final int IMAGE_ID = 2;
    private static final UriMatcher uriMatcher = getUriMatcher();

    private static UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "images", IMAGES);
        uriMatcher.addURI(PROVIDER_NAME, "images/#", IMAGE_ID);
        return uriMatcher;
    }

    private ImageDatabase imageDataBase = null;

    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case IMAGES:
                return "vnd.android.cursor.dir/vnd.com.androidcontentproviderdemo.androidcontentprovider.provider.images";
            case IMAGE_ID:
                return "vnd.android.cursor.item/vnd.com.androidcontentproviderdemo.androidcontentprovider.provider.images";

        }
        return "";
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        imageDataBase = new ImageDatabase(context);
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String id = null;
        if (uriMatcher.match(uri) == IMAGE_ID) {
            //Query is for one single image. Get the ID from the URI.
            id = uri.getPathSegments().get(1);
        }
        return imageDataBase.getImages(id, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        try {
            long id = imageDataBase.addNewImage(values);
            return ContentUris.withAppendedId(CONTENT_URI, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        String id = null;
        if (uriMatcher.match(uri) == IMAGE_ID) {
            //Delete is for one single image. Get the ID from the URI.
            id = uri.getPathSegments().get(1);
        }

        return imageDataBase.deleteImages(id);
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String id = null;
        if (uriMatcher.match(uri) == IMAGE_ID) {
            //Update is for one single image. Get the ID from the URI.
            id = uri.getPathSegments().get(1);
        }

        return imageDataBase.updateImages(id, values);
    }
}
