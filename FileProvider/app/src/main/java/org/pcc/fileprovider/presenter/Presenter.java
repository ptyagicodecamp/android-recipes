package org.pcc.fileprovider.presenter;

import android.content.Context;

import org.pcc.fileprovider.model.ImageFile;
import org.pcc.fileprovider.view.View;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ptyagi on 4/12/17.
 */

//Presenter handler User Actions taken on View/Activity.
public class Presenter implements UserActionListener {
    ImageFile mImageFile;
    private View mView;

    public Presenter(View view) {
        mView = view;
    }

    @Override
    public void takePicture(Context context) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        mImageFile = new ImageFile();
        mImageFile.createImageFile(context, imageFileName, ".jpg");
        mView.openCamera(context, mImageFile);
    }

    @Override
    public void imageAvailable() {
        if (mImageFile.exists()) {
            mView.showImagePreview(mImageFile.getPath());
        } else {
            imageCaptureFailed();
        }
    }

    @Override
    public void imageCaptureFailed() {
        captureFailed();
    }

    private void captureFailed() {
        mImageFile.delete();
        mView.showImageError();
    }
}
