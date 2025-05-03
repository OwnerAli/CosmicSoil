package me.ogali.cosmicsoil.soils.behaviors.triggerable_behaviors.growth_behaviors;

import me.ogali.cosmicsoil.soils.behaviors.triggerable_behaviors.TriggerableSoilBehavior;
import me.ogali.cosmicsoil.soils.contexts.SoilBehaviorContext;
import org.bukkit.event.block.BlockGrowEvent;

/**
 * Abstract class representing a growth behavior for custom soils.
 * This behavior is triggered when crops grow on the soil.
 */
public abstract class GrowthBehavior extends TriggerableSoilBehavior {

    /**
     * Constructor to initialize the growth behavior with a trigger chance.
     *
     * @param chance The probability of triggering this behavior.
     */
    public GrowthBehavior(double chance) {
        super(chance);
    }

    /**
     * Handles the triggering logic for the growth behavior.
     * Ensures the behavior only triggers if the conditions are met.
     *
     * @param context The context containing the block and event data.
     */
    @Override
    public void handle(SoilBehaviorContext context) {
        // Check if the behavior should trigger based on the chance.
        if (!(shouldTrigger())) return;

        // Ensure the event is a BlockGrowEvent before proceeding.
        if (!(context.event() instanceof BlockGrowEvent event)) return;

        // Delegate the growth handling logic to the subclass implementation.
        this.handleGrowth(context, event);
    }

    /**
     * Abstract method to handle the specific growth logic.
     * Subclasses must implement this to define their custom growth behavior.
     *
     * @param context The context containing the block and event data.
     * @param event   The BlockGrowEvent that triggered this behavior.
     */
    protected abstract void handleGrowth(SoilBehaviorContext context, BlockGrowEvent event);

}