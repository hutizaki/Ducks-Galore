package com.hutizaki.Ducks_Galore;

import com.hutizaki.Ducks_Galore.config.CommonConfigs;
import com.hutizaki.Ducks_Galore.constants.CommonConstants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hutizaki.Ducks_Galore.core.ForgeEventHandler;
import com.hutizaki.Ducks_Galore.core.Registries;

@Mod("ducksgalore")
public class DucksGalore
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "ducksgalore";

    public DucksGalore() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    	bus.addListener(this::setup);
    	bus.addListener(this::clientSetup);
        bus.addListener(this::addCreative);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfigs.SPEC, MODID+"-common.toml");

    	Registries.BLOCKS.register(bus);
    	Registries.ITEMS.register(bus);
    	Registries.SOUND_EVENTS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(ForgeEventHandler.class);
    }

    private void setup(final FMLCommonSetupEvent event){
        CommonConstants.initConstants();
    }
    
    private void clientSetup(final FMLClientSetupEvent event){
    	// Client setup code (if needed)
    }
    
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        Registries.addItemsToTabs(event);
    }
} 