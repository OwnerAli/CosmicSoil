package me.ogali.cosmicsoil.soils;

import lombok.Getter;
import me.ogali.cosmicsoil.CosmicSoil;
import me.ogali.cosmicsoil.soils.behaviors.SoilBehavior;
import me.ogali.cosmicsoil.soils.handler.SoilBehaviorHandler;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Optional;

@Getter
public abstract class CustomSoil {
    // Key used to store and retrieve soil-specific data in persistent storage
    public static final NamespacedKey SOIL_KEY = new NamespacedKey(CosmicSoil.getInstance(), "custom-soil");

    // Unique identifier for this custom soil type
    private final String id;

    // Handler responsible for managing and executing soil behaviors
    private final SoilBehaviorHandler handler;

    public CustomSoil(String id, SoilBehavior... soilBehaviors) {
        // Assign the unique identifier for this custom soil type
        this.id = id;

        // Initialize the behavior handler with the provided soil behaviors
        this.handler = new SoilBehaviorHandler(soilBehaviors);
    }

    /**
     * Retrieves the soil ID from the given item stack, if present.
     *
     * @param itemStack the item stack to extract the soil ID from
     * @return an Optional containing the soil ID, or empty if not found
     */
    public static Optional<String> getSoilIdFromItem(ItemStack itemStack) {
        // Return empty if the item has no metadata
        if (itemStack.getItemMeta() == null) return Optional.empty();

        // Retrieve the metadata of the item
        ItemMeta itemMeta = itemStack.getItemMeta();

        // Access the persistent data container from the item's metadata
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

        // Retrieve the soil ID stored in the persistent data container, if present
        return Optional.ofNullable(persistentDataContainer.get(SOIL_KEY, PersistentDataType.STRING));
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

}
