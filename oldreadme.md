# Duck's Galore

A Mod for my love

A Minecraft Forge mod that adds various duck blocks and items with unique functionalities, built with an extensible architecture inspired by the Create mod.

## File Structure

### Source Code Structure

```
src/main/java/com/hutizaki/ducksgalore/
├── DucksGalore.java                     // Main mod class that initializes everything
├── config/                              // Configuration options
│   └── DucksGaloreConfig.java           // Configuration settings for the mod
├── registry/                            // Central registry system
│   ├── AllBlocks.java                   // Contains all block registrations
│   ├── AllItems.java                    // Contains all item registrations
│   ├── AllBlockEntityTypes.java         // Contains all block entity registrations
│   ├── AllEntityTypes.java              // Contains all entity registrations
│   ├── AllSoundEvents.java              // Contains all sound registrations
│   ├── AllTags.java                     // Contains all tag definitions
│   ├── AllCreativeModeTabs.java         // Creative tabs for organizing items
│   └── RegistryHandler.java             // Handles registration of everything
├── content/                             // Main content package
│   ├── ducks/                           // All duck-related content
│   │   ├── blocks/                      // Duck block implementations
│   │   │   ├── DuckBlock.java           // Base duck block with common functionality
│   │   │   ├── RubberDuckBlock.java     // Default rubber duck implementation
│   │   │   └── special/                 // Special duck variants
│   │   │       ├── GoldenDuckBlock.java // Special golden duck implementation
│   │   │       └── ...                  // Other special ducks
│   │   ├── items/                       // Duck item implementations
│   │   │   ├── DuckItem.java            // Base duck item with common functionality
│   │   │   ├── RubberDuckItem.java      // Default rubber duck item
│   │   │   └── special/                 // Special duck variant items
│   │   │       ├── GoldenDuckItem.java  // Special golden duck item
│   │   │       └── ...                  // Other special duck items
│   │   ├── blockentities/               // Block entity implementations for ducks
│   │   │   ├── DuckBlockEntity.java     // Base duck block entity
│   │   │   └── special/                 // Special duck block entities
│   │   │       └── ...                  // Special duck block entities
│   │   └── entities/                    // Entity implementations for animated ducks
│   │       ├── DuckEntity.java          // Base duck entity
│   │       └── ...                      // Special duck entities
│   ├── buffs/                           // Duck buff system
│   │   ├── DuckBuff.java                // Base buff interface/class
│   │   ├── BuffRegistry.java            // Registry for duck buffs
│   │   └── buffs/                       // Specific buff implementations
│   │       ├── SpeedBuff.java           // Speed buff implementation
│   │       ├── StrengthBuff.java        // Strength buff implementation
│   │       └── ...                      // Other buff implementations
│   └── gui/                             // GUI implementations
│       ├── DuckBuffScreen.java          // Screen for duck buff configuration
│       ├── menu/                        // Menu implementations
│       │   └── DuckBuffMenu.java        // Container/Menu for duck buffs
│       └── widgets/                     // Custom GUI widgets
│           ├── BuffSlotWidget.java      // Widget for buff slots in GUI
│           └── ...                      // Other custom widgets
├── events/                              // Event handlers
│   ├── ModEventBusEvents.java           // Mod event bus events
│   └── ForgeEventHandlers.java          // Forge event handlers
├── client/                              // Client-side code
│   ├── DucksGaloreClient.java           // Client initialization
│   ├── render/                          // Rendering code
│   │   ├── blockentity/                 // Block entity renderers
│   │   │   └── DuckBlockEntityRenderer.java // Renderer for duck block entities
│   │   ├── entity/                      // Entity renderers
│   │   │   └── DuckEntityRenderer.java  // Renderer for duck entities
│   │   └── model/                       // Custom models
│   │       └── DuckModel.java           // Model for ducks
│   ├── sound/                           // Sound-related client code
│   │   └── DuckSoundHandler.java        // Handles duck sounds
│   └── particle/                        // Particle effects
│       └── DuckParticles.java           // Duck-related particles
├── data/                                // Data generators
│   ├── DataGenerators.java              // Data generator coordinator
│   ├── recipe/                          // Recipe generators
│   │   └── DuckRecipeProvider.java      // Generates duck recipes
│   ├── loot/                            // Loot table generators
│   │   └── DuckLootTableProvider.java   // Generates duck loot tables
│   ├── model/                           // Model generators
│   │   └── DuckModelProvider.java       // Generates duck models
│   └── lang/                            // Language generators
│       └── DuckLanguageProvider.java    // Generates duck lang entries
├── integration/                         // Mod integrations
│   └── ponder/                          // Ponder integration
│       ├── DucksPonderIndex.java        // Ponder index for the mod
│       ├── DucksPonderRegistry.java     // Registry for all duck ponder scenes
│       ├── PonderScenes.java            // Ponder scene implementations
│       ├── DucksPonderTags.java         // Tags for organizing ponder scenes
│       └── scenes/                      // Individual scene implementations
│           ├── DuckBuffScenes.java      // Buff-related ponder scenes
│           ├── BasicDuckScenes.java     // Basic duck ponder scenes
│           └── ...                      // Other ponder scenes
└── util/                                // Utility classes
    ├── DuckHelper.java                  // Duck-related helper methods
    ├── ModTags.java                     // Custom tag definitions
    └── ColorHelper.java                 // Color-related helper methods
```

### Resources Structure

```
src/main/resources/
├── META-INF/
│   └── mods.toml                        // Mod metadata
├── pack.mcmeta                          // Pack metadata
├── assets/ducksgalore/                  // Asset files
│   ├── blockstates/                     // Block state definitions
│   │   ├── rubber_duck.json             // Rubber duck blockstate
│   │   └── ...                          // Other duck blockstates
│   ├── lang/                            // Language files
│   │   ├── en_us.json                   // English language file
│   │   └── ...                          // Other language files
│   ├── models/                          // Model definitions
│   │   ├── block/                       // Block models
│   │   │   ├── rubber_duck.json         // Rubber duck block model
│   │   │   └── ...                      // Other duck block models
│   │   └── item/                        // Item models
│   │       ├── rubber_duck.json         // Rubber duck item model
│   │       └── ...                      // Other duck item models
│   ├── ponder/                          // Ponder scene files
│   │   ├── rubber_duck.nbt              // NBT file for rubber duck ponder scene
│   │   ├── duck_buff_gui.nbt            // NBT file for duck buff GUI ponder
│   │   └── ...                          // Other ponder NBT files
│   ├── shaders/                         // Custom shaders if needed
│   │   └── ...
│   ├── sounds/                          // Sound files
│   │   ├── quack.ogg                    // Basic quack sound
│   │   └── ...                          // Other duck sounds
│   └── textures/                        // Texture files
│       ├── block/                       // Block textures
│       │   ├── rubber_duck.png          // Rubber duck block texture
│       │   └── ...                      // Other duck block textures
│       ├── item/                        // Item textures
│       │   ├── rubber_duck.png          // Rubber duck item texture
│       │   └── ...                      // Other duck item textures
│       ├── gui/                         // GUI textures
│       │   ├── duck_buff_gui.png        // Duck buff GUI texture
│       │   └── ...                      // Other GUI textures
│       ├── entity/                      // Entity textures
│       │   ├── duck.png                 // Duck entity texture
│       │   └── ...                      // Other duck entity textures
│       └── ponder/                      // Ponder-specific textures
│           └── ...                      // Ponder textures
└── data/ducksgalore/                    // Data files
    ├── advancements/                    // Advancement definitions
    │   └── ...                          // Duck-related advancements
    ├── loot_tables/                     // Loot table definitions
    │   ├── blocks/                      // Block loot tables
    │   │   ├── rubber_duck.json         // Rubber duck block loot table
    │   │   └── ...                      // Other duck block loot tables
    │   └── entities/                    // Entity loot tables
    │       └── ...                      // Duck entity loot tables
    ├── recipes/                         // Recipe definitions
    │   ├── rubber_duck.json             // Rubber duck crafting recipe
    │   └── ...                          // Other duck recipes
    └── tags/                            // Tag definitions
        ├── blocks/                      // Block tags
        │   └── ducks.json               // Duck block tag
        ├── items/                       // Item tags
        │   └── ducks.json               // Duck item tag
        └── entity_types/                // Entity tags
            └── ducks.json               // Duck entity tag
```

