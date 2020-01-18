package com.gamebuster19901.scps.infiniteikea.dimension;

import javax.annotation.Nullable;

import com.gamebuster19901.scps.infiniteikea.Main;
import static com.gamebuster19901.scps.infiniteikea.block.Blocks.*;
import com.gamebuster19901.scps.infiniteikea.network.Network;
import com.gamebuster19901.scps.infiniteikea.network.packet.server.PlayLightSound;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.FlatLayerInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.PacketDistributor;

public class InfiniteIkeaDimension extends Dimension{

	public static final ResourceLocation DIMENSION_NAME = new ResourceLocation(Main.MODID, "scp-3008");
	
	public InfiniteIkeaDimension(World worldIn, DimensionType typeIn) {
		super(worldIn, typeIn);
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		//ChunkGeneratorType<InfiniteIkeaGenerationSettings, InfiniteIkeaChunkGenerator> chunkGeneratorType = new ChunkGeneratorType<>(InfiniteIkeaChunkGenerator::new, true, InfiniteIkeaGenerationSettings::new);
		//return chunkGeneratorType.create(this.world, null, chunkGeneratorType.createSettings());
		FlatGenerationSettings flatSettings = new FlatGenerationSettings();
		flatSettings.setBiome(Biomes.BADLANDS);
		flatSettings.getFlatLayers().add(new FlatLayerInfo(1, IKEA_FLOOR_LIGHT_BLOCK));
		flatSettings.updateLayers();
		return ChunkGeneratorType.FLAT.create(this.world, new SingleBiomeProvider(new SingleBiomeProviderSettings().setBiome(Biomes.BADLANDS)), flatSettings);
	}

	@Override
	public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
		for(int i = chunkPosIn.getXStart(); i <= chunkPosIn.getXEnd(); i++) {
			for( int j = chunkPosIn.getZStart(); j <= chunkPosIn.getZEnd(); j++) {
				BlockPos spawnPos = this.findSpawn(i, j, checkValid);
				if (spawnPos != null) {
					return spawnPos;
				}
			}
		}
		return null;
	}

	@Override
	public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(posX, 0, posZ);
		Biome biome = this.world.getBiome(blockpos$mutableblockpos);
		BlockState blockstate = biome.getSurfaceBuilderConfig().getTop();
		if (checkValid && !blockstate.getBlock().isIn(BlockTags.VALID_SPAWN)) {
			 return null;
		} 
		else {
			 Chunk chunk = this.world.getChunk(posX >> 4, posZ >> 4);
			 int i = chunk.getTopBlockY(Heightmap.Type.MOTION_BLOCKING, posX & 15, posZ & 15);
			 if (i < 0) {
				return null;
			 } 
			 else if (chunk.getTopBlockY(Heightmap.Type.WORLD_SURFACE, posX & 15, posZ & 15) > chunk.getTopBlockY(Heightmap.Type.OCEAN_FLOOR, posX & 15, posZ & 15)) {
				return null;
			 } 
			 else {
				for(int j = i + 1; j >= 0; --j) {
					blockpos$mutableblockpos.setPos(posX, j, posZ);
					BlockState blockstate1 = this.world.getBlockState(blockpos$mutableblockpos);
					if (!blockstate1.getFluidState().isEmpty()) {
						break;
					}
	
					if (blockstate1.equals(blockstate)) {
						return blockpos$mutableblockpos.up().toImmutable();
					}
				}

				return null;
			 }
		}
	}

	private boolean wasDay = true;
	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		long time = worldTime % 24000;

		if(time >= 4000 && time <= 15000) { //IKEA is open between 9AM and 10PM
			if(!wasDay) {
				playLightSound();
			}
			return 1f;
		}
		if(wasDay) {
			playLightSound();
		}
		return -0.5f; //The store is now closed, please exit the building.
	}
	
	@Nullable
	@OnlyIn(Dist.CLIENT)
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
		return super.calcSunriseSunsetColors(celestialAngle, partialTicks);
	}
	
	@OnlyIn(Dist.CLIENT)
	public double getVoidFogYFactor() {
		return 1;
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	public Vec3d getFogColor(float celestialAngle, float partialTicks) {
		if(celestialAngle > 0) {
			return new Vec3d(1,1,1);
		}
		else {
			return new Vec3d(-500,-500,-500);
		}
	}

	@Override
	public boolean canRespawnHere() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean doesXZShowFog(int x, int z) {
		return true;
	}

	private void playLightSound() {
		if(!this.world.isRemote) {
			Network.NETWORK.send(PacketDistributor.DIMENSION.with(this.getDimension()::getType), new PlayLightSound());
			wasDay = !wasDay;
		}
	}
	
}
