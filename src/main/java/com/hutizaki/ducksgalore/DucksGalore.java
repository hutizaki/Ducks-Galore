package com.hutizaki.ducksgalore;

import com.hutizaki.ducksgalore.config.DucksGaloreConfig;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main mod class for Ducks Galore
 */
@Mod(DucksGalore.MOD_ID)
public class DucksGalore {
    public static final String MOD_ID = "ducksgalore";
    public static final String NAME = "Ducks Galore";
    
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    // Centralized registries
    public static final DeferredRegister<net.minecraft.world.level.block.Block> BLOCKS = 
            DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<net.minecraft.world.item.Item> ITEMS = 
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<net.minecraft.sounds.SoundEvent> SOUND_EVENTS = 
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    
    // Singleton instance for easy access
    private static DucksGalore instance;
    
    public DucksGalore() {
        instance = this;
        
        // Get the mod event bus
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        
        // Register the setup methods for mod loading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        // Temporarily comment out data generation
        // modEventBus.addListener(this::gatherData);
        
        // Register all registries with the event bus
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        SOUND_EVENTS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        
        // Register configuration
        DucksGaloreConfig.register();
        
        // Initialize all registrations
        AllBlocks.register();
        AllItems.register();
        AllSoundEvents.register();
        AllCreativeModeTabs.register();
        
        LOGGER.info("Ducks Galore initialized");
    }
    
    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Ducks Galore common setup starting...");
        // Common setup code goes here
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Ducks Galore client setup starting...");
        // Client-specific setup code goes here
    }
    
    /*
    private void gatherData(final GatherDataEvent event) {
        LOGGER.info("Ducks Galore data generation starting...");
        // Run our own datagen if needed
        // DucksGaloreDataGen.gatherData(event);
    }
    */

    public static DucksGalore getInstance() {
        return instance;
    }
    
    /**
     * Helper method to create ResourceLocation with the mod's namespace
     */
    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
} 