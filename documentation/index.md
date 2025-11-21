# Ducks Galore - Developer Documentation

Welcome to the comprehensive developer documentation for the **Ducks Galore** mod. This documentation will help you understand the mod's architecture and guide you through common development tasks.

## 📚 Table of Contents

### Core Documentation
1. **[Architecture Overview](architecture-overview.md)** - Understanding how the mod is structured
2. **[Registration System](registration-system.md)** - How blocks, items, and sounds are registered
3. **[Resource Files](resource-files.md)** - Models, textures, blockstates, and translations

### How-To Guides
4. **[How to Add a Block](how-to-add-a-block.md)** - Complete guide for adding new blocks
5. **[How to Add an Item](how-to-add-an-item.md)** - Complete guide for adding new items
6. **[Sound System](sound-system.md)** - Working with the custom sound system
7. **[Configuration System](config-system.md)** - Adding and using config options

### Advanced Topics
8. **[Block Behavior Patterns](block-behavior-patterns.md)** - Common block behaviors and patterns
9. **[Tags and Data](tags-and-data.md)** - Working with tags and data generation

## 🦆 What is Ducks Galore?

Ducks Galore is a Minecraft mod that adds various rubber duck blocks to the game. Each duck type has unique properties, behaviors, and visual effects.

### Current Features
- **Rubber Duck** - A basic yellow rubber duck that quacks when interacted with
- **Golden Rubber Duck** - A premium variant that glows, spawns particles, and grants beneficial effects
- **Gold Ore Rubber Duck** - A mining-themed duck with special abilities

### Technical Stack
- **Minecraft Version**: 1.20.1
- **Forge Version**: 47.2.6
- **Java Version**: 17
- **Build System**: Gradle 8.8

## 🏗️ Project Structure

```
Ducks-Galore/
├── src/main/java/com/hutizaki/ducksgalore/
│   ├── DucksGalore.java              # Main mod class
│   ├── AllBlocks.java                 # Block registration
│   ├── AllItems.java                  # Item registration
│   ├── AllSoundEvents.java            # Sound registration
│   ├── AllCreativeModeTabs.java       # Creative tab setup
│   ├── AllTags.java                   # Tag definitions
│   ├── config/
│   │   └── DucksGaloreConfig.java    # Configuration
│   └── content/
│       └── rubberducks/               # Duck implementations
│
├── src/main/resources/
│   ├── assets/ducksgalore/           # Client-side resources
│   │   ├── blockstates/              # Block state definitions
│   │   ├── models/                    # Block and item models
│   │   ├── textures/                  # Textures
│   │   ├── sounds/                    # Sound files (.ogg)
│   │   └── lang/                      # Translations
│   └── data/ducksgalore/             # Server-side data
│       ├── loot_tables/              # Loot table definitions
│       ├── recipes/                   # Crafting recipes
│       └── tags/                      # Tag data
│
└── documentation/                     # This documentation!
```

## 🚀 Quick Start

### For New Developers

1. **Start Here**: Read the [Architecture Overview](architecture-overview.md) to understand the mod's design philosophy
2. **Learn the Basics**: Study the [Registration System](registration-system.md) to see how everything connects
3. **Add Your First Block**: Follow [How to Add a Block](how-to-add-a-block.md) step-by-step

### For Experienced Developers

If you're familiar with Forge modding:
- Review the [Registration System](registration-system.md) to see our specific patterns
- Check out [Block Behavior Patterns](block-behavior-patterns.md) for our coding conventions
- See the [Sound System](sound-system.md) for our Create-inspired sound implementation

## 📖 Documentation Conventions

Throughout this documentation:
- 📝 **Code blocks** show actual implementation code
- 💡 **Tips** provide helpful insights and best practices
- ⚠️ **Warnings** highlight common pitfalls
- 🔍 **References** link to related documentation sections

## 🎯 Design Philosophy

The Ducks Galore mod follows these core principles:

1. **Centralized Registration** - All registrations go through `DucksGalore.java` deferred registers
2. **Organized by Type** - Blocks, items, sounds, and tabs are organized in dedicated "All*" classes
3. **Feature Separation** - Content is organized in the `content/` package by feature
4. **Clean Sound System** - Builder pattern for sound registration inspired by Create mod
5. **Configuration First** - Major features are configurable through `DucksGaloreConfig`

## 🛠️ Common Development Tasks

### Adding New Content
- [Add a new block type](how-to-add-a-block.md)
- [Add a standalone item](how-to-add-an-item.md)
- [Add sound effects](sound-system.md#adding-new-sounds)
- [Add configuration options](config-system.md#adding-config-values)

### Modifying Existing Content
- [Change block properties](block-behavior-patterns.md#modifying-properties)
- [Add particle effects](block-behavior-patterns.md#particle-effects)
- [Add mob effects](block-behavior-patterns.md#mob-effects)

## 🤝 Contributing

When adding new features or content to this mod:

1. Follow the existing architecture patterns
2. Use the centralized registration system
3. Add appropriate configuration options
4. Include all necessary resource files (models, textures, translations)
5. Update this documentation with your changes

## 📚 External Resources

- [Forge Documentation](https://docs.minecraftforge.net/)
- [Minecraft Wiki](https://minecraft.wiki/)
- [Forge Forums](https://forums.minecraftforge.net/)
- [Parchment Mappings](https://parchmentmc.org/)

## 💬 Need Help?

- Check the specific documentation pages for detailed guides
- Review existing code in the mod for examples
- Refer to the [FRAMEWORKS_GUIDE.md](../FRAMEWORKS_GUIDE.md) for dependency information
- See [JAVA_TROUBLESHOOTING.md](../JAVA_TROUBLESHOOTING.md) for build issues

---

**Last Updated**: November 2024  
**Mod Version**: 0.1.0  
**Minecraft Version**: 1.20.1