## Adding New Duck Blocks & Items

### Creating a New Duck Block

1. **Create the Block Class**:
   ```java
   public class NewDuckBlock extends DuckBlock {
       public NewDuckBlock(Properties properties) {
           super(properties);
           // Custom duck block initialization
       }
       
       // Override methods for custom behavior
   }
   ```

2. **Register the Block**:
   Add your new duck block to `AllBlocks.java`:
   ```java
   public static final RegistryObject<Block> NEW_DUCK = registerBlock("new_duck",
       () -> new NewDuckBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
   ```

3. **Create Block Item**:
   Create a corresponding item class if needed, or use the default `DuckItem` class.

4. **Register the Block Item**:
   Add your new duck item to `AllItems.java`:
   ```java
   public static final RegistryObject<Item> NEW_DUCK_ITEM = registerItem("new_duck",
       () -> new DuckItem(AllBlocks.NEW_DUCK.get(), new Item.Properties()));
   ```

### Structuring Textures & Models

1. **Block Model**:
   Create a new JSON model file in `assets/ducksgalore/models/block/new_duck.json`:
   ```json
   {
       "parent": "ducksgalore:block/duck_base",
       "textures": {
           "0": "ducksgalore:block/new_duck"
       }
   }
   ```

2. **Item Model**:
   Create a new JSON model file in `assets/ducksgalore/models/item/new_duck.json`:
   ```json
   {
       "parent": "ducksgalore:block/new_duck"
   }
   ```

3. **Block State**:
   Create a new JSON blockstate file in `assets/ducksgalore/blockstates/new_duck.json`:
   ```json
   {
       "variants": {
           "": { "model": "ducksgalore:block/new_duck" }
       }
   }
   ```

4. **Textures**:
   Add texture files to the appropriate directories:
   - Block texture: `assets/ducksgalore/textures/block/new_duck.png`
   - Item texture (if different): `assets/ducksgalore/textures/item/new_duck.png`

5. **Language Entry**:
   Add translation in `assets/ducksgalore/lang/en_us.json`:
   ```json
   {
       "block.ducksgalore.new_duck": "New Duck Block",
       "item.ducksgalore.new_duck": "New Duck"
   }
   ```

### Adding Sounds

1. **Create Sound File**:
   Add your OGG sound file to `assets/ducksgalore/sounds/new_duck_quack.ogg`

2. **Register Sound**:
   Add the sound to `AllSoundEvents.java`:
   ```java
   public static final RegistryObject<SoundEvent> NEW_DUCK_QUACK = registerSoundEvent("new_duck_quack");
   ```

3. **Sound Definition**:
   Define the sound in `assets/ducksgalore/sounds.json`:
   ```json
   {
       "new_duck_quack": {
           "category": "blocks",
           "sounds": [
               "ducksgalore:new_duck_quack"
           ]
       }
   }
   ```

4. **Sound Usage**:
   Implement the sound in your block class:
   ```java
   @Override
   public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                              InteractionHand hand, BlockHitResult hit) {
       level.playSound(player, pos, AllSoundEvents.NEW_DUCK_QUACK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
       return InteractionResult.sidedSuccess(level.isClientSide);
   }
   ```

## Adding New Rubber Duck Types

The Ducks Galore mod follows a consistent pattern for adding new rubber duck types. This guide provides a step-by-step approach that both human developers and AI assistants can follow.

### Core Components for a New Rubber Duck

Each rubber duck type in the mod consists of the following components:

#### 1. Java Classes (Source Code)

1. **Block Class**: Defines the duck block's behavior
2. **Item Class**: Defines the duck item's behavior (optional if no special functionality needed)

#### 2. Registration

1. **Block Registration**: In `AllBlocks.java`
2. **Item Registration**: In `AllItems.java`
3. **Sound Registration**: In `AllSoundEvents.java`

#### 3. Resource Files

1. **Models**: Block and item model files
2. **Blockstate**: JSON file defining the block's states
3. **Textures**: PNG texture files
4. **Sounds**: OGG sound files
5. **Language**: Translation entries
6. **Loot Tables**: Defining what drops when the block is broken

### Step-by-Step Guide

Below is a comprehensive guide to adding a new rubber duck type, using example code for a "Copper Rubber Duck".

#### Step 1: Create the Block Class

Create a new Java class extending `RubberDuckBlock` in `content/ducks/blocks/`:

```java
package com.hutizaki.ducksgalore.content.ducks.blocks;

import com.hutizaki.ducksgalore.registry.AllSoundEvents;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nonnull;

/**
 * Copper Rubber Duck block implementation - a special variant with unique properties
 */
public class CopperRubberDuckBlock extends RubberDuckBlock {
    
    public CopperRubberDuckBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResult use(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, 
                              @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        // Play copper rubber duck sound when right-clicked and give player effect
        if (hand == InteractionHand.MAIN_HAND) {  // Only trigger for main hand
            // Apply special effect
            if (!level.isClientSide) {
                // Give player Haste effect for 45 seconds (900 ticks)
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 900, 1));
                
                // Play copper duck quack sound
                level.playSound(null, pos, AllSoundEvents.COPPER_RUBBER_DUCK_QUACK.get(), SoundSource.BLOCKS, 1.5F, 
                               1.0F + (level.random.nextFloat() * 0.1F));
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            if (!level.isClientSide) {
                // Play block event sound
                level.playSound(null, pos, AllSoundEvents.COPPER_RUBBER_DUCK_BLOCK_EVENT.get(), SoundSource.BLOCKS, 1.5F, 
                              1.0F + (level.random.nextFloat() * 0.1F));
            }
        }
        // Do NOT call super.onRemove to prevent double sound
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        // Do NOT call super.onPlace to prevent double sound
        if (!level.isClientSide) {
            // Play block event sound
            level.playSound(null, pos, AllSoundEvents.COPPER_RUBBER_DUCK_BLOCK_EVENT.get(), SoundSource.BLOCKS, 1.5F, 
                           1.0F + (level.random.nextFloat() * 0.1F));
        }
    }
}
```

#### Step 2: Create the Item Class (if needed)

Create a new Java class extending `BlockItem` in `content/ducks/items/`:

```java
package com.hutizaki.ducksgalore.content.ducks.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Copper Rubber Duck item implementation
 */
public class CopperRubberDuckItem extends BlockItem {
    public CopperRubberDuckItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        // Add tooltip text
        tooltip.add(Component.translatable("tooltip.ducksgalore.copper_rubber_duck"));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
```

#### Step 3: Register Sound Events

Add new sound events in `registry/AllSoundEvents.java`:

