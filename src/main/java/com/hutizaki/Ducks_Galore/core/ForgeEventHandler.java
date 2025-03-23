package com.hutizaki.Ducks_Galore.core;

import com.hutizaki.Ducks_Galore.DucksGalore;
import com.hutizaki.Ducks_Galore.constants.CommonConstants;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeEventHandler {
    // Loot Table Event
    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event) {
        if (CommonConstants.getChestLootEnabled()) {
            String prefix = "minecraft:chests/";
            String name = event.getName().toString();
            
            if (name.startsWith(prefix)) {
                String file = name.substring(name.indexOf(prefix) + prefix.length());
                switch (file) {
                    case "abandoned_mineshaft":
                    case "buried_treasure":
                    case "desert_pyramid":
                    case "igloo_chest":
                    case "jungle_temple":
                    case "pillager_outpost":
                    case "simple_dungeon":
                    case "underwater_ruin_big":
                    case "underwater_ruin_small":
                    case "woodland_mansion":
                        event.getTable().addPool(getPool(file));
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    private static LootPool getPool(String entryName) {
        return LootPool.lootPool()
                .add(LootTableReference.lootTableReference(
                        new ResourceLocation(DucksGalore.MODID, "chests/" + entryName)))
                .build();
    }
} 