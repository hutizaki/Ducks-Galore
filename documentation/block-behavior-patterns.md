# Block Behavior Patterns

This document covers common block behaviors and patterns used in Ducks Galore, with code examples you can adapt.

## 🎯 Overview

This guide provides reusable patterns for:
- Player interaction
- Sound effects
- Particle effects
- Mob effects (buffs/debuffs)
- Light emission
- Block states
- Directional blocks

## 🖱️ Player Interaction

### Basic Right-Click Interaction

```java
@Override
public InteractionResult use(BlockState state, Level level, BlockPos pos,
                          Player player, InteractionHand hand, BlockHitResult hit) {
    // Only respond to main hand to avoid double-triggering
    if (hand == InteractionHand.MAIN_HAND) {
        if (!level.isClientSide) {
            // Server-side logic here
            player.displayClientMessage(
                Component.literal("You clicked the duck!"), 
                true  // Show above hotbar
            );
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
    return InteractionResult.PASS;
}
```

💡 **Key Points:**
- Check `hand == InteractionHand.MAIN_HAND` to prevent double-triggering
- Do gameplay logic on server (`!level.isClientSide`)
- Return `InteractionResult.sidedSuccess(level.isClientSide)` for success
- Return `InteractionResult.PASS` if interaction should pass through

### Require Specific Item

```java
@Override
public InteractionResult use(BlockState state, Level level, BlockPos pos,
                          Player player, InteractionHand hand, BlockHitResult hit) {
    ItemStack heldItem = player.getItemInHand(hand);
    
    if (heldItem.is(Items.GOLD_INGOT)) {
        if (!level.isClientSide) {
            // Consume the item
            if (!player.isCreative()) {
                heldItem.shrink(1);
            }
            
            // Do something special
            player.displayClientMessage(Component.literal("Duck upgraded!"), true);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
    
    return InteractionResult.PASS;
}
```

### Check Player Conditions

```java
@Override
public InteractionResult use(BlockState state, Level level, BlockPos pos,
                          Player player, InteractionHand hand, BlockHitResult hit) {
    if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;
    
    if (!level.isClientSide) {
        // Check if player is sneaking
        if (player.isShiftKeyDown()) {
            player.displayClientMessage(Component.literal("Sneaky duck!"), true);
        }
        
        // Check player's health
        if (player.getHealth() < player.getMaxHealth() / 2) {
            player.heal(2.0F);
            player.displayClientMessage(Component.literal("Duck healed you!"), true);
        }
        
        // Check if player has specific effect
        if (player.hasEffect(MobEffects.POISON)) {
            player.removeEffect(MobEffects.POISON);
            player.displayClientMessage(Component.literal("Duck cured you!"), true);
        }
    }
    
    return InteractionResult.sidedSuccess(level.isClientSide);
}
```

## 🔊 Sound Patterns

### Play Sound on Interaction

```java
@Override
public InteractionResult use(BlockState state, Level level, BlockPos pos,
                          Player player, InteractionHand hand, BlockHitResult hit) {
    if (hand == InteractionHand.MAIN_HAND) {
        if (!level.isClientSide) {
            var soundEntry = AllSoundEvents.RUBBER_DUCK_QUACK;
            level.playSound(
                null,  // null = everyone nearby hears it
                pos,
                soundEntry.getMainEvent(),
                SoundSource.BLOCKS,
                soundEntry.getVolume(),
                soundEntry.getPitch()
            );
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
    return InteractionResult.PASS;
}
```

### Sound on Place/Break

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

@Override
public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
    if (!state.is(newState.getBlock())) {  // Only if actually removed
        if (!level.isClientSide) {
            var soundEntry = AllSoundEvents.RUBBER_DUCK_BLOCK_EVENT;
            level.playSound(null, pos, soundEntry.getMainEvent(), SoundSource.BLOCKS,
                           soundEntry.getVolume(), soundEntry.getPitch());
        }
    }
    super.onRemove(state, level, pos, newState, isMoving);
}
```

### Random Pitch Variation

```java
var soundEntry = AllSoundEvents.RUBBER_DUCK_QUACK;
float pitchVariation = level.getRandom().nextFloat() * 0.2F - 0.1F;  // ±0.1

