package cn.leolezury.eternalstarlight.item.weapon;

import cn.leolezury.eternalstarlight.init.ItemInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum SLItemTiers implements ToolMaterial {
    SWAMP_SILVER(3, 2000, 7.5F, 2.5F, 10, () -> Ingredient.ofItems(ItemInit.SWAMP_SILVER_INGOT)),
    PETAL(3, 1500, 7.5F, 3.5F, 22, () -> Ingredient.ofItems(ItemInit.TENACIOUS_PETAL));

    private final int level;
    private final int durability;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Ingredient repairIngredient;

    SLItemTiers(int level, int durability, float miningSpeed, float damage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.durability = durability;
        this.speed = miningSpeed;
        this.damage = damage;
        this.enchantmentValue = enchantability;
        this.repairIngredient = this.getRepairIngredient();
    }

    public int getLevel() {
        return this.level;
    }

    @Override
    public int getDurability() {
        return this.durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.speed;
    }

    @Override
    public float getAttackDamage() {
        return this.damage;
    }

    @Override
    public int getMiningLevel() {
        return this.level;
    }

    @Override
    public int getEnchantability() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }
}
