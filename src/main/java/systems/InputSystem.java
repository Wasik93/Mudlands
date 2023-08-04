package systems;

import components.VelocityComponent;
import entities.Entity;
import entities.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputSystem extends System {
    public InputSystem(World world) {
        super(world);
    }

    @Override
    public void update(float deltaTime) {
        Entity player = world.getEntity(1);

        VelocityComponent velocity = player.get(VelocityComponent.class);

        float speed = 200;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velocity.setX(-speed);
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velocity.setX(speed);
        } else {
            velocity.setX(0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            velocity.setY(speed);
        } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            velocity.setY(-speed);
        } else {
            velocity.setY(0);
        }
    }
}