```java
public static final RegistryObject<SoundEvent> COPPER_RUBBER_DUCK_QUACK = registerSoundEvent("copper_rubber_duck_quack");
public static final RegistryObject<SoundEvent> COPPER_RUBBER_DUCK_BLOCK_EVENT = registerSoundEvent("copper_rubber_duck_block_event");
```

#### Step 4: Register Block and Item

In `registry/AllBlocks.java`:

```java
// Register copper rubber duck block
public static final RegistryObject<Block> COPPER_RUBBER_DUCK = registerBlock("copper_rubber_duck",
    () -> new CopperRubberDuckBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));
```

In `registry/AllItems.java`:

```java
public static final RegistryObject<Item> COPPER_RUBBER_DUCK = registerItem("copper_rubber_duck",
    () -> new CopperRubberDuckItem(AllBlocks.COPPER_RUBBER_DUCK.get(), new Item.Properties()));
```

#### Step 5: Create Resource Files

1. **Create Blockstate File** (`assets/ducksgalore/blockstates/copper_rubber_duck.json`):

```json
{
    "variants": {
        "facing=north": { "model": "ducksgalore:block/copper_rubber_duck/block" },
        "facing=east": { "model": "ducksgalore:block/copper_rubber_duck/block", "y": 90 },
        "facing=south": { "model": "ducksgalore:block/copper_rubber_duck/block", "y": 180 },
        "facing=west": { "model": "ducksgalore:block/copper_rubber_duck/block", "y": 270 }
    }
}
```

2. **Create Block Model** (`assets/ducksgalore/models/block/copper_rubber_duck/block.json`):

Use the existing duck model structure with your custom texture:

```json
{
    "credit": "Designed by Hutizaki",
    "parent": "minecraft:block/block",
    "ambientocclusion": false,
    "texture_size": [8, 8],
    "textures": {
        "all": "ducksgalore:block/copper_rubber_duck",
        "particle": "ducksgalore:block/copper_rubber_duck"
    },
    "elements": [
        // Duck model elements (same as other duck models)
    ]
}
```

3. **Create Item Model** (`assets/ducksgalore/models/item/copper_rubber_duck.json`):

```json
{
    "parent": "ducksgalore:block/copper_rubber_duck/block"
}
```

4. **Create Loot Table** (`data/ducksgalore/loot_tables/blocks/copper_rubber_duck.json`):

```json
{
    "type": "minecraft:block",
    "pools": [
      {
        "rolls": 1,
        "bonus_rolls": 0,
        "entries": [
          {
            "type": "minecraft:item",
            "name": "ducksgalore:copper_rubber_duck"
          }
        ],
        "conditions": [
          {
            "condition": "minecraft:survives_explosion"
          }
        ]
      }
    ]
}
```

5. **Add Sound Definitions** in `assets/ducksgalore/sounds.json`:

```json
"copper_rubber_duck_quack": {
    "category": "blocks",
    "subtitle": "copper_rubber_duck_quack.sub",
    "sounds": [
        "ducksgalore:copper_rubber_duck_quack"
    ]
},
"copper_rubber_duck_block_event": {
    "category": "blocks",
    "subtitle": "copper_rubber_duck_block_event.sub",
    "sounds": [
        "ducksgalore:copper_rubber_duck_place"
    ]
}
```

6. **Add Language Entries** in `assets/ducksgalore/lang/en_us.json`:

```json
"block.ducksgalore.copper_rubber_duck": "Copper Rubber Duck Block",
"item.ducksgalore.copper_rubber_duck": "Copper Rubber Duck",
"tooltip.ducksgalore.copper_rubber_duck": "Grants Haste when used",
"copper_rubber_duck_quack": "Copper Rubber Duck Quacks",
"copper_rubber_duck_quack.sub": "Copper Rubber Duck Quacking",
"copper_rubber_duck_block_event": "Copper Rubber Duck Block Interaction",
"copper_rubber_duck_block_event.sub": "Copper Rubber Duck Block Interaction"
```

#### Step 6: Create Required Sound Files

Add the following OGG files to `assets/ducksgalore/sounds/`:
- `copper_rubber_duck_quack.ogg`
- `copper_rubber_duck_place.ogg`

### Adding Special Mechanics (Optional)

For ducks with special mechanics (like the Gold Ore Rubber Duck), you may need to:

1. Add event handlers in `events/ForgeEventHandlers.java` for special drop mechanics
2. Create specialized effect components
3. Add recipe definitions if the duck is craftable

#### Example: Adding a Rare Duck that Drops from Blocks

To add a duck that has a chance to drop when breaking specific blocks (like the Gold Ore Rubber Duck), add the following code to `events/ForgeEventHandlers.java`:

```java
@SubscribeEvent
public static void onBlockBreak(BlockEvent.BreakEvent event) {
    if (event.getLevel().isClientSide()) return;
    
    // Check if the broken block is copper ore
    if (event.getState().getBlock() == Blocks.COPPER_ORE) {
        // 1/1500 chance (0.067%) to drop a copper rubber duck
        if (RANDOM.nextInt(1500) == 0) {
            ItemStack duckStack = new ItemStack(AllItems.COPPER_RUBBER_DUCK.get());
            ItemEntity itemEntity = new ItemEntity(
                event.getLevel().getLevel(),
                event.getPos().getX() + 0.5,
                event.getPos().getY() + 0.5,
                event.getPos().getZ() + 0.5,
                duckStack
            );
            itemEntity.setDefaultPickUpDelay();
            event.getLevel().getLevel().addFreshEntity(itemEntity);
        }
    }
}
```

### Tips for Maintaining Consistency

1. **Naming Conventions**:
   - Always use the format `[MATERIAL]_rubber_duck` for resource names
   - For blocks: `[Material]RubberDuckBlock.java`
   - For items: `[Material]RubberDuckItem.java`

2. **Sound Management**:
   - Use separate `[MATERIAL]_rubber_duck_quack` for quacking sounds
   - Use a unified `[MATERIAL]_rubber_duck_block_event` for both placement and breaking sounds

3. **Effects**:
   - Choose effects that thematically match the material of the duck
   - Keep effect durations and amplifiers balanced

4. **Documentation**:
   - Comment your code to explain the duck's special behaviors
   - Add tooltips to explain the duck's effects to players

By following these guidelines, you can easily add new rubber duck types to the mod that maintain consistency with the existing system.

## Handling Forge Registries

### Registry Structure

The mod uses a centralized registry system with classes in the `registry` package:

1. **Registry Objects**:
   Each registry type has its own class:
   - `AllBlocks` - Block registrations
   - `AllItems` - Item registrations
   - `AllSoundEvents` - Sound registrations
   - `AllBlockEntityTypes` - Block entity registrations

2. **RegistryHandler**:
   The `RegistryHandler` class coordinates all registrations and should be called from the main mod class:
   ```java
   public class DucksGalore {
       public static final String MOD_ID = "ducksgalore";
       
       public DucksGalore() {
           IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
           
           // Register all registry objects
           RegistryHandler.register(modEventBus);
           
           // Register mod event bus handlers
           modEventBus.addListener(this::setup);
           modEventBus.addListener(DataGenerators::gatherData);
       }
       
       private void setup(final FMLCommonSetupEvent event) {
           // Common setup code
       }
   }
   ```

3. **Helper Methods**:
   Use consistent helper methods for registration:
   ```java
   // In AllBlocks.java
   public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
       RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
       registerBlockItem(name, registeredBlock);
       return registeredBlock;
   }
   
   // In AllItems.java  
   public static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
       return ITEMS.register(name, item);
   }
   ```

