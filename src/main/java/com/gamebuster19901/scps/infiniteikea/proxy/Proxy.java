package com.gamebuster19901.scps.infiniteikea.proxy;

import java.util.function.Consumer;

import com.gamebuster19901.scps.infiniteikea.Main;
import com.gamebuster19901.scps.infiniteikea.dimension.InfiniteIkeaDimension;
import com.gamebuster19901.scps.infiniteikea.dimension.InfiniteIkeaDimensionType;
import com.gamebuster19901.scps.infiniteikea.dimension.InfiniteIkeaTeleporter;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public abstract class Proxy{
	
	public Proxy() {
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	@SuppressWarnings("unused")
	public void setup(FMLCommonSetupEvent event) {
		Main.LOGGER.info("COMMON SETUP");
		MinecraftForge.EVENT_BUS.register(this);
	}

	protected static IEventBus getBus() {
		return FMLJavaModLoadingContext.get().getModEventBus();
	}
	
	protected static <T extends Event> void addListener(Consumer<T>consumer) {
		getBus().addListener(consumer);
	}
	
	@SubscribeEvent
	public void onTick(PlayerTickEvent e) {
		if(e.player.isSneaking() && e.player.dimension != InfiniteIkeaDimensionType.getDimensionType() && !e.player.world.isRemote) {
			//ServerWorld world = e.player.getServer().getWorld(InfiniteIkeaDimensionType.getDimensionType());
			InfiniteIkeaTeleporter.teleport(e.player);
		}
	}
	
	@SubscribeEvent
	public void registerModDimensions(RegistryEvent.Register<ModDimension> e) {
		e.getRegistry().register(InfiniteIkeaDimensionType.DIMENSION_TYPE);
	}
	
	@SubscribeEvent
	public void registerDimensions(RegisterDimensionsEvent e) {
		if(DimensionType.byName(InfiniteIkeaDimension.DIMENSION_TYPE) == null) {
			DimensionManager.registerDimension(InfiniteIkeaDimension.DIMENSION_TYPE, InfiniteIkeaDimensionType.DIMENSION_TYPE, null, true);
		}
	}
	
}
