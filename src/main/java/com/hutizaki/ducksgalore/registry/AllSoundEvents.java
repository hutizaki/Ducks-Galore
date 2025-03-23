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
    public static final RegistryObject<SoundEvent> RUBBER_DUCK_QUACK = registerSoundEvent("rubber_duck_quack");
    public static final RegistryObject<SoundEvent> RUBBER_DUCK_PLACE = registerSoundEvent("rubber_duck_place");
    public static final RegistryObject<SoundEvent> RUBBER_DUCK_BREAK = registerSoundEvent("rubber_duck_break");

    /**
     * Helper method to register a sound event
     */
    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation location = new ResourceLocation(DucksGalore.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(location));
    }

    /**
     * Register this registry with the mod event bus
     */
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
} 