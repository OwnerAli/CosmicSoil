package me.ogali.cosmicsoil.soils.behaviors.triggerable_behaviors.growth_behaviors;

import me.ogali.cosmicsoil.soils.contexts.SoilBehaviorContext;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.block.BlockGrowEvent;

/**
 * Represents a behavior that accelerates crop growth by a specified percentage
 * of its maximum age. The growth is rounded up to ensure at least one stage of growth.
 */
public class AcceleratedGrowthBehavior extends GrowthBehavior {
    private final double growthPercent;

    /**
     * Constructs an AcceleratedGrowthBehavior with a given chance and growth percentage.
     *
     * @param chance        The probability of triggering this behavior.
     * @param growthPercent The percentage of the crop's max age to grow. (e.g., 0.25 = 25%)
     */
    public AcceleratedGrowthBehavior(double chance, double growthPercent) {
        super(chance);
        this.growthPercent = growthPercent;
    }

    /**
     * Handles the growth logic for crops. Increases the crop's age by a percentage
     * of its maximum age, ensuring it does not exceed the maximum age.
     *
     * @param context The context containing the block and event data.
     * @param event   The BlockGrowEvent that triggered this behavior.
     */
    @Override
    protected void handleGrowth(SoilBehaviorContext context, BlockGrowEvent event) {
        Block block = context.block();

        // Ensure the block is ageable (e.g., crops) before proceeding
        if (!(block.getBlockData() instanceof Ageable ageable)) return;

        int currentAge = ageable.getAge(); // Current growth stage of the crop
        int maxAge = ageable.getMaximumAge(); // Maximum growth stage of the crop

        // Calculate the growth amount, ensuring at least one stage of growth
        int growthAmount = Math.max(1, (int) Math.ceil(maxAge * growthPercent));
        int newAge = Math.min(currentAge + growthAmount, maxAge); // Cap growth at max age

        // Update the crop's age and apply the changes to the block
        ageable.setAge(newAge);
        block.setBlockData(ageable);

        // Spawn a visual particle effect to indicate accelerated growth
        block.getWorld().spawnParticle(Particle.GLOW, block.getLocation(), 1, 0.5, 0.5, 0.5);

        // Cancel the default growth event to prevent double growth
        event.setCancelled(true);
    }

}
