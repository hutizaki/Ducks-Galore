# Registration System

This document explains how the registration system works in Ducks Galore and how to properly register blocks, items, sounds, and other game objects.

## 🎯 Overview

The mod uses Forge's **DeferredRegister** system for all registrations. This provides:
- **Thread-safe registration** during mod loading
- **Load-order independence** - no need to worry about initialization order
- **Type safety** - compile-time checking for registry types
- **Lazy evaluation** - objects created when needed, not during static init

## 🏗️ The DeferredRegister Pattern

### Basic Concept

```java
// 1. Create a DeferredRegister
public static final DeferredRegister<Block> BLOCKS = 
    DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

// 2. Register an object
public static final RegistryObject<Block> MY_BLOCK = 
    BLOCKS.register("my_block", () -> new MyBlock(...));

// 3. Register the DeferredRegister with the event bus
BLOCKS.register(modEventBus);

// 4. Access the registered object
Block block = MY_BLOCK.get();
```

### Why Use RegistryObject?

`RegistryObject<T>` is a **lazy wrapper**:

```java
// During static initialization (SAFE)
public static final RegistryObject<Block> MY_BLOCK = BLOCKS.register(...);

// Later, when needed (SAFE)
Block actualBlock = MY_BLOCK.get();

// DO NOT DO THIS (UNSAFE - may be null)
public static final Block MY_BLOCK = new MyBlock(...);
```

💡 **Key Point:** Never create game objects directly in static initializers. Always use `RegistryObject`.

## 📦 Centralized Registration

Ducks Galore uses a **centralized approach** - all `DeferredRegister` instances are created in one place:

### DucksGalore.java (The Central Hub)

```java
@Mod(DucksGalore.MOD_ID)
public class DucksGalore {
    public static final String MOD_ID = "ducksgalore";
    
    // All DeferredRegister instances
    public static final DeferredRegister<Block> BLOCKS = 
            DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = 
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = 
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    
    public DucksGalore() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        // Register all DeferredRegister instances
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        SOUND_EVENTS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        
        // Initialize registration classes
        AllBlocks.register();
        AllItems.register();
        AllSoundEvents.register();
        AllCreativeModeTabs.register();
    }
}
```

### Benefits of Centralization

1. **Single Source of Truth** - All registries in one place
2. **Easy to Add New Registry Types** - Just add to `DucksGalore.java`
3. **Consistent Pattern** - Every "All*" class follows the same structure
4. **No Circular Dependencies** - Clear dependency direction

## 🔨 Block Registration

### In AllBlocks.java

```java
public class AllBlocks {
    // Reference the centralized registry
    private static final DeferredRegister<Block> BLOCKS = DucksGalore.BLOCKS;

    // Register blocks
    public static final RegistryObject<Block> RUBBER_DUCK =
            BLOCKS.register("rubber_duck",
                    () -> new RubberDuckBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_YELLOW)
                            .strength(0.05F)
                            .noOcclusion()
                    ));

    public static final RegistryObject<Block> GOLDEN_RUBBER_DUCK =
            BLOCKS.register("golden_rubber_duck",
                    () -> new GoldenRubberDuckBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.GOLD)
                            .requiresCorrectToolForDrops()
                            .strength(3.05F, 3.0F)
                            .lightLevel(state -> 8)
                            .noOcclusion()
                            .pushReaction(PushReaction.BLOCK)
                    ));

    public static void register() {
        DucksGalore.LOGGER.info("Ducks Galore blocks initialized");
    }
}
```

### Registry Name Guidelines

- **Use snake_case** - `rubber_duck` not `rubberDuck` or `RubberDuck`
- **Be descriptive** - `golden_rubber_duck` not `gold_duck`
- **Match file names** - Registry name should match texture/model file names
- **Keep consistent** - Block, item, and resource files all use the same name

## 🎒 Item Registration

### Block Items (Most Common)

```java
public class AllItems {
    private static final DeferredRegister<Item> ITEMS = DucksGalore.ITEMS;
    
    // Automatically create block items
    public static final RegistryObject<Item> RUBBER_DUCK_ITEM = 
            fromBlock(AllBlocks.RUBBER_DUCK);
    
    public static final RegistryObject<Item> GOLDEN_RUBBER_DUCK_ITEM = 
            fromBlock(AllBlocks.GOLDEN_RUBBER_DUCK);
    
    /**
     * Helper method to create an item from a block
     */
    private static RegistryObject<Item> fromBlock(RegistryObject<? extends Block> block) {
        return ITEMS.register(block.getId().getPath(), 
                () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
```

