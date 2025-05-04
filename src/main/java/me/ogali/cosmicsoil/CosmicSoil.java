package me.ogali.cosmicsoil;

import com.jeff_media.customblockdata.CustomBlockData;
import lombok.Getter;
import me.ogali.cosmicsoil.listeners.BlockBreakListener;
import me.ogali.cosmicsoil.listeners.BlockGrowthListener;
import me.ogali.cosmicsoil.listeners.BlockPlaceListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class CosmicSoil extends JavaPlugin {

    @Getter
    public static CosmicSoil instance;

    @Getter
    public Random random;

    @Override
    public void onEnable() {
        instance = this;
        this.random = new Random();
        this.registerListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(new BlockPlaceListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new BlockGrowthListener(), this);
        CustomBlockData.registerListener(this);
    }

}
