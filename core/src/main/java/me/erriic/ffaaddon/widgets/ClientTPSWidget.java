package me.erriic.ffaaddon.widgets;

import me.erriic.ffaaddon.ErriicAddon;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;

public class ClientTPSWidget extends TextHudWidget<TextHudWidgetConfig> {

  private TextLine tps;

  public ClientTPSWidget(String id) {
    super(id);
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);
    this.tps = super.createLine("ClientTPS", "Loading...");
  }

  @Override
  public void onTick() {
      tps.updateAndFlush("1s: " + (ErriicAddon.tickListener.tone==-1 ? "N/A" : ErriicAddon.tickListener.tone) + ", 10s: " + (
          ErriicAddon.tickListener.tten==-1 ? "N/A" : ErriicAddon.tickListener.tten) + ", 30s: " + (
          ErriicAddon.tickListener.tthirty==-1 ? "N/A" : Math.round(ErriicAddon.tickListener.tthirty * 100f) / 100f));
  }
}
