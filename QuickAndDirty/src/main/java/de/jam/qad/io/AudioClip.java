package de.jam.qad.io;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Audio Clip
 * TODO review related to async behavior
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class AudioClip implements IStreamResource {
    private Clip clip = null;
    @Override
    public void loadByStream(InputStream stream) throws Exception {
       clip = AudioSystem.getClip();
       final var audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(stream));
       clip.open(audioStream);
    }

    @Override
    public void dispose() {
        clip.flush();
        clip = null;
    }

    public void play() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public void loop(final int count) {
        clip.loop(count);
    }

}
