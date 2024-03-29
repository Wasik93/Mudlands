package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;
import java.util.Map;

public class AssetManager {
    private final String assetDirectoryName;

    private final Map<String, Sprite> sprites = new HashMap<>();

    private final Map<String, Texture> inventoryTextures = new HashMap<>();

    public AssetManager(String assetDirectoryName) {
        this.assetDirectoryName = assetDirectoryName;
    }

    public Sprite getSprite(String name) {
        if(sprites.containsKey(name))
            return sprites.get(name);
        Sprite sprite = loadSprite(name);
        sprites.put(name, sprite);
        return sprite;
    }

    public Texture getInventoryTexture(String name) {
        if(inventoryTextures.containsKey(name))
            return inventoryTextures.get(name);
        Texture texture = loadInventoryTexture(name);
        inventoryTextures.put(name, texture);
        return texture;
    }

    private Sprite loadSprite(String name) {
        return new Sprite(new Texture(Gdx.files.internal(assetDirectoryName + "/" + name + ".png")));
    }

    private Texture loadInventoryTexture(String name) {
        return new Texture(Gdx.files.internal(assetDirectoryName + "/inventory/" + name + ".png"));
    }

    public void dispose() {
        for(Sprite sprite : sprites.values())
            sprite.getTexture().dispose();
        for(Texture texture : inventoryTextures.values())
            texture.dispose();
        sprites.clear();
        inventoryTextures.clear();
    }
}
