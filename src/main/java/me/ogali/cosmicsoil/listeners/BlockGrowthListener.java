package me.ogali.cosmicsoil.listeners;

import com.jeff_media.customblockdata.CustomBlockData;
import me.ogali.cosmicsoil.CosmicSoil;
import me.ogali.cosmicsoil.registries.SoilRegistry;
import me.ogali.cosmicsoil.soils.CustomSoil;
import me.ogali.cosmicsoil.soils.contexts.SoilBehaviorContext;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.persistence.PersistentDataType;


/**
 * Handles the BlockGrowEvent to apply custom soil behavior.
 * This method checks if the block below the growing block has custom soil data,
 * retrieves the corresponding soil behavior, and executes it.
 */
public class BlockGrowthListener implements Listener {

    @EventHandler
    public void onGrow(BlockGrowEvent event) {
        // Get the block that triggered the growth event
        Block block = event.getBlock();

        // Retrieve custom block data from the block below the growing block
        CustomBlockData customBlockData = new CustomBlockData(
                event.getBlock().getLocation().clone().add(0, -1, 0).getBlock(),
                CosmicSoil.getInstance()
        );

        // Fetch the soil ID stored in the custom block data
        String soilID = customBlockData.get(CustomSoil.SOIL_KEY, PersistentDataType.STRING);

        // If a soil with the given ID exists, handle its behavior using the soil's handler
        SoilRegistry.getInstance()
                .getSoilById(soilID)
                .ifPresent(customSoil -> customSoil.getBehaviorHandler().handle(new SoilBehaviorContext(customSoil, event, block)));
    }

}