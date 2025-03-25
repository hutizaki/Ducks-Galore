# Ducks Galore Framework Integration Guide

This document provides guidance on integrating and using the recommended frameworks for the Ducks Galore mod. This guide includes proper version references and implementation details based on testing.

## Base API: Forge 47.2.6

Forge 47.2.6 is the core mod loading API we're using for Minecraft 1.20.1. This version is stable and well-supported.

### Configuration:
```gradle
minecraft_version=1.20.1
forge_version=47.2.6
```

## Registry System Options

### Option 1: DeferredRegister (Built-in with Forge)

The simplest approach is to use Forge's built-in DeferredRegister system, which provides a clean way to register objects.

```java
// In main mod class
public static final DeferredRegister<Block> BLOCKS = 
    DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
public static final DeferredRegister<Item> ITEMS = 
    DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

// Register with event bus
BLOCKS.register(modEventBus);
ITEMS.register(modEventBus);

// Creating a block registration
public static final RegistryObject<Block> MY_BLOCK = 
    BLOCKS.register("my_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));

// Creating a block item
public static final RegistryObject<Item> MY_BLOCK_ITEM = 
    ITEMS.register("my_block", () -> new BlockItem(MY_BLOCK.get(), new Item.Properties()));
```

### Option 2: Registrate (More Advanced)

For more complex registration needs with automatic datagen support, Registrate can be used. However, ensure you have the correct version:

```gradle
// Add repository
repositories {
    maven { url = 'https://maven.tterrag.com/' }
}

// Add dependencies - Check for the latest version compatible with your Minecraft version
dependencies {
    implementation fg.deobf("com.tterrag.registrate:Registrate:MC1.20.1-1.3.3")
    jarJar(group: 'com.tterrag.registrate', name: 'Registrate', version: "[MC1.20.1,MC1.20.2)")
}
```

Basic usage:
```java
// In main mod class
public static final Registrate REGISTRATE = Registrate.create(MOD_ID);

// Register a block with automatic item
public static final RegistryEntry<MyBlock> MY_BLOCK = REGISTRATE
    .block("my_block", MyBlock::new)
    .defaultItem()
    .register();
```

## Animation System: GeckoLib

GeckoLib provides advanced animation capabilities. For Minecraft 1.20.1, use version compatible with Forge 47.2.6:

```gradle
// Add repository
repositories {
    maven { url = 'https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/' }
}

// Add dependencies - use Maven version format
dependencies {
    implementation fg.deobf("software.bernie.geckolib:geckolib-forge-${minecraft_version}:4.2.2")
    jarJar(group: 'software.bernie.geckolib', name: "geckolib-forge-${minecraft_version}", version: "[4.2.2,5.0.0)")
}
```

## Config System: ForgeConfigSpec

ForgeConfigSpec is the standard configuration system for Forge mods:

```java
public class ModConfig {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final CommonConfig COMMON;

    static {
        final Pair<CommonConfig, ForgeConfigSpec> commonPair = new ForgeConfigSpec.Builder()
                .configure(CommonConfig::new);
        COMMON = commonPair.getLeft();
        COMMON_SPEC = commonPair.getRight();
    }

    public static void register() {
        ModLoadingContext context = ModLoadingContext.get();
        context.registerConfig(ModConfig.Type.COMMON, COMMON_SPEC);
    }

    public static class CommonConfig {
        public final ForgeConfigSpec.BooleanValue enableFeature;
        
        public CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.comment("Common configuration settings")
                   .push("common");
            
            enableFeature = builder
                .comment("Whether to enable the feature")
                .define("enableFeature", true);
                
            builder.pop();
        }
    }
}
```

## Data Generation

Forge's data generation system:

```java
// In main mod class
modEventBus.addListener(this::gatherData);

private void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
    
    if (event.includeClient()) {
        // Client asset providers (models, textures, sounds)
        generator.addProvider(event.includeClient(), 
            new SoundProvider(generator.getPackOutput(), existingFileHelper));
    }
    
    if (event.includeServer()) {
        // Server data providers (recipes, loot tables, tags)
        generator.addProvider(event.includeServer(), 
            new RecipeProvider(generator.getPackOutput()));
    }
}
```

