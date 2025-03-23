package com.hutizaki.ducksgalore.registry;

import com.hutizaki.ducksgalore.DucksGalore;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllSoundEvents {
    // Create the sound event registry
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = 
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DucksGalore.MOD_ID);

    // Register sound events
    public static final RegistryObject<SoundEvent> DUCK_USE = registerSoundEvent("duck_use");
    public static final RegistryObject<SoundEvent> DUCK_PLACE = registerSoundEvent("duck_place");
    public static final RegistryObject<SoundEvent> RUBBER_DUCK_QUACK = registerSoundEvent("rubber_duck_quack");
    public static final RegistryObject<SoundEvent> GOLDEN_DUCK_QUACK = registerSoundEvent("golden_duck_quack");

    /**
     * Helper method to register a sound event
     */
    public static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> 
            SoundEvent.createVariableRangeEvent(new ResourceLocation(DucksGalore.MOD_ID, name)));
    }

    /**
     * Register this registry with the mod event bus
     */
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
} 