package systems;

import actions.Movement;
import components.MutablePositionComponent;
import components.PositionComponent;
import components.VelocityComponent;
import entities.grounds.Ground;
import entities.mobs.Mob;
import entities.passives.Passive;
import utils.Pair;

import java.util.*;

public class MoveSystem {
    public MoveSystem() {
    }

    public void move(
        Collection<Mob> mobs,
        Map<Pair<Integer,Integer>,Passive> passives,
        Map<Pair<Integer,Integer>, Ground> grounds,
        float deltaTime
    ) {
        for(var mob : mobs) {
            Movement movement = mob.getMovement();
            if(movement == null)
                continue;
            VelocityComponent velocityComponent = movement.getVelocity();
            Pair<Float, Float> velocity = velocityComponent.getAsPair();
            mob.rotationComponent.setRotationFromVector(velocity);
            if(tryMove(movement,mob,velocity,passives,grounds,mobs,deltaTime))
                continue;
            else if(tryMove(movement,mob,new Pair<>(velocity.getFirst(),0f),passives,grounds,mobs,deltaTime))
                continue;
            else if(tryMove(movement,mob,new Pair<>(0f,velocity.getSecond()),passives,grounds,mobs,deltaTime))
                continue;
            movement.reject();
        }
    }

    //returns true on success
    boolean tryMove(
        Movement movement,
        Mob mob,
        Pair<Float,Float> velocity,
        Map<Pair<Integer,Integer>,Passive> passives,
        Map<Pair<Integer,Integer>, Ground> grounds,
        Collection<Mob> mobs,
        float deltaTime
    ) {
        float modifier = grounds.get(PositionComponent.getFieldAsPair(mob.mutablePositionComponent)).getSpeedModifier();
        float x = mob.mutablePositionComponent.getX();
        float y = mob.mutablePositionComponent.getY();
        float dx = velocity.getFirst();
        float dy = velocity.getSecond();
        float length = dx * dx + dy * dy;
        int cost = (int)Math.ceil(length * deltaTime / modifier);
        if(cost >= movement.getAvailableStamina())
            return false;
        float newX = x + dx * deltaTime * modifier;
        float newY = y + dy * deltaTime * modifier;
        MutablePositionComponent newPositionComponent = new MutablePositionComponent(newX, newY);
        Passive passive = passives.get(new Pair<>((int)Math.floor(newX),(int)Math.floor(newY)));
        if(passive != null && passive.isActive())
            return false;
        for(Mob other : mobs)
            if(other != mob && squaredDistance(other.mutablePositionComponent, newPositionComponent) < 1)
                return false;
        mob.mutablePositionComponent.setX(newPositionComponent.getX());
        mob.mutablePositionComponent.setY(newPositionComponent.getY());
        movement.accept(cost);
        return true;
    }

    private static float squaredDistance(PositionComponent first, PositionComponent second) {
        float xDiff = first.getX() - second.getX();
        float yDiff = first.getY() - second.getY();
        return xDiff * xDiff + yDiff * yDiff;
    }
}
