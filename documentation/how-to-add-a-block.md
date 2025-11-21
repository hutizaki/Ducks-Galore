# How to Add a Block

This guide walks you through adding a new block to the Ducks Galore mod, from Java code to resource files.

## 📋 Prerequisites

Before starting, you should understand:
- Java programming basics
- The [Architecture Overview](architecture-overview.md)
- The [Registration System](registration-system.md)

## 🎯 Overview

Adding a block requires:
1. **Create the block class** in `content/` package
2. **Register the block** in `AllBlocks.java`
3. **Create a block item** in `AllItems.java`
4. **Add resource files** (model, texture, blockstate)
5. **Add translations** in `lang/en_us.json`
6. **Add loot table** so the block drops itself
7. **Add tags** if needed (mining tool, etc.)

## Step 1: Create the Block Class

Create a new Java class in `src/main/java/com/hutizaki/ducksgalore/content/rubberducks/`:

### Example: Simple Block

```java
package com.hutizaki.ducksgalore.content.rubberducks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Example: Simple directional duck block
 */
public class SimpleDuckBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    
    // Define the block's hitbox (in pixels, 16x16x16 = full block)
    private static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
    
    public SimpleDuckBlock(Properties properties) {
        super(properties);
        // Set default facing direction
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Face away from the player when placed
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
```

### Block Properties

Common block properties you can set:

```java
BlockBehaviour.Properties.of()
    .mapColor(MapColor.COLOR_YELLOW)           // Color on maps
    .strength(0.5F)                            // Break speed (lower = faster)
    .strength(0.5F, 6.0F)                      // (hardness, blast resistance)
    .requiresCorrectToolForDrops()             // Needs proper tool to drop
    .noOcclusion()                             // Doesn't block light
    .lightLevel(state -> 15)                   // Emits light (0-15)
    .sound(SoundType.WOOD)                     // Break/place sound
    .pushReaction(PushReaction.BLOCK)          // Can't be pushed by pistons
```

💡 **Tip:** Look at vanilla Minecraft blocks for reference:
- `Blocks.GOLD_BLOCK` for solid blocks
- `Blocks.TORCH` for light-emitting blocks
- `Blocks.GLASS` for transparent blocks

## Step 2: Register the Block

Add your block to `AllBlocks.java`:

```java
public class AllBlocks {
    private static final DeferredRegister<Block> BLOCKS = DucksGalore.BLOCKS;

    // ... existing blocks ...

    public static final RegistryObject<Block> SIMPLE_DUCK =
            BLOCKS.register("simple_duck",
                    () -> new SimpleDuckBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLUE)
                            .strength(0.5F)
                            .noOcclusion()
                    ));
    
    public static void register() {
        DucksGalore.LOGGER.info("Ducks Galore blocks initialized");
    }
}
```

🔍 **Key Points:**
- The first parameter `"simple_duck"` is the registry name
- This becomes the resource location: `ducksgalore:simple_duck`
- The lambda `() -> new SimpleDuckBlock(...)` creates the block lazily

## Step 3: Create a Block Item

Add the block item to `AllItems.java`:

```java
public class AllItems {
    private static final DeferredRegister<Item> ITEMS = DucksGalore.ITEMS;
    
    // ... existing items ...
    
    public static final RegistryObject<Item> SIMPLE_DUCK_ITEM = 
            fromBlock(AllBlocks.SIMPLE_DUCK);
    
    // ... rest of the class ...
}
```

The `fromBlock()` helper method automatically creates a `BlockItem` with the same registry name.

## Step 4: Add Resource Files

### 4.1 Block Model

Create `src/main/resources/assets/ducksgalore/models/block/simple_duck.json`:

```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "ducksgalore:block/simple_duck"
  }
}
```

💡 **Common parent models:**
- `minecraft:block/cube_all` - All sides same texture
- `minecraft:block/cube` - Different texture per side
- `minecraft:block/cross` - Two intersecting planes (flowers)
- `minecraft:block/orientable` - Front/side/top textures

### 4.2 Item Model

Create `src/main/resources/assets/ducksgalore/models/item/simple_duck.json`:

```json
{
  "parent": "ducksgalore:block/simple_duck"
}
```

Most block items simply reference their block model.

### 4.3 Blockstate File

Create `src/main/resources/assets/ducksgalore/blockstates/simple_duck.json`:

#### Simple (Non-directional) Blockstate:
```json
{
  "variants": {
    "": {
      "model": "ducksgalore:block/simple_duck"
    }
  }
}
```

#### Directional Blockstate:
```json
{
  "variants": {
    "facing=north": { "model": "ducksgalore:block/simple_duck" },
    "facing=east": { "model": "ducksgalore:block/simple_duck", "y": 90 },
    "facing=south": { "model": "ducksgalore:block/simple_duck", "y": 180 },
    "facing=west": { "model": "ducksgalore:block/simple_duck", "y": 270 }
  }
}
```

### 4.4 Texture

Create a 16x16 PNG texture:
`src/main/resources/assets/ducksgalore/textures/block/simple_duck.png`

🎨 **Texture Tips:**
- Use 16x16 pixels (can be higher for higher resolution)
- Use transparency (alpha channel) for non-solid parts
- Follow Minecraft's art style
- Test in-game to see how lighting affects it

## Step 5: Add Translations

