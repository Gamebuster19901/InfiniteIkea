package com.gamebuster19901.scps.infiniteikea.client.audio;

import com.gamebuster19901.scps.infiniteikea.Main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.TickableSound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class WorldLightSound extends TickableSound {

	public static ResourceLocation LIGHT_SOUND = new ResourceLocation(Main.MODID, "lights");
	public static SoundEvent soundEvent = new SoundEvent(LIGHT_SOUND);
	static {
		soundEvent.setRegistryName(LIGHT_SOUND);
	}
	
	private final World world;
	private final PlayerEntity player;
	
	public WorldLightSound() {
		super(soundEvent, SoundCategory.MASTER);
		this.world = Minecraft.getInstance().world;
		this.player = Minecraft.getInstance().player;
		this.volume = Float.MAX_VALUE;
		this.pitch = 1f;
		this.repeat = false;
		this.global = true;
	}

	@Override
	public void tick() {
		if(player.world != world) {
			this.donePlaying = true;
		}
	}

}
