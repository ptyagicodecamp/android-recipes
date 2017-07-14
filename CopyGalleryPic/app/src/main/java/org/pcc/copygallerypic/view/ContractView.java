package org.pcc.copygallerypic.view;

import android.net.Uri;
import android.support.annotation.NonNull;

import org.pcc.copygallerypic.presenter.ContractUserActionListener;

/**
 * Created by ptyagi on 7/13/17.
 */

public interface ContractView {
    //displays image in Imageview widget
    void showImagePreview(@NonNull Uri uri);

    //Displays image file information in textview
    void showImageInfo(String infoSting);

    //That's how a presenter is assigned to a view
    void setUserActionListener(ContractUserActionListener userActionListener);

}
