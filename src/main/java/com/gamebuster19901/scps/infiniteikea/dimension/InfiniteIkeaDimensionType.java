package com.gamebuster19901.scps.infiniteikea.dimension;

import java.util.function.BiFunction;

import com.gamebuster19901.scps.infiniteikea.Main;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

public class InfiniteIkeaDimensionType extends ModDimension {

	public static final InfiniteIkeaDimensionType DIMENSION_TYPE = new InfiniteIkeaDimensionType();
	
	private InfiniteIkeaDimensionType() {
		this.setRegistryName(Main.MODID, "scp-3008");
	}
	
	public static DimensionType getDimensionType() {
		return DimensionType.byName(new ResourceLocation(Main.MODID, "scp-3008"));
	}
	
	@Override
	public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
		return InfiniteIkeaDimension::new;
	}

}
