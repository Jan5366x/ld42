package de.jam.qad.io;

import javax.sound.sampled.Clip;

/**
 * AudioClipInfo
 *
 * @author Jan5366x
 * Created on 12.08.2018.
 */
public class AudioClipInfo {
    public final Clip clip;


    public AudioClipInfo(Clip clip) {
        this.clip = clip;
    }

    public Clip getClip() {
        return clip;
    }

    public boolean isDone() {
        return clip.isRunning();
    }

    public void dispose() {
        clip.flush();
    }
}
