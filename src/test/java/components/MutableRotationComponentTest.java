package components;

import org.junit.jupiter.api.Test;
import utils.Debug;
import utils.Pair;

import static org.junit.jupiter.api.Assertions.*;

class MutableRotationComponentTest {
    @Test
    void testGettersSetters(){
        MutableRotationComponent rotationComponent = new MutableRotationComponent();
        assertEquals(90f,rotationComponent.getRotation(),Debug.TEST_DELTA);

        rotationComponent.setRotation(20.5f);
        assertEquals(20.5f,rotationComponent.getRotation(),Debug.TEST_DELTA);

        rotationComponent.setRotation(-20.5f);
        assertEquals(339.5f,rotationComponent.getRotation(),Debug.TEST_DELTA);
    }
    @Test
    void testRotationFromVector(){
        MutableRotationComponent rotationComponent = new MutableRotationComponent();

        rotationComponent.setRotationFromVector(new Pair<>(1f,0f));
        assertEquals(0,rotationComponent.getRotation(), Debug.TEST_DELTA);

        rotationComponent.setRotationFromVector(new Pair<>(1f,1f));
        assertEquals(45,rotationComponent.getRotation(), Debug.TEST_DELTA);

        rotationComponent.setRotationFromVector(new Pair<>(0f,1f));
        assertEquals(90,rotationComponent.getRotation(), Debug.TEST_DELTA);

        rotationComponent.setRotationFromVector(new Pair<>(-1f,1f));
        assertEquals(135,rotationComponent.getRotation(), Debug.TEST_DELTA);

        rotationComponent.setRotationFromVector(new Pair<>(-1f,0f));
        assertEquals(180,rotationComponent.getRotation(), Debug.TEST_DELTA);

        rotationComponent.setRotationFromVector(new Pair<>(-1f,-1f));
        assertEquals(225,rotationComponent.getRotation(), Debug.TEST_DELTA);

        rotationComponent.setRotationFromVector(new Pair<>(0f,-1f));
        assertEquals(270,rotationComponent.getRotation(), Debug.TEST_DELTA);

        rotationComponent.setRotationFromVector(new Pair<>(1f,-1f));
        assertEquals(315,rotationComponent.getRotation(), Debug.TEST_DELTA);

        rotationComponent.setRotationFromVector(new Pair<>((float)-Math.sqrt(3),1f));
        assertEquals(150,rotationComponent.getRotation(), Debug.TEST_DELTA);

        rotationComponent.setRotationFromVector(new Pair<>((float)-Math.sqrt(3),-1f));
        assertEquals(210,rotationComponent.getRotation(), Debug.TEST_DELTA);
    }

}