package com.hutizaki.ducksgalore.registry;

import com.hutizaki.ducksgalore.DucksGalore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DucksGaloreSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = 
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DucksGalore.MOD_ID);

    // Regular Rubber Duck
    public static final RegistryObject<SoundEvent> RUBBER_DUCK_QUACK = registerSoundEvent("rubber_duck_quack");
    public static final RegistryObject<SoundEvent> RUBBER_DUCK_BLOCK_EVENT = registerSoundEvent("rubber_duck_block_event");

    // Golden Rubber Duck
    public static final RegistryObject<SoundEvent> GOLDEN_RUBBER_DUCK_QUACK = registerSoundEvent("golden_rubber_duck_quack");
    public static final RegistryObject<SoundEvent> GOLDEN_RUBBER_DUCK_BLOCK_EVENT = registerSoundEvent("golden_rubber_duck_block_event");

    // Emerald Rubber Duck
    public static final RegistryObject<SoundEvent> EMERALD_RUBBER_DUCK_QUACK = registerSoundEvent("emerald_rubber_duck_quack");
    public static final RegistryObject<SoundEvent> EMERALD_RUBBER_DUCK_BLOCK_EVENT = registerSoundEvent("emerald_rubber_duck_block_event");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DucksGalore.MOD_ID, name)));
    }
} 