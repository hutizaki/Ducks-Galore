package com.hutizaki.ducksgalore.registry;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.block.*;
import com.hutizaki.ducksgalore.item.RubberDuckItem;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;

public class AllDucks {
    private static final Registrate REGISTRATE = Registrate.create(DucksGalore.MOD_ID);

    // Helper method to create a regular rubber duck
    public static BlockEntry<RubberDuckBlock> createRubberDuck() {
        return REGISTRATE.block("rubber_duck", RubberDuckBlock::new)
            .properties(p -> BlockBehaviour.Properties.of()
                .strength(0.5F)
                .sound(SoundType.WOOD)
                .noOcclusion())
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), prov.models().getExistingFile(prov.modLoc("block/duck_template"))))
            .item(RubberDuckItem::new)
            .model((ctx, prov) -> prov.withExistingParent("rubber_duck", prov.modLoc("item/duck_template")))
            .build()
            .register();
    }

    // Helper method to create a golden rubber duck
    public static BlockEntry<GoldenRubberDuckBlock> createGoldenRubberDuck() {
        return REGISTRATE.block("golden_rubber_duck", GoldenRubberDuckBlock::new)
            .properties(p -> BlockBehaviour.Properties.of()
                .strength(3.05F, 3.0F)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .pushReaction(PushReaction.BLOCK)
                .sound(SoundType.EMPTY))
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), prov.models().getExistingFile(prov.modLoc("block/duck_template"))))
            .item(RubberDuckItem::new)
            .model((ctx, prov) -> prov.withExistingParent("golden_rubber_duck", prov.modLoc("item/duck_template")))
            .build()
            .register();
    }

    // Helper method to create an emerald rubber duck
    public static BlockEntry<EmeraldRubberDuckBlock> createEmeraldRubberDuck() {
        return REGISTRATE.block("emerald_rubber_duck", EmeraldRubberDuckBlock::new)
            .properties(p -> BlockBehaviour.Properties.of()
                .strength(3.05F, 3.0F)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .pushReaction(PushReaction.BLOCK)
                .sound(SoundType.EMPTY))
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), prov.models().getExistingFile(prov.modLoc("block/duck_template"))))
            .item(RubberDuckItem::new)
            .model((ctx, prov) -> prov.withExistingParent("emerald_rubber_duck", prov.modLoc("item/duck_template")))
            .build()
            .register();
    }

    // Helper method to create a gold ore rubber duck
    public static BlockEntry<GoldOreRubberDuckBlock> createGoldOreRubberDuck() {
        return REGISTRATE.block("gold_ore_rubber_duck", GoldOreRubberDuckBlock::new)
            .properties(p -> BlockBehaviour.Properties.of()
                .strength(3.0F, 3.0F)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .pushReaction(PushReaction.BLOCK)
                .sound(SoundType.STONE))
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), prov.models().getExistingFile(prov.modLoc("block/duck_template"))))
            .item(RubberDuckItem::new)
            .model((ctx, prov) -> prov.withExistingParent("gold_ore_rubber_duck", prov.modLoc("item/duck_template")))
            .build()
            .register();
    }

    // Register all ducks
    public static void register(IEventBus modEventBus) {
        // Create all duck blocks
        createRubberDuck();
        createGoldenRubberDuck();
        createEmeraldRubberDuck();
        createGoldOreRubberDuck();
    }
} 