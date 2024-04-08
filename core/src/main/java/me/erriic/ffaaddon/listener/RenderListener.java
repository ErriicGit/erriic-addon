package me.erriic.ffaaddon.listener;

import me.erriic.ffaaddon.ErriicAddon;
import me.erriic.ffaaddon.Highlighter;
import me.erriic.ffaaddon.config.HighlightConig;
import net.labymod.api.Laby;
import net.labymod.api.client.Minecraft;
import net.labymod.api.client.component.serializer.plain.PlainTextComponentSerializer;
import net.labymod.api.client.entity.Entity;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.client.options.Perspective;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.event.Subscribe;
import net.labymod.api.util.bounds.Rectangle;
import net.labymod.api.util.math.vector.FloatVector3;

public class RenderListener {

  @Subscribe
  public void onRender(net.labymod.api.event.client.render.world.RenderWorldEvent event){
    HighlightConig config = ErriicAddon.INSTANCE.configuration().highlightConig();
    if(!config.enabled().get()){
      return;
    }
    Minecraft minecraft = Laby.labyAPI().minecraft();
    Player player = minecraft.getClientPlayer();
    FloatVector3 pos = event.camera().renderPosition();
    if(config.esp().get()){
      Laby.labyAPI().gfxRenderPipeline().gfx().disableDepth();
    } else {
      Laby.labyAPI().gfxRenderPipeline().gfx().enableDepth();
    }
    Laby.labyAPI().gfxRenderPipeline().gfx().disableCull();
    for(Player pl : minecraft.clientWorld().getPlayers()){
      if(pl!=player||minecraft.options().perspective()!=Perspective.FIRST_PERSON){
        if(config.allyplayersettings().highligt().get() && Highlighter.allyPlayers.contains(pl.getUniqueId().toString().replace("-", ""))){
          renderHitbox(pl, event.stack(), pos, event.delta(), config.allyplayersettings().fill().get(), config.allyplayersettings().outline().get());
        }
        else if(config.enemyplayersettings().highligt().get() && Highlighter.enemyPlayers.contains(pl.getUniqueId().toString().replace("-", ""))){
          renderHitbox(pl, event.stack(), pos, event.delta(), config.enemyplayersettings().fill().get(), config.enemyplayersettings().outline().get());
        }
        else if(pl.getNetworkPlayerInfo() !=null){
          String[] name = PlainTextComponentSerializer.plainText().serialize(pl.networkPlayerInfo().displayName()).split("\\[");
          if(name.length==2){
            String tag = name[1].replaceFirst("]", "");
            if(config.allyclansettings().highligt().get() && Highlighter.allyClans.contains(tag)){
              renderHitbox(pl, event.stack(), pos, event.delta(), config.allyclansettings().fill().get(), config.allyclansettings().outline().get());
            }
            else if(config.enemyclansettings().highligt().get() && Highlighter.enemyClans.contains(tag)){
              renderHitbox(pl, event.stack(), pos, event.delta(), config.enemyclansettings().fill().get(), config.enemyclansettings().outline().get());
            }
          }
        }
      }
    }
    Laby.labyAPI().gfxRenderPipeline().gfx().enableDepth();
    Laby.labyAPI().gfxRenderPipeline().gfx().enableCull();

  }


