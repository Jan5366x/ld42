package de.jam.qad.io;

import de.jam.qad.structure.IComponent;

import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AudioManager
 *
 * @author Jan5366x
 * Created on 12.08.2018.
 */
public class AudioManager implements IComponent {

    private final AtomicInteger idHelper = new AtomicInteger();
    private final ArrayList<AudioClipInfo> currentClips = new ArrayList<>();

    public void playOnce(final String soundFile) {

        // FIXME handle async
        final Clip clip = ResourceManager.get(soundFile,RawAudioClip.class).getClip();
        clip.start();
        currentClips.add(new AudioClipInfo(clip));
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        for (var i = currentClips.size() -1 ; i >= 0; i--) {
            final var audioClip = currentClips.get(i);
            if (audioClip.isDone()) {
                audioClip.dispose();
                currentClips.remove(i);
            }
        }
    }

    @Override
    public void shutdown() {

    }
}
