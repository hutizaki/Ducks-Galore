package com.hutizaki.ducksgalore.client.render.entity;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.client.render.model.DuckModel;
import com.hutizaki.ducksgalore.content.ducks.entities.DuckEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

/**
 * Renderer for duck entities
 */
public class DuckEntityRenderer extends MobRenderer<DuckEntity, DuckModel> {
    
    private static final ResourceLocation TEXTURE = new ResourceLocation(DucksGalore.MOD_ID, "textures/entity/duck.png");
    
    public DuckEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new DuckModel(context.bakeLayer(DuckModel.LAYER_LOCATION)), 0.3F);
    }
    
    @Override
    public ResourceLocation getTextureLocation(DuckEntity entity) {
        return TEXTURE;
    }
} 