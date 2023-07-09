package cn.leolezury.eternalstarlight.init;

import cn.leolezury.eternalstarlight.EternalStarlight;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ParticleInit implements ModInitializer {
    public static final DefaultParticleType STARLIGHT = FabricParticleTypes.simple(false);
    public static final DefaultParticleType POISON = FabricParticleTypes.simple(false);
    public static final DefaultParticleType ENERGY = FabricParticleTypes.simple(false);

    @Override
    public void onInitialize() {
        Registry.register(Registries.PARTICLE_TYPE,new Identifier(EternalStarlight.MODID,"starlight"), STARLIGHT);
        Registry.register(Registries.PARTICLE_TYPE,new Identifier(EternalStarlight.MODID,"poison"), POISON);
        Registry.register(Registries.PARTICLE_TYPE,new Identifier(EternalStarlight.MODID,"energy"), ENERGY);
    });
}
