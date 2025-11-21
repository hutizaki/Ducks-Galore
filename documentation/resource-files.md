# Resource Files

This document covers all the resource files (models, textures, blockstates, translations, etc.) needed for blocks and items in Ducks Galore.

## 📂 Resource Structure

```
src/main/resources/
├── assets/ducksgalore/          # Client-side resources
│   ├── blockstates/             # Block state definitions
│   ├── models/
│   │   ├── block/              # 3D block models
│   │   └── item/               # Item models (usually reference block models)
│   ├── textures/
│   │   ├── block/              # Block textures
│   │   ├── item/               # Item textures (if different from block)
│   │   └── gui/                # GUI textures
│   ├── sounds/                  # Sound files (.ogg)
│   ├── lang/                    # Translation files
│   │   └── en_us.json          # English translations
│   └── sounds.json              # Sound definitions
│
└── data/ducksgalore/            # Server-side data
    ├── loot_tables/
    │   └── blocks/              # Block drop tables
    ├── recipes/                 # Crafting/smelting recipes
    └── tags/
        ├── blocks/              # Block tags
        └── items/               # Item tags
```

## 🎨 Blockstates

Blockstates define which model to use based on block properties.

### Simple Block (No Properties)

`blockstates/my_block.json`:
```json
{
  "variants": {
    "": {
      "model": "ducksgalore:block/my_block"
    }
  }
}
```

### Directional Block (FACING property)

`blockstates/rubber_duck.json`:
```json
{
  "variants": {
    "facing=north": { "model": "ducksgalore:block/rubber_duck" },
    "facing=east": { "model": "ducksgalore:block/rubber_duck", "y": 90 },
    "facing=south": { "model": "ducksgalore:block/rubber_duck", "y": 180 },
    "facing=west": { "model": "ducksgalore:block/rubber_duck", "y": 270 }
  }
}
```

**Rotation parameters:**
- `x` - Rotation around X axis (pitch)
- `y` - Rotation around Y axis (yaw)
- `z` - Rotation around Z axis (roll)
- `uvlock` - Lock UV coordinates when rotating

### Multiple Properties

```json
{
  "variants": {
    "facing=north,powered=false": { "model": "ducksgalore:block/my_block" },
    "facing=north,powered=true": { "model": "ducksgalore:block/my_block_on" },
    "facing=east,powered=false": { "model": "ducksgalore:block/my_block", "y": 90 },
    "facing=east,powered=true": { "model": "ducksgalore:block/my_block_on", "y": 90 }
  }
}
```

### Multipart Blockstates

For complex blocks with multiple visual components:

```json
{
  "multipart": [
    {
      "when": { "facing": "north" },
      "apply": { "model": "ducksgalore:block/my_block_north" }
    },
    {
      "when": { "powered": "true" },
      "apply": { "model": "ducksgalore:block/my_block_powered_overlay" }
    }
  ]
}
```

## 🧊 Block Models

Block models are JSON files that define the 3D shape and textures.

### Parent: minecraft:block/cube_all

All sides use the same texture:

`models/block/simple_block.json`:
```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "ducksgalore:block/simple_block"
  }
}
```

### Parent: minecraft:block/cube

Different texture for each side:

```json
{
  "parent": "minecraft:block/cube",
  "textures": {
    "down": "ducksgalore:block/my_block_bottom",
    "up": "ducksgalore:block/my_block_top",
    "north": "ducksgalore:block/my_block_side",
    "east": "ducksgalore:block/my_block_side",
    "south": "ducksgalore:block/my_block_side",
    "west": "ducksgalore:block/my_block_side",
    "particle": "ducksgalore:block/my_block_side"
  }
}
```

### Parent: minecraft:block/orientable

For blocks with front/side/top textures (like furnaces):

```json
{
  "parent": "minecraft:block/orientable",
  "textures": {
    "top": "ducksgalore:block/my_block_top",
    "front": "ducksgalore:block/my_block_front",
    "side": "ducksgalore:block/my_block_side"
  }
}
```

### Parent: minecraft:block/cross

For plant-like blocks (two intersecting planes):

```json
{
  "parent": "minecraft:block/cross",
  "textures": {
    "cross": "ducksgalore:block/my_plant"
  }
}
```

### Custom Model

Define your own shape with elements:

```json
{
  "parent": "minecraft:block/block",
  "textures": {
    "texture": "ducksgalore:block/my_texture",
    "particle": "ducksgalore:block/my_texture"
  },
  "elements": [
    {
      "from": [4, 0, 4],
      "to": [12, 8, 12],
      "faces": {
        "down": { "texture": "#texture", "cullface": "down" },
        "up": { "texture": "#texture" },
        "north": { "texture": "#texture" },
        "south": { "texture": "#texture" },
        "west": { "texture": "#texture" },
        "east": { "texture": "#texture" }
      }
    }
  ]
}
```

