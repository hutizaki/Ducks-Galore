package com.hutizaki.ducksgalore;

import com.hutizaki.ducksgalore.content.rubberducks.GoldenRubberDuckBlock;
import com.hutizaki.ducksgalore.content.rubberducks.RubberDuckBlock;
import com.hutizaki.ducksgalore.content.rubberducks.GoldOreRubberDuckBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;

/**
 * This class holds all block registrations for the Ducks Galore mod
 */
public class AllBlocks {
    // Use the centralized registry from DucksGalore 
    private static final net.minecraftforge.registries.DeferredRegister<Block> BLOCKS = DucksGalore.BLOCKS;

    public static final RegistryObject<Block> RUBBER_DUCK =
            BLOCKS.register("rubber_duck",
                    () -> new RubberDuckBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_YELLOW)
                            .strength(0.05F)
                            .noOcclusion()
                    ));

    public static final RegistryObject<Block> GOLDEN_RUBBER_DUCK =
            BLOCKS.register("golden_rubber_duck",
                    () -> new GoldenRubberDuckBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.GOLD)
                            .requiresCorrectToolForDrops()
                            .strength(3.05F, 3.0F)
                            .lightLevel(state -> 8)
                            .noOcclusion()
                            .pushReaction(PushReaction.BLOCK)
                    ));

    public static final RegistryObject<Block> GOLD_ORE_RUBBER_DUCK =
            BLOCKS.register("gold_ore_rubber_duck",
                    () -> new GoldOreRubberDuckBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F)
                            .noOcclusion()
                            .sound(SoundType.STONE)
                            .pushReaction(PushReaction.BLOCK)
                    ));

    // Register method doesn't need to register with event bus anymore
    public static void register() {
        DucksGalore.LOGGER.info("Ducks Galore blocks initialized");
        // Registration happens automatically through DucksGalore.BLOCKS
    }
}
