package com.hutizaki.ducksgalore;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * This class holds all tag definitions for the Ducks Galore mod
 */
public class AllTags {
    /**
     * Item Tags
     */
    public static class Items {
        // Add item tags as needed
    }
    
    /**
     * Block Tags
     */
    public static class Blocks {
        // Define common block tags
        public static final TagKey<Block> MINEABLE_PICKAXE = BlockTags.MINEABLE_WITH_PICKAXE;
        public static final TagKey<Block> MINEABLE_AXE = BlockTags.MINEABLE_WITH_AXE;
        
        // Add mod-specific block tags as needed
        public static final TagKey<Block> RUBBER_DUCKS = 
                create("rubber_ducks");
        
        private static TagKey<Block> create(String path) {
            return TagKey.create(net.minecraft.core.registries.Registries.BLOCK,
                    new ResourceLocation(DucksGalore.MOD_ID, path));
        }
    }
} 