Add to `src/main/resources/assets/ducksgalore/lang/en_us.json`:

```json
{
  "block.ducksgalore.simple_duck": "Simple Duck",
  "block.ducksgalore.rubber_duck": "Rubber Duck",
  ...
}
```

## Step 6: Add Loot Table

Create `src/main/resources/data/ducksgalore/loot_tables/blocks/simple_duck.json`:

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "ducksgalore:simple_duck"
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

This makes the block drop itself when broken.

### Advanced Loot Table Example

For blocks requiring specific tools:

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "ducksgalore:simple_duck"
        }
      ],
      "conditions": [
        {
          "condition": "minecraft:match_tool",
          "predicate": {
            "enchantments": [
              {
                "enchantment": "minecraft:silk_touch",
                "levels": {
                  "min": 1
                }
              }
            ]
          }
        }
      ]
    }
  ]
}
```

## Step 7: Add Tags

Tags define what tools can mine your block and how fast.

### Mineable with Pickaxe

Create or edit `src/main/resources/data/minecraft/tags/blocks/mineable/pickaxe.json`:

```json
{
  "replace": false,
  "values": [
    "ducksgalore:simple_duck"
  ]
}
```

### Tool Tier Required

Create or edit `src/main/resources/data/minecraft/tags/blocks/needs_iron_tool.json`:

```json
{
  "replace": false,
  "values": [
    "ducksgalore:simple_duck"
  ]
}
```

Available tool tiers:
- `needs_stone_tool.json` - Stone pickaxe or better
- `needs_iron_tool.json` - Iron pickaxe or better
- `needs_diamond_tool.json` - Diamond pickaxe or better

### Custom Mod Tags

To group your blocks, edit `src/main/resources/data/ducksgalore/tags/blocks/rubber_ducks.json`:

```json
{
  "replace": false,
  "values": [
    "ducksgalore:rubber_duck",
    "ducksgalore:golden_rubber_duck",
    "ducksgalore:gold_ore_rubber_duck",
    "ducksgalore:simple_duck"
  ]
}
```

## ✅ Testing Your Block

1. Build the mod: `gradlew build`
2. Run the game: `gradlew runClient`
3. In creative mode, open the "Ducks Galore" creative tab
4. Place your block and test:
   - Does it place correctly?
   - Does it face the right direction?
   - Does it break and drop itself?
   - Is the texture correct?
   - Does the name show correctly?

## 🚀 Advanced Features

### Adding Interaction

```java
@Override
public InteractionResult use(BlockState state, Level level, BlockPos pos,
                          Player player, InteractionHand hand, BlockHitResult hit) {
    if (hand == InteractionHand.MAIN_HAND) {
        if (!level.isClientSide) {
            // Server-side logic
            player.displayClientMessage(Component.literal("Quack!"), true);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
    return InteractionResult.PASS;
}
```

### Adding Sounds

```java
@Override
public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
    if (!level.isClientSide) {
        var soundEntry = AllSoundEvents.RUBBER_DUCK_BLOCK_EVENT;
        level.playSound(null, pos, soundEntry.getMainEvent(), SoundSource.BLOCKS, 
                       soundEntry.getVolume(), soundEntry.getPitch());
    }
    super.onPlace(state, level, pos, oldState, isMoving);
}
```

See [Sound System](sound-system.md) for details.

### Adding Particle Effects

```java
@Override
public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
    if (random.nextInt(10) == 0) {
        level.addParticle(
            ParticleTypes.HAPPY_VILLAGER,
            pos.getX() + 0.5,
            pos.getY() + 0.5,
            pos.getZ() + 0.5,
            0, 0, 0
        );
    }
}
```

### Giving Player Effects

```java
@Override
public InteractionResult use(BlockState state, Level level, BlockPos pos,
                          Player player, InteractionHand hand, BlockHitResult hit) {
    if (!level.isClientSide) {
        // Give speed effect for 10 seconds (200 ticks)
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0));
    }
    return InteractionResult.sidedSuccess(level.isClientSide);
}
```

## 🐛 Common Issues

### Block doesn't appear in creative tab
- Check that the item is registered in `AllItems`
- Verify `AllCreativeModeTabs` includes all items

### Block has missing texture (purple/black)
- Check texture path matches exactly
- Verify texture file is 16x16 PNG
- Check console for missing texture errors

### Block doesn't drop when broken
- Verify loot table exists with correct name
- Check loot table JSON syntax
- Ensure loot table path matches block registry name

### Block can't be mined with correct tool
- Check tags are correctly set up
- Verify `requiresCorrectToolForDrops()` is set if needed
- Test with different tool types

### Block faces wrong direction
- Check `getStateForPlacement()` logic
- Verify blockstate file has correct rotations
- Test placing from different directions

## 📚 See Also

- [Block Behavior Patterns](block-behavior-patterns.md) - Advanced block behaviors
- [Sound System](sound-system.md) - Adding sounds to blocks
- [Resource Files](resource-files.md) - Detailed resource file reference
- [Tags and Data](tags-and-data.md) - Working with tags

---

**Example Blocks to Study:**
- `RubberDuckBlock.java` - Basic interactive block with sounds
- `GoldenRubberDuckBlock.java` - Block with particles and effects
- `GoldOreRubberDuckBlock.java` - Block with tool requirements

