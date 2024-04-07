package me.erriic.ffaaddon.activity;

import me.erriic.ffaaddon.Highlighter;
import me.erriic.ffaaddon.ListEditOperation;
import me.erriic.ffaaddon.utills.UUIDCallback;
import me.erriic.ffaaddon.utills.UUIDConverter;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.Activity;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.Link;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.DivWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.FlexibleContentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.HorizontalListWidget;
import java.io.IOException;

@AutoActivity
@Link("highlighter.lss")
public class HighlighterActivity extends Activity {

  private String text = "";

  public HighlighterActivity() {
  }
  public HighlighterActivity(String name) {
    text = name;
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);
    /*FlexibleContentWidget container = new FlexibleContentWidget();
    container.addId("highlighter-container");*/
    DivWidget inputcontainer = new DivWidget();
    inputcontainer.addId("input-container");
    FlexibleContentWidget inputlist = new FlexibleContentWidget();
    inputlist.addId("input-list");
    HorizontalListWidget textfieldlist = new HorizontalListWidget();
    textfieldlist.addId("horizontal-list");
    HorizontalListWidget dropdownlist = new HorizontalListWidget();
    dropdownlist.addId("horizontal-list");
    HorizontalListWidget buttonlist = new HorizontalListWidget();
    buttonlist.addId("horizontal-list");
    ComponentWidget title = ComponentWidget.text("Highlight Player");
    title.addId("title");
    TextFieldWidget textfield = new TextFieldWidget().placeholder(Component.text("Clantag / Username"));
    textfield.addId("text-field");
    DropdownWidget dropdown = new DropdownWidget();
    dropdown.add("Ally");
    dropdown.add("Ally-Clan");
    dropdown.add("Enemy");
    dropdown.add("Enemy-Clan");
    dropdown.setSelected("Enemy");
    ButtonWidget btnremove = ButtonWidget.text("Remove");
    ButtonWidget btnadd = ButtonWidget.text("Add");
    ButtonWidget btncancel = ButtonWidget.text("Cancel");
    /*this.document.addChild(container);
    container.addContent(inputcontainer);*/
    this.document.addChild(inputcontainer);
    inputcontainer.addChild(title);
    inputcontainer.addChild(inputlist);
    inputlist.addContent(textfieldlist);
    inputlist.addContent(dropdownlist);
    inputlist.addContent(buttonlist);
    buttonlist.addEntry(btnadd);
    buttonlist.addEntry(btnremove);
    buttonlist.addEntry(btncancel);
    textfieldlist.addEntry(textfield);
    dropdownlist.addEntry(dropdown);
    textfield.setText(text);
    btncancel.setPressable(() -> displayPreviousScreen());
    btnadd.setPressable(() -> {
      if(dropdown.getSelected().equals("Ally")){
        UUIDConverter.nameToUuid(textfield.getText(), (s, e) -> {
          if(e==null){
            Highlighter.editLists(ListEditOperation.ADD_ALLY_PLAYER, s);
          } else {
            Laby.labyAPI().minecraft().chatExecutor().displayClientMessage(Component.text("§6[PlayerHighlight] §cCouldnt Resolve PLayer"));
          }
        });
      }
      else if(dropdown.getSelected().equals("Ally-Clan")) {
        Highlighter.editLists(ListEditOperation.ADD_ALLY_CLAN, textfield.getText());
      }
      else if(dropdown.getSelected().equals("Enemy")) {
        UUIDConverter.nameToUuid(textfield.getText(), (s, e) -> {
          if(e==null){
            Highlighter.editLists(ListEditOperation.ADD_ENEMY_PLAYER, s);
          } else {
            Laby.labyAPI().minecraft().chatExecutor().displayClientMessage(Component.text("§6[PlayerHighlight] §cCouldnt Resolve PLayer"));
          }
        });
      }
      else if(dropdown.getSelected().equals("Enemy-Clan")) {
        Highlighter.editLists(ListEditOperation.ADD_ENEMY_CLAN, textfield.getText());
      }
      displayPreviousScreen();
    });
    btnremove.setPressable(() -> {
      if(dropdown.getSelected().equals("Ally")){
        UUIDConverter.nameToUuid(textfield.getText(), (s, e) -> {
          if(e==null){
            Highlighter.editLists(ListEditOperation.REMOVE_ALLY_PLAYER, s);
          } else {
            Laby.labyAPI().minecraft().chatExecutor().displayClientMessage(Component.text("§6[PlayerHighlight] §cCouldnt Resolve PLayer"));
          }
        });
      }
      else if(dropdown.getSelected().equals("Ally-Clan")) {
        Highlighter.editLists(ListEditOperation.REMOVE_ALLY_CLAN, textfield.getText());
      }
      else if(dropdown.getSelected().equals("Enemy")) {
        UUIDConverter.nameToUuid(textfield.getText(), (s, e) -> {
          if(e==null){
            Highlighter.editLists(ListEditOperation.REMOVE_ENEMY_PLAYER, s);
          } else {
            Laby.labyAPI().minecraft().chatExecutor().displayClientMessage(Component.text("§6[PlayerHighlight] §cCouldnt Resolve PLayer"));
          }
        });
      }
      else if(dropdown.getSelected().equals("Enemy-Clan")) {
        Highlighter.editLists(ListEditOperation.REMOVE_ENEMY_CLAN, textfield.getText());
      }
      displayPreviousScreen();
    });
  }
}
