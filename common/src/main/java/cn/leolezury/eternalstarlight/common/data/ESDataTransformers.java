package cn.leolezury.eternalstarlight.common.data;

import cn.leolezury.eternalstarlight.common.EternalStarlight;
import cn.leolezury.eternalstarlight.common.world.gen.system.biome.BiomeData;
import cn.leolezury.eternalstarlight.common.world.gen.system.transformer.DataTransformer;
import cn.leolezury.eternalstarlight.common.world.gen.system.transformer.biome.*;
import cn.leolezury.eternalstarlight.common.world.gen.system.transformer.height.FinalizeHeightsTransformer;
import cn.leolezury.eternalstarlight.common.world.gen.system.transformer.height.NoiseHeightTransformer;
import cn.leolezury.eternalstarlight.common.world.gen.system.transformer.height.SmoothHeightsTransformer;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class ESDataTransformers {
    public static final ResourceKey<DataTransformer> DUPLICATE = create("duplicate");
    public static final ResourceKey<DataTransformer> FINALIZE_BIOMES = create("finalize_biomes");
    public static final ResourceKey<DataTransformer> APPLY_BIOMES = create("apply_biomes");
    public static final ResourceKey<DataTransformer> ADD_OCEAN = create("add_ocean");
    public static final ResourceKey<DataTransformer> ADD_BEACHES = create("add_beaches");
    public static final ResourceKey<DataTransformer> ADD_RIVERS = create("add_rivers");
    public static final ResourceKey<DataTransformer> ADD_TRANSITIONS = create("add_transitions");
    public static final ResourceKey<DataTransformer> ADD_THE_ABYSS = create("add_the_abyss");
    public static final ResourceKey<DataTransformer> RANDOMIZE_BIOMES = create("randomize_biomes");
    public static final ResourceKey<DataTransformer> ASSIMILATE_BIOMES = create("assimilate_biomes");
    public static final ResourceKey<DataTransformer> ASSIMILATE_LONELY_BIOMES = create("assimilate_lonely_biomes");

    public static final ResourceKey<DataTransformer> FINALIZE_HEIGHTS = create("finalize_heights");
    public static final ResourceKey<DataTransformer> NOISE_HEIGHT = create("noise_height");
    public static final ResourceKey<DataTransformer> SMOOTH_HEIGHTS_LARGE = create("smooth_heights_large");
    public static final ResourceKey<DataTransformer> SMOOTH_HEIGHTS_SMALL = create("smooth_heights_small");

    public static void bootstrap(BootstapContext<DataTransformer> context) {
        HolderGetter<BiomeData> data = context.lookup(ESRegistries.BIOME_DATA);
        context.register(DUPLICATE, new DuplicateSizeTransformer());
        context.register(FINALIZE_BIOMES, new FinalizeBiomesTransformer());
        context.register(APPLY_BIOMES, new AddBiomesTransformer(0.06, List.of(
                data.getOrThrow(ESBiomeData.STARLIGHT_FOREST),
                data.getOrThrow(ESBiomeData.STARLIGHT_DENSE_FOREST),
                data.getOrThrow(ESBiomeData.DARK_SWAMP),
                data.getOrThrow(ESBiomeData.STARLIGHT_PERMAFROST_FOREST),
                data.getOrThrow(ESBiomeData.SCARLET_FOREST),
                data.getOrThrow(ESBiomeData.CRYSTALLIZED_DESERT)
        ), List.of(
                data.getOrThrow(ESBiomeData.STARLIT_SEA)
        )));
        context.register(ADD_OCEAN, new AddOceanTransformer(data.getOrThrow(ESBiomeData.STARLIT_SEA)));
        context.register(ADD_BEACHES, new AddBeachesTransformer(data.getOrThrow(ESBiomeData.WARM_SHORE)));
        context.register(ADD_RIVERS, new AddRiversTransformer(List.of(
                new AddRiversTransformer.RiverWithOffset(data.getOrThrow(ESBiomeData.SHIMMER_RIVER), 0.03f, 0),
                new AddRiversTransformer.RiverWithOffset(data.getOrThrow(ESBiomeData.ETHER_RIVER), 0.05f, 4096)
        )));
        context.register(ADD_THE_ABYSS, new AddTheAbyssTransformer(data.getOrThrow(ESBiomeData.THE_ABYSS)));
        context.register(ADD_TRANSITIONS, new AddTransitionBiomesTransformer(List.of(
                new AddTransitionBiomesTransformer.BiomeWithTransition(data.getOrThrow(ESBiomeData.SHIMMER_RIVER), data.getOrThrow(ESBiomeData.SHIMMER_RIVER_TRANSITION)),
                new AddTransitionBiomesTransformer.BiomeWithTransition(data.getOrThrow(ESBiomeData.THE_ABYSS), data.getOrThrow(ESBiomeData.THE_ABYSS_TRANSITION)),
                new AddTransitionBiomesTransformer.BiomeWithTransition(data.getOrThrow(ESBiomeData.ETHER_RIVER), data.getOrThrow(ESBiomeData.TORREYA_FOREST))
        )));
        context.register(RANDOMIZE_BIOMES, new RandomizeBiomesTransformer());
        context.register(ASSIMILATE_BIOMES, new AssimilateBiomesTransformer(false));
        context.register(ASSIMILATE_LONELY_BIOMES, new AssimilateBiomesTransformer(true));

        context.register(FINALIZE_HEIGHTS, new FinalizeHeightsTransformer());
        context.register(NOISE_HEIGHT, new NoiseHeightTransformer());
        context.register(SMOOTH_HEIGHTS_LARGE, new SmoothHeightsTransformer(5));
        context.register(SMOOTH_HEIGHTS_SMALL, new SmoothHeightsTransformer(1));
    }

    public static ResourceKey<DataTransformer> create(String name) {
        return ResourceKey.create(ESRegistries.DATA_TRANSFORMER, new ResourceLocation(EternalStarlight.MOD_ID, name));
    }
}