level.playSound(null, pos, soundEntry.getMainEvent(), SoundSource.BLOCKS,
               soundEntry.getVolume(), 
               soundEntry.getPitch() + pitchVariation);
```

## ✨ Particle Effects

### Spawn Particles on Interaction

```java
@Override
public InteractionResult use(BlockState state, Level level, BlockPos pos,
                          Player player, InteractionHand hand, BlockHitResult hit) {
    if (hand == InteractionHand.MAIN_HAND) {
        if (level.isClientSide) {  // Particles are client-side only
            // Spawn 8 happy villager particles
            for (int i = 0; i < 8; i++) {
                double offsetX = level.getRandom().nextDouble() * 0.5 - 0.25;
                double offsetY = level.getRandom().nextDouble() * 0.5;
                double offsetZ = level.getRandom().nextDouble() * 0.5 - 0.25;
                
                level.addParticle(
                    ParticleTypes.HAPPY_VILLAGER,
                    pos.getX() + 0.5 + offsetX,
                    pos.getY() + 0.5 + offsetY,
                    pos.getZ() + 0.5 + offsetZ,
                    0, 0, 0  // Velocity (x, y, z)
                );
            }
        } else {
            // Server-side logic
            player.displayClientMessage(Component.literal("Quack!"), true);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
    return InteractionResult.PASS;
}
```

### Ambient Particles (animateTick)

```java
@Override
public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
    // 10% chance each tick to spawn a particle
    if (random.nextInt(10) == 0) {
        double x = pos.getX() + 0.5 + (random.nextDouble() * 0.6 - 0.3);
        double y = pos.getY() + 0.5 + (random.nextDouble() * 0.6 - 0.3);
        double z = pos.getZ() + 0.5 + (random.nextDouble() * 0.6 - 0.3);
        
        level.addParticle(
            ParticleTypes.ENCHANT,
            x, y, z,
            0, 0.05, 0  // Slow upward drift
        );
    }
}
```

### Common Particle Types

```java
ParticleTypes.HAPPY_VILLAGER     // Green sparkles (positive effect)
ParticleTypes.HEART              // Hearts (love/health)
ParticleTypes.ENCHANT            // White letters (enchanting table)
ParticleTypes.END_ROD            // White beam
ParticleTypes.FLAME              // Fire particle
ParticleTypes.SMOKE              // Gray smoke
ParticleTypes.LARGE_SMOKE        // Larger smoke
ParticleTypes.CLOUD              // White puff
ParticleTypes.EXPLOSION          // Explosion particle
ParticleTypes.BUBBLE_POP         // Water bubble
ParticleTypes.SPLASH             // Water splash
ParticleTypes.DRIPPING_WATER     // Water drip
ParticleTypes.FALLING_DUST       // Falling dust (requires BlockState parameter)
ParticleTypes.SOUL               // Soul sand particles
ParticleTypes.SOUL_FIRE_FLAME    // Blue flame
ParticleTypes.NAUTILUS           // Conduit particles
ParticleTypes.WAX_ON             // Honeycomb wax particles
ParticleTypes.WAX_OFF            // Wax removal particles
ParticleTypes.ELECTRIC_SPARK     // Lightning particles
```

## 💊 Mob Effects (Buffs/Debuffs)

### Give Player Effect on Interaction

```java
@Override
public InteractionResult use(BlockState state, Level level, BlockPos pos,
                          Player player, InteractionHand hand, BlockHitResult hit) {
    if (hand == InteractionHand.MAIN_HAND && !level.isClientSide) {
        // Give Regeneration II for 30 seconds (600 ticks)
        player.addEffect(new MobEffectInstance(
            MobEffects.REGENERATION,  // Effect type
            600,                      // Duration in ticks (20 ticks = 1 second)
            1,                        // Amplifier (0 = I, 1 = II, 2 = III, etc.)
            false,                    // Is ambient (from beacon)
            true                      // Show particles
        ));
        
        return InteractionResult.SUCCESS;
    }
    return InteractionResult.sidedSuccess(level.isClientSide);
}
```

### Multiple Effects

```java
if (!level.isClientSide) {
    int duration = DucksGaloreConfig.COMMON.effectDurationSeconds.get() * 20;
    
    // Regeneration II
    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duration, 1));
    
    // Absorption II (golden hearts)
    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, duration * 3, 1));
    
    // Speed I
    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, 0));
}
```

### Common Mob Effects

```java
MobEffects.MOVEMENT_SPEED         // Speed
MobEffects.MOVEMENT_SLOWDOWN      // Slowness
MobEffects.DIG_SPEED              // Haste
MobEffects.DIG_SLOWDOWN           // Mining Fatigue
MobEffects.DAMAGE_BOOST           // Strength
MobEffects.HEAL                   // Instant Health
MobEffects.HARM                   // Instant Damage
MobEffects.JUMP                   // Jump Boost
MobEffects.CONFUSION              // Nausea
MobEffects.REGENERATION           // Regeneration
MobEffects.DAMAGE_RESISTANCE      // Resistance
MobEffects.FIRE_RESISTANCE        // Fire Resistance
MobEffects.WATER_BREATHING        // Water Breathing
MobEffects.INVISIBILITY           // Invisibility
MobEffects.BLINDNESS              // Blindness
MobEffects.NIGHT_VISION           // Night Vision
MobEffects.HUNGER                 // Hunger
MobEffects.WEAKNESS               // Weakness
MobEffects.POISON                 // Poison
MobEffects.WITHER                 // Wither
MobEffects.HEALTH_BOOST           // Health Boost
MobEffects.ABSORPTION             // Absorption (golden hearts)
MobEffects.SATURATION             // Saturation
MobEffects.GLOWING                // Glowing
MobEffects.LEVITATION             // Levitation
MobEffects.LUCK                   // Luck
MobEffects.UNLUCK                 // Bad Luck
MobEffects.SLOW_FALLING           // Slow Falling
MobEffects.CONDUIT_POWER          // Conduit Power
MobEffects.DOLPHINS_GRACE         // Dolphin's Grace
MobEffects.BAD_OMEN               // Bad Omen
MobEffects.HERO_OF_THE_VILLAGE    // Hero of the Village
```

### Remove Effect

```java
// Remove specific effect
player.removeEffect(MobEffects.POISON);