## DataGenDSL (Data Generation DSL)

While Forge provides a functional data generation system, you can enhance it with a more expressive DSL (Domain-Specific Language) for cleaner, more maintainable data generation code.

### Custom DSL Implementation:

```java
public class DataGenDSL {
    // DSL for recipe generation
    public static class RecipeDSL {
        private final Consumer<FinishedRecipe> consumer;
        
        public RecipeDSL(Consumer<FinishedRecipe> consumer) {
            this.consumer = consumer;
        }
        
        public RecipeDSL shaped(String pattern1, String pattern2, String pattern3) {
            // Builder for shaped recipe
            return this;
        }
        
        public RecipeDSL key(char key, Item item) {
            // Add key to recipe
            return this;
        }
        
        public void result(Item result, int count) {
            // Finalize and register recipe
        }
    }
    
    // Example usage in a data generator
    public static class RecipeGen extends RecipeProvider {
        @Override
        protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
            RecipeDSL dsl = new RecipeDSL(consumer);
            
            dsl.shaped("AAA", "ABA", "AAA")
               .key('A', Items.GOLD_INGOT)
               .key('B', Items.DIAMOND)
               .result(AllItems.MY_SPECIAL_ITEM.get(), 1);
        }
    }
}
```

## KubeJS Integration (Optional Layer)

KubeJS allows for runtime scripting and modification of game content, making your mod more flexible for modpack developers.

### Setup:

```gradle
repositories {
    maven { url = "https://maven.saps.dev/minecraft" } // For KubeJS
}

dependencies {
    // KubeJS for Minecraft 1.20.1
    implementation fg.deobf("dev.latvian.mods:kubejs-forge:2000.6.3-build.45")
    
    // Optional KubeJS addons
    implementation fg.deobf("dev.latvian.mods:kubejs-create-forge:2000.6.0-build.5")
}
```

### KubeJS Integration Example:

```java
// Register custom KubeJS bindings
@SubscribeEvent
public static void onKubeJSRegistration(RegisterKubeJSEvent event) {
    // Register a custom global binding
    event.registerGlobalBinding("myMod", MyModAPI.class);
    
    // Register a custom event type
    event.registerEventType("myModEvent", MyCustomEvent.class);
}

// API class exposed to KubeJS
public class MyModAPI {
    public static void registerCustomDuck(String id, Properties props) {
        // Register a custom duck variant from KubeJS scripts
    }
}
```

This allows modpack developers to extend your mod with scripts like:

```javascript
// In kubejs/startup_scripts/ducks.js
onEvent('myModEvent', event => {
    // Create a custom duck variant
    myMod.registerCustomDuck('diamond_duck', {
        material: 'diamond',
        effects: ['speed', 'jump_boost'],
        quackVolume: 2.0
    });
});
```

## Troubleshooting

1. **Dependency Resolution Issues**: If you encounter "Could not find artifact" errors, check:
   - The repository URL is correct
   - The version format is compatible with Maven/Gradle
   - Try using a specific version instead of a version range

2. **Forge Version Compatibility**: Ensure all libraries are compatible with your Forge version. Test each separately before combining.

3. **JarJar Configuration**: When using JarJar to embed dependencies, use Maven version format for version ranges:
   ```gradle
   jarJar(group: 'group.id', name: 'artifact-name', version: "[1.0.0,2.0.0)")
   ```

4. **Data Generation Errors**: If data generation fails, try running with just basic providers first, then add more complex ones.

## Implementation Strategy

1. **Start with basic Forge registration** using DeferredRegister
2. **Add ForgeConfigSpec** for configuration
3. **Set up basic Data Generation** for recipes, models, etc.
4. **Add GeckoLib** if animation is needed
5. **Consider Registrate** for more complex registration needs once basic setup is working
6. **Add KubeJS support** as a final layer for maximum modpack compatibility

By starting simple and incrementally adding features, you can avoid complex dependency issues and have a more robust mod. 