# Architecture Overview

This document explains the overall architecture of the Ducks Galore mod and how all the pieces fit together.

## 🏛️ High-Level Architecture

The mod follows a **centralized registration pattern** with **organized content separation**:

```
┌─────────────────────────────────────────────────────────────┐
│                    DucksGalore.java                          │
│                   (Main Mod Class)                           │
│                                                              │
│  • Creates DeferredRegister instances                       │
│  • Registers with mod event bus                             │
│  • Initializes all "All*" classes                           │
│  • Handles lifecycle events                                 │
└──────────────────────┬──────────────────────────────────────┘
                       │
        ┌──────────────┼──────────────┬───────────────┐
        │              │               │               │
        ▼              ▼               ▼               ▼
┌─────────────┐ ┌─────────────┐ ┌──────────┐ ┌──────────────┐
│  AllBlocks  │ │  AllItems   │ │AllSounds │ │AllCreative   │
│             │ │             │ │          │ │   ModeTabs   │
│ Registers   │ │ Registers   │ │ Custom   │ │              │
│ all blocks  │ │ all items   │ │ builder  │ │ Creative tab │
│             │ │             │ │ pattern  │ │ setup        │
└──────┬──────┘ └──────┬──────┘ └────┬─────┘ └──────────────┘
       │               │              │
       │               │              │
       ▼               ▼              ▼
┌──────────────────────────────────────────────────────────────┐
│               content/ package                                │
│                                                               │
│  content/rubberducks/                                        │
│    • RubberDuckBlock.java                                    │
│    • GoldenRubberDuckBlock.java                              │
│    • GoldOreRubberDuckBlock.java                             │
│                                                               │
│  Each implements specific behaviors and interactions         │
└──────────────────────────────────────────────────────────────┘
```

## 📦 Core Components

### 1. DucksGalore.java - The Heart of the Mod

```java
@Mod(DucksGalore.MOD_ID)
public class DucksGalore {
    public static final String MOD_ID = "ducksgalore";
    
    // Centralized DeferredRegister instances
    public static final DeferredRegister<Block> BLOCKS = ...;
    public static final DeferredRegister<Item> ITEMS = ...;
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = ...;
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = ...;
}
```

**Responsibilities:**
- Creates all `DeferredRegister` instances
- Registers them with the mod event bus
- Initializes all registration classes (`AllBlocks`, `AllItems`, etc.)
- Handles mod lifecycle events (common setup, client setup)
- Provides utility methods (`asResource()`)

**Why this approach?**
- **Single source of truth** for all registries
- **Automatic registration** - no need to pass registries around
- **Clean initialization** - everything happens in the constructor
- **Easy access** - static fields available throughout the mod

### 2. Registration Classes (All*.java)

These classes follow a consistent pattern:

#### AllBlocks.java
```java
public class AllBlocks {
    private static final DeferredRegister<Block> BLOCKS = DucksGalore.BLOCKS;
    
    public static final RegistryObject<Block> RUBBER_DUCK = 
        BLOCKS.register("rubber_duck", () -> new RubberDuckBlock(...));
    
    public static void register() {
        // Called from DucksGalore constructor for initialization
    }
}
```

#### AllItems.java
```java
public class AllItems {
    private static final DeferredRegister<Item> ITEMS = DucksGalore.ITEMS;
    
    // Block items automatically created from blocks
    public static final RegistryObject<Item> RUBBER_DUCK_ITEM = 
        fromBlock(AllBlocks.RUBBER_DUCK);
    
    private static RegistryObject<Item> fromBlock(RegistryObject<? extends Block> block) {
        return ITEMS.register(block.getId().getPath(), 
            () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
```

**Key Pattern:**
- Reference the centralized `DeferredRegister` from `DucksGalore`
- Use `RegistryObject<T>` for type-safe registry entries
- Provide static fields for easy access throughout the mod
- Call `register()` method from `DucksGalore` for initialization logging

### 3. AllSoundEvents.java - Custom Sound System

This uses a **builder pattern** inspired by the Create mod:

```java
public class AllSoundEvents {
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DucksGalore.SOUND_EVENTS;
    public static final Map<ResourceLocation, SoundEntry> ALL = new HashMap<>();
    
    public static final SoundEntry RUBBER_DUCK_QUACK = create("rubber_duck_quack")
        .subtitle("Rubber duck quacks")
        .category(SoundSource.BLOCKS)
        .build();
}
```

**Why a builder pattern?**
- Fluent, readable syntax
- Encapsulates sound configuration
- Stores volume, pitch, and other settings
- Supports both custom sounds and wrapping vanilla sounds

See [Sound System](sound-system.md) for more details.

### 4. Content Package Structure

Content is organized by **feature** rather than by type:

```
content/
└── rubberducks/
    ├── RubberDuckBlock.java
    ├── GoldenRubberDuckBlock.java
    └── GoldOreRubberDuckBlock.java
```

**Benefits:**
- Related code stays together
- Easy to add new duck types
- Clear feature boundaries
- Scales well as the mod grows

## 🔄 Registration Flow

Here's how everything comes together when the mod loads:

