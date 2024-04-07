package me.erriic.ffaaddon.utills;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.labymod.api.util.io.web.request.Callback;
import net.labymod.api.util.io.web.request.Request;
import net.labymod.api.util.io.web.request.Request.Method;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UUIDConverter {

  public static void uuidToName(String uuid, UUIDCallback callback) {
    Request.ofString()
        .method(Method.GET)
        .url("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid)
        .async()
        .connectTimeout(5000)
        .readTimeout(20000)
        .execute((response -> {
          callback.run(new Gson().fromJson(response.get(), JsonObject.class).get("name").getAsString(), response.hasException()?response.exception():null);
        }));
  }

  public static void nameToUuid(String name, UUIDCallback callback) {
    Request.ofString()
        .method(Method.GET)
        .url("https://api.mojang.com/users/profiles/minecraft/" + name)
        .async()
        .connectTimeout(5000)
        .readTimeout(20000)
        .execute((response -> {
          callback.run(new Gson().fromJson(response.get(), JsonObject.class).get("id").getAsString(), response.hasException()?response.exception():null);
        }));
  }

}