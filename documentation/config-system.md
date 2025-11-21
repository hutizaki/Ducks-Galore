# Configuration System

This document explains how the configuration system works in Ducks Galore using Forge's `ForgeConfigSpec`.

## 🎯 Overview

The mod uses **ForgeConfigSpec** for configuration management, providing:
- **Type-safe config values** with validation
- **Automatic file generation** in the `config/` folder
- **Two config types** - Common (shared) and Client (client-only)
- **In-game reloading** without restart (for some values)
- **Comments and documentation** in the config files

## 🏗️ Configuration Structure

### DucksGaloreConfig.java

```java
public class DucksGaloreConfig {
    // The config objects
    public static final CommonConfig COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    static {
        // Setup Common configuration
        final Pair<CommonConfig, ForgeConfigSpec> commonPair = 
            new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON = commonPair.getLeft();
        COMMON_SPEC = commonPair.getRight();

        // Setup Client-side configuration
        final Pair<ClientConfig, ForgeConfigSpec> clientPair = 
            new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT = clientPair.getLeft();
        CLIENT_SPEC = clientPair.getRight();
    }

    public static void register() {
        ModLoadingContext context = ModLoadingContext.get();
        context.registerConfig(ModConfig.Type.COMMON, COMMON_SPEC);
        context.registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC);
    }
}
```

## 📦 Common vs. Client Config

### Common Config
- Shared between client and server
- Affects gameplay mechanics
- Synced in multiplayer
- File: `config/ducksgalore-common.toml`

**Examples:**
- Spawn rates
- Effect durations
- Feature toggles that affect gameplay
- Drop chances

### Client Config
- Client-side only
- Visual and audio settings
- Not synced in multiplayer
- File: `config/ducksgalore-client.toml`

**Examples:**
- Particle effects toggle
- Sound volume
- Render settings
- UI preferences

## 🔧 Adding Config Values

### Common Config Example

```java
public static class CommonConfig {
    public final ForgeConfigSpec.BooleanValue enableGoldenDuckEffects;
    public final ForgeConfigSpec.IntValue effectDurationSeconds;
    public final ForgeConfigSpec.DoubleValue effectMultiplier;
    public final ForgeConfigSpec.ConfigValue<String> specialMode;
    
    public CommonConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Common configuration settings for Ducks Galore")
               .push("common");

        // Boolean value
        enableGoldenDuckEffects = builder
            .comment("Whether golden ducks provide special effects when right-clicked")
            .define("enableGoldenDuckEffects", true);
        
        // Integer value with range
        effectDurationSeconds = builder
            .comment("Duration of effects granted by special ducks in seconds")
            .defineInRange("effectDurationSeconds", 30, 5, 300);
        
        // Double value with range
        effectMultiplier = builder
            .comment("Multiplier for effect strength (1.0 = normal, 2.0 = double strength)")
            .defineInRange("effectMultiplier", 1.0, 0.1, 10.0);
        
        // String value
        specialMode = builder
            .comment("Special mode: 'normal', 'enhanced', or 'creative'")
            .define("specialMode", "normal");
            
        builder.pop();
    }
}
```

### Client Config Example

```java
public static class ClientConfig {
    public final ForgeConfigSpec.BooleanValue enableParticleEffects;
    public final ForgeConfigSpec.DoubleValue duckSoundVolume;
    public final ForgeConfigSpec.EnumValue<RenderMode> renderMode;
    
    public ClientConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Client-side configuration settings for Ducks Galore")
               .push("client");

        // Boolean value
        enableParticleEffects = builder
            .comment("Whether to show particle effects for special ducks")
            .define("enableParticleEffects", true);
        
        // Double value with range
        duckSoundVolume = builder
            .comment("Volume multiplier for duck sounds (1.0 = normal)")
            .defineInRange("duckSoundVolume", 1.0, 0.0, 2.0);
        
        // Enum value
        renderMode = builder
            .comment("Rendering mode: NORMAL, FANCY, or MINIMAL")
            .defineEnum("renderMode", RenderMode.NORMAL);
            
        builder.pop();
    }
    
    public enum RenderMode {
        NORMAL, FANCY, MINIMAL
    }
}
```

## 📖 Config Value Types

### Boolean
```java
ForgeConfigSpec.BooleanValue myBoolean = builder
    .comment("Description of what this does")
    .define("myBoolean", true);  // default value

// Usage
if (DucksGaloreConfig.COMMON.myBoolean.get()) {
    // Do something
}
```

### Integer
```java
ForgeConfigSpec.IntValue myInt = builder
    .comment("Description")
    .defineInRange("myInt", 10, 1, 100);  // default, min, max

// Usage
int value = DucksGaloreConfig.COMMON.myInt.get();
```

