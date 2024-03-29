package graphics.drawable;

import graphics.GraphicsContext;

public class LocalizedSprite extends PlaceholderDrawable {
    private final String name;
    private float rotation = 0;
    private float alpha = 1;
    private int layer = 0;

    public LocalizedSprite(String name, Transform transform) {
        super(transform);
        this.name = name;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getRotation() {
        return rotation;
    }

    public void setAlpha(float alpha) {
        if(alpha < 0 || alpha > 1)
            throw new IllegalArgumentException("`alpha` must be from the interval [0, 1]");
        this.alpha = alpha;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawSprite(name, getTransform(), rotation, alpha, layer);
    }
}