  public void renderHitbox(Player pl, Stack stack, FloatVector3 pos, float delta, int fillColor, int outlineColor){
    FloatVector3 plpos = getInterpolatedPos(pl, delta);
    //Render the Planes
    stack.push();
    stack.translate(plpos.getX()-pos.getX(), plpos.getY()-pos.getY(), plpos.getZ()-pos.getZ()-0.3f);
    Laby.references().renderPipeline().rectangleRenderer().pos(-0.3f, 1.8f, 0.3f, 0f).color(fillColor).render(stack);
    stack.pop();
    stack.push();
    stack.translate(plpos.getX()-pos.getX(), plpos.getY()-pos.getY(), plpos.getZ()-pos.getZ()+0.3f);
    Laby.references().renderPipeline().rectangleRenderer().pos(-0.3f, 1.8f, 0.3f, 0f).color(fillColor).render(stack);
    stack.pop();
    stack.push();
    stack.translate(plpos.getX()-pos.getX()-0.3f, plpos.getY()-pos.getY(), plpos.getZ()-pos.getZ());
    stack.rotate(90f, 0f, 1f, 0f);
    Laby.references().renderPipeline().rectangleRenderer().pos(-0.3f, 1.8f, 0.3f, 0f).color(fillColor).render(stack);
    stack.pop();
    stack.push();
    stack.translate(plpos.getX()-pos.getX()+0.3f, plpos.getY()-pos.getY(), plpos.getZ()-pos.getZ());
    stack.rotate(90f, 0f, 1f, 0f);
    Laby.references().renderPipeline().rectangleRenderer().pos(-0.3f, 1.8f, 0.3f, 0f).color(fillColor).render(stack);
    stack.pop();
    stack.push();
    stack.translate(plpos.getX()-pos.getX(), plpos.getY()-pos.getY(), plpos.getZ()-pos.getZ());
    stack.rotate(90f, 1f, 0f, 0f);
    Laby.references().renderPipeline().rectangleRenderer().pos(-0.3f, 0.3f, 0.3f, -0.3f).color(fillColor).render(stack);
    stack.pop();
    stack.push();
    stack.translate(plpos.getX()-pos.getX(), plpos.getY()-pos.getY()+1.8f, plpos.getZ()-pos.getZ());
    stack.rotate(90f, 1f, 0f, 0f);
    Laby.references().renderPipeline().rectangleRenderer().pos(-0.3f, 0.3f, 0.3f, -0.3f).color(fillColor).render(stack);
    stack.pop();

    //Render the lines
    stack.push();
    stack.translate(plpos.getX()-pos.getX(), plpos.getY()-pos.getY(), plpos.getZ()-pos.getZ()-0.3f);
    Laby.references().renderPipeline().rectangleRenderer().renderOutline(stack, Rectangle.absolute(-0.3f, 1.8f, 0.3f, 0f), outlineColor, 0.01f);
    stack.pop();
    stack.push();
    stack.translate(plpos.getX()-pos.getX(), plpos.getY()-pos.getY(), plpos.getZ()-pos.getZ()+0.3f);
    Laby.references().renderPipeline().rectangleRenderer().renderOutline(stack, Rectangle.absolute(-0.3f, 1.8f, 0.3f, 0f), outlineColor, 0.01f);
    stack.pop();
    stack.push();
    stack.translate(plpos.getX()-pos.getX()-0.3f, plpos.getY()-pos.getY(), plpos.getZ()-pos.getZ());
    stack.rotate(90f, 0f, 1f, 0f);
    Laby.references().renderPipeline().rectangleRenderer().renderOutline(stack, Rectangle.absolute(-0.3f, 1.8f, 0.3f, 0f), outlineColor, 0.01f);
    stack.pop();
    stack.push();
    stack.translate(plpos.getX()-pos.getX()+0.3f, plpos.getY()-pos.getY(), plpos.getZ()-pos.getZ());
    stack.rotate(90f, 0f, 1f, 0f);
    Laby.references().renderPipeline().rectangleRenderer().renderOutline(stack, Rectangle.absolute(-0.3f, 1.8f, 0.3f, 0f), outlineColor, 0.01f);
    stack.pop();
    stack.push();
    stack.translate(plpos.getX()-pos.getX(), plpos.getY()-pos.getY(), plpos.getZ()-pos.getZ());
    stack.rotate(90f, 1f, 0f, 0f);
    Laby.references().renderPipeline().rectangleRenderer().renderOutline(stack, Rectangle.absolute(-0.3f, 0.3f, 0.3f, -0.3f), outlineColor, 0.01f);
    stack.pop();
    stack.push();
    stack.translate(plpos.getX()-pos.getX(), plpos.getY()-pos.getY()+1.8f, plpos.getZ()-pos.getZ());
    stack.rotate(90f, 1f, 0f, 0f);
    Laby.references().renderPipeline().rectangleRenderer().renderOutline(stack, Rectangle.absolute(-0.3f, 0.3f, 0.3f, -0.3f), outlineColor, 0.01f);
    stack.pop();

  }

  public FloatVector3 getInterpolatedPos(Entity entity, float delta){
    return new FloatVector3(entity.getPreviousPosX()+(entity.getPosX()-entity.getPreviousPosX()) * delta, entity.getPreviousPosY()+(entity.getPosY()-entity.getPreviousPosY()) * delta, entity.getPreviousPosZ()+(entity.getPosZ()-entity.getPreviousPosZ()) * delta);
  }

}
