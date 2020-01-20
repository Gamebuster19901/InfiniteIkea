package com.gamebuster19901.scps.infiniteikea.entity.ai;

import com.gamebuster19901.scps.infiniteikea.entity.Staff;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;

public class StaffTargetAgressorGoal extends NearestAttackableTargetGoal<LivingEntity> {

	public StaffTargetAgressorGoal(Staff goalOwner) {
		super(goalOwner, LivingEntity.class, true);
	}
	
	@Override
	public boolean shouldExecute() {
		return ((Staff)goalOwner).isAngry() && super.shouldExecute();
	}

}
