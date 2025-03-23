package com.hutizaki.ducksgalore.registry;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Central registry handler for all registries
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryHandler {
    
    /**
     * Register all registry objects with the mod event bus
     */
    public static void register(IEventBus modEventBus) {
        // Register block registry
        AllBlocks.register(modEventBus);
        
        // Register item registry
        AllItems.register(modEventBus);
        
        // Register sound events
        AllSoundEvents.register(modEventBus);
        
        // Register block entities
        AllBlockEntityTypes.register(modEventBus);
        
        // Register entities
        AllEntityTypes.register(modEventBus);
        
        // Register creative tabs
        AllCreativeModeTabs.register(modEventBus);
    }
} 