// Remove all effects
player.removeAllEffects();

// Check if player has effect
if (player.hasEffect(MobEffects.REGENERATION)) {
    // Do something
}
```

## 💡 Light Emission

### Static Light Level

In block registration:

```java
public static final RegistryObject<Block> GLOWING_DUCK =
    BLOCKS.register("glowing_duck",
        () -> new MyBlock(BlockBehaviour.Properties.of()
            .lightLevel(state -> 15)  // Full brightness (0-15)
        ));
```

### Dynamic Light Level (Based on State)

```java
.lightLevel(state -> {
    if (state.getValue(POWERED)) {
        return 15;  // Full light when powered
    }
    return 0;  // No light when unpowered
})
```

### Progressive Light Levels

```java
.lightLevel(state -> {
    int level = state.getValue(POWER_LEVEL);  // 0-4
    return level * 3;  // 0, 3, 6, 9, 12
})
```

## 🧭 Directional Blocks

### Horizontal Facing (4 directions)

```java
public class MyDirectionalBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    
    public MyDirectionalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(FACING, Direction.NORTH));
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Face away from the player
        return this.defaultBlockState()
            .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    
    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }
    
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}
```

### All Directions (6 directions)

```java
public class MyFullDirectionalBlock extends DirectionalBlock {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    
    public MyFullDirectionalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(FACING, Direction.NORTH));
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Face the direction the player is looking
        return this.defaultBlockState()
            .setValue(FACING, context.getNearestLookingDirection().getOpposite());
    }
}
```

## 🎮 Block State Properties

### Boolean Property

```java
public class MyBlock extends Block {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    
    public MyBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(POWERED, false));
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }
    
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, ...) {
        if (!level.isClientSide) {
            // Toggle the powered state
            boolean powered = state.getValue(POWERED);
            level.setBlock(pos, state.setValue(POWERED, !powered), 3);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
```

### Integer Property

```java
public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 4);

// In constructor
this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0));

// In createBlockStateDefinition
builder.add(LEVEL);

