package components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecreasingHungerComponentTest {
    @Test
    void testHungerDoesNotStartDecreasingBeforeTheProvidedTime() {
        DecreasingHungerComponent hunger = new DecreasingHungerComponent(100, 42, 4, 5);
        hunger.update(41);
        assertTrue(Vital.isSatisfied(hunger));
        hunger.update(1);
        assertTrue(Vital.isSatisfied(hunger));
    }

    @Test
    void testHungerDecreasesAtTheRateProvidedDuringConstruction() {
        DecreasingHungerComponent hunger = new DecreasingHungerComponent(100, 42, 4, 2);
        hunger.update(42);
        hunger.update(4);
        assertEquals(98, hunger.getCurrentPoints());
        hunger.update(2);
        assertEquals(98, hunger.getCurrentPoints());
        hunger.update(2);
        assertEquals(96, hunger.getCurrentPoints());
    }

    @Test
    void testHungerDecreasesCorrectlyInOneBurst() {
        DecreasingHungerComponent hunger = new DecreasingHungerComponent(100, 42, 4, 2);
        hunger.update(50);
        assertEquals(96, hunger.getCurrentPoints());
    }

    @Test
    void testHungerResetsToFullnessWhenFixedCompletely() {
        DecreasingHungerComponent hunger = new DecreasingHungerComponent(100, 42, 4, 2);
        hunger.update(50);
        hunger.fix(4);
        hunger.update(42);
        assertTrue(Vital.isSatisfied(hunger));
    }

    @Test
    void testOngoingDecreasesResetWhenHungerIsFixedCompletely() {
        DecreasingHungerComponent hunger = new DecreasingHungerComponent(100, 42, 4, 2);
        hunger.update(50);
        hunger.update(1);
        hunger.fix(4);
        hunger.update(42);
        hunger.update(3);
        assertTrue(Vital.isSatisfied(hunger));
    }
}