package com.hutizaki.ducksgalore;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

/**
 * This class holds all item registrations for the Ducks Galore mod
 */
public class AllItems {
    // Use the centralized registry from DucksGalore
    private static final net.minecraftforge.registries.DeferredRegister<Item> ITEMS = DucksGalore.ITEMS;
    
    // Block items - automatically created from blocks
    public static final RegistryObject<Item> RUBBER_DUCK_ITEM = 
            fromBlock(AllBlocks.RUBBER_DUCK);
    
    public static final RegistryObject<Item> GOLDEN_RUBBER_DUCK_ITEM = 
            fromBlock(AllBlocks.GOLDEN_RUBBER_DUCK);
            
    public static final RegistryObject<Item> GOLD_ORE_RUBBER_DUCK_ITEM = 
            fromBlock(AllBlocks.GOLD_ORE_RUBBER_DUCK);
    
    /**
     * Register method - initialization only, registration is handled by DucksGalore
     */
    public static void register() {
        DucksGalore.LOGGER.info("Ducks Galore items initialized");
        // Registration happens automatically through DucksGalore.ITEMS
    }
    
    /**
     * Helper method to create an item from a block
     */
    private static RegistryObject<Item> fromBlock(RegistryObject<? extends Block> block) {
        return ITEMS.register(block.getId().getPath(), 
                () -> new BlockItem(block.get(), new Item.Properties()));
    }
    
    /**
     * Get all registered items for creative tabs
     */
    public static Collection<RegistryObject<Item>> getAllItems() {
        return ITEMS.getEntries();
    }
} 