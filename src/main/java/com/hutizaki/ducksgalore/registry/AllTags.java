package com.hutizaki.ducksgalore.registry;

import com.hutizaki.ducksgalore.DucksGalore;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AllTags {
    // Block tags
    public static class Blocks {
        public static final TagKey<Block> DUCKS = tag("ducks");
        
        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation(DucksGalore.MOD_ID, name));
        }
    }
    
    // Item tags
    public static class Items {
        public static final TagKey<Item> DUCKS = tag("ducks");
        
        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, new ResourceLocation(DucksGalore.MOD_ID, name));
        }
    }
    
    // Entity type tags
    public static class EntityTypes {
        public static final TagKey<EntityType<?>> DUCKS = tag("ducks");
        
        private static TagKey<EntityType<?>> tag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(DucksGalore.MOD_ID, name));
        }
    }
} 