## Naming Conventions & Best Practices

1. **Naming**:
   - Class names: PascalCase (e.g., `RubberDuckBlock`)
   - Registry objects: UPPER_SNAKE_CASE (e.g., `RUBBER_DUCK`)
   - Method names: camelCase (e.g., `quackSound()`)
   - Resource files: snake_case (e.g., `rubber_duck.json`)

2. **Package Structure**:
   - Group related classes together (e.g., all ducks in the `content.ducks` package)
   - Separate client and server code (e.g., renderers in `client.render`)
   - Use consistent subpackage names (`blocks`, `items`, `entities`)

3. **Code Style**:
   - Use constructor parameters for shared properties
   - Override methods cleanly with proper Javadoc
   - Follow Forge's registry patterns
   - Organize imports alphabetically

4. **Resource Organization**:
   - Group textures by type
   - Use base/parent models for common elements
   - Create consistent naming patterns for related resources

## Comprehensive Create Ponder System

The Ponder system is Create mod's interactive tutorial mechanism that provides visual demonstrations of mod features. Integrating this system into Duck's Galore allows for interactive learning experiences.

### Ponder Architecture Overview

Ponder consists of several key components working together:

1. **Ponder Registry** - Central registration system for all Ponder scenes
2. **Ponder Tags** - Categories grouping related scenes for organization
3. **Ponder Scenes** - Individual tutorial demonstrations 
4. **NBT Structures** - Pre-built scenes used as foundations for demonstrations
5. **Storyboards** - Scripted sequences of animations and text overlays

### Detailed Component Explanation

#### 1. Ponder Registry System

The Ponder Registry connects blocks/items to their tutorial scenes and organizes them with tags:

```java
// Initialize Ponder registry
public static void register() {
    // Register a duck to a specific tag category
    PonderRegistry.TAGS.forTag(DucksPonderTags.BASIC_DUCKS)
        .add(AllBlocks.RUBBER_DUCK.get());
    
    // Connect a block to its scene implementation
    PonderRegistry.addStoryBoard(DucksGalore.asResource("rubber_duck"), 
        "rubber_duck_usage", 
        storyboard -> BasicDuckScenes.rubberDuck(storyboard));
}
```

#### 2. Ponder Tags

Tags organize related concepts to help users discover functionality:

```java
public class DucksPonderTags {
    // A tag for basic duck functionality
    public static final PonderTag BASIC_DUCKS = 
        new PonderTag(DucksGalore.asResource("basic_ducks"))
            .setIcon(AllItems.RUBBER_DUCK.get())
            .setDefaultLang("Basic Ducks", "Learn about basic duck functionality");
    
    // A tag for the advanced buff system
    public static final PonderTag BUFF_SYSTEM = 
        new PonderTag(DucksGalore.asResource("buff_system"))
            .setIcon(AllItems.GOLDEN_DUCK.get())
            .setDefaultLang("Duck Buff System", "Learn about the duck buff system");

    // Initialize all tags
    public static void register() {
        // Register all tags with the Ponder system
        PonderRegistry.TAGS.add(BASIC_DUCKS, BUFF_SYSTEM);
    }
}
```

#### 3. NBT Structure Files

Each Ponder scene requires an NBT structure file in `assets/ducksgalore/ponder/`:

- `rubber_duck.nbt` - Basic scene showing a rubber duck
- `duck_buff_gui.nbt` - Scene for demonstrating the buff system

These NBT files contain pre-built Minecraft structures that serve as the stage for your demonstrations.

#### 4. Scene Implementation

Each scene is a detailed storyboard with animations, text, and interactions:

```java
public static void rubberDuck(PonderStoryBoardEntry storyboard) {
    storyboard.scene("rubber_duck", "Rubber Duck Basics", 
        (scene, util) -> {
            // Basic scene setup
            scene.showBasePlate();
            scene.idle(10);
            
            // Add duck to the scene (specific location)
            BlockPos duckPos = new BlockPos(2, 1, 2);
            scene.world.setBlock(duckPos, AllBlocks.RUBBER_DUCK.get().defaultBlockState(), 1);
            scene.idle(20);
            
            // First instruction with pointing arrow
            scene.overlay.showText(60)
                .text("Right-click the duck to hear it quack")
                .pointAt(util.vector.blockSurface(duckPos, Direction.UP));
            scene.idle(70);
            
            // Demonstrate sound effect
            scene.effects.playSound(SoundEvents.DUCK_QUACK, 1.0f, 1.0f);
            scene.idle(20);
            
            // More instructions
            scene.overlay.showText(60)
                .text("Each duck type has unique sounds and effects")
                .pointAt(util.vector.blockSurface(duckPos, Direction.NORTH));
            scene.idle(70);
        }
    );
}
```

### GUI-Based Ponder Implementation

For the duck buff system with interactive GUI demonstrations:

1. **Complex Overlay Scenes:**

```java
public static void buffSystem(PonderStoryBoardEntry storyboard) {
    storyboard.scene("duck_buff_gui", "Duck Buff System", 
        (scene, util) -> {
            // Basic setup
            scene.showBasePlate();
            scene.idle(10);
            
            // Place a golden duck
            BlockPos duckPos = new BlockPos(2, 1, 2);
            scene.world.setBlock(duckPos, AllBlocks.GOLDEN_DUCK.get().defaultBlockState(), 1);
            scene.idle(20);
            
            // Show an item being used on the duck (with visual cue)
            scene.overlay.showControls(
                new InputWindowElement(util.vector.blockSurface(duckPos, Direction.UP), Pointing.DOWN)
                    .rightClick()
                    .withItem(new ItemStack(Items.FEATHER)), 
                40
            );
            scene.idle(50);
            
            // GUI overlay demonstration
            scene.overlay.showSelectionWithText(util.select.position(duckPos), 80)
                .text("Golden Ducks can be configured with special buffs")
                .placeNearTarget();
            scene.idle(90);
            
            // Show custom GUI screen mockup
            scene.overlay.showImage(
                util.getResource("textures/ponder/buff_gui.png"), 
                util.vector.of(120, 70)
            );
            scene.idle(30);
            
            // Demonstrate drag-and-drop functionality
            scene.overlay.showText(80)
                .text("Drag buff items into slots to enhance duck abilities")
                .pointAt(util.vector.of(2, 2, 2));
            scene.idle(90);
            
            // Show effect activation
            scene.effects.indicateSuccess(duckPos);
            scene.effects.createBall(VecHelper.getCenterOf(duckPos), 0.5f)
                .color(0xFFD700)
                .scale(3.0f)
                .spin(10, 1)
                .brightness(1.0f);
            scene.idle(20);
        }
    );
}
```

2. **Register GUI Ponder:**

```java
// Add to DucksPonderRegistry
PonderRegistry.addStoryBoard(
    DucksGalore.asResource("duck_buff_gui"),
    "duck_buff_system", 
    storyboard -> DuckBuffScenes.buffSystem(storyboard)
);
```

### Integration With Main Mod

1. **Initialize Ponder Registry:**

```java
// In DucksGaloreClient.java
@SubscribeEvent
public static void onClientSetup(FMLClientSetupEvent event) {
    event.enqueueWork(() -> {
        // Register all ponder scenes 
        DucksPonderRegistry.register();
        // Register all ponder tags
        DucksPonderTags.register();
    });
}
```