### Standalone Items

For items that aren't blocks:

```java
public static final RegistryObject<Item> DUCK_FEATHER = 
        ITEMS.register("duck_feather",
                () -> new Item(new Item.Properties()
                        .stacksTo(64)
                        .rarity(Rarity.COMMON)));

public static final RegistryObject<Item> SPECIAL_DUCK_WAND = 
        ITEMS.register("special_duck_wand",
                () -> new SpecialDuckWandItem(new Item.Properties()
                        .stacksTo(1)
                        .rarity(Rarity.RARE)
                        .durability(100)));
```

### Item Properties

Common item properties:

```java
new Item.Properties()
    .stacksTo(64)                    // Max stack size (default 64)
    .stacksTo(1)                     // Non-stackable (tools, weapons)
    .durability(250)                 // Durability (makes it non-stackable)
    .rarity(Rarity.RARE)            // Color in inventory (COMMON, UNCOMMON, RARE, EPIC)
    .fireResistant()                 // Doesn't burn in lava/fire
    .food(FoodProperties...)         // Makes it edible
```

## 🔊 Sound Registration

Sounds use a custom builder pattern (see [Sound System](sound-system.md)):

```java
public class AllSoundEvents {
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DucksGalore.SOUND_EVENTS;
    public static final Map<ResourceLocation, SoundEntry> ALL = new HashMap<>();
    
    public static final SoundEntry RUBBER_DUCK_QUACK = create("rubber_duck_quack")
        .subtitle("Rubber duck quacks")
        .category(SoundSource.BLOCKS)
        .build();
        
    private static SoundEntryBuilder create(String name) {
        return new SoundEntryBuilder(name);
    }
}
```

The builder internally calls `SOUND_EVENTS.register()`.

## 📑 Creative Tab Registration

Creative tabs are registered using the new 1.20+ system:

```java
public class AllCreativeModeTabs {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
            DucksGalore.CREATIVE_MODE_TABS;
    
    public static final RegistryObject<CreativeModeTab> MAIN_TAB = 
            CREATIVE_MODE_TABS.register("main_tab",
                () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.ducksgalore.main_tab"))
                    .icon(() -> new ItemStack(AllItems.GOLDEN_RUBBER_DUCK_ITEM.get()))
                    .displayItems((parameters, output) -> 
                        AllItems.getAllItems().forEach(item -> output.accept(item.get())))
                    .build()
            );
    
    public static void register() {
        DucksGalore.LOGGER.info("Ducks Galore creative mode tabs initialized");
    }
}
```

### Creative Tab Builder Options

```java
CreativeModeTab.builder()
    .title(Component)                          // Tab name (translated)
    .icon(() -> ItemStack)                     // Tab icon
    .displayItems((params, output) -> {...})   // Items to show
    .withTabsBefore(CreativeModeTabs.COMBAT)   // Tab ordering
    .withSearchBar()                           // Add search bar (like "Search Items")
    .backgroundSuffix("new_tab.png")           // Custom background
    .build();
```

## 🎨 Registration Order

Registration happens in this order:

```
1. Static initialization of All* classes
   └─> RegistryObject fields are created
   
2. DucksGalore constructor
   └─> DeferredRegister.register(eventBus) for each registry
   └─> All*.register() methods called for logging
   
3. Forge registration events
   └─> RegisterEvent for blocks, items, sounds, etc.
   └─> DeferredRegister processes and registers objects
   
4. Objects are now available via .get()
```

## 🔍 Accessing Registered Objects

### In Code

```java
// Get the actual block
Block rubberDuck = AllBlocks.RUBBER_DUCK.get();

// Get the actual item
Item rubberDuckItem = AllItems.RUBBER_DUCK_ITEM.get();

// Create ItemStack
ItemStack stack = new ItemStack(AllItems.RUBBER_DUCK_ITEM.get());

// Check if a block matches
if (state.is(AllBlocks.GOLDEN_RUBBER_DUCK.get())) {
    // Do something
}
```

### In Resources (JSON)

Use the full resource location:

```json
{
  "name": "ducksgalore:rubber_duck"
}
```

## 🧪 Custom Registry Objects

