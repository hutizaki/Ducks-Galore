package com.hutizaki.ducksgalore.data;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.data.recipe.DuckRecipeProvider;
import com.hutizaki.ducksgalore.data.loot.DuckLootTableProvider;
import com.hutizaki.ducksgalore.data.model.DuckModelProvider;
import com.hutizaki.ducksgalore.data.lang.DuckLanguageProvider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DucksGalore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    
    /**
     * Gather all data generators and register them with the data generator
     */
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        
        // Add providers with their required dependencies
        generator.addProvider(event.includeServer(), new DuckRecipeProvider(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new DuckLootTableProvider(generator.getPackOutput()));
        generator.addProvider(event.includeClient(), new DuckModelProvider(generator.getPackOutput(), event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new DuckLanguageProvider(generator.getPackOutput()));
    }
} 