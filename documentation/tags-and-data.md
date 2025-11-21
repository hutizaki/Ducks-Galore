# Tags and Data Generation

This document covers how to use tags and data generation in Ducks Galore.

## 🏷️ Tags Overview

Tags are used to group blocks and items for shared behavior without hardcoding specific blocks/items. This makes mods more compatible with each other.

### Why Use Tags?

- **Mod compatibility** - Other mods can add their blocks to your tags
- **Data-driven** - Change behavior without code changes
- **Flexible** - One block can have multiple tags
- **Standard practice** - Minecraft and Forge use tags extensively

## 📂 Tag File Locations

```
data/
├── ducksgalore/              # Your mod's tags
│   └── tags/
│       ├── blocks/
│       │   └── rubber_ducks.json
│       └── items/
│           └── rubber_ducks.json
│
├── minecraft/                # Vanilla tags
│   └── tags/
│       └── blocks/
│           ├── mineable/
│           │   ├── pickaxe.json
│           │   ├── axe.json
│           │   ├── shovel.json
│           │   └── hoe.json
│           ├── needs_stone_tool.json
│           ├── needs_iron_tool.json
│           ├── needs_diamond_tool.json
│           └── wool.json
│
└── forge/                    # Forge tags
    └── tags/
        ├── blocks/
        │   ├── ores.json
        │   └── storage_blocks.json
        └── items/
            ├── ingots.json
            └── gems.json
```

## 🔨 Block Tags

### Mineable Tags (REQUIRED for proper mining)

Defines what tool can mine the block:

`data/minecraft/tags/blocks/mineable/pickaxe.json`:
```json
{
  "replace": false,
  "values": [
    "ducksgalore:golden_rubber_duck",
    "ducksgalore:gold_ore_rubber_duck"
  ]
}
```

**Available mineable tags:**
- `minecraft:mineable/pickaxe` - Pickaxe required
- `minecraft:mineable/axe` - Axe required
- `minecraft:mineable/shovel` - Shovel required
- `minecraft:mineable/hoe` - Hoe required

### Tool Tier Tags (REQUIRED for drops)

Defines minimum tool tier for drops:

`data/minecraft/tags/blocks/needs_stone_tool.json`:
```json
{
  "replace": false,
  "values": [
    "ducksgalore:sturdy_duck_block"
  ]
}
```

**Available tier tags:**
- `minecraft:needs_stone_tool` - Stone pickaxe or better
- `minecraft:needs_iron_tool` - Iron pickaxe or better
- `minecraft:needs_diamond_tool` - Diamond pickaxe or better
- `forge:needs_netherite_tool` - Netherite pickaxe required

### Custom Mod Tags

Group your blocks together:

`data/ducksgalore/tags/blocks/rubber_ducks.json`:
```json
{
  "replace": false,
  "values": [
    "ducksgalore:rubber_duck",
    "ducksgalore:golden_rubber_duck",
    "ducksgalore:gold_ore_rubber_duck"
  ]
}
```

### Using Tags in Code

#### Define Tag in AllTags.java

```java
public class AllTags {
    public static class Blocks {
        public static final TagKey<Block> RUBBER_DUCKS = 
                create("rubber_ducks");
        
        public static final TagKey<Block> GOLDEN_BLOCKS = 
                create("golden_blocks");
        
        private static TagKey<Block> create(String path) {
            return TagKey.create(
                net.minecraft.core.registries.Registries.BLOCK,
                new ResourceLocation(DucksGalore.MOD_ID, path)
            );
        }
    }
}
```

#### Check if Block Has Tag

```java
// Check if a block is in a tag
if (state.is(AllTags.Blocks.RUBBER_DUCKS)) {
    // This is a rubber duck block
}

// Check vanilla tag
if (state.is(BlockTags.WOOL)) {
    // This is wool
}

// Check Forge tag
if (state.is(Tags.Blocks.ORES)) {
    // This is an ore
}
```

### Common Vanilla Block Tags

```java
BlockTags.PLANKS                    // All planks
BlockTags.LOGS                      // All logs
BlockTags.STONE_BRICKS              // Stone brick variants
BlockTags.WOOL                      // All wool colors
BlockTags.DIRT                      // Dirt, grass block, etc.
BlockTags.FLOWERS                   // All flowers
BlockTags.LEAVES                    // All leaves
BlockTags.FENCES                    // All fences
BlockTags.WALLS                     // All walls
BlockTags.BUTTONS                   // All buttons
BlockTags.DOORS                     // All doors
BlockTags.DRAGON_IMMUNE             // Immune to dragon
BlockTags.WITHER_IMMUNE             // Immune to wither
```

## 🎒 Item Tags

### Custom Item Tags

`data/ducksgalore/tags/items/rubber_ducks.json`:
```json
{
  "replace": false,
  "values": [
    "ducksgalore:rubber_duck",
    "ducksgalore:golden_rubber_duck",
    "ducksgalore:gold_ore_rubber_duck"
  ]
}
```

