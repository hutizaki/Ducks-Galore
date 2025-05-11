package com.hutizaki.ducksgalore.registry;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.content.ducks.blocks.EmeraldRubberDuckBlock;
import com.hutizaki.ducksgalore.content.ducks.blocks.GoldenRubberDuckBlock;
import com.hutizaki.ducksgalore.content.ducks.blocks.RubberDuckBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;

public class DucksGaloreRegistrate {
    public static final Registrate REGISTRATE = Registrate.create(DucksGalore.MOD_ID);

    // Blocks
    public static final BlockEntry<RubberDuckBlock> RUBBER_DUCK = REGISTRATE.block("rubber_duck", RubberDuckBlock::new)
        .properties(p -> Properties.of()
            .strength(0.5F)
            .sound(SoundType.WOOD)
            .noOcclusion())
        .register();

    public static final BlockEntry<GoldenRubberDuckBlock> GOLDEN_RUBBER_DUCK = REGISTRATE.block("golden_rubber_duck", GoldenRubberDuckBlock::new)
        .properties(p -> Properties.of()
            .strength(3.05F, 3.0F)
            .requiresCorrectToolForDrops()
            .noOcclusion()
            .sound(SoundType.EMPTY))
        .register();

    public static final BlockEntry<EmeraldRubberDuckBlock> EMERALD_RUBBER_DUCK = REGISTRATE.block("emerald_rubber_duck", EmeraldRubberDuckBlock::new)
        .properties(p -> Properties.of()
            .strength(3.05F, 3.0F)
            .requiresCorrectToolForDrops()
            .noOcclusion()
            .sound(SoundType.EMPTY))
        .register();

    // Gold Ore Rubber Duck (add this block)
    public static final BlockEntry<RubberDuckBlock> GOLD_ORE_RUBBER_DUCK = REGISTRATE.block("gold_ore_rubber_duck", RubberDuckBlock::new)
        .properties(p -> Properties.of()
            .strength(1.5F, 3.0F)
            .requiresCorrectToolForDrops()
            .noOcclusion()
            .sound(SoundType.STONE))
        .register();

    // Block Items
    public static final ItemEntry<BlockItem> RUBBER_DUCK_ITEM = REGISTRATE.item("rubber_duck", p -> new BlockItem(RUBBER_DUCK.get(), p)).register();
    public static final ItemEntry<BlockItem> GOLDEN_RUBBER_DUCK_ITEM = REGISTRATE.item("golden_rubber_duck", p -> new BlockItem(GOLDEN_RUBBER_DUCK.get(), p)).register();
    public static final ItemEntry<BlockItem> EMERALD_RUBBER_DUCK_ITEM = REGISTRATE.item("emerald_rubber_duck", p -> new BlockItem(EMERALD_RUBBER_DUCK.get(), p)).register();
    public static final ItemEntry<BlockItem> GOLD_ORE_RUBBER_DUCK_ITEM = REGISTRATE.item("gold_ore_rubber_duck", p -> new BlockItem(GOLD_ORE_RUBBER_DUCK.get(), p)).register();

    public static void register() {
        // No explicit register() needed in Registrate 1.20+; registration is handled by static initialization
    }
} 