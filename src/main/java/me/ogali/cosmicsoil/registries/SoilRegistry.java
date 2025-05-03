package me.ogali.cosmicsoil.registries;

import me.ogali.cosmicsoil.soils.CustomSoil;
import me.ogali.cosmicsoil.soils.growth_soil.BasicGrowthSoil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SoilRegistry {
    private final Map<String, CustomSoil> soilMap;

    public SoilRegistry() {
        this.soilMap = new HashMap<>();
        this.registerSoil(new BasicGrowthSoil());
    }

    public void registerSoil(CustomSoil customSoil) {
        soilMap.put(customSoil.getId(), customSoil);
    }

    public Optional<CustomSoil> getSoilById(String id) {
        return Optional.ofNullable(soilMap.get(id));
    }

    //#region Lazy Initialization
    /**
     * Provides a globally accessible instance of the SoilRegistry.
     * This implementation uses the Bill Pugh Singleton Design Pattern,
     * which ensures thread-safe lazy initialization without requiring
     * synchronization blocks or double-checked locking.
     * <p>
     * The instance is only created when the getInstance() method is called
     * for the first time, ensuring that resources are not allocated until needed.
     */
    public static SoilRegistry getInstance() {
        return SoilRegistry.InstanceHolder.instance;
    }

    /**
     * Inner static class responsible for holding the singleton instance.
     * The JVM guarantees that this class will only be loaded when it is
     * referenced for the first time, ensuring lazy initialization.
     */
    private static final class InstanceHolder {
        private static final SoilRegistry instance = new SoilRegistry();
    }
    //#endregion

}
