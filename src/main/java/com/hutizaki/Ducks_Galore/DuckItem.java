package com.hutizaki.Ducks_Galore;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class DuckItem extends BlockItem {
	public DuckItem(Block block, Properties properties) {
		super(block, properties.stacksTo(1));
	}
} 