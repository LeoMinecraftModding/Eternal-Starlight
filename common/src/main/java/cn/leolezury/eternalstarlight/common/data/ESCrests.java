package cn.leolezury.eternalstarlight.common.data;

import cn.leolezury.eternalstarlight.common.EternalStarlight;
import cn.leolezury.eternalstarlight.common.crest.Crest;
import cn.leolezury.eternalstarlight.common.registry.ESSpells;
import cn.leolezury.eternalstarlight.common.spell.ManaType;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;

public class ESCrests {
    public static final ResourceKey<Crest> BOULDERS_SHIELD = create("boulders_shield");
    public static final ResourceKey<Crest> GUIDANCE_OF_STARS = create("guidance_of_stars");

    public static void bootstrap(BootstapContext<Crest> context) {
        context.register(BOULDERS_SHIELD, new Crest(ManaType.TERRA, new ResourceLocation(EternalStarlight.MOD_ID, "textures/crest/boulders_shield.png"), null, new Crest.MobEffectWithLevel(MobEffects.DAMAGE_RESISTANCE, 0)));
        context.register(GUIDANCE_OF_STARS, new Crest(ManaType.LUNAR, new ResourceLocation(EternalStarlight.MOD_ID, "textures/crest/guidance_of_stars.png"), ESSpells.GUIDANCE_OF_STARS.get()));
    }

    public static ResourceKey<Crest> create(String name) {
        return ResourceKey.create(ESRegistries.CREST, new ResourceLocation(EternalStarlight.MOD_ID, name));
    }
}
