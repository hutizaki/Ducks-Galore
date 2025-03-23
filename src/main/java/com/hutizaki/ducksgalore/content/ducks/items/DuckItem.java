package com.hutizaki.ducksgalore.content.ducks.items;

import com.hutizaki.ducksgalore.registry.AllSoundEvents;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class DuckItem extends BlockItem {
    public DuckItem(Block block, Properties properties) {
        super(block, properties.stacksTo(1));
    }
    
    @Override
    protected boolean placeBlock(BlockPlaceContext context, net.minecraft.world.level.block.state.BlockState state) {
        boolean result = super.placeBlock(context, state);
        if (result) {
            Level level = context.getLevel();
            if (!level.isClientSide) {
                // Play a custom placement sound with improved parameters
                level.playSound(
                    context.getPlayer(), 
                    context.getClickedPos(), 
                    AllSoundEvents.DUCK_PLACE.get(), 
                    SoundSource.BLOCKS,
                    1.0F, 
                    level.random.nextFloat() * 0.2F + 0.9F
                );
            }
        }
        return result;
    }
} 