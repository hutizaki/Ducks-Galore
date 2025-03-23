package com.hutizaki.ducksgalore.content.buffs.buffs;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.content.buffs.DuckBuff;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

/**
 * Strength buff implementation
 */
public class StrengthBuff implements DuckBuff {
    private static final ResourceLocation ID = new ResourceLocation(DucksGalore.MOD_ID, "strength");
    
    @Override
    public ResourceLocation getId() {
        return ID;
    }
    
    @Override
    public String getTranslationKey() {
        return "buff.ducksgalore.strength";
    }
    
    @Override
    public ItemStack getDisplayIcon() {
        return new ItemStack(Items.BLAZE_POWDER);
    }
    
    @Override
    public void applyEffect(Player player, Level level, BlockPos pos) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0));
    }
} 