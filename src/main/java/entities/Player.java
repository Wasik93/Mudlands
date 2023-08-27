package entities;

import actions.ActionType;
import actions.Cooldown;

public class Player extends Mob{
    private final Cooldown hitCooldown = new Cooldown(0.2f);

    @Override
    public void update(float deltaTime) {
        hitCooldown.advance(deltaTime);
    }

    @Override
    public void updateVelocity() {
        super.updateVelocity();
    }

    public void requestAction(ActionType actionType) {
        if(actionType == ActionType.HIT && hitCooldown.use())
            nextAction = actionType;
    }
}