### Forge Convention Tags

Follow Forge's tag conventions for compatibility:

`data/forge/tags/items/ingots.json`:
```json
{
  "replace": false,
  "values": [
    "ducksgalore:golden_duck_ingot"
  ]
}
```

`data/forge/tags/items/gems.json`:
```json
{
  "replace": false,
  "values": [
    "ducksgalore:duck_gem"
  ]
}
```

### Using Item Tags in Code

#### Define Tag

```java
public class AllTags {
    public static class Items {
        public static final TagKey<Item> RUBBER_DUCKS = 
                create("rubber_ducks");
        
        public static final TagKey<Item> DUCK_FOOD = 
                create("duck_food");
        
        private static TagKey<Item> create(String path) {
            return TagKey.create(
                net.minecraft.core.registries.Registries.ITEM,
                new ResourceLocation(DucksGalore.MOD_ID, path)
            );
        }
    }
}
```

#### Check Item Tag

```java
ItemStack stack = player.getItemInHand(hand);

// Check if item is in tag
if (stack.is(AllTags.Items.RUBBER_DUCKS)) {
    // This is a rubber duck item
}

// Check vanilla tag
if (stack.is(ItemTags.PLANKS)) {
    // This is a plank
}
```

### Common Vanilla Item Tags

```java
ItemTags.PLANKS                     // All planks
ItemTags.LOGS                       // All logs
ItemTags.WOOL                       // All wool colors
ItemTags.FISHES                     // All fish items
ItemTags.COALS                      // Coal and charcoal
ItemTags.PICKAXES                   // All pickaxes
ItemTags.SWORDS                     // All swords
ItemTags.AXES                       // All axes
ItemTags.SHOVELS                    // All shovels
ItemTags.HOES                       // All hoes
```

### Common Forge Item Tags

```java
Tags.Items.INGOTS                   // All ingots
Tags.Items.GEMS                     // All gems
Tags.Items.ORES                     // All ore items
Tags.Items.DUSTS                    // All dusts
Tags.Items.NUGGETS                  // All nuggets
Tags.Items.RODS                     // All rods/sticks
Tags.Items.TOOLS                    // All tools
```

## 🎯 Using Tags in Recipes

### Recipe with Tag Ingredient

`data/ducksgalore/recipes/duck_from_any_plank.json`:
```json
{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "PPP",
    "P P",
    "PPP"
  ],
  "key": {
    "P": {
      "tag": "minecraft:planks"
    }
  },
  "result": {
    "item": "ducksgalore:rubber_duck"
  }
}
```

Now any plank type can be used!

### Multiple Tag Options

```json
{
  "type": "minecraft:crafting_shapeless",
  "ingredients": [
    {
      "tag": "forge:ingots/gold"
    },
    {
      "tag": "forge:ingots/iron"
    },
    {
      "item": "ducksgalore:rubber_duck"
    }
  ],
  "result": {
    "item": "ducksgalore:mixed_duck"
  }
}
```

## 📊 Data Generation

Data generation creates JSON files automatically from code.

### Why Use Data Generation?

- **Type-safe** - Compile-time checking
- **Consistent** - No typos in JSON
- **Maintainable** - Change one place, all files update
- **DRY** - Don't repeat yourself

### Basic Data Generator Setup

Currently commented out in `DucksGalore.java`, but here's how to set it up:

```java
@Mod.EventBusSubscriber(modid = DucksGalore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        
        // Client-side providers
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModLanguageProvider(output));
        
        // Server-side providers
        generator.addProvider(event.includeServer(), new ModRecipeProvider(output));
        generator.addProvider(event.includeServer(), new ModLootTableProvider(output));
        
        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(output, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(output, lookupProvider, blockTagsProvider, existingFileHelper));
    }
}
```

### Block State Provider

```java
public class ModBlockStateProvider extends BlockStateProvider {
    
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, DucksGalore.MOD_ID, exFileHelper);
    }
    
    @Override
    protected void registerStatesAndModels() {
        // Simple block (all sides same texture)
        simpleBlock(AllBlocks.SIMPLE_DUCK.get());
        
        // Block with item
        simpleBlockWithItem(AllBlocks.RUBBER_DUCK.get(), 
            cubeAll(AllBlocks.RUBBER_DUCK.get()));
        
        // Horizontal directional block
        horizontalBlock(AllBlocks.GOLDEN_RUBBER_DUCK.get(),
            models().cubeAll("golden_rubber_duck",
                blockTexture(AllBlocks.GOLDEN_RUBBER_DUCK.get())));
    }
}
```

### Item Model Provider

