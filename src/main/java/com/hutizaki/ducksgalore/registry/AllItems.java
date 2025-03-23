package com.hutizaki.ducksgalore.registry;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.content.ducks.items.GoldenRubberDuckItem;
import com.hutizaki.ducksgalore.content.ducks.items.RubberDuckItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class AllItems {
    // Create the item registry
    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, DucksGalore.MOD_ID);

    // Register custom items
    public static final RegistryObject<Item> RUBBER_DUCK = registerItem("rubber_duck",
        () -> new RubberDuckItem(AllBlocks.RUBBER_DUCK.get(), new Item.Properties()));
    
    public static final RegistryObject<Item> GOLDEN_RUBBER_DUCK = registerItem("golden_rubber_duck",
        () -> new GoldenRubberDuckItem(AllBlocks.GOLDEN_RUBBER_DUCK.get(), new Item.Properties()));

    /**
     * Helper method to register an item
     */
    public static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    /**
     * Register this registry with the mod event bus
     */
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
} 