```
1. Forge calls DucksGalore constructor
   │
   ├─> 2. Create DeferredRegister instances
   │       BLOCKS, ITEMS, SOUND_EVENTS, CREATIVE_MODE_TABS
   │
   ├─> 3. Register DeferredRegisters with mod event bus
   │       modEventBus.register(BLOCKS)
   │       modEventBus.register(ITEMS)
   │       ...
   │
   ├─> 4. Call All*.register() methods
   │       │
   │       ├─> AllBlocks.register()
   │       │   └─> BLOCKS.register("rubber_duck", ...)
   │       │       BLOCKS.register("golden_rubber_duck", ...)
   │       │
   │       ├─> AllItems.register()
   │       │   └─> ITEMS.register("rubber_duck", ...)
   │       │       ITEMS.register("golden_rubber_duck", ...)
   │       │
   │       ├─> AllSoundEvents.register()
   │       │   └─> Builder creates SoundEntry instances
   │       │       Registers with SOUND_EVENTS
   │       │
   │       └─> AllCreativeModeTabs.register()
   │           └─> Creates creative tab with all items
   │
   └─> 5. Forge processes DeferredRegister entries
           Objects are registered to game registries
```

## 🎮 Lifecycle Events

The mod responds to key Forge lifecycle events:

### Common Setup (FMLCommonSetupEvent)
```java
private void commonSetup(final FMLCommonSetupEvent event) {
    // Code that runs on both client and server
    // Network registration, capabilities, etc.
}
```

### Client Setup (FMLClientSetupEvent)
```java
private void clientSetup(final FMLClientSetupEvent event) {
    // Client-only code
    // Render layers, screen bindings, etc.
}
```

### Data Generation (GatherDataEvent)
```java
private void gatherData(final GatherDataEvent event) {
    // Generate JSON files for recipes, loot tables, etc.
    // Currently commented out - can be enabled as needed
}
```

## 📐 Design Patterns Used

### 1. DeferredRegister Pattern
**What:** Forge's lazy registration system  
**Why:** Thread-safe, load-order independent  
**Where:** `DucksGalore.java`, all `All*.java` classes

### 2. Builder Pattern
**What:** Fluent API for constructing objects  
**Why:** Readable, flexible, self-documenting  
**Where:** `AllSoundEvents.SoundEntryBuilder`

### 3. Factory Methods
**What:** Static methods that create objects  
**Why:** Encapsulate creation logic  
**Where:** `AllItems.fromBlock()`, block property creators

### 4. Centralized Configuration
**What:** Single source for all registries  
**Why:** Reduces coupling, easier to maintain  
**Where:** `DucksGalore` provides all `DeferredRegister` instances

### 5. Separation of Concerns
**What:** Each class has a single responsibility  
**Why:** Easier to understand and modify  
**Where:** Registration vs. Implementation vs. Configuration

## 🗂️ Resource Organization

The mod follows Minecraft's standard resource structure:

### Client Resources (`assets/ducksgalore/`)
```
assets/ducksgalore/
├── blockstates/          # Block state JSON files
├── models/
│   ├── block/           # Block models
│   └── item/            # Item models
├── textures/
│   ├── block/           # Block textures
│   └── item/            # Item textures (if different from block)
├── sounds/              # .ogg sound files
└── lang/                # Translations (en_us.json)
```

### Server Data (`data/ducksgalore/`)
```
data/ducksgalore/
├── loot_tables/         # Loot table definitions
│   └── blocks/          # Block drops
├── recipes/             # Crafting recipes
└── tags/                # Tags for blocks and items
    ├── blocks/
    └── items/
```

See [Resource Files](resource-files.md) for detailed information.

## ⚙️ Configuration System

The mod uses Forge's `ForgeConfigSpec` system:

```java
public class DucksGaloreConfig {
    public static final CommonConfig COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;
}
```

**Two config types:**
- **Common Config** - Shared between client and server
- **Client Config** - Client-side only (visuals, audio)

See [Configuration System](config-system.md) for details.

## 🔍 Key Concepts

### RegistryObject vs. Direct References

**RegistryObject:**
```java
public static final RegistryObject<Block> RUBBER_DUCK = BLOCKS.register(...);
```
- Lazy evaluation
- Safe to use during static initialization
- Access with `.get()`

**Direct Object:**
```java
Block block = RUBBER_DUCK.get(); // Get the actual block
```

### Resource Locations

Every registered object needs a unique `ResourceLocation`:
```java
ResourceLocation id = new ResourceLocation("ducksgalore", "rubber_duck");
// Namespace: ducksgalore
// Path: rubber_duck
```

Our helper method simplifies this:
```java
DucksGalore.asResource("rubber_duck"); // Same as above
```

## 🚀 Why This Architecture?

### Benefits:
1. **Maintainability** - Easy to find and modify code
2. **Scalability** - Simple to add new content
3. **Clarity** - Obvious where everything goes
4. **Type Safety** - Compile-time checking for registrations
5. **Testability** - Each component can be tested independently

### Trade-offs:
- Slightly more boilerplate than direct registration
- Need to understand the flow to make changes
- Static initialization can be tricky for complex cases

## 📚 Next Steps

Now that you understand the architecture:
1. Learn the [Registration System](registration-system.md) in detail
2. Follow [How to Add a Block](how-to-add-a-block.md) to create your first content
3. Explore [Block Behavior Patterns](block-behavior-patterns.md) for advanced features

---

**See Also:**
- [Registration System](registration-system.md)
- [How to Add a Block](how-to-add-a-block.md)
- [Sound System](sound-system.md)

