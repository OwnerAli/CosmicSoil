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

public class BehaviorPropertyDisplay {

    public static void constructDisplay(Location location, List<SoilBehavior> soilBehaviors) {
        location.getWorld().spawn(location, TextDisplay.class, textDisplay -> {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Chat.colorize("&6&lEffects")).append("\n");
            for (int i = 0; i < soilBehaviors.size(); i++) {
                SoilBehavior soilBehavior = soilBehaviors.get(i);
                stringBuilder.append(Chat.colorize("&f")).append(soilBehavior.getEffectDescription());
                if (i < soilBehaviors.size() - 1) {
                    stringBuilder.append("\n");
                }
            }
            textDisplay.text(Component.text(stringBuilder.toString()));


            textDisplay.setBillboard(Display.Billboard.CENTER);
            textDisplay.setPersistent(true);
            textDisplay.setViewRange(1);

            Transformation transformation = textDisplay.getTransformation();
            Vector3f scale = transformation.getScale();
            Vector3f newScale = new Vector3f(
                    scale.x / 4,
                    scale.y / 3,
                    scale.z / 4
            );
            scale.set(newScale);
            textDisplay.setTransformation(transformation);
        });
    }

}