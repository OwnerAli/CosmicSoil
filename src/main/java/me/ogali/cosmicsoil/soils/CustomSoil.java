package me.ogali.cosmicsoil.soils;

import com.jeff_media.customblockdata.CustomBlockData;
import lombok.Getter;
import me.ogali.cosmicsoil.CosmicSoil;
import me.ogali.cosmicsoil.displays.BehaviorPropertyDisplay;
import me.ogali.cosmicsoil.registries.SoilRegistry;
import me.ogali.cosmicsoil.soils.behaviors.SoilBehavior;
import me.ogali.cosmicsoil.soils.handler.SoilBehaviorHandler;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Optional;

@Getter
public abstract class CustomSoil {
    // Key used to store and retrieve soil-specific data in persistent storage
    public static final NamespacedKey SOIL_KEY = new NamespacedKey(CosmicSoil.getInstance(), "custom-soil");

    // Handler responsible for managing and executing soil behaviors
    protected final SoilBehaviorHandler behaviorHandler;
    private final String id;
    // Visual properties like particles, text, sounds, etc.
    private final VisualProperties visualProperties;

    /**
     * Constructor to create a custom soil type with a unique ID and associated behaviors.
     *
     * @param id            the unique identifier for this custom soil type
     * @param soilBehaviors the behaviors associated with this custom soil type
     */
    public CustomSoil(String id, VisualProperties visualProperties, SoilBehavior... soilBehaviors) {
        this.id = id;
        // Initialize the behavior handler with the provided soil behaviors
        this.behaviorHandler = new SoilBehaviorHandler(soilBehaviors);
        this.visualProperties = visualProperties;
    }

    public void place(Block block) {
        // If a valid soil ID is found, create persistent data storage for this block
        CustomBlockData customBlockData = new CustomBlockData(block,
                CosmicSoil.getInstance());

        // Store the soil ID as a string in the block's persistent data
        // This allows the block to maintain its custom soil identity
        // even after server restarts
        customBlockData.set(CustomSoil.SOIL_KEY, PersistentDataType.STRING, id);

        // Spawn text display showing the effects of the soil
        BehaviorPropertyDisplay.constructDisplay(block.getLocation().clone().add(0.5, 2, 0.5),
                behaviorHandler.getBehaviors());
    }

    /**
     * Adds the custom soil ID to the given item stack.
     *
     * @param itemStack the item stack to modify with the custom soil ID
     * @return an Optional containing the modified item stack, or empty if the item has no metadata
     */
    public Optional<ItemStack> makeCustomItem(ItemStack itemStack) {
        // Return empty if the item has no metadata
        if (itemStack.getItemMeta() == null) return Optional.empty();

        // Retrieve the metadata of the item
        ItemMeta itemMeta = itemStack.getItemMeta();

        // Access the persistent data container from the item's metadata
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

        // Store the custom soil ID in the persistent data container
        persistentDataContainer.set(SOIL_KEY, PersistentDataType.STRING, id);

        // Apply the updated metadata back to the item
        itemStack.setItemMeta(itemMeta);

        // Return the modified item wrapped in an Optional
        return Optional.of(itemStack);
    }

    public abstract Optional<ItemStack> makeCustomItem();

    public static Optional<CustomSoil> getSoilFromItem(ItemStack itemStack) {
        // Return empty if the item has no metadata
        if (itemStack.getItemMeta() == null) return Optional.empty();

        // Retrieve the metadata of the item
        ItemMeta itemMeta = itemStack.getItemMeta();

        // Access the persistent data container from the item's metadata
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

        // Retrieve the soil ID stored in the persistent data container, if present
        Optional<String> id = Optional.ofNullable(persistentDataContainer.get(SOIL_KEY, PersistentDataType.STRING));

        if (id.isEmpty()) return Optional.empty();

        return SoilRegistry.getInstance()
                .getSoilById(id.get());
    }

}
