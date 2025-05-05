package me.ogali.cosmicsoil.soils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.jetbrains.annotations.Nullable;

public class VisualProperties {
    private final @Nullable Particle particle;

    public VisualProperties(@Nullable Particle particle) {
        this.particle = particle;
    }

    public void playParticle(Location location) {
        if (this.particle == null) return;
        location.getWorld().spawnParticle(particle, location, 1, 0.5, 0.5, 0.5);
    }
}
