package me.ogali.cosmicsoil.displays;

import me.ogali.cosmicsoil.soils.behaviors.SoilBehavior;
import me.ogali.cosmicsoil.utils.Chat;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.Transformation;
import org.joml.Vector3f;

import java.util.List;

/**
 * Utility class for creating floating text displays that show soil behavior effects.
 * These displays are used to visually represent the active effects of custom soils in the world.
 */
public class BehaviorPropertyDisplay {

    /**
     * Creates a text display at the specified location showing all behavior effects.
     *
     * @param location      The location where the text display should be created
     * @param soilBehaviors The list of soil behaviors to display
     */
    public static void constructDisplay(Location location, List<SoilBehavior> soilBehaviors) {
        // Validate parameters
        if (location == null || location.getWorld() == null || soilBehaviors == null) {
            return;
        }

        // Spawn a text display entity at the specified location
        location.getWorld().spawn(location, TextDisplay.class, textDisplay -> {
            // Create the display content
            String content = formatBehaviorText(soilBehaviors);
            textDisplay.text(Component.text(content));

            // Configure display properties
            configureDisplaySettings(textDisplay);
        });
    }

    /**
     * Formats the behavior list into a readable text display.
     *
     * @param soilBehaviors The behaviors to display
     * @return Formatted text content
     */
    private static String formatBehaviorText(List<SoilBehavior> soilBehaviors) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Chat.colorize("&6&lEffects")).append("\n");

        for (int i = 0; i < soilBehaviors.size(); i++) {
            SoilBehavior soilBehavior = soilBehaviors.get(i);
            stringBuilder.append(Chat.colorize("&f"))
                    .append(soilBehavior.getEffectDescription());

            if (i < soilBehaviors.size() - 1) {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Applies visual settings to the text display.
     *
     * @param textDisplay The text display to configure
     */
    private static void configureDisplaySettings(TextDisplay textDisplay) {
        // Make the display face the player from any direction
        textDisplay.setBillboard(Display.Billboard.CENTER);

        // Ensure the display persists across server restarts
        textDisplay.setPersistent(true);

        // Set the view range to keep displays from being visible from too far away
        textDisplay.setViewRange(1);

        // Scale down the text display to a more appropriate size
        Transformation transformation = textDisplay.getTransformation();
        Vector3f scale = transformation.getScale();
        Vector3f newScale = new Vector3f(
                scale.x / 4,
                scale.y / 3,
                scale.z / 4
        );
        scale.set(newScale);
        textDisplay.setTransformation(transformation);
    }

}