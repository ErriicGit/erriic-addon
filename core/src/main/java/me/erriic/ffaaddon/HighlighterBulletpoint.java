package me.erriic.ffaaddon;

import me.erriic.ffaaddon.activity.HighlighterActivity;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.client.entity.player.interaction.BulletPoint;
import net.labymod.api.client.gui.icon.Icon;

public class HighlighterBulletpoint implements BulletPoint {

  @Override
  public Component getTitle() {
    return Component.text("Highlight");
  }

  @Override
  public Icon getIcon() {
    return null;
  }

  @Override
  public void execute(Player player) {
    HighlighterActivity activity = new HighlighterActivity(player.getName());
    Laby.labyAPI().minecraft().minecraftWindow().displayScreen(activity);
  }
}