```java
public class ModItemModelProvider extends ItemModelProvider {
    
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DucksGalore.MOD_ID, existingFileHelper);
    }
    
    @Override
    protected void registerModels() {
        // Simple item (references block model)
        withExistingParent(AllItems.RUBBER_DUCK_ITEM.getId().getPath(),
            modLoc("block/rubber_duck"));
        
        // Generated item (flat 2D)
        basicItem(AllItems.DUCK_FEATHER.get());
        
        // Handheld item (tools/weapons)
        handheldItem(AllItems.DUCK_SWORD.get());
    }
    
    private ItemModelBuilder handheldItem(Item item) {
        return withExistingParent(item.toString(),
            new ResourceLocation("item/handheld")).texture("layer0",
            new ResourceLocation(DucksGalore.MOD_ID, "item/" + item.toString()));
    }
}
```

### Recipe Provider

```java
public class ModRecipeProvider extends RecipeProvider {
    
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }
    
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // Shaped crafting
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.GOLDEN_RUBBER_DUCK.get())
            .pattern("GGG")
            .pattern("GDG")
            .pattern("GGG")
            .define('G', Items.GOLD_INGOT)
            .define('D', AllBlocks.RUBBER_DUCK.get())
            .unlockedBy("has_rubber_duck", has(AllBlocks.RUBBER_DUCK.get()))
            .save(consumer);
        
        // Shapeless crafting
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.DUCK_FEATHER.get(), 4)
            .requires(AllBlocks.RUBBER_DUCK.get())
            .unlockedBy("has_rubber_duck", has(AllBlocks.RUBBER_DUCK.get()))
            .save(consumer);
        
        // Smelting
        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(AllBlocks.RUBBER_DUCK.get()),
                RecipeCategory.MISC,
                AllBlocks.GOLDEN_RUBBER_DUCK.get(),
                0.7F,   // Experience
                200)    // Cooking time in ticks
            .unlockedBy("has_rubber_duck", has(AllBlocks.RUBBER_DUCK.get()))
            .save(consumer, "golden_duck_from_smelting");
    }
}
```

### Loot Table Provider

```java
public class ModLootTableProvider extends LootTableProvider {
    
    public ModLootTableProvider(PackOutput output) {
        super(output, Collections.emptySet(), 
              List.of(new SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)));
    }
    
    private static class ModBlockLootTables extends BlockLootSubProvider {
        
        protected ModBlockLootTables() {
            super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
        }
        
        @Override
        protected void generate() {
            // Block drops itself
            dropSelf(AllBlocks.RUBBER_DUCK.get());
            dropSelf(AllBlocks.GOLDEN_RUBBER_DUCK.get());
            
            // Block drops different item
            add(AllBlocks.SPECIAL_DUCK.get(),
                block -> createSingleItemTable(AllItems.DUCK_FEATHER.get()));
            
            // Block requires silk touch
            add(AllBlocks.FRAGILE_DUCK.get(),
                block -> createSilkTouchDispatchTable(block,
                    applyExplosionDecay(block, 
                        LootItem.lootTableItem(Items.FEATHER)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))));
        }
        
        @Override
        protected Iterable<Block> getKnownBlocks() {
            return AllBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .collect(Collectors.toList());
        }
    }
}
```

### Tag Provider

```java
public class ModBlockTagsProvider extends BlockTagsProvider {
    
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, DucksGalore.MOD_ID, existingFileHelper);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Mineable with pickaxe
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(AllBlocks.GOLDEN_RUBBER_DUCK.get())
            .add(AllBlocks.GOLD_ORE_RUBBER_DUCK.get());
        
        // Needs iron tool
        tag(BlockTags.NEEDS_IRON_TOOL)
            .add(AllBlocks.GOLDEN_RUBBER_DUCK.get());
        
        // Custom mod tag
        tag(AllTags.Blocks.RUBBER_DUCKS)
            .add(AllBlocks.RUBBER_DUCK.get())
            .add(AllBlocks.GOLDEN_RUBBER_DUCK.get())
            .add(AllBlocks.GOLD_ORE_RUBBER_DUCK.get());
    }
}
```

### Running Data Generation

```bash
# Generate all data
gradlew runData

# The generated files appear in:
# src/generated/resources/
```

💡 **Tip:** Generated files go in `src/generated/resources/`. You can copy them to `src/main/resources/` and customize them.

## 📋 Tag Best Practices

1. **Always use `"replace": false`** - Unless you want to completely override
2. **Follow conventions** - Use Forge tags when appropriate
3. **Document custom tags** - Add comments explaining what they're for
4. **Keep tags organized** - Group related blocks/items
5. **Test compatibility** - Make sure other mods can add to your tags

## 📚 See Also

- [How to Add a Block](how-to-add-a-block.md#step-7-add-tags) - Adding tags to blocks
- [Resource Files](resource-files.md#-tags) - Tag file format reference
- [Forge Documentation](https://docs.minecraftforge.net/) - Official Forge docs

---

**Key Takeaways:**
1. Tags group blocks/items for shared behavior
2. Always include mineable and tool tier tags for blocks
3. Use `"replace": false` in tag files
4. Follow Forge tag conventions for compatibility
5. Data generation creates files from code automatically

