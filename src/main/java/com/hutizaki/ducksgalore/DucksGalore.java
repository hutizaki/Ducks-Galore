package com.hutizaki.ducksgalore;

import com.hutizaki.ducksgalore.ponder.PonderIndex;
import com.hutizaki.ducksgalore.registry.AllBlocks;
import com.hutizaki.ducksgalore.registry.AllItems;
import com.hutizaki.ducksgalore.registry.RegistryHandler;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DucksGalore.MOD_ID)
public class DucksGalore {
    public static final String MOD_ID = "ducksgalore";
    public static final Logger LOGGER = LogManager.getLogger();

    public DucksGalore() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        // Register all registry objects
        RegistryHandler.register(modEventBus);
        
        // Register mod event bus handlers
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);
        
        // Register server and client events
        MinecraftForge.EVENT_BUS.register(this);
        
        LOGGER.info("Ducks Galore initialized!");
        
        // Add detailed logging for duck registrations
        modEventBus.addListener(this::onRegister);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Register Ponder scenes
            PonderIndex.register();
            LOGGER.info("Ducks Galore: Ponder scenes registered");
        });
    }

    private void onRegister(RegisterEvent event) {
        LOGGER.info("Ducks Galore: Checking registrations");
        LOGGER.info("Rubber Duck block registered: " + AllBlocks.RUBBER_DUCK.getId());
        LOGGER.info("Golden Rubber Duck block registered: " + AllBlocks.GOLDEN_RUBBER_DUCK.getId());
        LOGGER.info("Gold Ore Rubber Duck block registered: " + AllBlocks.GOLD_ORE_RUBBER_DUCK.getId());
        
        LOGGER.info("Rubber Duck item registered: " + AllItems.RUBBER_DUCK.getId());
        LOGGER.info("Golden Rubber Duck item registered: " + AllItems.GOLDEN_RUBBER_DUCK.getId());
        LOGGER.info("Gold Ore Rubber Duck item registered: " + AllItems.GOLD_ORE_RUBBER_DUCK.getId());
        
        LOGGER.info("Checking texture paths:");
        LOGGER.info("Rubber Duck texture path: ducksgalore:block/rubber_duck");
        LOGGER.info("Golden Rubber Duck texture path: ducksgalore:block/golden_rubber_duck");
        LOGGER.info("Gold Ore Rubber Duck texture path: ducksgalore:block/gold_ore_rubber_duck");
    }
    
    private void setup(final FMLCommonSetupEvent event) {
        // Common setup code
        LOGGER.info("Ducks Galore: Setup phase beginning");
        
        // Log texture info during setup
        event.enqueueWork(() -> {
            LOGGER.info("Ducks Galore: Checking texture resources");
            ResourceLocation rubberDuckTexture = asResource("block/rubber_duck");
            ResourceLocation goldenRubberDuckTexture = asResource("block/golden_rubber_duck");
            ResourceLocation goldOreRubberDuckTexture = asResource("block/gold_ore_rubber_duck");
            
            LOGGER.info("Rubber Duck texture: " + rubberDuckTexture);
            LOGGER.info("Golden Rubber Duck texture: " + goldenRubberDuckTexture);
            LOGGER.info("Gold Ore Rubber Duck texture: " + goldOreRubberDuckTexture);
        });
    }
    
    /**
     * Helper method to create ResourceLocation with the mod's namespace
     */
    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
} 