package me.ogali.cosmicsoil.soils.handler;

import me.ogali.cosmicsoil.soils.behaviors.SoilBehavior;
import me.ogali.cosmicsoil.soils.contexts.SoilBehaviorContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the execution of soil behaviors by dispatching them to a list of handlers.
 * This class ensures that all registered behaviors are executed in the context provided.
 */
public final class SoilBehaviorHandler {
    // List of behaviors to be executed
    private final List<SoilBehavior> behaviors;

    /**
     * Constructs a handler with a variable number of behaviors.
     *
     * @param behaviors The behaviors to be managed by this handler.
     */
    public SoilBehaviorHandler(SoilBehavior... behaviors) {
        // Initialize the list with the provided behaviors
        this.behaviors = new ArrayList<>(List.of(behaviors));
    }

    /**
     * Executes all registered behaviors using the provided context.
     *
     * @param context The context containing data required for behavior execution.
     */
    public void handle(SoilBehaviorContext context) {
        // Iterate through each behavior and execute it with the given context
        behaviors.forEach(behavior -> behavior.handle(context));
    }

}