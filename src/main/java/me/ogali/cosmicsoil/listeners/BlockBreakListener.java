package me.ogali.cosmicsoil.listeners;

import com.jeff_media.customblockdata.CustomBlockData;
import me.ogali.cosmicsoil.CosmicSoil;
import me.ogali.cosmicsoil.registries.SoilRegistry;
import me.ogali.cosmicsoil.soils.CustomSoil;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        String id = new CustomBlockData(block, CosmicSoil.getInstance())
                .get(CustomSoil.SOIL_KEY, PersistentDataType.STRING);
        SoilRegistry.getInstance()
                .getSoilById(id)
                .ifPresent(customSoil -> {
                    event.setDropItems(false);

                    World world = block.getWorld();
                    Location blockLocation = block.getLocation();
                    ItemStack soilItem = customSoil.makeCustomItem().orElseThrow();

                    world.dropItem(blockLocation, soilItem);
                    world.getNearbyEntitiesByType(TextDisplay.class, blockLocation, -0.8, 4)
                            .stream().findFirst().ifPresent(Entity::remove);
                });
    }

}
