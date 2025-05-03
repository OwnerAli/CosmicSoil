package me.ogali.cosmicsoil.soils.contexts;

import org.bukkit.block.Block;
import org.bukkit.event.Event;

/**
 * Represents the context in which a soil behavior is executed.
 * This record encapsulates the event that triggered the behavior
 * and the block associated with the behavior.
 *
 * @param event The event that triggered the soil behavior.
 * @param block The block associated with the soil behavior.
 */
public record SoilBehaviorContext(Event event, Block block) { }