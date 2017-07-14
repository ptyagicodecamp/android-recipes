package org.pcc.copygallerypic.model;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * A thin wrapper around Android file APIs to make them more testable and allows the injection of a
 * fake implementation for hermetic UI tests.
 */
public class ImageFileImpl implements ImageFile {

    File mImageFile;
    Context mContext;
    Uri mImageUri;

    @Override
    public void create(Context context) {}

    @Override
    public void create(Context context, String filePath) throws IOException {
        mContext = context;
        mImageFile = new File(filePath);
    }

//    @Override
//    public void create(String filePath) throws IOException {
//        mImageFile = new File(filePath);
//    }

    @Override
    public void create(Context context, String name, String extension) throws IOException {
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);

        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        mImageFile = File.createTempFile(
                name,  /* prefix */
                extension,        /* suffix */
                storageDir      /* directory */
        );
    }

    public File getFile() {
        return mImageFile;
    }

    public void setFile(File file) {
        mImageFile = file;
    }

    @Override
    public boolean exists() {
        return null != mImageFile && mImageFile.exists();
    }

    @Override
    public void delete() {
        mImageFile = null;
    }

    @Override
    public String getPath() {
        if (mImageFile == null) {
            return "";
        }
        mImageUri = Uri.fromFile(mImageFile);
        return mImageUri.toString();
    }

    @Override
    public Uri getContentUri() {
        if (mImageUri == null) {
            return Uri.parse(getPath());
        }

        return mImageUri;
    }

    @Override
    public void setContentUri(Uri uri) {
        mImageUri = uri;
    }

    @Override
    public String toString() {
        if (mImageFile == null) return "";

        return "File Name: " + mImageFile.getName() + "\n" +
                "File Path: " + mImageFile.getAbsolutePath() + "\n"+
                "File URI: " + mImageUri;
    }

}
