package de.jam.qad.io;

import de.jam.qad.threading.Guard;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;

/**
 * Raw Audio Clip
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class RawAudioClip implements IStreamResource {

    private ByteArrayInputStream bai;
    private final Guard guard = new Guard();


    @Override
    public void loadByStream(InputStream stream) throws Exception {
        guard.writeLock();
        try {
            // read binary data
            var buffer = new byte[8000];
            var bytesRead = 0;

            try (var bao = new ByteArrayOutputStream()) {

                while((bytesRead = stream.read(buffer)) != -1)
                    bao.write(buffer,0,bytesRead);

                bai = new ByteArrayInputStream(bao.toByteArray());
            }
        } finally {
            guard.writeUnlock();
        }
    }

    @Override
    public void dispose() {
        if (bai != null) {
            try {
                bai.close();
            } catch (Exception e) {
                // I don't care in a jam ;-)
            }
        }
    }

    public Clip getClip() {
        guard.writeLock();
        try {
            final var clip = AudioSystem.getClip();
            bai.reset();
            final var audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(bai));
            clip.open(audioStream);
            return clip;
        } catch (Exception e){
            System.err.println("Can't spawn audio clip!");
            e.printStackTrace();
            return null;
        } finally {
            guard.writeUnlock();
        }
    }



}
