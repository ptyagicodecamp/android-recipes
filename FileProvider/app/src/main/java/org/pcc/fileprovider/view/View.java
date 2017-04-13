package org.pcc.fileprovider.view;

import android.content.Context;
import android.support.annotation.NonNull;

import org.pcc.fileprovider.model.ImageFile;

/**
 * Created by ptyagi on 4/12/17.
 */

public interface View {
    void openCamera(Context context, ImageFile imageFile);
    void showImagePreview(@NonNull String uri);
    void showImageError();
}