**Element properties:**
- `from` - Start corner [x, y, z] (0-16)
- `to` - End corner [x, y, z] (0-16)
- `rotation` - Rotate element around axis
- `shade` - Enable/disable shading (default: true)
- `faces` - Define each face

**Face properties:**
- `texture` - Texture to use
- `cullface` - Hide face if adjacent block is solid
- `rotation` - Rotate texture (0, 90, 180, 270)
- `tintindex` - Use block color tinting

## 🎒 Item Models

### Block Item (Most Common)

Simply reference the block model:

`models/item/rubber_duck.json`:
```json
{
  "parent": "ducksgalore:block/rubber_duck"
}
```

### Generated Item (Flat 2D)

For non-block items:

`models/item/duck_feather.json`:
```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "ducksgalore:item/duck_feather"
  }
}
```

**Multiple layers:**
```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "ducksgalore:item/base",
    "layer1": "ducksgalore:item/overlay"
  }
}
```

### Handheld Item (Tools/Weapons)

```json
{
  "parent": "minecraft:item/handheld",
  "textures": {
    "layer0": "ducksgalore:item/duck_sword"
  }
}
```

## 🖼️ Textures

### Block Textures

- **Size**: 16x16 pixels (can be higher for HD packs)
- **Format**: PNG with transparency support
- **Location**: `textures/block/`
- **Naming**: Match the model reference (e.g., `rubber_duck.png`)

### Item Textures

- **Size**: 16x16 pixels
- **Format**: PNG with transparency
- **Location**: `textures/item/`
- **Only needed if different from block texture**

### Texture Guidelines

1. **Use proper alpha** - Transparent pixels for non-solid parts
2. **Match Minecraft style** - Look at vanilla textures for reference
3. **Keep it simple** - Clarity is more important than detail
4. **Test in-game** - Textures look different in 3D
5. **Consider lighting** - How will shading affect it?

### Creating Textures

**Recommended tools:**
- **Paint.NET** - Free, easy to use
- **GIMP** - Free, more advanced
- **Aseprite** - Great for pixel art
- **Blockbench** - Can paint textures on 3D models

## 🌐 Translations

### en_us.json

`lang/en_us.json`:
```json
{
  "block.ducksgalore.rubber_duck": "Rubber Duck",
  "block.ducksgalore.golden_rubber_duck": "Golden Rubber Duck",
  "block.ducksgalore.gold_ore_rubber_duck": "Gold Ore Rubber Duck",
  
  "item.ducksgalore.duck_feather": "Duck Feather",
  
  "itemGroup.ducksgalore.main_tab": "Ducks Galore",
  
  "subtitles.ducksgalore.rubber_duck_quack": "Rubber duck quacks",
  "subtitles.ducksgalore.golden_duck_quack": "Golden duck quacks majestically",
  
  "config.ducksgalore.enableGoldenDuckEffects": "Enable Golden Duck Effects",
  "config.ducksgalore.effectDurationSeconds": "Effect Duration (seconds)",
  
  "death.attack.rubber_duck": "%1$s was quacked to death"
}
```

### Translation Keys

**Blocks:**
```
block.<modid>.<registry_name>
```

**Items:**
```
item.<modid>.<registry_name>
```

**Creative Tabs:**
```
itemGroup.<modid>.<tab_name>
```

**Sounds/Subtitles:**
```
subtitles.<modid>.<sound_name>
```

**Config (if using config GUI):**
```
config.<modid>.<config_key>
```

### Additional Languages

To support other languages, create additional files:
- `fr_fr.json` - French
- `es_es.json` - Spanish
- `de_de.json` - German
- `ja_jp.json` - Japanese
- etc.

## 🎁 Loot Tables

Define what blocks drop when broken.

### Simple Drop (Drop Self)

`loot_tables/blocks/rubber_duck.json`:
```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "ducksgalore:rubber_duck"
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

### Require Silk Touch

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "ducksgalore:special_block"
        }
      ],
      "conditions": [
        {
          "condition": "minecraft:match_tool",
          "predicate": {
            "enchantments": [
              {
                "enchantment": "minecraft:silk_touch",
                "levels": { "min": 1 }
              }
            ]
          }
        }
      ]
    },
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "minecraft:stone"
        }
      ],
      "conditions": [
        {
          "condition": "minecraft:inverted",
          "term": {
            "condition": "minecraft:match_tool",
            "predicate": {
              "enchantments": [
                {
                  "enchantment": "minecraft:silk_touch",
                  "levels": { "min": 1 }
                }
              ]
            }
          }
        }
      ]
    }
  ]
}
```