To register other types of objects, follow the same pattern:

### Example: Custom Particle Types

```java
public class AllParticleTypes {
    private static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = 
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, DucksGalore.MOD_ID);
    
    public static final RegistryObject<SimpleParticleType> DUCK_SPLASH = 
            PARTICLE_TYPES.register("duck_splash",
                    () -> new SimpleParticleType(false));
    
    public static void register(IEventBus modEventBus) {
        PARTICLE_TYPES.register(modEventBus);
    }
}
```

Then add to `DucksGalore.java`:

```java
public DucksGalore() {
    // ...
    AllParticleTypes.register(modEventBus);
}
```

## ⚠️ Common Mistakes

### 1. Calling .get() Too Early

```java
// ❌ WRONG - may be null during static init
public static final Block BLOCK = BLOCKS.register(...).get();

// ✅ CORRECT - lazy evaluation
public static final RegistryObject<Block> BLOCK = BLOCKS.register(...);
// Later:
Block actualBlock = BLOCK.get();
```

### 2. Creating Objects Directly

```java
// ❌ WRONG - not registered
public static final Block MY_BLOCK = new MyBlock(...);

// ✅ CORRECT - registered via DeferredRegister
public static final RegistryObject<Block> MY_BLOCK = 
    BLOCKS.register("my_block", () -> new MyBlock(...));
```

### 3. Forgetting to Register DeferredRegister

```java
// ❌ WRONG - registry never registered with event bus
public static final DeferredRegister<Block> BLOCKS = 
    DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

// ✅ CORRECT - registered in constructor
public DucksGalore() {
    BLOCKS.register(modEventBus);
}
```

### 4. Wrong Registry Location Format

```java
// ❌ WRONG - capital letters, spaces
BLOCKS.register("My Block", ...);
BLOCKS.register("myBlock", ...);

// ✅ CORRECT - lowercase, underscores
BLOCKS.register("my_block", ...);
```

### 5. Inconsistent Naming

```java
// ❌ CONFUSING - different names for same thing
public static final RegistryObject<Block> RUBBER_DUCK = 
    BLOCKS.register("yellow_duck", ...);

// ✅ CORRECT - consistent naming
public static final RegistryObject<Block> RUBBER_DUCK = 
    BLOCKS.register("rubber_duck", ...);
```

## 📋 Registration Checklist

When adding new content:

- [ ] Block registered in `AllBlocks.java`
- [ ] Block item registered in `AllItems.java` using `fromBlock()`
- [ ] Sounds registered in `AllSoundEvents.java` (if needed)
- [ ] Resource files created (model, texture, blockstate)
- [ ] Translation added to `lang/en_us.json`
- [ ] Loot table created in `data/ducksgalore/loot_tables/blocks/`
- [ ] Tags added (mineable, tool tier, custom tags)
- [ ] Item appears in creative tab automatically

## 🚀 Advanced: Conditional Registration

Sometimes you want to register objects based on config or other conditions:

```java
public class AllBlocks {
    private static final DeferredRegister<Block> BLOCKS = DucksGalore.BLOCKS;
    
    // Always registered
    public static final RegistryObject<Block> RUBBER_DUCK = 
        BLOCKS.register("rubber_duck", () -> new RubberDuckBlock(...));
    
    // Conditionally registered
    public static final RegistryObject<Block> SPECIAL_DUCK = 
        DucksGaloreConfig.COMMON.enableSpecialDuck.get() 
            ? BLOCKS.register("special_duck", () -> new SpecialDuckBlock(...))
            : null;
    
    // Helper to check if registered
    public static boolean isSpecialDuckEnabled() {
        return SPECIAL_DUCK != null;
    }
}
```

⚠️ **Warning:** This is advanced and can cause issues. Only use if necessary.

## 📚 See Also

- [Architecture Overview](architecture-overview.md) - Overall mod structure
- [How to Add a Block](how-to-add-a-block.md) - Complete block registration guide
- [How to Add an Item](how-to-add-an-item.md) - Complete item registration guide
- [Sound System](sound-system.md) - Sound registration details

---

**Key Takeaways:**
1. Use `DeferredRegister` for all registrations
2. Store as `RegistryObject<T>`, access with `.get()`
3. Register the `DeferredRegister` with the mod event bus
4. Follow consistent naming conventions
5. Reference centralized registries from `DucksGalore.java`

