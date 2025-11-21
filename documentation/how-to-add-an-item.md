# How to Add an Item

This guide explains how to add standalone items (not block items) to the Ducks Galore mod.

## 📋 Overview

Adding an item requires:
1. **Create the item class** (optional - can use vanilla `Item` class)
2. **Register the item** in `AllItems.java`
3. **Add item model** in `models/item/`
4. **Add item texture** in `textures/item/`
5. **Add translation** in `lang/en_us.json`

## 🎯 Simple Item (Using Vanilla Item Class)

For basic items without special behavior, use the vanilla `Item` class:

### Step 1: Register in AllItems.java

```java
public class AllItems {
    private static final DeferredRegister<Item> ITEMS = DucksGalore.ITEMS;
    
    // ... existing items ...
    
    public static final RegistryObject<Item> DUCK_FEATHER = 
            ITEMS.register("duck_feather",
                    () -> new Item(new Item.Properties()
                            .stacksTo(64)
                            .rarity(Rarity.COMMON)));
    
    public static final RegistryObject<Item> GOLDEN_DUCK_NUGGET = 
            ITEMS.register("golden_duck_nugget",
                    () -> new Item(new Item.Properties()
                            .stacksTo(64)
                            .rarity(Rarity.UNCOMMON)
                            .fireResistant()));
    
    public static void register() {
        DucksGalore.LOGGER.info("Ducks Galore items initialized");
    }
}
```

### Item Properties

```java
new Item.Properties()
    .stacksTo(64)                    // Max stack size (1-99, default 64)
    .stacksTo(1)                     // Non-stackable (tools, weapons)
    .durability(250)                 // Item durability (also makes it non-stackable)
    .rarity(Rarity.COMMON)          // COMMON (white), UNCOMMON (yellow), RARE (aqua), EPIC (magenta)
    .fireResistant()                 // Item doesn't burn in lava/fire
    .food(FoodProperties.Builder...) // Makes the item edible
```

### Step 2: Create Item Model

Create `src/main/resources/assets/ducksgalore/models/item/duck_feather.json`:

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "ducksgalore:item/duck_feather"
  }
}
```

💡 **Parent Types:**
- `minecraft:item/generated` - Flat 2D item (most common)
- `minecraft:item/handheld` - For tools/weapons (slightly different rotation)

### Step 3: Create Item Texture

Create a 16x16 PNG texture:
`src/main/resources/assets/ducksgalore/textures/item/duck_feather.png`

### Step 4: Add Translation

Add to `src/main/resources/assets/ducksgalore/lang/en_us.json`:

```json
{
  "item.ducksgalore.duck_feather": "Duck Feather",
  "item.ducksgalore.golden_duck_nugget": "Golden Duck Nugget"
}
```

## 🍖 Food Items

### Basic Food Item

```java
public static final RegistryObject<Item> DUCK_MEAT = 
        ITEMS.register("duck_meat",
                () -> new Item(new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(3)           // Hunger restored
                                .saturationMod(0.3F)    // Saturation multiplier
                                .meat()                 // Marks as meat (for dogs)
                                .build())));
```

### Food with Effects

```java
public static final RegistryObject<Item> GOLDEN_DUCK_MEAT = 
        ITEMS.register("golden_duck_meat",
                () -> new Item(new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(6)
                                .saturationMod(0.8F)
                                .meat()
                                .alwaysEat()  // Can eat even when full
                                .effect(() -> new MobEffectInstance(
                                    MobEffects.REGENERATION, 
                                    200,  // Duration in ticks
                                    1     // Amplifier (0 = I, 1 = II)
                                ), 1.0F)  // Probability (1.0 = 100%)
                                .build())));
```

### Fast Eating Item

```java
public static final RegistryObject<Item> DUCK_SNACK = 
        ITEMS.register("duck_snack",
                () -> new Item(new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(2)
                                .saturationMod(0.1F)
                                .fast()  // Eats faster (like dried kelp)
                                .build())));