### Random Quantity

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "ducksgalore:duck_feather",
          "functions": [
            {
              "function": "minecraft:set_count",
              "count": {
                "min": 2,
                "max": 5
              }
            }
          ]
        }
      ]
    }
  ]
}
```

### Fortune Enchantment

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "minecraft:diamond",
          "functions": [
            {
              "function": "minecraft:apply_bonus",
              "enchantment": "minecraft:fortune",
              "formula": "minecraft:ore_drops"
            }
          ]
        }
      ]
    }
  ]
}
```

## 🏷️ Tags

Tags group blocks/items for shared behavior.

### Block Tags

`tags/blocks/rubber_ducks.json`:
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

### Mineable Tags

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

### Tool Tier Tags

`data/minecraft/tags/blocks/needs_iron_tool.json`:
```json
{
  "replace": false,
  "values": [
    "ducksgalore:golden_rubber_duck"
  ]
}
```

### Item Tags

`tags/items/rubber_ducks.json`:
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

## 🍳 Recipes

### Shaped Crafting

`recipes/golden_rubber_duck.json`:
```json
{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "GGG",
    "GDG",
    "GGG"
  ],
  "key": {
    "G": {
      "item": "minecraft:gold_ingot"
    },
    "D": {
      "item": "ducksgalore:rubber_duck"
    }
  },
  "result": {
    "item": "ducksgalore:golden_rubber_duck"
  }
}
```

### Shapeless Crafting

```json
{
  "type": "minecraft:crafting_shapeless",
  "ingredients": [
    { "item": "ducksgalore:rubber_duck" },
    { "item": "minecraft:feather" },
    { "item": "minecraft:gold_nugget" }
  ],
  "result": {
    "item": "ducksgalore:fancy_duck"
  }
}
```

### Smelting

```json
{
  "type": "minecraft:smelting",
  "ingredient": {
    "item": "ducksgalore:raw_rubber"
  },
  "result": "ducksgalore:rubber_duck",
  "experience": 0.1,
  "cookingtime": 200
}
```

## 🎵 Sounds

### sounds.json

`assets/ducksgalore/sounds.json`:
```json
{
  "rubber_duck_quack": {
    "subtitle": "Rubber duck quacks",
    "sounds": [
      "ducksgalore:rubber_duck_quack"
    ]
  },
  "golden_duck_quack": {
    "subtitle": "Golden duck quacks majestically",
    "sounds": [
      {
        "name": "ducksgalore:golden_duck_quack",
        "volume": 1.2,
        "pitch": 1.1
      }
    ]
  }
}
```

See [Sound System](sound-system.md) for detailed information.

## ✅ Resource Checklist

When adding a new block:

- [ ] Blockstate JSON (`blockstates/`)
- [ ] Block model JSON (`models/block/`)
- [ ] Item model JSON (`models/item/`)
- [ ] Block texture PNG (`textures/block/`)
- [ ] Translation entry (`lang/en_us.json`)
- [ ] Loot table JSON (`loot_tables/blocks/`)
- [ ] Mining tags (`data/minecraft/tags/blocks/mineable/`)
- [ ] Tool tier tags (`data/minecraft/tags/blocks/needs_*_tool.json`)
- [ ] Custom tags if needed (`data/ducksgalore/tags/`)

## 🐛 Common Issues

### Missing texture (purple/black)
- Check texture path matches model reference exactly
- Ensure texture file exists and is PNG format
- Verify texture is in correct folder (`textures/block/` or `textures/item/`)

### Missing model (wireframe)
- Check model path in blockstate file
- Ensure model JSON exists
- Verify model JSON is valid (use a JSON validator)

### Block doesn't drop
- Check loot table exists with correct path
- Verify loot table JSON is valid
- Ensure filename matches block registry name

### Wrong translation
- Check key format: `block.modid.name` or `item.modid.name`
- Verify key matches registry name
- Ensure file is named `en_us.json` (lowercase)

### Block faces wrong direction
- Check blockstate rotation values (y: 90, 180, 270)
- Verify FACING property in blockstate matches Java code
- Test all four directions

## 📚 See Also

- [How to Add a Block](how-to-add-a-block.md) - Complete block creation guide
- [Sound System](sound-system.md) - Detailed sound information
- [Tags and Data](tags-and-data.md) - Working with tags

---

**Key Takeaways:**
1. Blockstates link block properties to models
2. Models define 3D shape and textures
3. Textures are 16x16 PNG files
4. Loot tables define drops
5. Tags group blocks/items for shared behavior
6. All resource names use lowercase and underscores

