package me.ogali.cosmicsoil.listeners;

import com.jeff_media.customblockdata.CustomBlockData;
import me.ogali.cosmicsoil.CosmicSoil;
import me.ogali.cosmicsoil.registries.SoilRegistry;
import me.ogali.cosmicsoil.soils.CustomSoil;
import me.ogali.cosmicsoil.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataType;

/**
 * Listens for block placement events to handle custom soil blocks.
 * This listener attaches persistent data to placed blocks that are identified
 * as custom soil types.
 */
public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        event.getPlayer().getInventory().addItem(SoilRegistry.getInstance().getSoilById("basic_growth").orElseThrow().makeCustomItem(
                new ItemBuilder(Material.FARMLAND).setName("&7Basic Growth Soil").glowing().build()).orElseThrow());

        // Try to extract soil ID from the item the player is placing
        CustomSoil.getSoilIdFromItem(event.getItemInHand())
                .ifPresent(id -> {
                    // If a valid soil ID is found, create persistent data storage for this block
                    CustomBlockData customBlockData = new CustomBlockData(event.getBlockPlaced(),
                            CosmicSoil.getInstance());

                    // Store the soil ID as a string in the block's persistent data
                    // This allows the block to maintain its custom soil identity
                    // even after server restarts
                    customBlockData.set(CustomSoil.SOIL_KEY, PersistentDataType.STRING, id);
                });

        // Hologram showing applied effects
    }

}
