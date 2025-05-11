package com.hutizaki.ducksgalore.events;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.registry.AllItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DucksGalore.MOD_ID)
public class LootTableModifier {
    private static final ResourceLocation EVOKER_LOOT = new ResourceLocation("minecraft", "entities/evoker");
    private static final ResourceLocation GOLD_ORE_LOOT = new ResourceLocation("minecraft", "blocks/gold_ore");

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        if (event.getName().equals(EVOKER_LOOT)) {
            // Create a new pool for the Emerald Rubber Duck
            LootPool emeraldDuckPool = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(AllItems.EMERALD_RUBBER_DUCK.get())
                    .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                .build();

            // Add the pool to the loot table
            event.getTable().addPool(emeraldDuckPool);
        }
        else if (event.getName().equals(GOLD_ORE_LOOT)) {
            // Create a new pool for the Gold Ore Rubber Duck
            LootPool goldOreDuckPool = LootPool.lootPool()
                .setRolls(UniformGenerator.between(0, 1)) // 50% chance to drop
                .add(LootItem.lootTableItem(AllItems.GOLD_ORE_RUBBER_DUCK.get()))
                .build();

            // Add the pool to the loot table
            event.getTable().addPool(goldOreDuckPool);
        }
    }
} 