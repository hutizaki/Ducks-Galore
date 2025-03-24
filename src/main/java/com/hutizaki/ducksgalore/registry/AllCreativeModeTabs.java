package com.hutizaki.ducksgalore.registry;

import com.hutizaki.ducksgalore.DucksGalore;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AllCreativeModeTabs {
    // Create the creative mode tab registry
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DucksGalore.MOD_ID);

    // Register creative mode tabs
    public static final RegistryObject<CreativeModeTab> DUCKS_TAB = CREATIVE_MODE_TABS.register("ducks_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + DucksGalore.MOD_ID + ".ducks_tab"))
            .icon(() -> new ItemStack(AllBlocks.RUBBER_DUCK.get()))
            .displayItems((parameters, output) -> {
                output.accept(AllBlocks.RUBBER_DUCK.get());
                output.accept(AllBlocks.GOLDEN_RUBBER_DUCK.get());
                output.accept(AllBlocks.GOLD_ORE_RUBBER_DUCK.get());
            })
            .build());

    /**
     * Register this registry with the mod event bus
     */
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    /**
     * Add items to vanilla creative tabs
     */
    public static void addToTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(AllBlocks.RUBBER_DUCK.get());
            event.accept(AllBlocks.GOLDEN_RUBBER_DUCK.get());
            event.accept(AllBlocks.GOLD_ORE_RUBBER_DUCK.get());
        }
    }
} 