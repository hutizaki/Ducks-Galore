package com.hutizaki.ducksgalore.client.render.model;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.content.ducks.entities.DuckEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

/**
 * 3D model for duck entities
 */
public class DuckModel extends EntityModel<DuckEntity> {
    
    // Layer location for the model
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
        new ResourceLocation(DucksGalore.MOD_ID, "duck"), "main");
    
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    
    public DuckModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
    }
    
    /**
     * Create the layer definition for the duck model
     */
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        
        // Define body
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
            .texOffs(0, 0)
            .addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
            PartPose.offset(0.0F, 19.0F, 0.0F));
        
        // Define head
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
            .texOffs(0, 12)
            .addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(16, 12)
            .addBox(-1.0F, 0.0F, -6.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), // Bill
            PartPose.offset(0.0F, 15.0F, -3.0F));
        
        // Define legs
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
            .texOffs(22, 0)
            .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offset(-1.5F, 21.0F, 0.0F));
            
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
            .texOffs(22, 0)
            .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offset(1.5F, 21.0F, 0.0F));
            
        return LayerDefinition.create(meshdefinition, 32, 32);
    }
    
    @Override
    public void setupAnim(DuckEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Head rotation
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        
        // Leg movement
        this.rightLeg.xRot = (float) Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.xRot = (float) Math.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    }
    
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
} 