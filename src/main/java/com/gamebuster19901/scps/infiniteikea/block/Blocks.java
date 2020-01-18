package com.gamebuster19901.scps.infiniteikea.block;

import com.gamebuster19901.scps.infiniteikea.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class Blocks {
	public static final Block IKEA_FLOOR_LIGHT_BLOCK = new Block(Block.Properties.create(Material.ROCK, MaterialColor.SNOW).hardnessAndResistance(-1f, Float.MAX_VALUE)).setRegistryName(Main.MODID, "ikea_floor_light");
	public static final Block IKEA_FLOOR_DARK_BLOCK = new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(-1f, Float.MAX_VALUE)).setRegistryName(Main.MODID, "ikea_floor_dark");
	
	private Blocks() {
		throw new AssertionError();
	}
}