### Double
```java
ForgeConfigSpec.DoubleValue myDouble = builder
    .comment("Description")
    .defineInRange("myDouble", 1.5, 0.0, 10.0);  // default, min, max

// Usage
double value = DucksGaloreConfig.COMMON.myDouble.get();
```

### String
```java
ForgeConfigSpec.ConfigValue<String> myString = builder
    .comment("Description")
    .define("myString", "default");

// Usage
String value = DucksGaloreConfig.COMMON.myString.get();
```

### List
```java
ForgeConfigSpec.ConfigValue<List<? extends String>> myList = builder
    .comment("Description")
    .defineList("myList", Arrays.asList("item1", "item2"), obj -> obj instanceof String);

// Usage
List<? extends String> list = DucksGaloreConfig.COMMON.myList.get();
```

### Enum
```java
public enum MyEnum { OPTION1, OPTION2, OPTION3 }

ForgeConfigSpec.EnumValue<MyEnum> myEnum = builder
    .comment("Description")
    .defineEnum("myEnum", MyEnum.OPTION1);

// Usage
MyEnum value = DucksGaloreConfig.COMMON.myEnum.get();
```

## 🗂️ Organizing Config Values

### Using Push/Pop for Categories

```java
public CommonConfig(ForgeConfigSpec.Builder builder) {
    builder.comment("Common configuration").push("common");
    
    // Spawning category
    builder.comment("Duck spawn weights and generation settings").push("spawning");
    
    rubberDuckRarity = builder
        .comment("Rarity of rubber ducks")
        .defineInRange("rubberDuckRarity", 15, 1, 100);
        
    goldenDuckRarity = builder
        .comment("Rarity of golden rubber ducks")
        .defineInRange("goldenDuckRarity", 5, 1, 100);
    
    builder.pop(); // End spawning
    
    // Effects category
    builder.comment("Duck special effects settings").push("effects");
    
    enableGoldenDuckEffects = builder
        .comment("Whether golden ducks provide special effects")
        .define("enableGoldenDuckEffects", true);
        
    effectDurationSeconds = builder
        .comment("Duration of effects in seconds")
        .defineInRange("effectDurationSeconds", 30, 5, 300);
    
    builder.pop(); // End effects
    builder.pop(); // End common
}
```

This creates a structured config file:

```toml
[common]
    [common.spawning]
        # Rarity of rubber ducks
        rubberDuckRarity = 15
        # Rarity of golden rubber ducks
        goldenDuckRarity = 5
    
    [common.effects]
        # Whether golden ducks provide special effects
        enableGoldenDuckEffects = true
        # Duration of effects in seconds
        effectDurationSeconds = 30
```

## 💻 Using Config Values in Code

### Simple Value Check

```java
@Override
public InteractionResult use(BlockState state, Level level, BlockPos pos,
                          Player player, InteractionHand hand, BlockHitResult hit) {
    // Check if effects are enabled
    if (!DucksGaloreConfig.COMMON.enableGoldenDuckEffects.get()) {
        return InteractionResult.PASS;
    }
    
    // Get duration from config
    int durationTicks = DucksGaloreConfig.COMMON.effectDurationSeconds.get() * 20;
    
    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, durationTicks, 1));
    
    return InteractionResult.sidedSuccess(level.isClientSide);
}
```

### Caching Config Values

For performance-critical code, cache config values:

```java
public class MyBlock extends Block {
    private static boolean effectsEnabled;
    private static int effectDuration;
    
    public static void reloadConfig() {
        effectsEnabled = DucksGaloreConfig.COMMON.enableGoldenDuckEffects.get();
        effectDuration = DucksGaloreConfig.COMMON.effectDurationSeconds.get() * 20;
    }
    
    @Override
    public InteractionResult use(...) {
        if (!effectsEnabled) return InteractionResult.PASS;
        
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, effectDuration, 1));
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
```

Call `reloadConfig()` in your mod's config reload event.

### Client-Side Config Usage

```java
// In rendering or client-only code
public void render(...) {
    if (DucksGaloreConfig.CLIENT.enableParticleEffects.get()) {
        // Spawn particles
        level.addParticle(ParticleTypes.HAPPY_VILLAGER, ...);
    }
}

public void playSound(...) {
    double volume = DucksGaloreConfig.CLIENT.duckSoundVolume.get();
    level.playSound(null, pos, sound, SoundSource.BLOCKS, (float)volume, 1.0F);
}
```

## 🔄 Config Reloading

### Handling Config Changes