```

## 🔧 Custom Item Classes

For items with special behavior, create a custom class:

### Simple Custom Item

```java
package com.hutizaki.ducksgalore.content.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DuckWandItem extends Item {
    
    public DuckWandItem(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        
        if (!level.isClientSide) {
            // Do something when right-clicked
            player.displayClientMessage(
                net.minecraft.network.chat.Component.literal("Duck wand activated!"), 
                true
            );
            
            // Optional: damage the item
            stack.hurtAndBreak(1, player, (p) -> {
                p.broadcastBreakEvent(hand);
            });
        }
        
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}
```

### Register Custom Item

```java
public static final RegistryObject<Item> DUCK_WAND = 
        ITEMS.register("duck_wand",
                () -> new DuckWandItem(new Item.Properties()
                        .stacksTo(1)
                        .durability(100)
                        .rarity(Rarity.RARE)));
```

## ⚔️ Tool Items

### Custom Tool

```java
package com.hutizaki.ducksgalore.content.items;

import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.tags.BlockTags;

public class DuckPickaxeItem extends DiggerItem {
    
    public DuckPickaxeItem(Tier tier, int attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(
            attackDamageModifier,
            attackSpeedModifier,
            tier,
            BlockTags.MINEABLE_WITH_PICKAXE,
            properties
        );
    }
}
```

### Register Tool

```java
public static final RegistryObject<Item> GOLDEN_DUCK_PICKAXE = 
        ITEMS.register("golden_duck_pickaxe",
                () -> new DuckPickaxeItem(
                    Tiers.GOLD,        // Tool tier
                    1,                  // Attack damage modifier
                    -2.8F,              // Attack speed modifier
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(250)));
```

### Custom Tool Tier

```java
package com.hutizaki.ducksgalore.content.items;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;

public enum DuckToolTiers implements Tier {
    DUCK(
        BlockTags.INCORRECT_FOR_IRON_TOOL,  // Incorrect blocks tag
        500,                                  // Durability
        6.0F,                                // Mining speed
        2.0F,                                // Attack damage bonus
        14,                                  // Enchantability
        () -> Ingredient.of(AllItems.GOLDEN_DUCK_NUGGET.get())  // Repair ingredient
    );
    
    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;
    
    DuckToolTiers(TagKey<Block> incorrectBlocksForDrops, int uses, float speed, 
                  float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }
    
    @Override
    public int getUses() { return uses; }
    
    @Override
    public float getSpeed() { return speed; }
    
    @Override
    public float getAttackDamageBonus() { return damage; }
    
    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() { 
        return incorrectBlocksForDrops; 
    }
    
    @Override
    public int getEnchantmentValue() { return enchantmentValue; }
    
    @Override
    public Ingredient getRepairIngredient() { 
        return repairIngredient.get(); 
    }
}
```

## 🎨 Multi-Layer Item Textures

For items with multiple texture layers (like potions):

### Item Model with Layers

`models/item/special_duck_item.json`:
```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "ducksgalore:item/special_duck_base",
    "layer1": "ducksgalore:item/special_duck_overlay"
  }
}
```

The layers are rendered on top of each other, with `layer1` on top of `layer0`.

## 🪣 Bucket Item

### Custom Fluid Bucket

```java
public static final RegistryObject<Item> DUCK_FLUID_BUCKET = 
        ITEMS.register("duck_fluid_bucket",
                () -> new BucketItem(
                    AllFluids.DUCK_FLUID,  // Your custom fluid
                    new Item.Properties()
                            .craftRemainder(Items.BUCKET)
                            .stacksTo(1)));
