package cn.leolezury.eternalstarlight.init;

import cn.leolezury.eternalstarlight.EternalStarlight;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageEffects;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DamageTypeInit {
    public static final RegistryKey<DamageType> LASER = create("laser");
    public static final RegistryKey<DamageType> GROUND_SHAKE = create("ground_shake");
    public static final RegistryKey<DamageType> FIRE_COLUMN = create("fire_column");
    public static final RegistryKey<DamageType> POISON = create("poison");
    public static final RegistryKey<DamageType> BITE = create("bite");


    public static RegistryKey<DamageType> create(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(EternalStarlight.MODID, name));
    }

    public static DamageSource getDamageSource(World world, RegistryKey<DamageType> type) {
        return getEntityDamageSource(world, type, null);
    }

    public static DamageSource getEntityDamageSource(World world, RegistryKey<DamageType> type, @Nullable Entity attacker) {
        return getIndirectEntityDamageSource(world, type, attacker, attacker);
    }

    public static DamageSource getIndirectEntityDamageSource(World world, RegistryKey<DamageType> type, @Nullable Entity attacker, @Nullable Entity indirectAttacker) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(type), attacker, indirectAttacker);
    }

    public static void bootstrap(Registerable<DamageType> context) {
        context.register(LASER, new DamageType(slDamage("laser"), 0.0F, DamageEffects.BURNING));
        context.register(GROUND_SHAKE, new DamageType(slDamage("ground_shake"), 0.0F));
        context.register(FIRE_COLUMN, new DamageType(slDamage("fire_column"), 0.0F, DamageEffects.BURNING));
        context.register(POISON, new DamageType(slDamage("poison"), 0.0F));
        context.register(BITE, new DamageType(slDamage("bite"), 0.0F));
    }

    public static String slDamage(String source) {
        return EternalStarlight.MODID + "." + source;
    }
}