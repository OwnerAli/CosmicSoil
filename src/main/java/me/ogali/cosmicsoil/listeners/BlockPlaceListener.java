package me.ogali.cosmicsoil.listeners;

import me.ogali.cosmicsoil.soils.CustomSoil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Listens for block placement events to handle custom soil blocks.
 * This listener attaches persistent data to placed blocks that are identified
 * as custom soil types.
 */
public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        // Try to extract soil ID from the item the player is placing
        CustomSoil.getSoilFromItem(event.getItemInHand())
                .ifPresent(customSoil -> customSoil.place(event.getBlockPlaced()));
    }

}
