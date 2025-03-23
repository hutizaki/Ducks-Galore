# Ducks-Galore
Mod for my Love

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

## AI Integration Guidelines

When using AI tools to modify or expand this mod, the following structured approach is recommended:

### 1. Adding New Duck Variants

To create a new duck variant (e.g., "Diamond Duck"):

1. **Create these files:**
   - `content/ducks/blocks/special/DiamondDuckBlock.java`
   - `assets/ducksgalore/textures/block/diamond_duck.png`
   - `assets/ducksgalore/models/block/diamond_duck.json`
   - `assets/ducksgalore/blockstates/diamond_duck.json`

2. **Register in these locations:**
   - Add to `registry/AllBlocks.java`
   - Add to `registry/AllItems.java`
   - Add to `assets/ducksgalore/lang/en_us.json`

3. **Example Code for AI Generation:**
   ```java
   // DiamondDuckBlock.java
   package com.hutizaki.ducksgalore.content.ducks.blocks.special;
   
   import com.hutizaki.ducksgalore.content.ducks.blocks.DuckBlock;
   import net.minecraft.world.effect.MobEffectInstance;
   import net.minecraft.world.effect.MobEffects;
   import net.minecraft.world.entity.player.Player;
   import net.minecraft.world.level.block.state.BlockState;
   import net.minecraft.world.level.Level;
   import net.minecraft.core.BlockPos;
   import net.minecraft.world.InteractionHand;
   import net.minecraft.world.InteractionResult;
   import net.minecraft.world.phys.BlockHitResult;
   
   public class DiamondDuckBlock extends DuckBlock {
       public DiamondDuckBlock(Properties properties) {
           super(properties);
       }
       
       @Override
       public InteractionResult use(BlockState state, Level level, BlockPos pos, 
                                  Player player, InteractionHand hand, BlockHitResult hit) {
           // Special diamond duck effect
           if (!level.isClientSide()) {
               player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0));
           }
           
           // Play duck sound
           level.playSound(player, pos, 
               com.hutizaki.ducksgalore.registry.AllSoundEvents.DIAMOND_DUCK_QUACK.get(), 
               net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);
               
           return InteractionResult.sidedSuccess(level.isClientSide);
       }
   }
   ```

### 2. Adding New Buff Types

To add a new buff type (e.g., "JumpBoost"):

1. **Create:**
   - `content/buffs/buffs/JumpBoostBuff.java`

2. **Register in:**
   - `content/buffs/BuffRegistry.java`
   - Add translation in `assets/ducksgalore/lang/en_us.json`

3. **Example Implementation:**
   ```java
   package com.hutizaki.ducksgalore.content.buffs.buffs;
   
   import com.hutizaki.ducksgalore.DucksGalore;
   import com.hutizaki.ducksgalore.content.buffs.DuckBuff;
   import net.minecraft.resources.ResourceLocation;
   import net.minecraft.world.effect.MobEffectInstance;
   import net.minecraft.world.effect.MobEffects;
   import net.minecraft.world.entity.player.Player;
   import net.minecraft.world.item.ItemStack;
   import net.minecraft.world.item.Items;
   import net.minecraft.world.level.Level;
   import net.minecraft.core.BlockPos;
   
   public class JumpBoostBuff implements DuckBuff {
       private static final ResourceLocation ID = 
           new ResourceLocation(DucksGalore.MOD_ID, "jump_boost");
       
       @Override
       public ResourceLocation getId() {
           return ID;
       }
       
       @Override
       public String getTranslationKey() {
           return "buff.ducksgalore.jump_boost";
       }
       
       @Override
       public ItemStack getDisplayIcon() {
           return new ItemStack(Items.RABBIT_FOOT);
       }
       
       @Override
       public void applyEffect(Player player, Level level, BlockPos pos) {
           player.addEffect(new MobEffectInstance(MobEffects.JUMP, 400, 1));
       }
   }
   ```

### 3. Creating Ponder Scenes for New Content

1. **Build the NBT structure in-game**
2. **Save to `assets/ducksgalore/ponder/new_feature.nbt`**
3. **Create implementation in `integration/ponder/scenes/NewFeatureScenes.java`**
4. **Register in `DucksPonderRegistry`**

5. **Example Scene Structure for AI Generation:**
   ```java
   public static void diamondDuckScene(PonderStoryBoardEntry storyboard) {
       storyboard.scene("diamond_duck", "Diamond Duck Features", 
           (scene, util) -> {
               // Set up the scene
               scene.showBasePlate();
               scene.idle(10);
               
               // Place the diamond duck
               BlockPos duckPos = new BlockPos(2, 1, 2);
               scene.world.setBlock(duckPos, AllBlocks.DIAMOND_DUCK.get().defaultBlockState(), 1);
               scene.idle(20);
               
               // First instruction
               scene.overlay.showText(60)
                   .text("The Diamond Duck provides Night Vision when right-clicked")
                   .pointAt(util.vector.blockSurface(duckPos, Direction.UP));
               scene.idle(70);
               
               // Show effect
               scene.effects.indicateSuccess(duckPos);
               scene.overlay.showOutline(
                   PonderPalette.BLUE, 
                   "playerArea", 
                   util.select.fromTo(duckPos.north(3), duckPos.south(3).east(3).above(3)), 
                   100
               );
               scene.idle(120);
               
               // Show duck in action
               scene.effects.emitParticles(
                   util.vector.topOf(duckPos),
                   ParticleTypes.END_ROD,
                   new Vec3(0, 0.1, 0),
                   20,
                   2.0f
               );
               scene.idle(30);
           }
       );
   }
   ```
