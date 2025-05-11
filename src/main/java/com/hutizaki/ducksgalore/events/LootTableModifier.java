package com.hutizaki.ducksgalore.events;

import com.hutizaki.ducksgalore.registry.AllBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LootTableModifier {
    private static final ResourceLocation EVOKER_LOOT = new ResourceLocation("minecraft", "entities/evoker");
    private static final ResourceLocation GOLD_ORE_LOOT = new ResourceLocation("minecraft", "blocks/gold_ore");
    private static final ResourceLocation DEEPSLATE_GOLD_ORE_LOOT = new ResourceLocation("minecraft", "blocks/deepslate_gold_ore");

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        if (event.getName().equals(EVOKER_LOOT)) {
            event.getTable().addPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(AllBlocks.EMERALD_RUBBER_DUCK.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.1f)))
                    .build());
        }

        if (event.getName().equals(GOLD_ORE_LOOT)) {
            event.getTable().addPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(AllBlocks.GOLD_ORE_RUBBER_DUCK.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.05f)))
                    .build());
        }

        if (event.getName().equals(DEEPSLATE_GOLD_ORE_LOOT)) {
            event.getTable().addPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(AllBlocks.GOLD_ORE_RUBBER_DUCK.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.05f)))
                    .build());
        }
    }
} 