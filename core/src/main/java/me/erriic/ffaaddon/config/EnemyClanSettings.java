package me.erriic.ffaaddon.config;

import java.awt.*;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class EnemyClanSettings extends Config {
  @ParentSwitch
  @SpriteSlot(x = 2)
  @SwitchSetting
  private ConfigProperty<Boolean> highligt = new ConfigProperty<>(true);

  @SpriteSlot(x = 0, y = 1)
  @ColorPickerSetting(alpha = true)
  private ConfigProperty<Integer> outline = new ConfigProperty<>(new Color(0, 0, 0, 255).getRGB());

  @SpriteSlot(x = 1, y = 1)
  @ColorPickerSetting(alpha = true)
  private ConfigProperty<Integer> fill = new ConfigProperty<>(new Color(255, 0, 0, 100).getRGB());

  @SpriteSlot(x = 2, y = 1)
  @ColorPickerSetting(alpha = true)
  private ConfigProperty<Integer> tab = new ConfigProperty<>(new Color(255, 0, 0, 100).getRGB());


  public ConfigProperty<Boolean> highligt() {
    return this.highligt;
  }

  public ConfigProperty<Integer> outline(){
    return this.outline;
  }

  public ConfigProperty<Integer> fill(){
    return this.fill;
  }

  public ConfigProperty<Integer> tab(){
    return this.tab;
  }
}
