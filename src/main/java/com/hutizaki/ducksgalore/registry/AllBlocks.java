package com.hutizaki.ducksgalore.registry;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.content.ducks.blocks.RubberDuckBlock;
import com.hutizaki.ducksgalore.content.ducks.blocks.GoldenRubberDuckBlock;
import com.hutizaki.ducksgalore.content.ducks.blocks.GoldOreRubberDuckBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class AllBlocks {
    // Create the block registry
    public static final DeferredRegister<Block> BLOCKS = 
        DeferredRegister.create(ForgeRegistries.BLOCKS, DucksGalore.MOD_ID);
    
    // Register rubber duck block with RUBBER material properties (no tool needed)
    public static final RegistryObject<Block> RUBBER_DUCK = registerBlock("rubber_duck",
        () -> new RubberDuckBlock(RubberDuckBlock.createRubberDuckProperties()));
    
    // Register golden rubber duck block with METAL material properties (pickaxe needed)
    public static final RegistryObject<Block> GOLDEN_RUBBER_DUCK = registerBlock("golden_rubber_duck",
        () -> new GoldenRubberDuckBlock(GoldenRubberDuckBlock.createGoldenRubberDuckProperties()));
        
    // Register gold ore rubber duck block with STONE material properties (pickaxe needed)
    public static final RegistryObject<Block> GOLD_ORE_RUBBER_DUCK = registerBlock("gold_ore_rubber_duck",
        () -> new GoldOreRubberDuckBlock(GoldOreRubberDuckBlock.createGoldOreRubberDuckProperties()));
    
    /**
     * Helper method to register a block without automatically registering an item
     */
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
    
    /**
     * Register this registry with the mod event bus
     */
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
} 