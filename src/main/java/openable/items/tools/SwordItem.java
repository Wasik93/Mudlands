package openable.items.tools;

import entities.materials.Damage;
import openable.inventory.Inventory;
import openable.items.Item;
import openable.items.materials.StickItem;
import openable.items.materials.StoneItem;

public class SwordItem extends Item {
    String name = "Sword";
    private final int requiredStone = 2;
    private final int requiredStick = 1;
    private final StoneItem stone = new StoneItem();
    private final StickItem stick = new StickItem();

    public SwordItem() {
        stackable = false;
        craftable = true;
        edible = false;
        equipable = true;
        usable = false;
        damage = new Damage(1, 1, 1, 20);
    }


    @Override
    public void craft(Inventory inventory) {
        if(inventory.checkInventory(stone, requiredStone) && inventory.checkInventory(stick, requiredStick)){
            inventory.addItem(new SwordItem(), 1);
            inventory.removeItem(stone, requiredStone);
            inventory.removeItem(stick, requiredStick);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}