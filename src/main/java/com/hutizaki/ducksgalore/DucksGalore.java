package com.hutizaki.ducksgalore;

import com.hutizaki.ducksgalore.registry.AllDucks;
import com.hutizaki.ducksgalore.registry.AllSounds;
import com.hutizaki.ducksgalore.registry.AllCreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DucksGalore.MOD_ID)
public class DucksGalore {
    public static final String MOD_ID = "ducksgalore";

    public DucksGalore() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register sounds
        AllSounds.SOUNDS.register(modEventBus);

        // Register ducks
        AllDucks.register(modEventBus);

        // Register creative tab
        AllCreativeModeTabs.register(modEventBus);

        // Register client setup
        MinecraftForge.EVENT_BUS.register(this);
    }
} 