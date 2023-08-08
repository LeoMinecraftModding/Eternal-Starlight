package cn.leolezury.eternalstarlight.init;

import cn.leolezury.eternalstarlight.EternalStarlight;
import cn.leolezury.eternalstarlight.world.structure.placement.SLRandomSpreadStructurePlacement;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
public class StructurePlacementTypeInit {
    public static final DeferredRegister<StructurePlacementType<?>> STRUCTURE_PLACEMENT_TYPES = DeferredRegister.create(Registries.STRUCTURE_PLACEMENT, EternalStarlight.MOD_ID);

    public static final RegistryObject<StructurePlacementType<SLRandomSpreadStructurePlacement>> SL_RANDOM_SPREAD = STRUCTURE_PLACEMENT_TYPES.register("sl_random_spread", () -> () -> SLRandomSpreadStructurePlacement.CODEC);
}
