package com.hutizaki.ducksgalore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * Centralized registry for all sound events - following Create's approach
 */
public class AllSoundEvents {
    // Use the centralized registry from DucksGalore
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DucksGalore.SOUND_EVENTS;
    public static final Map<ResourceLocation, SoundEntry> ALL = new HashMap<>();
    
    // Build-style sound registrations
    public static final SoundEntry 
    
        // Regular rubber duck sounds
        RUBBER_DUCK_QUACK = create("rubber_duck_quack")
            .subtitle("Rubber duck quacks")
            .category(SoundSource.BLOCKS)
            .build(),
            
        RUBBER_DUCK_BLOCK_EVENT = create("rubber_duck_block_event")
            .subtitle("Rubber duck placed/removed")
            .category(SoundSource.BLOCKS)
            .build(),
            
        // Golden duck sounds
        GOLDEN_DUCK_QUACK = create("golden_duck_quack")
            .subtitle("Golden duck quacks majestically")
            .category(SoundSource.BLOCKS)
            .build(),
            
        GOLDEN_RUBBER_DUCK_BLOCK_EVENT = create("golden_rubber_duck_block_event")
            .subtitle("Golden rubber duck placed/removed")
            .category(SoundSource.BLOCKS)
            .build(),
            
        // Gold ore duck sounds - using existing stone sounds
        GOLD_ORE_RUBBER_DUCK_BLOCK_EVENT = create("gold_ore_rubber_duck_block_event")
            .subtitle("Gold ore rubber duck placed/removed")
            .playExisting(SoundEvents.STONE_PLACE, 1.0f, 1.0f)
            .category(SoundSource.BLOCKS)
            .build();
    
    /**
     * Register all sound events
     */
    public static void register() {
        DucksGalore.LOGGER.info("Ducks Galore sound events initialized");
    }
    
    /**
     * Create a new sound builder
     */
    private static SoundEntryBuilder create(String name) {
        return new SoundEntryBuilder(name);
    }
    
    /**
     * Sound entry class that holds the registered sound event
     */
    public static class SoundEntry {
        private final RegistryObject<SoundEvent> soundEvent;
        private final ResourceLocation location;
        private final boolean useExistingSound;
        private final SoundEvent existingSound;
        private final float volume;
        private final float pitch;
        
        public SoundEntry(RegistryObject<SoundEvent> soundEvent, ResourceLocation location, 
                          boolean useExistingSound, SoundEvent existingSound, float volume, float pitch) {
            this.soundEvent = soundEvent;
            this.location = location;
            this.useExistingSound = useExistingSound;
            this.existingSound = existingSound;
            this.volume = volume;
            this.pitch = pitch;
        }
        
        public SoundEvent getMainEvent() {
            return useExistingSound ? existingSound : soundEvent.get();
        }
        
        public float getVolume() {
            return volume;
        }
        
        public float getPitch() {
            return pitch;
        }
        
        public RegistryObject<SoundEvent> getSoundEvent() {
            return soundEvent;
        }
    }
    
    /**
     * Builder class for creating sound entries in a fluent way
     */
    private static class SoundEntryBuilder {
        private String name;
        private String subtitle;
        private SoundSource category = SoundSource.BLOCKS;
        private boolean useExistingSound = false;
        private SoundEvent existingSound = null;
        private float volume = 1.0f;
        private float pitch = 1.0f;
        
        public SoundEntryBuilder(String name) {
            this.name = name;
        }
        
        public SoundEntryBuilder subtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }
        
        public SoundEntryBuilder category(SoundSource category) {
            this.category = category;
            return this;
        }
        
        public SoundEntryBuilder playExisting(SoundEvent event, float volume, float pitch) {
            this.useExistingSound = true;
            this.existingSound = event;
            this.volume = volume;
            this.pitch = pitch;
            return this;
        }
        
        public SoundEntry build() {
            ResourceLocation id = DucksGalore.asResource(name);
            RegistryObject<SoundEvent> soundEvent = SOUND_EVENTS.register(name, 
                    () -> SoundEvent.createVariableRangeEvent(id));
            
            SoundEntry entry = new SoundEntry(soundEvent, id, useExistingSound, existingSound, volume, pitch);
            ALL.put(id, entry);
            return entry;
        }
    }
} 