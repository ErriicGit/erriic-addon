package me.erriic.ffaaddon.listener;

import me.erriic.ffaaddon.ErriicAddon;
import me.erriic.ffaaddon.activity.HighlighterActivity;
import me.erriic.ffaaddon.config.HighlightConig;
import net.labymod.api.Laby;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.input.KeyEvent;

public class HighlighterHotkeyListener {

  private final ErriicAddon addon;

  public HighlighterHotkeyListener(ErriicAddon addon) {
    this.addon = addon;
  }

  @Subscribe
  public void createWaypoints(KeyEvent event) {
    if (event.state() != KeyEvent.State.PRESS || Laby.labyAPI().minecraft().minecraftWindow().isScreenOpened()) return;
    HighlightConig config = this.addon.configuration().highlightConig();
    if (config.addPlayerHotkey().get() == event.key()) {
      HighlighterActivity activity = new HighlighterActivity();
      Laby.labyAPI().minecraft().minecraftWindow().displayScreen(activity);
    }
  }
}
