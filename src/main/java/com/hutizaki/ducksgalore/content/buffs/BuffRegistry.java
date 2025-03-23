package com.hutizaki.ducksgalore.content.buffs;

import com.hutizaki.ducksgalore.content.buffs.buffs.SpeedBuff;
import com.hutizaki.ducksgalore.content.buffs.buffs.StrengthBuff;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Registry for duck buffs
 */
public class BuffRegistry {
    private static final Map<ResourceLocation, DuckBuff> BUFFS = new HashMap<>();
    
    // Register all buff types
    static {
        register(new SpeedBuff());
        register(new StrengthBuff());
    }
    
    /**
     * Register a new buff
     */
    public static void register(DuckBuff buff) {
        BUFFS.put(buff.getId(), buff);
    }
    
    /**
     * Get a buff by ID
     */
    public static DuckBuff getBuff(ResourceLocation id) {
        return BUFFS.get(id);
    }
    
    /**
     * Get all registered buffs
     */
    public static Set<ResourceLocation> getRegisteredBuffs() {
        return BUFFS.keySet();
    }
    
    /**
     * Get all buff instances
     */
    public static Iterable<DuckBuff> getAllBuffs() {
        return BUFFS.values();
    }
} 