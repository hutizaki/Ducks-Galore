# Sound System

This document explains the custom sound system in Ducks Galore, inspired by the Create mod's approach.

## 🎵 Overview

The mod uses a **builder pattern** for sound registration, making it easy to:
- Register custom sounds
- Set volume, pitch, and categories
- Wrap vanilla sounds
- Add subtitles for accessibility

## 🏗️ Architecture

### The SoundEntry System

```java
public static final SoundEntry RUBBER_DUCK_QUACK = create("rubber_duck_quack")
    .subtitle("Rubber duck quacks")
    .category(SoundSource.BLOCKS)
    .build();
```

This creates a `SoundEntry` that wraps a `SoundEvent` with additional properties.

### Why a Custom System?

Instead of registering raw `SoundEvent` objects, we use `SoundEntry` because:
1. **Encapsulates configuration** - Volume, pitch, subtitle in one place
2. **Cleaner code** - Play sounds with `.getMainEvent()` instead of multiple parameters
3. **Reusable** - Same sound can have different volumes/pitches
4. **Wraps vanilla sounds** - Can use Minecraft sounds with custom settings

## 📝 Basic Sound Registration

### Step 1: Register in AllSoundEvents.java

```java
public class AllSoundEvents {
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DucksGalore.SOUND_EVENTS;
    public static final Map<ResourceLocation, SoundEntry> ALL = new HashMap<>();
    
    // Register a new custom sound
    public static final SoundEntry MY_SOUND = create("my_sound")
        .subtitle("My custom sound")
        .category(SoundSource.BLOCKS)
        .build();
    
    private static SoundEntryBuilder create(String name) {
        return new SoundEntryBuilder(name);
    }
    
    public static void register() {
        DucksGalore.LOGGER.info("Ducks Galore sound events initialized");
    }
}
```

### Step 2: Create sounds.json

Create `src/main/resources/assets/ducksgalore/sounds.json`:

```json
{
  "my_sound": {
    "subtitle": "My custom sound",
    "sounds": [
      "ducksgalore:my_sound"
    ]
  },
  "rubber_duck_quack": {
    "subtitle": "Rubber duck quacks",
    "sounds": [
      "ducksgalore:rubber_duck_quack"
    ]
  }
}
```

### Step 3: Add Sound File

Add your `.ogg` file:
`src/main/resources/assets/ducksgalore/sounds/my_sound.ogg`

🎼 **Sound File Requirements:**
- Format: **OGG Vorbis** (.ogg)
- Sample rate: **44100 Hz** recommended
- Channels: **Mono** (stereo works but wastes space)
- Bitrate: **96-128 kbps** for sound effects