// Usage
int currentLevel = state.getValue(LEVEL);
level.setBlock(pos, state.setValue(LEVEL, currentLevel + 1), 3);
```

### Enum Property

```java
public enum DuckType {
    RUBBER, GOLDEN, SPECIAL
}

public static final EnumProperty<DuckType> TYPE = 
    EnumProperty.create("type", DuckType.class);

// In constructor
this.registerDefaultState(this.stateDefinition.any()
    .setValue(TYPE, DuckType.RUBBER));

// In createBlockStateDefinition
builder.add(TYPE);

// Usage
DuckType type = state.getValue(TYPE);
level.setBlock(pos, state.setValue(TYPE, DuckType.GOLDEN), 3);
```

## 🔄 Periodic Effects (Random Ticks)

### Random Tick

```java
// In block properties
.randomTicks()

// In block class
@Override
public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
    // 33% chance to do something
    if (random.nextInt(3) == 0) {
        // Spawn item
        ItemEntity item = new ItemEntity(level, 
            pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
            new ItemStack(Items.GOLD_NUGGET));
        level.addFreshEntity(item);
    }
}
```

## 🎯 Config-Based Behavior

### Check Config Values

```java
@Override
public InteractionResult use(BlockState state, Level level, BlockPos pos,
                          Player player, InteractionHand hand, BlockHitResult hit) {
    if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;
    
    // Check if effects are enabled in config
    if (!DucksGaloreConfig.COMMON.enableGoldenDuckEffects.get()) {
        return InteractionResult.PASS;
    }
    
    if (!level.isClientSide) {
        // Get duration from config
        int durationTicks = DucksGaloreConfig.COMMON.effectDurationSeconds.get() * 20;
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, durationTicks, 1));
    }
    
    return InteractionResult.sidedSuccess(level.isClientSide);
}
```

## 📚 Complete Example: Interactive Block

Here's a complete example combining multiple patterns:

```java
public class AdvancedDuckBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    
    public AdvancedDuckBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(FACING, Direction.NORTH)
            .setValue(ACTIVATED, false));
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, ACTIVATED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
            .setValue(FACING, context.getHorizontalDirection().getOpposite())
            .setValue(ACTIVATED, false);
    }
    
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                              Player player, InteractionHand hand, BlockHitResult hit) {
        if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;
        
        if (!level.isClientSide) {
            boolean wasActivated = state.getValue(ACTIVATED);
            
            if (!wasActivated) {
                // Activate the block
                level.setBlock(pos, state.setValue(ACTIVATED, true), 3);
                
                // Play sound
                var soundEntry = AllSoundEvents.RUBBER_DUCK_QUACK;
                level.playSound(null, pos, soundEntry.getMainEvent(), 
                              SoundSource.BLOCKS, soundEntry.getVolume(), soundEntry.getPitch());
                
                // Give player effects
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1));
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1800, 1));
            }
        } else {
            // Client-side particles
            for (int i = 0; i < 8; i++) {
                double offsetX = level.getRandom().nextDouble() * 0.5 - 0.25;
                double offsetY = level.getRandom().nextDouble() * 0.5;
                double offsetZ = level.getRandom().nextDouble() * 0.5 - 0.25;
                level.addParticle(ParticleTypes.HAPPY_VILLAGER, 
                    pos.getX() + 0.5 + offsetX, 
                    pos.getY() + 0.5 + offsetY, 
                    pos.getZ() + 0.5 + offsetZ, 
                    0, 0, 0);
            }
        }
        
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
    
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(ACTIVATED) && random.nextInt(5) == 0) {
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 0.5;
            double z = pos.getZ() + 0.5;
            level.addParticle(ParticleTypes.ENCHANT, x, y, z, 0, 0.05, 0);
        }
    }
}
```

## 📚 See Also

- [How to Add a Block](how-to-add-a-block.md) - Complete block creation guide
- [Sound System](sound-system.md) - Detailed sound documentation
- [Configuration System](config-system.md) - Using config values

---

**Key Takeaways:**
1. Always check `InteractionHand.MAIN_HAND` to prevent double-triggering
2. Server-side (`!level.isClientSide`) for gameplay, client-side for visuals
3. Particles are client-side only
4. Sounds should be played on server-side
5. Use config values to make features toggleable

