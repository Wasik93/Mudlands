package graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import openable.inventory.Inventory;
import openable.inventory.InventoryField;

import static utils.Config.INVENTORY_HEIGHT;

public class InventoryChangeListener extends InputListener {

    private final InventoryRendering inventoryRendering;
    private final Inventory inventory;
    private final int finalRow;
    private final int finalCol;

    public InventoryChangeListener(InventoryRendering inventoryRendering, int row, int col) {
        this.inventoryRendering = inventoryRendering;
        this.inventory = inventoryRendering.getInventory();
        finalRow = row;
        finalCol = col;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if(button == Input.Buttons.RIGHT || inventory.get(finalRow, finalCol).getItem().toString().equals("None")) {
            return;
        }
        if(inventoryRendering.dragging) {
            Actor hitActor = event.getStage().hit(Gdx.input.getX(), Gdx.input.getY(), true);
            if(hitActor instanceof InventoryImage inventoryImage) {
                InventoryField tmp = new InventoryField();
                InventoryField source = inventory.get(finalRow, finalCol);
                InventoryField target = inventory.get(INVENTORY_HEIGHT - inventoryImage.i - 1, inventoryImage.j);
                if(target.accept(source.getItem()) && (source.accept(target.getItem()) || target.getItem().toString().equals("None"))) {
                    tmp.setField(source);
                    source.setField(target);
                    target.setField(tmp);
                    inventoryRendering.updateInventory();
                    inventoryRendering.lastClickedI = INVENTORY_HEIGHT - inventoryImage.i - 1;
                    inventoryRendering.lastClickedJ = inventoryImage.j;
                }
            }
            inventoryRendering.image.remove();
        }
        inventoryRendering.dragging = false;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        if(inventoryRendering.dragging) {
            float deltaX = x - inventoryRendering.offsetX;
            float deltaY = y - inventoryRendering.offsetY;

            inventoryRendering.image.moveBy(deltaX, deltaY);

            inventoryRendering.offsetX = x;
            inventoryRendering.offsetY = y;
        }
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(inventory.get(finalRow, finalCol).getItem().toString().equals("None")) {
            return false;
        }
        if(button == Input.Buttons.RIGHT){
            inventoryRendering.showDescription(finalRow, finalCol);
            return true;
        }
        if(inventoryRendering.dragging) {
            return false;
        }
        inventoryRendering.image = new Image(inventoryRendering.assetManager.getInventoryTexture(inventory.get(finalRow, finalCol).getItem().toString()));
        inventoryRendering.image.setSize(64f, 64f);
        inventoryRendering.image.setPosition(Gdx.input.getX() - inventoryRendering.image.getWidth() / 2f, Gdx.graphics.getHeight() - Gdx.input.getY() - inventoryRendering.image.getHeight() / 2f);
        inventoryRendering.getStage().addActor(inventoryRendering.image);

        inventoryRendering.offsetX = x;
        inventoryRendering.offsetY = y;
        inventoryRendering.dragging = true;
        inventoryRendering.handleClick(finalRow, finalCol);
        return true;
    }
}
