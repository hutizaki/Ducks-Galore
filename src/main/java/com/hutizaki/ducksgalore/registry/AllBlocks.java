package com.hutizaki.ducksgalore.registry;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.content.ducks.blocks.RubberDuckBlock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
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
    
    // Register rubber duck block
    public static final RegistryObject<Block> RUBBER_DUCK = registerBlock("rubber_duck",
        () -> new RubberDuckBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_WOOL)));
    
    /**
     * Helper method to register a block with a corresponding item
     */
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, registeredBlock);
        return registeredBlock;
    }
    
    /**
     * Helper method to register a block item
     */
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        AllItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    
    /**
     * Register this registry with the mod event bus
     */
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
} 