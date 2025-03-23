package com.hutizaki.ducksgalore.registry;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.content.ducks.blockentities.DuckBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllBlockEntityTypes {
    // Create the block entity type registry
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = 
        DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DucksGalore.MOD_ID);

    // Register block entity types
    public static final RegistryObject<BlockEntityType<DuckBlockEntity>> DUCK = 
        BLOCK_ENTITY_TYPES.register("duck", 
            () -> BlockEntityType.Builder.of(
                DuckBlockEntity::new, 
                AllBlocks.DEFAULT_DUCK.get(),
                AllBlocks.RUBBER_DUCK.get(),
                AllBlocks.GOLDEN_DUCK.get()
            ).build(null));

    /**
     * Register this registry with the mod event bus
     */
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
} 