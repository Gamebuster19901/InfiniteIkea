package com.gamebuster19901.scps.infiniteikea.entity.ai;

import com.gamebuster19901.scps.infiniteikea.entity.Staff;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class StaffAttackGoal extends MeleeAttackGoal{

	private final Staff staff;
	private int raiseArmTicks;
	
	public StaffAttackGoal(Staff staff, double speedIn, boolean useLongMemory) {
		super(staff, speedIn, useLongMemory);
		this.staff = staff;
	}
	
	public void startExecuting() {
		super.startExecuting();
		this.raiseArmTicks = 0;
	}
	
	public void resetTask() {
		super.resetTask();
		this.staff.setAggroed(false);
	}
	
	public void tick() {
		super.tick();
		this.raiseArmTicks++;
		if(this.raiseArmTicks >= 5 && this.attackTick < 10) {
			this.staff.setAggroed(true);
		}
		else {
			this.staff.setAggroed(false);
		}
	}

}
