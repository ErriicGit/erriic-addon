package me.erriic.ffaaddon;

import me.erriic.ffaaddon.commands.ListCommand;
import me.erriic.ffaaddon.config.ErriicConfig;
import me.erriic.ffaaddon.listener.HighlighterHotkeyListener;
import me.erriic.ffaaddon.listener.TickListener;
import me.erriic.ffaaddon.listener.RenderListener;
import me.erriic.ffaaddon.widgets.ClientTPSWidget;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class ErriicAddon extends LabyAddon<ErriicConfig> {

  public static ErriicAddon INSTANCE;
  public static TickListener tickListener;
  @Override
  protected void enable() {
    this.INSTANCE = this;
    this.registerSettingCategory();
    this.tickListener = new TickListener(this);
    this.registerListener(tickListener);
    this.registerListener(new RenderListener());
    this.registerListener(new HighlighterHotkeyListener(this));
    this.registerCommand(new ListCommand());
    this.labyAPI().hudWidgetRegistry().register(new ClientTPSWidget("client_tps"));
    this.labyAPI().interactionMenuRegistry().register(new HighlighterBulletpoint());

    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<ErriicConfig> configurationClass() {
    return ErriicConfig.class;
  }

}
