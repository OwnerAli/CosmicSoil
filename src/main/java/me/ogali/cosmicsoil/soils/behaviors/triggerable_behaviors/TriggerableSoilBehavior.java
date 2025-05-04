package me.ogali.cosmicsoil.soils.behaviors.triggerable_behaviors;

import lombok.Getter;
import me.ogali.cosmicsoil.CosmicSoil;
import me.ogali.cosmicsoil.soils.behaviors.SoilBehavior;

/**
 * Represents a soil behavior that can be triggered based on a probability.
 * This abstract class provides the foundation for behaviors that depend on
 * a chance to activate, allowing subclasses to define specific behavior logic.
 */
@Getter
public abstract class TriggerableSoilBehavior implements SoilBehavior {

    // The probability (0.0 to 1.0) that this behavior will trigger.
    private final double chance;

    /**
     * Constructs a TriggerableSoilBehavior with a specified chance.
     *
     * @param chance The probability of triggering this behavior.
     */
    protected TriggerableSoilBehavior(double chance) {
        this.chance = chance;
    }

    /**
     * Determines whether the behavior should trigger based on the chance.
     * Uses a random number generator to compare against the chance value.
     *
     * @return True if the behavior should trigger, false otherwise.
     */
    protected boolean shouldTrigger() {
        return CosmicSoil.getInstance()
                .getRandom()
                .nextDouble() < chance;
    }

}