2. **Add Ponder Button to Blocks:**

```java
// In DucksGaloreClient.java
@SubscribeEvent
public static void onPonderIndexRegistration(PonderRegistrationEvent event) {
    // Make ducks show the ponder button in tooltips and JEI
    PonderRegistry.TAGS.forTag(DucksPonderTags.BASIC_DUCKS)
        .add(AllBlocks.RUBBER_DUCK.get())
        .add(AllBlocks.GOLDEN_DUCK.get());
}
```

### Creating Custom Ponder Scenes - Step by Step

1. **Create NBT Structure File:**
   - Build your scene structure in a creative world
   - Use the Create Structure Wand item to capture it
   - Save as `assets/ducksgalore/ponder/my_scene.nbt`

2. **Register Scene in PonderRegistry:**
   ```java
   PonderRegistry.addStoryBoard(
       DucksGalore.asResource("my_scene"),
       "my_scene_id", 
       storyboard -> MySceneClass.mySceneMethod(storyboard)
   );
   ```

3. **Write Scene Implementation:**
   ```java
   public static void mySceneMethod(PonderStoryBoardEntry storyboard) {
       storyboard.scene("my_scene", "My Scene Title", (scene, util) -> {
           // Scene code here
       });
   }
   ```

4. **Add to Appropriate Tag:**
   ```java
   PonderRegistry.TAGS.forTag(DucksPonderTags.MY_TAG)
       .add(AllBlocks.MY_BLOCK.get());
   ```

### Advanced Ponder Techniques

#### 1. Animation Elements:

```java
// Rotate a duck
scene.rotatePart(
    util.select.position(duckPos), 
    0, 10, 0,     // Start rotation
    0, 370, 0,    // End rotation
    100           // Duration in ticks
);

// Move a duck
scene.movePart(
    util.select.position(duckPos),
    new Vec3(0, 0, 0),    // Start offset
    new Vec3(0, 3, 0),    // End offset (3 blocks up)
    100                   // Duration in ticks
);

// Show particles
scene.effects.emitParticles(
    util.vector.topOf(duckPos),    // Emission point
    ParticleTypes.HEART,          // Particle type
    new Vec3(0, 0.1, 0),          // Motion vector
    20,                           // Particle count
    2.0f                          // Spread
);
```

#### 2. Custom Sections/Chapters:

```java
// Create multi-part ponder scenes
public static void complexDuckDemo(PonderStoryBoardEntry storyboard) {
    // First chapter
    storyboard.scene("part1", "Basic Duck Usage", (scene, util) -> {
        // Basic duck usage scene code
    });
    
    // Second chapter
    storyboard.scene("part2", "Advanced Duck Features", (scene, util) -> {
        // Advanced features scene code
    });
}
```

#### 3. Linking Scenes Together:

```java
// Reference another ponder scene with link
scene.overlay.showText(60)
    .text("See additional features in the buff system tutorial")
    .colored(PonderPalette.BLUE)
    .attachKeyFrame()
    .pointAt(util.vector.topOf(duckPos));

// Add a clickable link to another ponder
PonderRegistry.addStoryBoard(DucksGalore.asResource("rubber_duck"), "rubber_duck",
    storyboard -> {
        storyboard.scene("rubber_duck", "Rubber Duck",
            (scene, util) -> {
                // Scene code
                
                // Add reference to another ponder
                scene.addLink(
                    util.grid.at(3, 1, 3),  // Position of linked block
                    PonderScenes.forEntry(DucksGalore.asResource("golden_duck")),  // Target ponder
                    80  // Duration of highlight
                );
            }
        );
    }
);
```

### Working with CreateSceneBuilder

The `CreateSceneBuilder` class is a powerful tool that helps you build interactive ponder scenes. Here are some common techniques used in the Create mod's ponder scenes:

#### Scene Setup and Timing

```java
// Initialize the builder
CreateSceneBuilder scene = new CreateSceneBuilder(builder);

// Set title and description
scene.title("example_scene", "How to Use Example Block");

// Configure the base plate size and position
scene.configureBasePlate(0, 0, 5);  // Origin X, Z, and size
scene.showBasePlate();

// Add pauses between actions
scene.idle(20);  // Waits for 20 ticks (1 second)

// Add keyframes to highlight important steps
scene.addKeyframe();
```

#### Showing and Manipulating Blocks

   ```java
// Show sections of the world
scene.world().showSection(util.select().layer(0), Direction.UP);
scene.world().showSection(util.select().position(blockPos), Direction.DOWN);

// Show or hide sections independently
ElementLink<WorldSectionElement> section = 
    scene.world().showIndependentSection(util.select().position(blockPos), Direction.UP);
scene.world().hideIndependentSection(section, Direction.DOWN);

// Modify block states
scene.world().setBlock(blockPos, newState, false);
scene.world().modifyBlock(blockPos, state -> state.setValue(PROPERTY, value), false);

// Work with redstone
scene.world().toggleRedstonePower(util.select().position(leverPos));
scene.effects().indicateRedstone(leverPos);
```

#### Working with Items and Entities

```java
// Create an item on a belt
ElementLink<BeltItemElement> beltItem = 
    scene.world().createItemOnBelt(beltPos, Direction.SOUTH, itemStack);

// Create an entity
ElementLink<EntityElement> entity = scene.world().createEntity(entitySupplier);

// Add a parrot for demonstration
ElementLink<ParrotElement> parrot = 
    scene.special().createBirb(util.vector().topOf(blockPos), ParrotPose::new);
scene.special().rotateParrot(parrot, 0, 180, 0, 20);
```

#### Visual Effects and Overlays

```java
// Show rotation direction and speed
scene.effects().rotationDirectionIndicator(blockPos);
scene.effects().rotationSpeedIndicator(blockPos);

// Show particles
scene.effects().emitParticles(position, emitter);

// Highlight a specific area
scene.overlay().showOutline(PonderPalette.GREEN, "highlight", 
    util.select().position(blockPos), 80);

// Show explanatory text
scene.overlay().showText(60)
    .text("This is how the block works")
    .pointAt(util.vector().topOf(blockPos))
    .placeNearTarget();

// Show controls
scene.overlay().showControls(
    new InputWindowElement(util.vector().topOf(blockPos), Pointing.DOWN)
        .withItem(itemStack), 40);
```

#### Working with Kinetics

   ```java
// Modify kinetic speed
scene.world().setKineticSpeed(util.select().position(blockPos), 32f);
scene.world().multiplyKineticSpeed(util.select().everywhere(), 2f);

// Change rotation direction
scene.world().modifyKineticSpeed(selection, f -> -f);
```

These examples showcase just a portion of what's possible with the CreateSceneBuilder. The class provides access to three main categories of instructions:

1. `scene.world()` - Manipulate blocks, entities, and items in the ponder world
2. `scene.effects()` - Add visual effects like particles, rotation indicators, etc.
3. `scene.overlay()` - Show UI elements like text, outlines, or item selections

For more advanced scenarios, look at the Create mod's existing ponder scenes as reference.

### Creating NBT Files for Ponder Scenes

The NBT files in the `ponder` directory are essential as they define the physical structure of your scenes. Here's how to create them:

#### Method 1: Using Create's Schematic Tools

1. **Setup a Creative World**: Create a new creative world or use an existing one.

2. **Build Your Scene**: Construct the blocks, machines, or structures you want to showcase in your ponder scene.

3. **Use Create's Schematic Tools**:
   - Craft a Schematic and Quill item
   - Right-click to select the first corner of your scene
   - Right-click again to select the opposite corner
   - Name your schematic and save it

