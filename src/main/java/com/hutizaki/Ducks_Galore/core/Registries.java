package com.hutizaki.Ducks_Galore.core;

import com.hutizaki.Ducks_Galore.DucksGalore;
import com.hutizaki.Ducks_Galore.DuckBlock;
import com.hutizaki.Ducks_Galore.DuckItem;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registries {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			DucksGalore.MODID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DucksGalore.MODID);
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister
			.create(ForgeRegistries.SOUND_EVENTS, DucksGalore.MODID);

	// Blocks
	public static final RegistryObject<DuckBlock> DUCK_BLOCK = BLOCKS.register("default_duck",
			() -> new DuckBlock());
	// Items
	public static final RegistryObject<DuckItem> DUCK_ITEM = ITEMS.register("default_duck",
			() -> new DuckItem(DUCK_BLOCK.get(),
					new Item.Properties().stacksTo(4).rarity(Rarity.RARE)));
	// Sound Events
	public static final RegistryObject<SoundEvent> DUCK_USE = SOUND_EVENTS.register("duck_use",
			() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DucksGalore.MODID, "duck_use")));
	public static final RegistryObject<SoundEvent> DUCK_PLACE = SOUND_EVENTS.register("duck_place",
			() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DucksGalore.MODID, "duck_place")));
			
	// Register items to creative tabs
	public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			event.accept(DUCK_BLOCK);
		}
		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			event.accept(DUCK_ITEM);
		}
	}
} 