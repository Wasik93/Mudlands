package components;

import utils.Pair;

public class VelocityComponent implements Component {
    private float x;
    private float y;

    public VelocityComponent() {
        x = 0;
        y = 0;
    }

    public VelocityComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Pair<Float, Float> getAsPair() {
        return new Pair<>(x, y);
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }
}