4. **Export the NBT File**:
   - The schematic will be saved in your `.minecraft/schematics` folder
   - Rename the file to match your scene ID (e.g., `example_scene.nbt`)
   - Move the file to your mod's `src/main/resources/assets/yourmod/ponder/` directory

#### Method 2: Using Debug Tools (For Development)

If you're familiar with the Create development environment, you can use debug tools:

1. Create a new class extending `PonderNBTBuilder` that sets up your scene programmatically

2. Add a method like:
   ```java
   public static void generateExampleSceneNBT() {
       PonderNBTBuilder builder = new PonderNBTBuilder();
       builder.emptyBasePlate(7, 7); // Size of the base plate
       
       // Add blocks to the scene
       builder.block(3, 1, 3, YourBlocks.EXAMPLE_BLOCK.getDefaultState());
       builder.block(3, 1, 4, Blocks.LEVER.getDefaultState());
       
       // Add entities or other elements
       
       // Save the NBT file
       builder.saveToNBT("example_scene");
   }
   ```

3. Call this method during development to generate your NBT files.

#### Testing Your NBT Files

Once your NBT files are in place, you can verify they work correctly by:

1. Loading your mod in a development environment
2. Opening the Ponder index (Press 'w' while hovering over your block in the inventory)
3. Finding your scene and making sure it loads correctly

Remember that any changes to the NBT files require the game to be restarted to see the updates.

### Localizing Ponder Content

To make your Ponder scenes accessible to players using different languages, you'll need to implement proper localization:

#### 1. Scene Title and Description Localization

Scene titles and descriptions are automatically pulled from language files when you use translation keys:

```java
// In your scene definition
scene.title("example_block.basic", "Basic Example Block Usage");
```

Then in your language files:

```json
// assets/yourmod/lang/en_us.json
{
  "ponder.yourmod.example_block.basic": "Basic Example Block Usage",
  "ponder.yourmod.example_block.basic.description": "Learn how to use the example block"
}

// assets/yourmod/lang/fr_fr.json
{
  "ponder.yourmod.example_block.basic": "Utilisation de base du bloc d'exemple",
  "ponder.yourmod.example_block.basic.description": "Apprenez à utiliser le bloc d'exemple"
}
```

#### 2. Text Overlay Localization

For text shown within the scene:

```java
// In your scene
scene.overlay().showText(60)
    .text(Component.translatable("ponder.yourmod.example_block.step1"))
    .pointAt(util.vector.topOf(blockPos))
    .placeNearTarget();
```

Then in your language files:

```json
{
  "ponder.yourmod.example_block.step1": "Place the block and right-click to activate"
}
```

#### 3. Tag and Chapter Localization

When registering tags:

   ```java
helper.registerTag(EXAMPLE_TAG)
    .addToIndex()
    .item(AllBlocks.EXAMPLE_BLOCK.get(), true, false)
    .title("Example Tag")  // Default English version
    .description("Blocks and items related to example functionality")
    .register();
```

These titles and descriptions can be localized in language files:

```json
{
  "ponder.tag.yourmod.example_tag": "Example Tag",
  "ponder.tag.yourmod.example_tag.description": "Blocks and items related to example functionality"
}
```

This approach ensures your Ponder scenes are accessible to a global audience and maintains consistency with Minecraft's localization system.

### Customizing Ponder Visuals

The Ponder system uses various textures for UI elements. Here's how to customize these for your mod:

#### 1. Tag Icons

Each Ponder tag needs a distinctive icon:

```
assets/yourmod/textures/ponder/tag/example_tag.png
```

Tag icons should be 16x16 pixels for best results. These icons appear in the Ponder index and when hovering over items with Ponder scenes.

#### 2. Chapter Icons

For mods with multiple chapters or categories:

```
assets/yourmod/textures/ponder/chapter/ducks.png
```

Chapter icons work best at 32x32 pixels and appear in the Ponder index navigation.

#### 3. Custom Scene Textures

For specialized overlays or custom illustrations within scenes:

```
assets/yourmod/textures/ponder/scenes/duck_diagram.png
```

You can show these using:

```java
scene.overlay().showImage(
    new ResourceLocation("yourmod", "textures/ponder/scenes/duck_diagram.png"),
    new Vec3(2, 1.5, 2), // position
    new Vec2(3, 2),      // size
    60                   // duration
);
```

#### 4. Styling Guidelines

For visual consistency:
- Use a similar color palette across your Ponder visuals
- Maintain a consistent style between your mod textures and Ponder icons
- For a unified look with Create, use their signature brown/brass tones
- Keep designs simple and recognizable at small sizes

These custom textures help establish your mod's visual identity within the Ponder system while making navigation intuitive for users.

### Troubleshooting Ponder Scenes

When developing Ponder scenes, you might encounter various issues. Here are common problems and their solutions:

#### 1. Scene Not Appearing

If your scene doesn't appear when clicking on an item:

- Verify that you've registered the scene correctly in your PonderPlugin
- Check that your NBT file exists in the correct location and is named exactly as referenced
- Ensure your mod ID is consistent in all registrations
- Look for errors in the log related to missing textures or NBT files

#### 2. Visual Glitches

If your scene has visual issues:

- Verify block positions in your scene code match those in your NBT file
- Check if any animations overlap or conflict with each other
- Ensure timing is properly sequenced (using `scene.idle()` appropriately)
- Try clearing Minecraft's cache folder if textures aren't loading correctly

#### 3. Performance Issues

If your scene runs slowly:

- Reduce the number of simultaneous animations
- Simplify large or complex NBT structures
- Space out animations with more idle time between them
- Avoid creating too many particle effects at once

#### 4. Development Iteration

For faster development:

```java
// Add this to your mod's development environment
@SubscribeEvent
public static void onKeyInput(InputEvent.KeyInputEvent event) {
    if (Minecraft.getInstance().level == null) return;
    
    // Add a hotkey (F8 in this example) to reload all ponder scenes
    if (event.getKey() == GLFW.GLFW_KEY_F8 && event.getAction() == GLFW.GLFW_PRESS) {
        PonderClient.runCleanup();  // Clear cached scenes
        PonderClient.refreshIndex(); // Reload all scenes
        
        // Send notification to player
        Minecraft.getInstance().player.displayClientMessage(
            Component.literal("Ponder scenes reloaded"), false);
    }
}
```

This allows you to reload scenes without restarting the game.

#### 5. Common Error Messages

- `Missing Ponder scene: [scene_id]` - Check your scene registration and NBT file
- `Failed to load NBT structure` - Verify your NBT file is correctly formatted and in the right location
- `Rendering error in Ponder scene` - Usually indicates an issue with animations or entity rendering

#### 6. Debugging Mode

To add visual debugging to your scenes:

```java
// Show block positions and selection boxes
scene.showDebugOutline(true);

// Add debug text at a specific point in the scene
scene.overlay().showText(40)
    .text("DEBUG: Position=" + blockPos)
    .colored(PonderPalette.RED)
    .placeNearTarget();
```

These troubleshooting techniques should help you identify and fix issues with your Ponder scenes more efficiently.

### Ponder Integration with Other Mods

Ponder scenes can be extended to demonstrate compatibility with other mods, creating a more cohesive player experience.

#### 1. Cross-Mod Ponder Scenes

To create scenes that demonstrate how your mod interacts with other mods:

