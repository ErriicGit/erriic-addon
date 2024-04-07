package me.erriic.ffaaddon;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.util.io.web.request.Request;
import net.labymod.api.util.io.web.request.Request.Method;

public class Highlighter {
  public static List<String> allyPlayers = new ArrayList<>();
  public static List<String> enemyPlayers = new ArrayList<>();
  public static List<String> allyClans = new ArrayList<>();
  public static List<String> enemyClans = new ArrayList<>();

  public static boolean updateLists() {
    try {
      Request.ofString()
          .method(Method.GET)
          .url(ErriicAddon.INSTANCE.configuration().highlightConig().server().get() + "/api/" + ErriicAddon.INSTANCE.configuration().highlightConig().sharekey().get())
          .async()
          .connectTimeout(5000)
          .readTimeout(20000)
          .execute(response -> {
            if(response.isPresent()){
              try {
                readresponse(response.get());
              }catch (JsonSyntaxException e){
                Laby.labyAPI().minecraft().chatExecutor().displayClientMessage(Component.text("§6[PlayerHighlight] §cInvalid Response from the Server"));
              }
            } else if(response.hasException()){
              //Rework needed
              Laby.labyAPI().minecraft().chatExecutor().displayClientMessage(Component.text("§6[PlayerHighlight] §c" + response.exception().getMessage().split("\\)", 2)[1]));
            }
          });
      return true;
    }catch (Exception e){
      return false;
    }
  }


  public static boolean editLists(ListEditOperation operation, String id) {
    try {
      Request.ofString()
          .method(Method.GET)
          .url(ErriicAddon.INSTANCE.configuration().highlightConig().server().get() + "/api/" + ErriicAddon.INSTANCE.configuration().highlightConig().sharekey().get() + "/" + getOperationString(operation) + "/" + id)
          .async()
          .connectTimeout(5000)
          .readTimeout(20000)
          .execute(response -> {
            if(response.isPresent()){
              try {
                readresponse(response.get());
                Laby.labyAPI().minecraft().chatExecutor().displayClientMessage(Component.text("§6[PlayerHighlight] §rList Edited"));
              }catch (JsonSyntaxException e){
                Laby.labyAPI().minecraft().chatExecutor().displayClientMessage(Component.text("§6[PlayerHighlight] §cInvalid Response from the Server"));
              }
            } else if(response.hasException()){
              Laby.labyAPI().minecraft().chatExecutor().displayClientMessage(Component.text("§6[PlayerHighlight] §c" + response.exception().getMessage().split("\\)", 2)[1]));
            }
          });
      return true;
    }catch (Exception e){
      return false;
    }
  }


  public static void readresponse(String s) throws JsonSyntaxException {
    Gson gson = new Gson();
    JsonObject jsonObject = gson.fromJson(s, JsonObject.class);
    enemyPlayers = new Gson().fromJson(jsonObject.getAsJsonArray("enemyPlayers"), List.class);
    allyPlayers = new Gson().fromJson(jsonObject.getAsJsonArray("allyPlayers"), List.class);
    enemyClans = new Gson().fromJson(jsonObject.getAsJsonArray("enemyClans"), List.class);
    allyClans = new Gson().fromJson(jsonObject.getAsJsonArray("allyClans"), List.class);
  }
  public static String getOperationString(ListEditOperation operation){
    switch (operation){
      case ADD_ALLY_PLAYER -> {
        return "addAllyPlayer";
      }
      case ADD_ENEMY_PLAYER -> {
        return "addEnemyPlayer";
      }
      case ADD_ALLY_CLAN -> {
        return "addAllyClan";
      }
      case ADD_ENEMY_CLAN -> {
        return "addEnemyClan";
      }
      case REMOVE_ALLY_PLAYER -> {
        return "removeAllyPlayer";
      }
      case REMOVE_ENEMY_PLAYER -> {
        return "removeEnemyPlayer";
      }
      case REMOVE_ALLY_CLAN -> {
        return "removeAllyClan";
      }
      case REMOVE_ENEMY_CLAN -> {
        return "removeEnemyClan";
      }
      default -> {
        return null;
      }
    }
  }

}
