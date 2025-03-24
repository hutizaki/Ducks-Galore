package com.hutizaki.ducksgalore.ponder;

import com.hutizaki.ducksgalore.registry.AllBlocks;
import com.simibubi.create.foundation.ponder.PonderPalette;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.utility.Pointing;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

/**
 * Contains all ponder scenes for Ducks Galore mod
 */
public class PonderScenes {
    /**
     * Basic rubber duck ponder scene
     */
    public static void rubberDuck(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("rubber_duck", "The Regular Rubber Duck");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.idle(10);
        
        // Add a water pool
        for (int x = 1; x < 4; x++) {
            for (int z = 1; z < 4; z++) {
                scene.world.setBlock(new BlockPos(x, 0, z), Blocks.WATER.defaultBlockState(), false);
            }
        }
        
        BlockPos duckPos = new BlockPos(2, 1, 2);
        
        // Show the duck placing animation
        ItemStack duckItem = new ItemStack(AllBlocks.RUBBER_DUCK.get().asItem());
        scene.overlay.showText(40)
            .text("Place a Rubber Duck")
            .placeNearTarget()
            .pointAt(Vec3.atBottomCenterOf(duckPos));
        scene.idle(40);
        
        scene.world.setBlock(duckPos, AllBlocks.RUBBER_DUCK.get().defaultBlockState(), false);
        scene.world.showSection(util.select.position(duckPos), Direction.DOWN);
        scene.idle(20);
        
        // Show that it floats on water
        scene.overlay.showText(60)
            .text("The Rubber Duck can float on water")
            .placeNearTarget()
            .pointAt(Vec3.atCenterOf(duckPos));
        scene.idle(70);
        
        // Show that it can be broken easily
        scene.overlay.showText(60)
            .text("It can be broken easily with any tool or by hand")
            .placeNearTarget()
            .pointAt(Vec3.atCenterOf(duckPos));
        scene.idle(70);
        
        // Highlight the duck when in use
        scene.overlay.showText(60)
            .text("Right-clicking grants you a short Absorption effect")
            .placeNearTarget()
            .pointAt(Vec3.atCenterOf(duckPos));
        scene.idle(20);
        
        // Make the duck glow
        scene.idle(50);
    }
    
    /**
     * Golden rubber duck ponder scene
     */
    public static void goldenRubberDuck(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("golden_rubber_duck", "The Golden Rubber Duck");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        
        // Create gold blocks altar
        for (int x = 1; x < 4; x++) {
            for (int z = 1; z < 4; z++) {
                scene.world.setBlock(new BlockPos(x, 0, z), Blocks.GOLD_BLOCK.defaultBlockState(), false);
                scene.world.showSection(util.select.position(new BlockPos(x, 0, z)), Direction.DOWN);
            }
        }
        scene.idle(30);
        
        // Put a regular rubber duck and enchanted golden apple
        BlockPos centerPos = new BlockPos(2, 1, 2);
        scene.overlay.showText(60)
            .text("To create a Golden Rubber Duck, you need a ritual...")
            .placeNearTarget()
            .pointAt(Vec3.atCenterOf(centerPos));
        scene.idle(70);
        
        // Show placing items step
        scene.overlay.showText(60)
            .text("Place a Rubber Duck and an Enchanted Golden Apple")
            .placeNearTarget()
            .pointAt(Vec3.atCenterOf(centerPos));
        scene.idle(40);
        
        // Transformation effect
        scene.idle(5);
        scene.world.setBlock(centerPos, AllBlocks.GOLDEN_RUBBER_DUCK.get().defaultBlockState(), false);
        scene.world.showSection(util.select.position(centerPos), Direction.DOWN);
        scene.idle(30);
        
        // Show that it requires a pickaxe
        scene.overlay.showText(60)
            .text("The Golden Duck requires a pickaxe to mine")
            .placeNearTarget()
            .pointAt(Vec3.atCenterOf(centerPos));
        scene.idle(70);
        
        scene.overlay.showText(60)
            .text("With a pickaxe, it breaks instantly")
            .placeNearTarget()
            .pointAt(Vec3.atCenterOf(centerPos));
        scene.idle(70);
        
        // Special effects
        scene.overlay.showText(70)
            .text("Right-clicking grants Regeneration II and Absorption II")
            .placeNearTarget()
            .pointAt(Vec3.atCenterOf(centerPos));
        scene.idle(30);
        
        // Make the duck glow
        scene.idle(50);
    }
    
    /**
     * Gold ore rubber duck ponder scene
     */
    public static void goldOreRubberDuck(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("gold_ore_rubber_duck", "The Gold Ore Rubber Duck");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        
        // Create stone with gold ore
        for (int x = 1; x < 4; x++) {
            for (int z = 1; z < 4; z++) {
                scene.world.setBlock(new BlockPos(x, 0, z), Blocks.STONE.defaultBlockState(), false);
                scene.world.showSection(util.select.position(new BlockPos(x, 0, z)), Direction.DOWN);
            }
        }
        
        // Add some gold ore blocks
        BlockPos orePos1 = new BlockPos(1, 0, 1);
        BlockPos orePos2 = new BlockPos(3, 0, 2);
        BlockPos orePos3 = new BlockPos(2, 0, 3);
        
        scene.world.setBlock(orePos1, Blocks.GOLD_ORE.defaultBlockState(), false);
        scene.world.setBlock(orePos2, Blocks.GOLD_ORE.defaultBlockState(), false);
        scene.world.setBlock(orePos3, Blocks.GOLD_ORE.defaultBlockState(), false);
        
        scene.idle(30);
        
        // Show mining gold ore
        scene.overlay.showText(60)
            .text("The Gold Ore Duck is a rare find when mining gold ore")
            .placeNearTarget()
            .pointAt(Vec3.atCenterOf(orePos2));
        scene.idle(70);
        
        // Mine the gold ore
        scene.overlay.showText(40)
            .text("Mine gold ore with a pickaxe")
            .placeNearTarget()
            .pointAt(Vec3.atCenterOf(orePos2));
        scene.idle(40);
        
        // Break the ore and drop the duck
        scene.world.hideSection(util.select.position(orePos2), Direction.UP);
        scene.idle(20);
        
        // Duck appears
        BlockPos duckPos = new BlockPos(2, 1, 2);
        scene.world.setBlock(duckPos, AllBlocks.GOLD_ORE_RUBBER_DUCK.get().defaultBlockState(), false);
        scene.world.showSection(util.select.position(duckPos), Direction.DOWN);
        scene.idle(30);
        
        // Show that it requires a pickaxe
        scene.overlay.showText(60)
            .text("The Gold Ore Duck requires a pickaxe to mine")
            .placeNearTarget()
            .pointAt(Vec3.atCenterOf(duckPos));
        scene.idle(70);
        
        scene.overlay.showText(60)
            .text("With a pickaxe, it breaks instantly")
            .placeNearTarget()
            .pointAt(Vec3.atCenterOf(duckPos));
        scene.idle(70);
        
        // Special luck effect
        scene.overlay.showText(70)
            .text("Right-clicking grants the Luck effect for better treasure finds")
            .placeNearTarget()
            .pointAt(Vec3.atCenterOf(duckPos));
        scene.idle(30);
        
        // Make the duck glow
        scene.idle(50);
    }
}