```java
public static void crossModInteraction(CreateSceneBuilder scene, SceneBuildingUtil util) {
    // Only show the scene if the other mod is present
    if (!ModList.get().isLoaded("othermodid")) {
        scene.title("cross_mod_functionality", "Feature requires Other Mod");
        scene.showBasePlate();
        scene.idle(20);
        scene.overlay().showText(80)
            .text("This feature requires Other Mod to be installed")
            .colored(PonderPalette.RED);
        return;
    }
    
    // Regular ponder scene with cross-mod interaction
    scene.title("cross_mod_functionality", "Integration with Other Mod");
    scene.configureBasePlate(0, 0, 5);
    scene.showBasePlate();
    
    // Scene showing interaction between your blocks and the other mod's blocks
    BlockPos yourBlockPos = new BlockPos(2, 1, 2);
    BlockPos otherModBlockPos = new BlockPos(2, 1, 3);
    
    // Show your block
    scene.world().setBlock(yourBlockPos, YourMod.EXAMPLE_BLOCK.defaultBlockState(), false);
    scene.idle(20);
    
    // Show other mod's block (access via Registry if possible)
    Block otherModBlock = ForgeRegistries.BLOCKS.getValue(
        new ResourceLocation("othermodid", "example_block"));
    if (otherModBlock != null) {
        scene.world().setBlock(otherModBlockPos, otherModBlock.defaultBlockState(), false);
    }
    
    // Show interaction between the blocks
    scene.idle(20);
    scene.overlay().showText(60)
        .text("Your block works with Other Mod's blocks")
        .pointAt(util.vector.blockSurface(otherModBlockPos, Direction.WEST));
}
```

#### 2. Conditional Registration

Register scenes that depend on other mods only when those mods are present:

```java
public static void registerPonderScenes() {
    // Register standard scenes
    PonderRegistry.addStoryBoard(
        YourMod.asResource("example_scene"),
        "example_scene", 
        YourScenes::standardScene
    );
    
    // Register integration scenes only when compatible mods are present
    if (ModList.get().isLoaded("othermodid")) {
        PonderRegistry.addStoryBoard(
            YourMod.asResource("othermod_integration"),
            "othermod_integration", 
            YourScenes::otherModIntegration
        );
    }
}
```

#### 3. Accessing Other Mods' Blocks Safely

To safely reference blocks from other mods:

```java
// Safe way to get blocks from other mods
public static Block getBlockFromMod(String modId, String blockId) {
    if (!ModList.get().isLoaded(modId)) {
        return Blocks.BARRIER; // Fallback
    }
    
    Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(modId, blockId));
    return block != null ? block : Blocks.BARRIER;
}

// Usage in scene
Block specialBlock = getBlockFromMod("othermod", "special_block");
scene.world().setBlock(blockPos, specialBlock.defaultBlockState(), false);
```

#### 4. Create API Integration

If extending Create's functionality, you can use CreateSceneBuilder directly:

```java
public static void registerWithCreate(PonderSceneRegistrationHelper<ResourceLocation> helper) {
    // Use this format to register scenes that extend Create features
    helper.forComponents(YourMod.YOUR_EXTENSION_OF_CREATE_BLOCK)
        .addStoryBoard("your_mod/extension", YourScenes::createExtension, 
            AllCreatePonderTags.KINETIC_APPLIANCES);
}
```

This approach helps players understand how your mod integrates with the broader modding ecosystem.

### Best Practices for Effective Ponder Scenes

Creating good Ponder scenes is as much an art as it is a technical implementation. Here are best practices for designing effective in-game tutorials:

#### 1. Progressive Complexity

Structure your scenes to build knowledge progressively:

- Start with the most basic functionality
- Gradually introduce more complex features
- Link related scenes together to create learning paths
- Use keyframes to highlight major conceptual shifts

```java
// Example of progressive complexity
public static void basicToAdvanced(CreateSceneBuilder scene, SceneBuildingUtil util) {
    scene.title("example_block.progression", "From Basic to Advanced Usage");
    
    // Start with basic setup
    BlockPos blockPos = new BlockPos(2, 1, 2);
    scene.world().showSection(util.select().position(blockPos), Direction.DOWN);
    scene.idle(20);
    
    // Basic usage
    scene.overlay().showText(60)
        .text("Start by placing the block")
        .pointAt(util.vector.topOf(blockPos));
    scene.idle(70);
    
    // Add keyframe to mark progression to next concept
    scene.addKeyframe();
    
    // Intermediate usage
    scene.world().showSection(util.select().position(blockPos.north()), Direction.SOUTH);
    scene.idle(20);
    scene.overlay().showText(60)
        .text("Connect another block to enable advanced features")
        .pointAt(util.vector.blockSurface(blockPos, Direction.NORTH));
    scene.idle(70);
    
    // Add another keyframe for next level
    scene.addKeyframe();
    
    // Advanced usage
    // ...and so on
}
```

#### 2. Readability Guidelines

Make your scenes easy to understand:

- Keep text concise (5-12 words per overlay when possible)
- Allow 2-3 seconds of reading time per word
- Use consistent terminology throughout all scenes
- Position text near the relevant action but not obscuring it
- Use color coding for different types of information

#### 3. Visual Clarity

Ensure players can easily follow what's happening:

- Highlight important elements with outlines or particles
- Use contrasting colors for key components
- Animate one element at a time when possible
- Provide sufficient idle time between actions (15-30 ticks minimum)
- Use keyframes to pause at critical points

#### 4. Teaching Patterns

Effective educational patterns to follow:

1. **Show, Then Explain**: Demonstrate first, then explain what happened
   ```java
   // Show the action
   scene.world().setBlock(blockPos, newState, false);
   scene.effects().indicateSuccess(blockPos);
   scene.idle(20);
   
   // Then explain it
   scene.overlay().showText(60)
       .text("The block transforms when activated")
       .pointAt(util.vector.topOf(blockPos));
   ```

2. **Compare & Contrast**: Show different approaches side by side
   ```java
   // Show two different setups
   ElementLink<WorldSectionElement> correctSetup = 
       scene.world().showIndependentSection(util.select().fromTo(1, 1, 1, 3, 3, 3), Direction.UP);
   scene.idle(20);
   
   ElementLink<WorldSectionElement> incorrectSetup = 
       scene.world().showIndependentSection(util.select().fromTo(5, 1, 1, 7, 3, 3), Direction.UP);
   scene.idle(20);
   
   // Compare them
   scene.overlay().showOutline(PonderPalette.GREEN, "correct", util.select().fromTo(1, 1, 1, 3, 3, 3), 60);
   scene.overlay().showOutline(PonderPalette.RED, "incorrect", util.select().fromTo(5, 1, 1, 7, 3, 3), 60);
   ```

3. **Cause & Effect**: Clearly connect actions with results
   ```java
   // Show the cause
   scene.world().toggleRedstonePower(util.select().position(leverPos));
   scene.effects().indicateRedstone(leverPos);
   scene.idle(10);
   
   // Show the effect
   scene.world().setBlock(blockPos, activeState, false);
   scene.effects().indicateSuccess(blockPos);
   ```

#### 5. Scene Pacing

Control the rhythm of your tutorials:

- Keep total scene length under 60-90 seconds
- Break complex topics into multiple linked scenes
- Include 10-20 tick pauses after each significant action
- Use longer pauses (40-60 ticks) after introducing new concepts
- Speed up repetitive actions to maintain engagement

By following these best practices, you'll create Ponder scenes that effectively teach players about your mod's features while keeping them engaged and interested.

