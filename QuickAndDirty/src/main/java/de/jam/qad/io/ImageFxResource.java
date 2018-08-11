package de.jam.qad.io;

import java.io.InputStream;
import javafx.scene.image.Image;
/**
 * ImageFxResource
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class ImageFxResource implements IStreamResource  {
    private Image image = null;

    @Override
    public void loadByStream(InputStream stream){
        image = new Image(stream);
    }

    @Override
    public void dispose() {
        image = null;
    }

    public Image get() {
        return image;
    }
}
