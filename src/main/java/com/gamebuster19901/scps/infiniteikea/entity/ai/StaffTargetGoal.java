package com.gamebuster19901.scps.infiniteikea.entity.ai;

import com.gamebuster19901.scps.infiniteikea.dimension.InfiniteIkeaDimension;
import com.gamebuster19901.scps.infiniteikea.entity.Staff;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;

public class StaffTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

	public StaffTargetGoal(Staff goalOwner, Class<T> targetClass) {
		super(goalOwner, targetClass, true);
	}
	
	@Override
	public boolean shouldExecute() {
		return InfiniteIkeaDimension.isDay(this.goalOwner.world.getDayTime());
	}

}
