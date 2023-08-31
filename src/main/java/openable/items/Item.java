package openable.items;

import com.badlogic.gdx.scenes.scene2d.Stage;
import entities.Player;
import openable.crafting.CraftedPopup;
import openable.inventory.Inventory;
import utils.Pair;

import java.util.ArrayList;

public class Item {

    protected boolean stackable;
    protected boolean usable;
    protected boolean edible;
    protected boolean equipable;
    protected boolean craftable;

    protected ArrayList<Pair<Item, Integer>> recipe;

    public String getRecipe() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Pair<Item, Integer> item : recipe) {
            stringBuilder.append(item.getFirst()).append(": ").append(item.getSecond()).append(", ");
        }
        return stringBuilder.toString();
    }

    public int getAttackStrength() {
        return attackStrength;
    }

    protected int attackStrength = 0;

    public boolean craft(Inventory inventory) {
        return isCraftable();
    }

    public void use(Player player) {

    }

    void equip() {

    }

    public boolean isStackable() {
        return stackable;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public boolean isEdible() {
        return edible;
    }

    public void setEdible(boolean edible) {
        this.edible = edible;
    }

    public boolean isEquipable() {
        return equipable;
    }

    public void setEquipable(boolean equipable) {
        this.equipable = equipable;
    }

    public boolean isCraftable() {
        return craftable;
    }

    public void setCraftable(boolean craftable) {
        this.craftable = craftable;
    }

    @Override
    public String toString() {
        return "item";
    }
}