```java
@Mod.EventBusSubscriber(modid = DucksGalore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigEvents {
    
    @SubscribeEvent
    public static void onConfigReload(ModConfigEvent.Reloading event) {
        if (event.getConfig().getModId().equals(DucksGalore.MOD_ID)) {
            DucksGalore.LOGGER.info("Reloading Ducks Galore config");
            
            // Refresh cached values
            MyBlock.reloadConfig();
            MyOtherClass.reloadConfig();
        }
    }
}
```

## ✅ Config Validation

### Custom Validators

```java
ForgeConfigSpec.IntValue positiveEvenNumber = builder
    .comment("Must be a positive even number")
    .defineInRange("positiveEvenNumber", 10, 2, 100, 
        value -> value > 0 && value % 2 == 0);  // Custom validator
```

### List with Validation

```java
ForgeConfigSpec.ConfigValue<List<? extends String>> validBlocks = builder
    .comment("List of valid block registry names")
    .defineList("validBlocks", 
        Arrays.asList("minecraft:stone", "minecraft:dirt"),
        obj -> {
            if (!(obj instanceof String)) return false;
            String str = (String) obj;
            return ResourceLocation.isValidResourceLocation(str);
        });
```

## 🎨 Advanced Patterns

### Dynamic Config-Based Registration

```java
// In a config class
public final ForgeConfigSpec.ConfigValue<List<? extends String>> enabledDuckTypes;

enabledDuckTypes = builder
    .comment("Which duck types should be enabled")
    .defineList("enabledDuckTypes",
        Arrays.asList("rubber", "golden", "gold_ore"),
        obj -> obj instanceof String);

// In registration code
public static void registerDucks() {
    List<? extends String> enabled = DucksGaloreConfig.COMMON.enabledDuckTypes.get();
    
    if (enabled.contains("rubber")) {
        // Register rubber duck
    }
    if (enabled.contains("golden")) {
        // Register golden duck
    }
    // etc.
}
```

### Config-Dependent Features

```java
public static void setupFeatures() {
    if (DucksGaloreConfig.COMMON.enableAdvancedMode.get()) {
        // Register advanced features
        registerAdvancedDucks();
        registerSpecialEffects();
    } else {
        // Basic mode only
        registerBasicDucks();
    }
}
```

## 📋 Config File Example

After running the mod, `config/ducksgalore-common.toml` will look like:

```toml
#Common configuration settings for Ducks Galore
[common]

    #Duck spawn weights and generation settings
    [common.spawning]
        #Rarity of rubber ducks when generated in loot chests (higher = more common)
        #Range: 1 ~ 100
        rubberDuckRarity = 15
        #Rarity of golden rubber ducks when generated in loot chests (higher = more common)
        #Range: 1 ~ 100
        goldenDuckRarity = 5
        #Rarity of gold ore rubber ducks when generated from mining gold ore (higher = more common)
        #Range: 1 ~ 100
        goldOreDuckRarity = 8

    #Duck special effects settings
    [common.effects]
        #Whether golden ducks provide special effects when right-clicked
        enableGoldenDuckEffects = true
        #Whether gold ore ducks provide special effects when right-clicked
        enableGoldOreDuckEffects = true
        #Duration of effects granted by special ducks in seconds
        #Range: 5 ~ 300
        effectDurationSeconds = 30
```

## 🐛 Troubleshooting

### Config doesn't generate
- Ensure `register()` is called in mod constructor
- Check for errors in config class static initializer
- Verify `ModConfig.Type` matches config type

### Config values don't update
- Make sure you're calling `.get()` each time
- Don't cache values in static final fields
- Implement config reload handling

### Invalid value errors
- Check range constraints match actual possible values
- Verify validators allow valid inputs
- Test edge cases (min, max values)

### Multiplayer sync issues
- Use COMMON config for gameplay-affecting values
- CLIENT config is never synced
- Some config changes require server restart

## 📚 Best Practices

1. **Use descriptive names** - `effectDurationSeconds` not `duration`
2. **Add helpful comments** - Users read these in the config file
3. **Provide good defaults** - Config should work well without changes
4. **Use appropriate types** - Int for counts, Double for multipliers
5. **Set reasonable ranges** - Prevent invalid or game-breaking values
6. **Group related settings** - Use push/pop for organization
7. **Document in-game effects** - Explain what each setting does
8. **Test config changes** - Verify the config actually works

## 📚 See Also

- [Architecture Overview](architecture-overview.md) - How config fits into the mod
- [How to Add a Block](how-to-add-a-block.md) - Using config in blocks
- [Block Behavior Patterns](block-behavior-patterns.md) - Config-based behaviors

---

**Key Takeaways:**
1. Use COMMON config for gameplay, CLIENT for visuals
2. Always call `.get()` to read config values
3. Add comments and validation
4. Organize with push/pop categories
5. Test config changes work as expected

