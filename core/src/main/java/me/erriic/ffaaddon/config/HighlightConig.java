package me.erriic.ffaaddon.config;

import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.client.gui.screen.widget.widgets.input.KeybindWidget.KeyBindSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class HighlightConig extends Config {
  @ParentSwitch
  @SpriteSlot()
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);
  @SpriteSlot(x=2)
  @TextFieldSetting
  private final ConfigProperty<String> server = new ConfigProperty<>("");
  @SpriteSlot(x=1)
  @TextFieldSetting
  private final ConfigProperty<String> sharekey = new ConfigProperty<>("");
  @SpriteSlot()
  @SliderSetting(min = 5, max = 60, steps = 1)
  private ConfigProperty<Integer> updateinterval = new ConfigProperty<>(30);
  @SpriteSlot()
  @KeyBindSetting
  private ConfigProperty<Key> addplayerhotkey = new ConfigProperty<>(Key.NONE);

  @SpriteSlot(x = 3)
  private AllyClanSettings allyclansettings = new AllyClanSettings();

  @SpriteSlot(x = 4)
  private EnemyClanSettings enemyclansettings = new EnemyClanSettings();

  @SpriteSlot(x = 5)
  private AllyPlayerSettings allyplayersettings = new AllyPlayerSettings();

  @SpriteSlot(x = 6)
  private EnemyPlayerSettings enemyplayersettings = new EnemyPlayerSettings();

  @SpriteSlot(x=7)
  @SwitchSetting
  private ConfigProperty<Boolean> esp = new ConfigProperty<>(false);

  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<String> server(){
    return this.server;
  }
  public ConfigProperty<String> sharekey(){
    return this.sharekey;
  }
  public ConfigProperty<Integer> updateinterval() {
    return this.updateinterval;
  }
  public AllyClanSettings allyclansettings(){ return allyclansettings; }
  public EnemyClanSettings enemyclansettings(){
    return enemyclansettings;
  }
  public AllyPlayerSettings allyplayersettings(){
    return allyplayersettings;
  }
  public EnemyPlayerSettings enemyplayersettings(){
    return enemyplayersettings;
  }

  public ConfigProperty<Boolean> esp() {
    return esp;
  }

    public ConfigProperty<Key> addPlayerHotkey() {
      return addplayerhotkey;
    }
}
