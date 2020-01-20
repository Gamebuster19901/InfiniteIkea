package com.gamebuster19901.scps.infiniteikea.entity.ai;

import com.gamebuster19901.scps.infiniteikea.entity.Staff;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;

public class StaffHurtByAgressorGoal extends HurtByTargetGoal {

	public StaffHurtByAgressorGoal(CreatureEntity creatureIn, Class<?>[] p_i50317_2_) {
		super(creatureIn);
		this.setCallsForHelp(new Class[] {Staff.class});
	}
	
	protected void setAttackTarget(MobEntity mob, LivingEntity target) {
		if(mob instanceof Staff && this.goalOwner.canEntityBeSeen(target) && ((Staff)mob).becomeAngryAt(target)) {
			mob.setAttackTarget(target);
		}
	}

}
