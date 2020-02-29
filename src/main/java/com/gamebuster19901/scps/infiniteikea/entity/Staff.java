package com.gamebuster19901.scps.infiniteikea.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

import javax.annotation.Nullable;

public class Staff extends MonsterEntity {
	
	public static final DataParameter<Float> HEAD_WIDTH = EntityDataManager.createKey(Staff.class, DataSerializers.FLOAT);
	public static final DataParameter<Float> HEAD_HEIGHT = EntityDataManager.createKey(Staff.class, DataSerializers.FLOAT);
	public static final DataParameter<Float> BODY_WIDTH = EntityDataManager.createKey(Staff.class, DataSerializers.FLOAT);
	public static final DataParameter<Float> BODY_HEIGHT = EntityDataManager.createKey(Staff.class, DataSerializers.FLOAT);
	public static final DataParameter<Float> ARM_WIDTH = EntityDataManager.createKey(Staff.class, DataSerializers.FLOAT);
	public static final DataParameter<Float> ARM_LENGTH = EntityDataManager.createKey(Staff.class, DataSerializers.FLOAT);
	public static final DataParameter<Float> LEG_LENGTH = EntityDataManager.createKey(Staff.class, DataSerializers.FLOAT);
	public static final DataParameter<Float> LEG_WIDTH	 = EntityDataManager.createKey(Staff.class, DataSerializers.FLOAT);
	
	private int angerLevel;
	private int randomSoundDelay = this.rand.nextInt(40);
	
	public Staff(EntityType<Staff> type, World worldIn) {
		super(type, worldIn);
		this.setBoundingBox(new AxisAlignedBB(0,0,0,0,0,0));
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
	public void registerData() {
		super.registerData();
		registerParam(HEAD_WIDTH, 1f);
		registerParam(HEAD_HEIGHT, 1f);
		registerParam(BODY_WIDTH, 1f);
		registerParam(BODY_HEIGHT, 1f);
		registerParam(ARM_WIDTH, 1f);
		registerParam(ARM_LENGTH, 1f);
		registerParam(LEG_LENGTH, 1f);
		registerParam(LEG_WIDTH, 1f);
	}
	
	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT nbt) {
		spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, nbt);
		
		setParam(HEAD_WIDTH, between(0.5f, 1.5f));
		setParam(HEAD_HEIGHT, between(0.5f, 1.5f));
		setParam(BODY_WIDTH, between(0.5f, 2f));
		setParam(BODY_HEIGHT, between(0.5f, 2f));
		setParam(ARM_WIDTH, between(0.5f, 1.5f));
		setParam(ARM_LENGTH, between(0.5f, 2f));
		setParam(LEG_LENGTH, between(0.5f, 2f));
		setParam(LEG_WIDTH, between(0.5f, 1.5f));
		
		this.recalculateSize();
		
		return spawnData;
	}
	
	@Override
	public void writeAdditional(CompoundNBT nbt) {
		super.writeAdditional(nbt);
		nbt.putFloat("HeadWidth", getParam(HEAD_WIDTH));
		nbt.putFloat("HeadHeight", getParam(HEAD_HEIGHT));
		nbt.putFloat("BodyWidth", getParam(BODY_WIDTH));
		nbt.putFloat("BodyHeight", getParam(BODY_HEIGHT));
		nbt.putFloat("ArmWidth", getParam(ARM_WIDTH));
		nbt.putFloat("ArmLength", getParam(ARM_LENGTH));
		nbt.putFloat("LegLength", getParam(LEG_LENGTH));
		nbt.putFloat("LegWidth", getParam(LEG_WIDTH));
		System.out.println(nbt.toFormattedComponent().getFormattedText());
	}
	
	@Override
	public void readAdditional(CompoundNBT nbt) {
		super.readAdditional(nbt);
		if(nbt.contains("HeadWidth")) {
			setParam(HEAD_WIDTH, nbt.getFloat("HeadWidth"));
			setParam(HEAD_HEIGHT, nbt.getFloat("HeadHeight"));
			setParam(BODY_WIDTH, nbt.getFloat("BodyWidth"));
			setParam(BODY_HEIGHT, nbt.getFloat("BodyHeight"));
			setParam(ARM_WIDTH, nbt.getFloat("ArmWidth"));
			setParam(ARM_LENGTH, nbt.getFloat("ArmLength"));
			setParam(LEG_LENGTH, nbt.getFloat("LegLength"));
			setParam(LEG_WIDTH, nbt.getFloat("LegWidth"));
		}
		this.recalculateSize();
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
	
	private final float between(float min, float max) {
		return min + this.rand.nextFloat() * (max - min);
	}
	
	private <T> void registerParam(DataParameter<T> param, T defaultValue) {
		this.dataManager.register(param, defaultValue);
	}
	
	public <T> T getParam(DataParameter<T> param) {
		return this.dataManager.get(param);
	}
	
	private <T> void setParam(DataParameter<T> param, T value) {
		this.dataManager.set(param, value);
	}
	
}
