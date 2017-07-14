package org.pcc.copygallerypic.injection;

import org.pcc.copygallerypic.model.ImageFile;
import org.pcc.copygallerypic.model.ImageFileImpl;

/**
 * Created by ptyagi on 7/13/17.
 */

public class Injection {

    public static ImageFile provideImageFile() {
        return new ImageFileImpl();
    }
}
