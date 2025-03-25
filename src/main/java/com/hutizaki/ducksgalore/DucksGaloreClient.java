package com.hutizaki.ducksgalore;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Client-specific registration and initialization
 */
@Mod.EventBusSubscriber(modid = DucksGalore.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DucksGaloreClient {
    
    /**
     * Client setup handler
     */
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            DucksGalore.LOGGER.info("Ducks Galore client setup");
            
            // Register block rendering types if needed
            
            // Register special renderers here
        });
    }
    
    /**
     * Block color handler registration
     */
    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        // Register any block color handlers for blocks that might need them
    }
    
    /**
     * Item color handler registration
     */
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        // Register any item color handlers for items that might need them
    }
    
    /**
     * Custom particle registration
     */
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        // Register any custom particle factories here
    }
} 