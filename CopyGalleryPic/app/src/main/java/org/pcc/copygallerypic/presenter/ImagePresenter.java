package org.pcc.copygallerypic.presenter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import org.pcc.copygallerypic.Util;
import org.pcc.copygallerypic.model.ImageFile;
import org.pcc.copygallerypic.view.ContractView;

import java.io.File;
import java.io.IOException;

/**
 * Created by ptyagi on 7/13/17.
 */

//Presenter handles user actions. View initiates action thru Presenter.
public class ImagePresenter implements ContractUserActionListener {

    @NonNull private ImageFile mImageFile;

    //Hold reference to View contract to update UI
    @NonNull private ContractView mImageViewer;

    public ImagePresenter(ContractView imageViewer, ImageFile imageFile) {
        mImageViewer = imageViewer;
        mImageFile = imageFile;

        mImageViewer.setUserActionListener(this);
    }

    //Fills-in model (image) details
    //Sends out request to View to show image in UI
    @Override
    public void loadImage(Context context, Uri uri) throws IOException {
        mImageFile.create(context, uri.toString());
        mImageFile.setContentUri(uri);
        mImageViewer.showImagePreview(uri);
    }

    @Override
    public void loadImageInfo() {
        String infoString = mImageFile.toString();

        mImageViewer.showImageInfo(infoString);
    }

    @Override
    public void copyImageIntoAppDir(Context context) throws IOException {

        File srcFile = new File(Util.getImageRealPathFromURI(context, mImageFile.getContentUri()));

        //Create a new File in app's directory
        mImageFile.create(context, "newFile", ".jpg");
        File destFile = mImageFile.getFile();

        Util.copyFile(srcFile, destFile);

        String infoString = mImageFile.toString();

        mImageViewer.showImageInfo(infoString);
    }

    @Override
    public void renameImage(Context context, String newName) {
        File srcFile = mImageFile.getFile();
        File renamedFile = Util.renameFile(srcFile, newName, ".jpg");

        mImageFile.setFile(renamedFile);

        String infoString = mImageFile.toString();

        mImageViewer.showImageInfo(infoString);
    }
}
