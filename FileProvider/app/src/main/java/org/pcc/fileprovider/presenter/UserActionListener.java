package org.pcc.fileprovider.presenter;

import android.content.Context;

import java.io.IOException;

/**
 * Created by ptyagi on 4/12/17.
 */

public interface UserActionListener {

    void takePicture(Context context) throws IOException;
    void imageAvailable();
    void imageCaptureFailed();
}