### Conclusion

The Ponder system provides an elegant way to teach players about your mod's mechanics directly in-game. By following this guide, you can create detailed, interactive tutorials for all your mod's features, giving players a smoother learning experience.

### Duck Material Types and Tool Requirements

The mod implements different duck material types with unique properties:

#### Material Types

1. **Rubber Ducks** (`DuckMaterial.RUBBER`)
   - No tool required to harvest - breakable with any tool or hand
   - Fast breaking speed (0.3F destroy time)
   - Uses bass instrument sound
   - Standard rubber duck texture

2. **Metal Ducks** (`DuckMaterial.METAL`)
   - Requires a pickaxe to properly harvest (will drop nothing without the right tool)
   - Higher strength (1.5F) and slower mining time
   - Uses bell instrument sound
   - Examples: Golden Rubber Duck

3. **Stone Ducks** (`DuckMaterial.STONE`)
   - Requires a pickaxe to properly harvest (will drop nothing without the right tool)
   - Higher strength (1.5F) and slower mining time
   - Uses basedrum (stone) instrument sound
   - Examples: Gold Ore Rubber Duck

#### Special Effects

Some duck types have unique visual effects:

1. **Golden Rubber Duck**
   - Has a continuous enchantment shimmer effect
   - Creates gold particles when interacted with
   - Sparkles when placed in the world

2. **Applying Effects to Custom Ducks**
   - Use the `DuckEffects` utility class to add visual effects to custom duck types
   - Available effects include enchantment particles, gold particles, and ambient effects

#### Implementing a Custom Duck with Material Properties

When creating a new duck type, specify its material in the constructor:

```java
public class DiamondRubberDuckBlock extends DuckBlock {
    public DiamondRubberDuckBlock() {
        // Create a metal duck that requires a pickaxe
        super(createDuckProperties(DuckMaterial.METAL), DuckMaterial.METAL);
    }
    
    // Override methods for custom behavior
}
```

Register the block with appropriate properties:

```java
// Register diamond rubber duck block with METAL material properties (pickaxe needed)
public static final RegistryObject<Block> DIAMOND_RUBBER_DUCK = registerBlock("diamond_rubber_duck",
    () -> new DiamondRubberDuckBlock(DuckBlock.createDuckProperties(DuckBlock.DuckMaterial.METAL)));
```

### Golden Rubber Duck Ritual

The Golden Rubber Duck is a special variant that cannot be crafted through normal means. It requires a mystical ritual to create:

#### The Ritual Altar

1. **Base Structure:**
   - Create a circle of Gold Blocks
   - Place a regular rubber duck in the center

2. **Duck Circle:**
   - Place 8 Gold Ore Rubber Ducks on top of the gold circle arrangement
   - The 8 ducks should be placed directly above the gold blocks

3. **Center Components:**
   - Drop an Enchanted Golden Apple (Notch Apple) onto the center

4. **Performing the Ritual:**
   - When all components are correctly positioned, the ritual will automatically begin
   - The Gold Ore Rubber Ducks will emit yellow particle beams toward the center
   - After 5 seconds, lightning will strike the center
   - The regular Rubber Duck, 8 Gold Ore Rubber Ducks, and the Enchanted Golden Apple will be consumed
   - A Golden Rubber Duck will appear in place of the Regular Rubber Duck

#### Visual Guide

**Bottom Level (Gold Block Platform):**
```
X X X G X X X
X G X X X G X
X X X X X X X
G X X R X X G
X X X X X X X
X G X X X G X
X X X G X X X
```

**Top Level (Duck Placements):**
```
X X X D X X X
X D X X X D X
X X X X X X X
D X X X X X D
X X X X X X X
X D X X X D X
X X X D X X X
```

Where:
- G = Gold Block (bottom level)
- D = Gold Ore Rubber Duck (top level)
- R = Regular Rubber Duck (top level, center) + Enchanted Golden Apple
- X = Air or other blocks

The ritual requires this exact placement pattern, with the Gold Blocks forming a 3×3 platform at the bottom level, and the Gold Ore Rubber Ducks forming a circle on the top level. The Regular Duck and Enchanted Golden Apple go at the center of the top level.

#### Properties of the Golden Rubber Duck

- Grants Regeneration II effect for 20 seconds when right-clicked
- Emits a subtle golden glow (light level 5)
- Displays enchantment particles and golden sparkles
- Requires a pickaxe to properly harvest
- Can be placed for decoration or used for its effect

You can view this ritual in-game using Create's Ponder system by holding W over the Golden Rubber Duck item in your inventory.

### Creating Duck Ponder Screens

To create a ponder screen for your duck, follow these steps:

1. Create a new class that extends `DuckPonderScreen` in the `com.hutizaki.ducksgalore.client.ponder` package:

```java
public class MyNewDuckPonder extends DuckPonderScreen {
    // Define resource locations for your ponder pages
    private static final ResourceLocation[] PAGES = {
        DucksGalore.asResource("textures/gui/ponder/my_duck_page1.png"),
        DucksGalore.asResource("textures/gui/ponder/my_duck_page2.png"),
        DucksGalore.asResource("textures/gui/ponder/my_duck_page3.png")
    };
    
    public MyNewDuckPonder(ItemStack item) {
        super(item);
    }

    @Override
    protected int getTotalPages() {
        return 3; // Number of pages
    }

    @Override
    protected Component getPageTitle(int page) {
        return switch (page) {
            case 0 -> Component.translatable("ponder.ducksgalore.my_duck.title");
            case 1 -> Component.translatable("ponder.ducksgalore.my_duck.page2_title");
            case 2 -> Component.translatable("ponder.ducksgalore.my_duck.page3_title");
            default -> Component.empty();
        };
    }

    @Override
    protected void renderPage(GuiGraphics graphics, int mouseX, int mouseY, float partialTick, int page) {
        // Draw page content for each page
        // See existing ponder screens for examples
    }
}
```

2. Add your ponder screen to the `DuckPonderScreenFactory`:

```java
public static DuckPonderScreen createPonderScreen(ItemStack itemStack) {
    // ... existing code ...
    
    else if (item == AllItems.MY_NEW_DUCK.get()) {
        return new MyNewDuckPonder(itemStack);
    }
    
    // ... existing code ...
}
```

3. Register your duck item for pondering in `DucksGaloreClient`:

```java
DuckPonderSystem.registerPonderItem(AllItems.MY_NEW_DUCK.get());
```

4. Create the necessary ponder page images (176x100 pixels) in `assets/ducksgalore/textures/gui/ponder/`

5. Add translations for your ponder in `en_us.json`:

```json
"ponder.ducksgalore.my_duck.title": "My Custom Duck",
"ponder.ducksgalore.my_duck.page2_title": "Duck Features",
"ponder.ducksgalore.my_duck.page3_title": "Duck Effects",
"ponder.ducksgalore.my_duck.desc1": "Description of your duck on page 1",
"ponder.ducksgalore.my_duck.desc2": "Description of your duck on page 2",
"ponder.ducksgalore.my_duck.desc3": "Description of your duck on page 3"
```

### Using the Duck Ponder System

The Duck Ponder System provides an in-game way to learn about duck mechanics:

1. In a container/inventory screen, hover over a duck item
2. Press and hold the 'W' key (default, configurable)
3. Wait for the loading bar to fill
4. The ponder screen will open with information about the duck

Navigate through the ponder screens:
- Left click or right arrow to advance pages
- Right click or left arrow to go back
- ESC to close
