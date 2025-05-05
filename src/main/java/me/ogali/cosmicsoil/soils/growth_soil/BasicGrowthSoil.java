package me.ogali.cosmicsoil.soils.growth_soil;

import me.ogali.cosmicsoil.soils.CustomSoil;
import me.ogali.cosmicsoil.soils.VisualProperties;
import me.ogali.cosmicsoil.soils.behaviors.triggerable_behaviors.growth_behaviors.AcceleratedGrowthBehavior;
import me.ogali.cosmicsoil.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

// Represents a basic growth soil type with specific behavior.
// This soil type has a 50% chance to trigger accelerated growth behavior.
public class BasicGrowthSoil extends CustomSoil {

    /**
     * Constructs a BasicGrowthSoil instance with predefined behavior.
     **/
    public BasicGrowthSoil() {
        super("basic_growth", new VisualProperties(Particle.GLOW),
                new AcceleratedGrowthBehavior(0.5, 0.5));
    }

    @Override
    public Optional<ItemStack> makeCustomItem() {
        // Create a custom item representing this soil type
        ItemBuilder itemBuilder = new ItemBuilder(Material.FARMLAND)
                .setName("&7&lBasic Growth Soil")
                .glowing()
                .addLoreLines(
                        "&fInfused with cosmic energy, this",
                        "&fsoil nurtures your plants,",
                        "&funlocking their hidden potential.",
                        " ",
                        "&6&lEffects"
                );

        // Add behavior descriptions to the item lore
        behaviorHandler.getBehaviors().forEach(soilBehavior -> {
            // Split multi-line descriptions by newline character
            String[] lines = soilBehavior.getEffectDescription().split("\\n");

            // Format the first line with bullet point, subsequent lines indented
            for (int i = 0; i < lines.length; i++) {
                if (i == 0) {
                    itemBuilder.addLoreLine("&f- " + lines[i]);
                } else {
                    itemBuilder.addLoreLine("&f  " + lines[i]);
                }
            }
        });

        // Use parent class method to finalize the custom item creation
        return super.makeCustomItem(itemBuilder.build());
    }

}
