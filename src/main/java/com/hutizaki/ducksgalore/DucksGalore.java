package com.hutizaki.ducksgalore;

import com.hutizaki.ducksgalore.registry.RegistryHandler;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DucksGalore.MOD_ID)
public class DucksGalore {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "ducksgalore";

    public DucksGalore() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        // Register all registry objects
        RegistryHandler.register(modEventBus);
        
        // Register mod event bus handlers
        modEventBus.addListener(this::setup);
    }
    
    private void setup(final FMLCommonSetupEvent event) {
        // Common setup code
    }
    
    /**
     * Helper method for creating mod resource locations
     */
    public static net.minecraft.resources.ResourceLocation asResource(String path) {
        return new net.minecraft.resources.ResourceLocation(MOD_ID, path);
    }
} 