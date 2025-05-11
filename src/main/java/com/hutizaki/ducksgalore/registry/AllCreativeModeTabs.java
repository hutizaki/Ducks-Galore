package com.hutizaki.ducksgalore.registry;

import com.hutizaki.ducksgalore.DucksGalore;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AllCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DucksGalore.MOD_ID);

    public static final RegistryObject<CreativeModeTab> DUCKS_GALORE_TAB = CREATIVE_MODE_TABS.register("ducks_galore_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.ducksgalore"))
                    .icon(() -> new ItemStack(AllBlocks.RUBBER_DUCK.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(AllBlocks.RUBBER_DUCK.get());
                        output.accept(AllBlocks.GOLDEN_RUBBER_DUCK.get());
                        output.accept(AllBlocks.GOLD_ORE_RUBBER_DUCK.get());
                        output.accept(AllBlocks.EMERALD_RUBBER_DUCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
} 