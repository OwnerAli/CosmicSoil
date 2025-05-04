package me.ogali.cosmicsoil.soils.contexts;

import lombok.Getter;
import me.ogali.cosmicsoil.soils.CustomSoil;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class SoilBehaviorContext {
    private final @NotNull CustomSoil customSoil;
    private final @Nullable Event event;
    private final @Nullable Block block;

    /**
     * Represents the context in which a soil behavior is executed.
     * This record encapsulates the event that triggered the behavior
     * and the block associated with the behavior.
     *
     * @param event The event that triggered the soil behavior.
     * @param block The block associated with the soil behavior.
     */
    public SoilBehaviorContext(@NotNull CustomSoil customSoil, @Nullable Event event, @Nullable Block block) {
        this.customSoil = customSoil;
        this.event = event;
        this.block = block;
    }

}