```

## 🎯 Advanced Item Behaviors

### Item with Cooldown

```java
@Override
public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    ItemStack stack = player.getItemInHand(hand);
    
    if (!level.isClientSide) {
        // Set cooldown (100 ticks = 5 seconds)
        player.getCooldowns().addCooldown(this, 100);
        
        // Do something
        player.displayClientMessage(Component.literal("Activated!"), true);
    }
    
    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
}
```

### Item That Spawns Entity

```java
@Override
public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    ItemStack stack = player.getItemInHand(hand);
    
    if (!level.isClientSide) {
        // Spawn a chicken at player's position
        Chicken chicken = new Chicken(EntityType.CHICKEN, level);
        chicken.setPos(player.getX(), player.getY(), player.getZ());
        level.addFreshEntity(chicken);
        
        // Consume item in survival
        if (!player.isCreative()) {
            stack.shrink(1);
        }
    }
    
    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
}
```

### Item with NBT Data

```java
@Override
public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    ItemStack stack = player.getItemInHand(hand);
    
    if (!level.isClientSide) {
        // Get or create NBT
        CompoundTag tag = stack.getOrCreateTag();
        
        // Store data
        int uses = tag.getInt("uses");
        uses++;
        tag.putInt("uses", uses);
        
        player.displayClientMessage(
            Component.literal("Used " + uses + " times"), 
            true
        );
    }
    
    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
}
```

### Item with Custom Tooltip

```java
@Override
public void appendHoverText(ItemStack stack, @Nullable Level level, 
                           List<Component> tooltip, TooltipFlag flag) {
    // Add custom tooltip lines
    tooltip.add(Component.literal("Special duck item").withStyle(ChatFormatting.GOLD));
    tooltip.add(Component.literal("Right-click to use").withStyle(ChatFormatting.GRAY));
    
    // Add NBT data to tooltip
    if (stack.hasTag()) {
        int uses = stack.getTag().getInt("uses");
        tooltip.add(Component.literal("Uses: " + uses).withStyle(ChatFormatting.BLUE));
    }
    
    super.appendHoverText(stack, level, tooltip, flag);
}
```

## 🎨 Item Rendering

### Custom Item Color

For items that need dynamic coloring (like leather armor):

```java
// In DucksGaloreClient.java
@SubscribeEvent
public static void onClientSetup(FMLClientSetupEvent event) {
    event.enqueueWork(() -> {
        ItemColors itemColors = Minecraft.getInstance().getItemColors();
        
        itemColors.register((stack, tintIndex) -> {
            // Return color based on tint index
            return tintIndex == 0 ? 0xFF0000 : 0xFFFFFF;  // Red for layer 0, white for others
        }, AllItems.COLORED_DUCK_ITEM.get());
    });
}
```

## 📋 Item Checklist

When adding a new item:

- [ ] Register item in `AllItems.java`
- [ ] Create item model JSON (`models/item/`)
- [ ] Create item texture PNG (`textures/item/`)
- [ ] Add translation (`lang/en_us.json`)
- [ ] Add to creative tab (automatically handled by `AllItems.getAllItems()`)
- [ ] Create recipe if craftable (`data/ducksgalore/recipes/`)
- [ ] Add to item tags if needed (`data/ducksgalore/tags/items/`)

## 🐛 Common Issues

### Item doesn't appear in creative tab
- Verify `AllItems.getAllItems()` includes the item
- Check creative tab registration in `AllCreativeModeTabs`

### Missing item texture (purple/black)
- Check texture path matches model reference
- Ensure texture file is 16x16 PNG
- Verify texture location is correct

### Item doesn't stack
- Check `stacksTo()` is set correctly
- Items with durability can't stack
- Items with NBT data can't stack by default

### Tool doesn't work on blocks
- Verify correct tag is used (`BlockTags.MINEABLE_WITH_PICKAXE`, etc.)
- Check tool tier is appropriate
- Ensure blocks have proper mining tags

## 📚 Examples from Vanilla

Study these vanilla items for reference:
- `Items.DIAMOND` - Simple item
- `Items.APPLE` - Food item  
- `Items.GOLDEN_APPLE` - Food with effects
- `Items.DIAMOND_PICKAXE` - Tool item
- `Items.ENDER_PEARL` - Throwable item
- `Items.WRITTEN_BOOK` - Item with NBT

## 📚 See Also

- [How to Add a Block](how-to-add-a-block.md) - Adding block items
- [Registration System](registration-system.md) - How registration works
- [Resource Files](resource-files.md) - Creating models and textures

---

**Key Takeaways:**
1. Use vanilla `Item` class for simple items
2. Create custom classes for special behaviors
3. Item models usually use `minecraft:item/generated` parent
4. Server-side logic in `!level.isClientSide`
5. Don't forget translation keys

