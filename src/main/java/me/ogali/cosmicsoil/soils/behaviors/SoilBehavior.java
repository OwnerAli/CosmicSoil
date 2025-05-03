package me.ogali.cosmicsoil.soils.behaviors;

import me.ogali.cosmicsoil.soils.contexts.SoilBehaviorContext;

/**
 * Represents a general contract for soil behaviors.
 * Any class implementing this interface must define how the behavior is handled.
 */
public interface SoilBehavior {

    /**
     * Handles the behavior logic for a given soil context.
     *
     * @param context The context containing relevant data for the behavior.
     */
    void handle(SoilBehaviorContext context);

}