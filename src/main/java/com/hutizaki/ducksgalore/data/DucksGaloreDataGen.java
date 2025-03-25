package com.hutizaki.ducksgalore.data;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

import com.hutizaki.ducksgalore.DucksGalore;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinition.Sound;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

/**
 * Central data generation class for Ducks Galore.
 * Handles registering all data providers and generating mod assets.
 */
public class DucksGaloreDataGen {

    /**
     * Main method called during data generation event
     */
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        
        // Register all data providers
        if (event.includeClient()) {
            // Client data (models, textures, sounds)
            DucksGalore.LOGGER.info("Registering client data generators");
            generator.addProvider(event.includeClient(), new SoundProvider(generator.getPackOutput(), existingFileHelper));
            // You can add model providers and other client asset generators here
        }
        
        if (event.includeServer()) {
            // Server data (recipes, loot tables, advancements)
            DucksGalore.LOGGER.info("Registering server data generators");
            // Server data generators would go here
        }
    }
    
    /**
     * Sound provider to generate sounds.json
     */
    private static class SoundProvider extends SoundDefinitionsProvider {
        
        public SoundProvider(net.minecraft.data.PackOutput packOutput, ExistingFileHelper helper) {
            super(packOutput, DucksGalore.MOD_ID, helper);
        }

        @Override
        public void registerSounds() {
            // Register all sound definitions
            DucksGalore.LOGGER.info("Generating sound definitions");
            
            // Register duck sounds
            this.add("rubber_duck_quack", SoundDefinition.definition()
                .subtitle("subtitles.ducksgalore.rubber_duck_quack")
                .with(Sound.sound(modLoc("rubber_duck_quack"), SoundDefinition.SoundType.SOUND))
            );
            
            this.add("rubber_duck_block_event", SoundDefinition.definition()
                .subtitle("subtitles.ducksgalore.rubber_duck_place_break")
                .with(Sound.sound(modLoc("rubber_duck_place"), SoundDefinition.SoundType.SOUND))
            );
            
            this.add("golden_duck_quack", SoundDefinition.definition()
                .subtitle("subtitles.ducksgalore.golden_duck_quack")
                .with(Sound.sound(modLoc("golden_duck_quack"), SoundDefinition.SoundType.SOUND))
            );
            
            this.add("golden_rubber_duck_block_event", SoundDefinition.definition()
                .subtitle("subtitles.ducksgalore.golden_rubber_duck_place_break")
                .with(Sound.sound(modLoc("golden_rubber_duck_place"), SoundDefinition.SoundType.SOUND))
            );
            
            this.add("gold_ore_rubber_duck_block_event", SoundDefinition.definition()
                .subtitle("subtitles.ducksgalore.gold_ore_rubber_duck_place_break")
                .with(Sound.sound(new ResourceLocation("minecraft", "block.stone.place"), SoundDefinition.SoundType.SOUND))
            );
        }
        
        /**
         * Helper method to create mod-specific ResourceLocation
         */
        private ResourceLocation modLoc(String name) {
            return new ResourceLocation(DucksGalore.MOD_ID, name);
        }
    }
} 