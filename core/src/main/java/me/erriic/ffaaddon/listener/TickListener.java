package me.erriic.ffaaddon.listener;

import me.erriic.ffaaddon.Highlighter;
import net.labymod.api.event.Phase;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.lifecycle.GameTickEvent;
import me.erriic.ffaaddon.ErriicAddon;
import java.util.ArrayDeque;
import java.util.Deque;

public class TickListener {

  private final ErriicAddon addon;

  public TickListener(ErriicAddon addon) {
    this.addon = addon;
  }

  private Deque<Long> one = new ArrayDeque<Long>();
  private Deque<Long> ten = new ArrayDeque<Long>();
  private Deque<Long> thirty = new ArrayDeque<Long>();

  public int tone = 0;
  public float tten = 0;
  public float tthirty = 0;
  private long firsttick = -1;

  private int counter = 0;
  @Subscribe
  public void onGameTick(GameTickEvent event) {
    if (event.phase() != Phase.PRE) {
      return;
    }
    if(ErriicAddon.INSTANCE.configuration().highlightConig().enabled().get()){
        if(counter>=ErriicAddon.INSTANCE.configuration().highlightConig().updateinterval().get()*20){
          counter = 0;
          Highlighter.updateLists();
        } else {
          counter++;
        }
    }
    long currenttime = System.currentTimeMillis();
    firsttick = firsttick==-1 ? firsttick=currenttime : firsttick;
    one.add(currenttime);
    ten.add(currenttime);
    thirty.add(currenttime);

    for(;currenttime-one.getFirst()>=1000;){
      one.removeFirst();
    }
    for(;currenttime-ten.getFirst()>=10000;){
      ten.removeFirst();
    }
    for(;currenttime-thirty.getFirst()>=30000;){
      thirty.removeFirst();
    }
    this.tone=currenttime-firsttick<=1000? -1 : one.size();
    this.tten=currenttime-firsttick<=10000? -1 : ten.size()/10f;
    this.tthirty=currenttime-firsttick<=30000? -1 : thirty.size()/30f;
  }
}
