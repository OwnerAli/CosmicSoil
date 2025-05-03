package me.ogali.cosmicsoil.soils.growth_soil;

import me.ogali.cosmicsoil.soils.CustomSoil;
import me.ogali.cosmicsoil.soils.behaviors.triggerable_behaviors.growth_behaviors.AcceleratedGrowthBehavior;

// Represents a basic growth soil type with specific behavior.
// This soil type has a 50% chance to trigger accelerated growth behavior.
public class BasicGrowthSoil extends CustomSoil {

    /**
     * Constructs a BasicGrowthSoil instance with predefined behavior.
     * The AcceleratedGrowthBehavior is initialized with:
     * - A 50% chance to trigger.
     * - A 50% growth acceleration factor.
     */
    public BasicGrowthSoil() {
        super("basic_growth", new AcceleratedGrowthBehavior(0.5, 0.5));
    }

}
