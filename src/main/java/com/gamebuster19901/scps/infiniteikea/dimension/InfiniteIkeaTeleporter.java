package com.gamebuster19901.scps.infiniteikea.dimension;

import net.minecraft.entity.Entity;

public class InfiniteIkeaTeleporter {
	public static void teleport(Entity entity) {
		if(!entity.world.isRemote) {
			entity.changeDimension(InfiniteIkeaDimensionType.getDimensionType());
		}
	}
}
