package org.pcc.copygallerypic;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by ptyagi on 7/13/17.
 */

public class Util {

    //Returns renamed file
    public static File renameFile(File srcFile, String newName, String ext) {
        boolean success = false;

        if (!srcFile.exists()) {
            return null;
        }

        File renamedFile = new File(srcFile.getParent() + "/" + newName + ext);
        success = srcFile.renameTo(renamedFile);

        if (success) {
            return renamedFile;
        } else {
            return srcFile;
        }
    }

    //Copies a file from one location to another
    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!sourceFile.exists()) {
            return;
        }

        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }
    }

    //Converts Content uri path to absolute file path for image
    public static String getImageRealPathFromURI(Context context, Uri contentURI) {
        String result;
        Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}
