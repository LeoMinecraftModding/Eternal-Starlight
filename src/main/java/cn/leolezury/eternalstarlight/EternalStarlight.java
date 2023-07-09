package cn.leolezury.eternalstarlight;

import cn.leolezury.eternalstarlight.init.*;
import net.fabricmc.api.ModInitializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class EternalStarlight implements ModInitializer {
    public static final String MODID = "eternal_starlight";

    @Override
    public void onInitialize() {
        BlockInit.BLOCKS.register(modEventBus);

        ItemInit.register(modEventBus);
        CreativeModeTabInit.TABS.register(modEventBus);

        EntityInit.ENTITIES.register(modEventBus);

        BlockEntityInit.BLOCK_ENTITIES.register(modEventBus);

        EnchantmentInit.ENCHANTMENTS.register(modEventBus);

        FeatureInit.FEATURES.register(modEventBus);
        PlacerInit.TRUNK_PLACERS.register(modEventBus);
        PlacerInit.FLOIAGE_PLACERS.register(modEventBus);
        TreeDecoratorInit.TREE_DECORATORS.register(modEventBus);

        POIInit.POIS.register(modEventBus);

        ParticleInit.PARTICLE_TYPES.register(modEventBus);

        SoundEventInit.SOUND_EVENTS.register(modEventBus);
    }
}