💡 **Tip:** Use [Audacity](https://www.audacityteam.org/) to convert audio files:
1. File → Export → Export as OGG
2. Set quality to 5-6 (96-128 kbps)

## 🎯 Playing Sounds

### In Block Code

```java
@Override
public InteractionResult use(BlockState state, Level level, BlockPos pos,
                          Player player, InteractionHand hand, BlockHitResult hit) {
    if (!level.isClientSide) {
        // Play a sound at the block position
        var soundEntry = AllSoundEvents.RUBBER_DUCK_QUACK;
        level.playSound(
            null,                           // Player (null = everyone hears it)
            pos,                            // Position
            soundEntry.getMainEvent(),      // SoundEvent
            SoundSource.BLOCKS,             // Category
            soundEntry.getVolume(),         // Volume
            soundEntry.getPitch()           // Pitch
        );
    }
    return InteractionResult.sidedSuccess(level.isClientSide);
}
```

### With Random Pitch Variation

```java
var soundEntry = AllSoundEvents.RUBBER_DUCK_QUACK;
level.playSound(
    null, 
    pos, 
    soundEntry.getMainEvent(), 
    SoundSource.BLOCKS,
    soundEntry.getVolume(), 
    soundEntry.getPitch() + (level.getRandom().nextFloat() * 0.2F - 0.1F)  // ±0.1 variation
);
```

### At Player Position

```java
player.playSound(
    AllSoundEvents.RUBBER_DUCK_QUACK.getMainEvent(),
    1.0F,  // Volume
    1.0F   // Pitch
);
```

### With Distance Attenuation

```java
level.playSound(
    player,                              // Only this player hears it
    pos,
    soundEntry.getMainEvent(),
    SoundSource.BLOCKS,
    1.0F,                               // Volume
    1.0F                                // Pitch
);
```

## 🔧 Sound Builder Options

### Basic Builder

```java
public static final SoundEntry MY_SOUND = create("my_sound")
    .subtitle("Subtitle for accessibility")
    .category(SoundSource.BLOCKS)
    .build();
```

### Available Categories

```java
SoundSource.MASTER        // Master volume
SoundSource.MUSIC         // Music & Jukebox
SoundSource.RECORDS       // Records/Music Discs
SoundSource.WEATHER       // Rain, thunder
SoundSource.BLOCKS        // Block sounds (most common for blocks)
SoundSource.HOSTILE       // Hostile mobs
SoundSource.NEUTRAL       // Neutral/friendly mobs
SoundSource.PLAYERS       // Player sounds
SoundSource.AMBIENT       // Ambient sounds (caves)
SoundSource.VOICE         // Voice/speech
```

### Using Vanilla Sounds

Sometimes you want to use Minecraft's sounds with custom settings:

```java
public static final SoundEntry GOLD_ORE_BLOCK_EVENT = create("gold_ore_rubber_duck_block_event")
    .subtitle("Gold ore rubber duck placed/removed")
    .playExisting(SoundEvents.STONE_PLACE, 1.0f, 1.0f)
    .category(SoundSource.BLOCKS)
    .build();
```

This registers a `SoundEvent` but plays the vanilla stone sound.

## 📊 sounds.json Reference

### Simple Sound

```json
{
  "my_sound": {
    "subtitle": "My Sound",
    "sounds": [
      "ducksgalore:my_sound"
    ]
  }
}
```

### Sound with Variants

Multiple sound files, one is randomly chosen:

```json
{
  "duck_quack": {
    "subtitle": "Duck quacks",
    "sounds": [
      "ducksgalore:duck_quack1",
      "ducksgalore:duck_quack2",
      "ducksgalore:duck_quack3"
    ]
  }
}
```

### Sound with Custom Settings

```json
{
  "loud_explosion": {
    "subtitle": "Loud explosion",
    "sounds": [
      {
        "name": "ducksgalore:explosion",
        "volume": 1.5,
        "pitch": 0.8,
        "stream": false,
        "attenuation_distance": 32,
        "preload": true
      }
    ]
  }
}
```

**Sound Properties:**
- `volume` - Base volume (0.0 - 1.0+)
- `pitch` - Base pitch (0.5 - 2.0)
- `stream` - Stream instead of loading into memory (for music)
- `attenuation_distance` - How far sound travels (default: 16)
- `preload` - Load sound immediately on game start
- `weight` - Probability of playing (when multiple sounds)

### Sound from Vanilla

```json
{
  "stone_sound": {
    "subtitle": "Stone sound",
    "sounds": [
      "minecraft:block/stone/break"
    ]
  }
}
```

## 🎨 Advanced Patterns

### Context-Sensitive Sounds

Different sounds based on conditions:

```java
@Override
public InteractionResult use(BlockState state, Level level, BlockPos pos,
                          Player player, InteractionHand hand, BlockHitResult hit) {
    if (!level.isClientSide) {
        // Choose sound based on player's health
        SoundEntry sound = player.getHealth() > 10 
            ? AllSoundEvents.HAPPY_QUACK 
            : AllSoundEvents.SAD_QUACK;
            
        level.playSound(null, pos, sound.getMainEvent(), 
                       SoundSource.BLOCKS, sound.getVolume(), sound.getPitch());
    }
    return InteractionResult.sidedSuccess(level.isClientSide);
}
```

### Looping Sounds

For continuous sounds (like a running machine):

```java
// In your block entity or entity class
private void tickSound() {
    if (level.isClientSide) {
        // Check if sound should be playing
        if (this.shouldPlaySound && this.soundInstance == null) {
            // Start sound
            this.soundInstance = SimpleSoundInstance.forLocalAmbience(
                AllSoundEvents.MY_LOOPING_SOUND.getMainEvent(),
                1.0F,
                1.0F
            );
            Minecraft.getInstance().getSoundManager().play(soundInstance);
        } else if (!this.shouldPlaySound && this.soundInstance != null) {
            // Stop sound
            Minecraft.getInstance().getSoundManager().stop(soundInstance);
            this.soundInstance = null;
        }
    }
}
```

⚠️ **Note:** Looping sounds require more complex setup and should use `stream: true` in sounds.json.

### Distance-Based Volume

Adjust volume based on distance:

```java
double distance = player.distanceToSqr(Vec3.atCenterOf(pos));
float volume = (float) Math.max(0, 1.0 - (distance / 256.0)); // Fade over 16 blocks

level.playSound(null, pos, sound.getMainEvent(), 
               SoundSource.BLOCKS, volume, sound.getPitch());
```

### Pitch Variation by Block State

```java
int variant = state.getValue(VARIANT); // 0-3
float pitchAdjustment = variant * 0.2F; // 0.0, 0.2, 0.4, 0.6

level.playSound(null, pos, sound.getMainEvent(), 
               SoundSource.BLOCKS, 
               sound.getVolume(), 
               sound.getPitch() + pitchAdjustment);
```

## 🐛 Troubleshooting

### Sound doesn't play
1. Check `sounds.json` exists and is valid JSON
2. Verify sound file path matches exactly
3. Ensure sound file is .ogg format
4. Check console for "missing sound" errors
5. Verify `SoundEvent` is registered

### Sound plays for client but not server (or vice versa)
- Sounds should be played on **server side** (`!level.isClientSide`)
- Use `null` for the player parameter to broadcast to all players

### Sound is too quiet/loud
- Adjust volume in `sounds.json` or when playing
- Check client's sound category volume in settings
- Ensure correct `SoundSource` category is used

### Sound has wrong pitch
- Reset pitch to `1.0F` for normal playback
- Check for pitch modifications in code
- Verify source audio isn't already pitched

### Can't hear sound far away
- Increase `attenuation_distance` in `sounds.json`
- Default is 16 blocks
- Music typically uses much larger values (e.g., 64+)

## 📋 Adding New Sounds Checklist

- [ ] Register `SoundEntry` in `AllSoundEvents.java`
- [ ] Add sound definition to `sounds.json`
- [ ] Add `.ogg` file to `assets/ducksgalore/sounds/`
- [ ] Add subtitle translation to `lang/en_us.json`
- [ ] Test sound plays correctly in-game
- [ ] Test subtitle appears (if enabled in settings)
- [ ] Test volume is appropriate
- [ ] Test pitch is appropriate

## 🎼 Creating Good Sound Effects

### Guidelines

1. **Keep it short** - Most block sounds should be 0.5-2 seconds
2. **Avoid clipping** - Make sure audio doesn't distort
3. **Normalize volume** - Aim for -6dB to -12dB peak
4. **Use mono** - Stereo is unnecessary for positional sounds
5. **Match Minecraft's style** - Listen to vanilla sounds for reference

### Recommended Tools

- **Audacity** - Free audio editor
- **Freesound.org** - Free sound effects library
- **Bfxr** - Retro sound effect generator

### Example Audacity Workflow

1. Import/record your sound
2. Effect → Normalize (to -6 dB)
3. Effect → Noise Reduction (if needed)
4. Tracks → Stereo Track to Mono
5. File → Export → Export as OGG
6. Quality: 5-6 (96-128 kbps)

## 📚 Examples from the Mod

### Simple Interactive Sound
```java
// RubberDuckBlock.java
var soundEntry = AllSoundEvents.RUBBER_DUCK_QUACK;
level.playSound(null, pos, soundEntry.getMainEvent(), SoundSource.BLOCKS, 
               soundEntry.getVolume(), soundEntry.getPitch() + (level.getRandom().nextFloat() * 0.1F));
```

### Block Place/Break Sound
```java
// GoldenRubberDuckBlock.java - onPlace()
var soundEntry = AllSoundEvents.GOLDEN_RUBBER_DUCK_BLOCK_EVENT;
level.playSound(null, pos, soundEntry.getMainEvent(), SoundSource.BLOCKS, 
               soundEntry.getVolume(), soundEntry.getPitch());
```

### Using Vanilla Sound
```java
// AllSoundEvents.java
GOLD_ORE_RUBBER_DUCK_BLOCK_EVENT = create("gold_ore_rubber_duck_block_event")
    .subtitle("Gold ore rubber duck placed/removed")
    .playExisting(SoundEvents.STONE_PLACE, 1.0f, 1.0f)
    .category(SoundSource.BLOCKS)
    .build();
```

## 📚 See Also

- [How to Add a Block](how-to-add-a-block.md#adding-sounds) - Adding sounds to blocks
- [Block Behavior Patterns](block-behavior-patterns.md) - When to play sounds
- [Resource Files](resource-files.md) - Resource file structure

---

**Key Takeaways:**
1. Use the builder pattern for consistent sound registration
2. Always play sounds on the server side (`!level.isClientSide`)
3. Sound files must be OGG format
4. Add subtitles for accessibility
5. Use appropriate `SoundSource` categories

