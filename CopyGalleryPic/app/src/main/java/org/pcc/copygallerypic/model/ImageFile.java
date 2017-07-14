package org.pcc.copygallerypic.model;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.IOException;

/**
 * A wrapper for handling image files.
 */
public interface ImageFile {
    void create(Context context);

    void create(Context context, String filePath) throws IOException;

    //void create(String filePath) throws IOException;

    void create(Context context, String name, String extension) throws IOException;

    boolean exists();

    void delete();

    String getPath();

    File getFile();

    void setFile(File file);

    //Content uri of file
    Uri getContentUri();
    void setContentUri(Uri uri);
}
