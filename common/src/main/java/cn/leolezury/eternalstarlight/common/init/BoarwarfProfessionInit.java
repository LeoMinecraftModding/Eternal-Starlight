package cn.leolezury.eternalstarlight.common.init;

import cn.leolezury.eternalstarlight.common.EternalStarlight;
import cn.leolezury.eternalstarlight.common.entity.npc.boarwarf.AbstractBoarwarfProfession;
import cn.leolezury.eternalstarlight.common.entity.npc.boarwarf.profession.BoarwarfSilversmithProfession;
import cn.leolezury.eternalstarlight.common.platform.registry.RegistrationProvider;
import cn.leolezury.eternalstarlight.common.platform.registry.RegistryObject;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;

public class BoarwarfProfessionInit {
    public static final RegistrationProvider<AbstractBoarwarfProfession> PROFESSIONS = RegistrationProvider.newRegistry(ResourceKey.createRegistryKey(new ResourceLocation(EternalStarlight.MOD_ID, "boarwarf_profession")), EternalStarlight.MOD_ID);
    public static final Codec<AbstractBoarwarfProfession> CODEC = ExtraCodecs.lazyInitializedCodec(PROFESSIONS.registry()::byNameCodec);
    public static final RegistryObject<AbstractBoarwarfProfession, BoarwarfSilversmithProfession> SILVERSMITH = PROFESSIONS.register("silversmith", BoarwarfSilversmithProfession::new);

    public static void loadClass() {}
}