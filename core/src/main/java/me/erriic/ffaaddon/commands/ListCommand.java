package me.erriic.ffaaddon.commands;

import java.io.IOException;
import me.erriic.ffaaddon.Highlighter;
import me.erriic.ffaaddon.ListEditOperation;
import me.erriic.ffaaddon.utills.UUIDConverter;
import net.labymod.api.Laby;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;

public class ListCommand extends Command {

  public ListCommand() {
    super("enemy", "target", "ally", "helper", "enemyclan", "targetclan", "allyclan", "helperclan");
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {

    if(!(arguments.length==2)){
      sendUsage(prefix);
    }
    else if(prefix.equalsIgnoreCase("enemy")||prefix.equalsIgnoreCase("target")){
      if(arguments[0].equalsIgnoreCase("add")){
        UUIDConverter.nameToUuid(arguments[1], (s, e) -> {
          if(e==null){
            Highlighter.editLists(ListEditOperation.ADD_ENEMY_PLAYER, s);
          } else {
            Laby.labyAPI().minecraft().chatExecutor().displayClientMessage(Component.text("§6[PlayerHighlight] §cCouldnt Resolve PLayer"));
          }
        });
      }
      else if(arguments[0].equalsIgnoreCase("remove")){
        UUIDConverter.nameToUuid(arguments[1], (s, e) -> {
          if(e==null){
            Highlighter.editLists(ListEditOperation.REMOVE_ENEMY_PLAYER, s);
          } else {
            Laby.labyAPI().minecraft().chatExecutor().displayClientMessage(Component.text("§6[PlayerHighlight] §cCouldnt Resolve PLayer"));
          }
        });
      }else {
        sendUsage(prefix);
      }
    }
    else if(prefix.equalsIgnoreCase("ally")|| prefix.equalsIgnoreCase("helper")) {
      if(arguments[0].equalsIgnoreCase("add")){
        UUIDConverter.nameToUuid(arguments[1], (s, e) -> {
          if(e==null){
            Highlighter.editLists(ListEditOperation.ADD_ALLY_PLAYER, s);
          } else {
            Laby.labyAPI().minecraft().chatExecutor().displayClientMessage(Component.text("§6[PlayerHighlight] §cCouldnt Resolve PLayer"));
          }
        });
      }
      else if(arguments[0].equalsIgnoreCase("remove")){
        UUIDConverter.nameToUuid(arguments[1], (s, e) -> {
          if(e==null){
            Highlighter.editLists(ListEditOperation.REMOVE_ALLY_PLAYER, s);
          } else {
            Laby.labyAPI().minecraft().chatExecutor().displayClientMessage(Component.text("§6[PlayerHighlight] §cCouldnt Resolve PLayer"));
          }
        });
      }else {
        sendUsage(prefix);
      }
    }
    else if(prefix.equalsIgnoreCase("targetclan")|| prefix.equalsIgnoreCase("enemyclan")) {
      if(arguments[0].equalsIgnoreCase("add")){
          Highlighter.editLists(ListEditOperation.ADD_ENEMY_CLAN, arguments[1]);
      }
      else if(arguments[0].equalsIgnoreCase("remove")){
          Highlighter.editLists(ListEditOperation.REMOVE_ENEMY_CLAN, arguments[1]);
      }else {
        sendUsage(prefix);
      }
    }
    else if(prefix.equalsIgnoreCase("allyclan")|| prefix.equalsIgnoreCase("helperclan")) {
      if(arguments[0].equalsIgnoreCase("add")){
          Highlighter.editLists(ListEditOperation.ADD_ALLY_CLAN, arguments[1]);
      }
      else if(arguments[0].equalsIgnoreCase("remove")){
          Highlighter.editLists(ListEditOperation.REMOVE_ALLY_CLAN, arguments[1]);
      }else {
        sendUsage(prefix);
      }
    }
    return true;
  }

  public void sendUsage(String prefix){
    this.displayMessage(Component.text("Please use: /" + prefix + " <add/remove> name", NamedTextColor.RED));
  }
}
