package com.hutizaki.ducksgalore.registry;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.content.ducks.entities.DuckEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllEntityTypes {
    // Create the entity type registry
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = 
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DucksGalore.MOD_ID);

    // Register entity types
    public static final RegistryObject<EntityType<DuckEntity>> DUCK = 
        ENTITY_TYPES.register("duck", 
            () -> EntityType.Builder.of(
                DuckEntity::new, 
                MobCategory.CREATURE)
                .sized(0.6F, 0.6F)
                .clientTrackingRange(8)
                .build(DucksGalore.MOD_ID + ":duck"));

    /**
     * Register this registry with the mod event bus
     */
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
} 