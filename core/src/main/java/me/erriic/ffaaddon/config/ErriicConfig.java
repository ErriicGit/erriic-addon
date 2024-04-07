package me.erriic.ffaaddon.config;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;

@ConfigName("settings")
@SpriteTexture("settings")
public class ErriicConfig extends AddonConfig {
  @SpriteSlot()
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);
  @SpriteSlot(x = 3)
  private HighlightConig highlightConfig = new HighlightConig();

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public HighlightConig highlightConig() {
    return this.highlightConfig;
  }
}
