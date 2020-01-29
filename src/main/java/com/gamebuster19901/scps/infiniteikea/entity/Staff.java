package com.gamebuster19901.scps.infiniteikea.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

public class Staff extends MonsterEntity{
	
	private int angerLevel;
	private int randomSoundDelay = this.rand.nextInt(40);
	
	public Staff(EntityType type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
	      this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	      this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(Staff.class));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(MOVEMENT_SPEED).setBaseValue(0.6f);
		this.getAttribute(ATTACK_DAMAGE).setBaseValue(8);
	}
	
	@Override
	protected void updateAITasks() {
		LivingEntity revengeTarget = this.getRevengeTarget();
		
		if(this.isAngry()) {
			this.angerLevel--;
			LivingEntity target = revengeTarget != null ? revengeTarget : this.getAttackTarget();
			if(!this.isAngry() && target != null) {
				if(!this.canEntityBeSeen(target)) {
					this.setRevengeTarget(null);
					this.setAttackTarget(null);
				}
				else {
					resetAngerLevel();
				}
			}
		}
		
		if (this.randomSoundDelay > 0 && this.randomSoundDelay-- == 0) {
			//TODO: play sound
		}
		
		super.updateAITasks();
	}

	public boolean becomeAngryAt(LivingEntity target) {
		resetAngerLevel();
		this.randomSoundDelay = this.rand.nextInt(40);
		if (target instanceof LivingEntity) {
			this.setRevengeTarget(target);
		}
		return true;
	}
	
	private void resetAngerLevel() {
		this.angerLevel = 400 + this.rand.nextInt(400);
	}

	public boolean isAngry() {
		return this.angerLevel > 0;
	}
	
}
