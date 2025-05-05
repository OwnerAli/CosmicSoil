# CosmicSoil

The latest Cosmic Sky update, improving farming mechanics through custom soils with special behaviors and effects.

## Overview

CosmicSoil introduces magical soil variants that affect crop growth in various ways. The plugin uses persistent block data to track custom soil blocks and applies special behaviors when crops grow on them.

## Features

- Custom soil types with unique visual effects
- Behavior system to define how soils interact with crops
- Persistent data storage to maintain soil properties across server restarts
- Visual displays showing soil effects in-game

## Example Implementation

The plugin includes a `BasicGrowthSoil` that accelerates crop growth:
- 50% chance to trigger when crops grow
- When triggered, increases growth by 50% of the normal rate
- Displays particle effects when triggered

## Creating Custom Soils

To create a new custom soil type:

1. Create a class extending `CustomSoil`:

```java
public class YourCustomSoil extends CustomSoil {
    
    public YourCustomSoil() {
        super("your_soil_id", new VisualProperties(Particle.YOUR_PARTICLE),
                new YourSoilBehavior(chance));
    }
    
    @Override
    public Optional<ItemStack> makeCustomItem() {
        ItemBuilder itemBuilder = new ItemBuilder(Material.FARMLAND)
                .setName("&7&lYour Custom Soil")
                .addLoreLines(
                        "&fCustom description",
                        "&6&lEffects"
                );
        
        // Add behavior descriptions to lore
        behaviorHandler.getBehaviors().forEach(soilBehavior -> {
            String[] lines = soilBehavior.getEffectDescription().split("\\n");
            for (int i = 0; i < lines.length; i++) {
                if (i == 0) {
                    itemBuilder.addLoreLine("&f- " + lines[i]);
                } else {
                    itemBuilder.addLoreLine("&f  " + lines[i]);
                }
            }
        });
        
        return super.makeCustomItem(itemBuilder.build());
    }
}
```

2. Register your soil in the `SoilRegistry` class:

```java
SoilRegistry.getInstance().registerSoil(new YourCustomSoil());
```

## Creating Custom Behaviors

To create a new soil behavior:

1. For growth-related behaviors, extend `GrowthBehavior`:

```java
public class YourGrowthBehavior extends GrowthBehavior {
    private final double someProperty;
    
    public YourGrowthBehavior(double chance, double someProperty) {
        super(chance);
        this.someProperty = someProperty;
    }
    
    @Override
    protected void handleGrowth(SoilBehaviorContext context, BlockGrowEvent event) {
        // Implement your custom growth behavior here
        
        // Example: modify the block somehow
        Block block = event.getBlock();
        
        // Play a particle effect
        context.getCustomSoil().getVisualProperties().playParticle(block.getLocation());
        
        // Optionally cancel the original event
        event.setCancelled(true);
    }
    
    @Override
    public String getEffectDescription() {
        return "Your effect description\nwith multiple lines";
    }
}
```

2. For other behavior types, you can extend `TriggerableSoilBehavior` or create a new behavior class hierarchy.

## Usage

Place custom soils like regular farmland. When crops grow on these soils, their special behaviors will activate according to their configured chance.

## Technical Implementation

The plugin uses:
- `CustomBlockData` for persistent soil data storage
- `NamespacedKey` to uniquely identify soil types
- `PersistentDataContainer` to store soil IDs in blocks and items
- Event listeners to detect block placement and crop growth
