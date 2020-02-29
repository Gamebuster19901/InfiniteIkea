package com.gamebuster19901.scps.infiniteikea.proxy;

import java.util.function.Consumer;

import com.gamebuster19901.scps.infiniteikea.Main;
import static com.gamebuster19901.scps.infiniteikea.block.Blocks.*;
import com.gamebuster19901.scps.infiniteikea.client.audio.WorldLightSound;
import com.gamebuster19901.scps.infiniteikea.dimension.InfiniteIkeaDimension;
import com.gamebuster19901.scps.infiniteikea.dimension.InfiniteIkeaDimensionType;
import com.gamebuster19901.scps.infiniteikea.dimension.InfiniteIkeaTeleporter;
import com.gamebuster19901.scps.infiniteikea.entity.Staff;
import com.gamebuster19901.scps.infiniteikea.network.Network;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
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
import net.minecraftforge.registries.ObjectHolder;

public abstract class Proxy {
	
	public Proxy() {
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	@SuppressWarnings("unused")
	public void setup(FMLCommonSetupEvent event) {
		Main.LOGGER.info("COMMON SETUP");
		MinecraftForge.EVENT_BUS.register(this);
		Network.Register();
	}

	protected static IEventBus getBus() {
		return FMLJavaModLoadingContext.get().getModEventBus();
	}
	
	protected static <T extends Event> void addListener(Consumer<T>consumer) {
		getBus().addListener(consumer);
	}
	
	@SubscribeEvent
	public void onTick(PlayerTickEvent e) {
		if(e.player.dimension != InfiniteIkeaDimensionType.getDimensionType() && !e.player.world.isRemote && e.player.getHeldItemMainhand().getItem().getRegistryName().equals(IKEA_FLOOR_LIGHT_BLOCK.getRegistryName())) {
			//ServerWorld world = e.player.getServer().getWorld(InfiniteIkeaDimensionType.getDimensionType());
			InfiniteIkeaTeleporter.teleport(e.player);
		}
	}
	
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> e) {
		e.getRegistry().register(IKEA_FLOOR_LIGHT_BLOCK);
		e.getRegistry().register(IKEA_FLOOR_DARK_BLOCK);
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> e) {
		e.getRegistry().register(new BlockItem(IKEA_FLOOR_LIGHT_BLOCK, new Item.Properties().maxStackSize(64)).setRegistryName(Main.MODID, "ikea_floor_light"));
		e.getRegistry().register(new BlockItem(IKEA_FLOOR_DARK_BLOCK, new Item.Properties().maxStackSize(64)).setRegistryName(Main.MODID, "ikea_floor_dark"));
	}
	
	@SubscribeEvent
	public void registerSoundEvents(RegistryEvent.Register<SoundEvent> e) {
		e.getRegistry().register(WorldLightSound.soundEvent);
	}
	
	@ObjectHolder("infiniteikea:staff")
	public static EntityType<Staff> staff;
	
	@SubscribeEvent
	public void registerEntities(RegistryEvent.Register<EntityType<?>> e) {
		e.getRegistry().register(EntityType.Builder
				.create(Staff::new, EntityClassification.MONSTER)
				.size(1, 1)
				.setTrackingRange(70)
				.setUpdateInterval(1)
				.setShouldReceiveVelocityUpdates(false)
				.setCustomClientFactory((spawnEntity, world) -> new Staff(staff, world))
				.build("infiniteikea:staff")
				.setRegistryName(new ResourceLocation("infiniteikea:staff")));
	}
	
	@SubscribeEvent
	public void registerModDimensions(RegistryEvent.Register<ModDimension> e) {
		e.getRegistry().register(InfiniteIkeaDimensionType.DIMENSION_TYPE);
	}
	
	@SubscribeEvent
	public void registerDimensions(RegisterDimensionsEvent e) {
		if(DimensionType.byName(InfiniteIkeaDimension.DIMENSION_NAME) == null) {
			DimensionManager.registerDimension(InfiniteIkeaDimension.DIMENSION_NAME, InfiniteIkeaDimensionType.DIMENSION_TYPE, null, true);
		}
	}
}
