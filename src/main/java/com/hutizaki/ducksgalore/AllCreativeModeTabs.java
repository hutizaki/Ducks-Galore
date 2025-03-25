package com.hutizaki.ducksgalore;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

/**
 * Centralized registry for all creative mode tabs
 */
@Mod.EventBusSubscriber(modid = DucksGalore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AllCreativeModeTabs {
    
    // Use the centralized registry from DucksGalore
    private static final net.minecraftforge.registries.DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
            DucksGalore.CREATIVE_MODE_TABS;
    
    // Register our main creative tab
    public static final RegistryObject<CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register("main_tab",
            () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup." + DucksGalore.MOD_ID + ".main_tab"))
                .icon(() -> new ItemStack(AllItems.GOLDEN_RUBBER_DUCK_ITEM.get()))
                .displayItems((parameters, output) -> 
                    // Add all items from our mod to this tab
                    AllItems.getAllItems().forEach(item -> output.accept(item.get())))
                .build()
    );
    
    /**
     * Register method - initialization only
     */
    public static void register() {
        DucksGalore.LOGGER.info("Ducks Galore creative mode tabs initialized");
        // Registration happens automatically through DucksGalore.CREATIVE_MODE_TABS
    }
} 