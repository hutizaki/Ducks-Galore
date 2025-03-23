package com.hutizaki.ducksgalore.content.ducks.entities;

import com.hutizaki.ducksgalore.registry.AllEntityTypes;
import com.hutizaki.ducksgalore.registry.AllSoundEvents;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Duck entity implementation
 */
public class DuckEntity extends Animal {
    
    public DuckEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }
    
    /**
     * Create a new duck entity instance
     */
    public DuckEntity(Level level) {
        this(AllEntityTypes.DUCK.get(), level);
    }
    
    /**
     * Register attributes for the duck entity
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 4.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }
    
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }
    
    @Override
    protected SoundEvent getAmbientSound() {
        return AllSoundEvents.DUCK_USE.get();
    }
    
    @Override
    public Animal getBreedOffspring(net.minecraft.server.level.ServerLevel level, Animal animal) {
        return new DuckEntity(level);
    }
} 