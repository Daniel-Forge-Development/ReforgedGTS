package com.envyful.gts.api.player;

public class PlayerSettings {

    private boolean toggledBroadcasts = true;

    public PlayerSettings() {}

    public boolean isToggledBroadcasts() {
        return this.toggledBroadcasts;
    }

    public void setToggledBroadcasts(boolean toggledBroadcasts) {
        this.toggledBroadcasts = toggledBroadcasts;
    }
}
