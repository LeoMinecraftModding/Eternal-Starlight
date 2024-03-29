package cn.leolezury.eternalstarlight.forge.datagen.provider.tags;

import cn.leolezury.eternalstarlight.common.EternalStarlight;
import cn.leolezury.eternalstarlight.common.registry.ESItems;
import cn.leolezury.eternalstarlight.common.util.ESTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ESItemTagsProvider extends ItemTagsProvider {
    public ESItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future, CompletableFuture<TagLookup<Block>> provider, ExistingFileHelper helper) {
        super(output, future, provider, EternalStarlight.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        // mod tags
        copy(ESTags.Blocks.LUNAR_LOGS, ESTags.Items.LUNAR_LOGS);
        copy(ESTags.Blocks.NORTHLAND_LOGS, ESTags.Items.NORTHLAND_LOGS);
        copy(ESTags.Blocks.STARLIGHT_MANGROVE_LOGS, ESTags.Items.STARLIGHT_MANGROVE_LOGS);
        copy(ESTags.Blocks.SCARLET_LOGS, ESTags.Items.SCARLET_LOGS);
        copy(ESTags.Blocks.TORREYA_LOGS, ESTags.Items.TORREYA_LOGS);
        copy(ESTags.Blocks.YETI_FUR, ESTags.Items.YETI_FUR);
        copy(ESTags.Blocks.YETI_FUR_CARPETS, ESTags.Items.YETI_FUR_CARPETS);
        tag(ESTags.Items.THERMAL_SPRINGSTONE_WEAPONS).add(
                ESItems.THERMAL_SPRINGSTONE_SWORD.get(),
                ESItems.THERMAL_SPRINGSTONE_PICKAXE.get(),
                ESItems.THERMAL_SPRINGSTONE_AXE.get(),
                ESItems.THERMAL_SPRINGSTONE_SCYTHE.get(),
                ESItems.THERMAL_SPRINGSTONE_HAMMER.get()
        );
        tag(ESTags.Items.GLACITE_WEAPONS).add(
                ESItems.GLACITE_SWORD.get(),
                ESItems.GLACITE_PICKAXE.get(),
                ESItems.GLACITE_AXE.get(),
                ESItems.GLACITE_SCYTHE.get()
        );
        tag(ESTags.Items.DOOMEDEN_KEYS).add(
                ESItems.EYE_OF_DOOM.get(),
                ESItems.LIVING_ARM.get(),
                ESItems.DOOMEDEN_SWORD.get()
        );
        tag(ESTags.Items.MANA_CRYSTAL_INGREDIENTS).add(
                ESItems.RED_STARLIGHT_CRYSTAL_SHARD.get()
        );
        tag(ESTags.Items.MANA_CRYSTALS).addTags(
                ESTags.Items.TERRA_CRYSTALS,
                ESTags.Items.WIND_CRYSTALS,
                ESTags.Items.WATER_CRYSTALS,
                ESTags.Items.LUNAR_CRYSTALS,
                ESTags.Items.BLAZE_CRYSTALS,
                ESTags.Items.LIGHT_CRYSTALS
        );
        tag(ESTags.Items.TERRA_CRYSTALS).add(
                ESItems.TERRA_CRYSTAL.get(),
                ESItems.MANA_CRYSTAL.get()
        );
        tag(ESTags.Items.WIND_CRYSTALS).add(
                ESItems.WIND_CRYSTAL.get(),
                ESItems.MANA_CRYSTAL.get()
        );
        tag(ESTags.Items.WATER_CRYSTALS).add(
                ESItems.WATER_CRYSTAL.get(),
                ESItems.MANA_CRYSTAL.get()
        );
        tag(ESTags.Items.LUNAR_CRYSTALS).add(
                ESItems.LUNAR_CRYSTAL.get(),
                ESItems.MANA_CRYSTAL.get()
        );
        tag(ESTags.Items.BLAZE_CRYSTALS).add(
                ESItems.BLAZE_CRYSTAL.get(),
                ESItems.MANA_CRYSTAL.get()
        );
        tag(ESTags.Items.LIGHT_CRYSTALS).add(
                ESItems.LIGHT_CRYSTAL.get(),
                ESItems.MANA_CRYSTAL.get()
        );
        // mc tags
        copy(BlockTags.LOGS, ItemTags.LOGS);
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
        copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);
        copy(BlockTags.SAND, ItemTags.SAND);
        copy(BlockTags.SMELTS_TO_GLASS, ItemTags.SMELTS_TO_GLASS);
        copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
        copy(BlockTags.CANDLES, ItemTags.CANDLES);
        tag(ItemTags.BOATS).add(
                ESItems.LUNAR_BOAT.get(),
                ESItems.NORTHLAND_BOAT.get(),
                ESItems.STARLIGHT_MANGROVE_BOAT.get(),
                ESItems.SCARLET_BOAT.get(),
                ESItems.TORREYA_BOAT.get()
        );
        tag(ItemTags.CHEST_BOATS).add(
                ESItems.LUNAR_CHEST_BOAT.get(),
                ESItems.NORTHLAND_CHEST_BOAT.get(),
                ESItems.STARLIGHT_MANGROVE_CHEST_BOAT.get(),
                ESItems.SCARLET_CHEST_BOAT.get(),
                ESItems.TORREYA_CHEST_BOAT.get()
        );
        tag(ItemTags.FREEZE_IMMUNE_WEARABLES).add(
                ESItems.THERMAL_SPRINGSTONE_HELMET.get(),
                ESItems.THERMAL_SPRINGSTONE_CHESTPLATE.get(),
                ESItems.THERMAL_SPRINGSTONE_LEGGINGS.get(),
                ESItems.THERMAL_SPRINGSTONE_BOOTS.get(),
                ESItems.GLACITE_HELMET.get(),
                ESItems.GLACITE_CHESTPLATE.get(),
                ESItems.GLACITE_LEGGINGS.get(),
                ESItems.GLACITE_BOOTS.get()
        );
        tag(ItemTags.SWORDS).add(
                ESItems.RAGE_OF_STARS.get(),
                ESItems.THERMAL_SPRINGSTONE_SWORD.get(),
                ESItems.SWAMP_SILVER_SWORD.get(),
                ESItems.BONEMORE_BROADSWORD.get(),
                ESItems.DOOMEDEN_SWORD.get(),
                ESItems.SHATTERED_SWORD.get(),
                ESItems.ENERGY_SWORD.get(),
                ESItems.MOONRING_GREATSWORD.get()
        );
        tag(ItemTags.PICKAXES).add(
                ESItems.THERMAL_SPRINGSTONE_PICKAXE.get(),
                ESItems.SWAMP_SILVER_PICKAXE.get()
        );
        tag(ItemTags.AXES).add(
                ESItems.THERMAL_SPRINGSTONE_PICKAXE.get(),
                ESItems.SWAMP_SILVER_AXE.get(),
                ESItems.AMARAMBER_AXE.get()
        );
        tag(ItemTags.HOES).add(
                ESItems.THERMAL_SPRINGSTONE_SCYTHE.get(),
                ESItems.SWAMP_SILVER_SICKLE.get(),
                ESItems.AMARAMBER_HOE.get(),
                ESItems.PETAL_SCYTHE.get(),
                ESItems.AURORA_DEER_ANTLER.get()
        );
        tag(ItemTags.SHOVELS).add(
                ESItems.AMARAMBER_SHOVEL.get()
        );
        tag(ItemTags.ARROWS).add(
                ESItems.AMARAMBER_ARROW.get()
        );
        tag(ItemTags.TRIMMABLE_ARMOR).add(
                ESItems.AETHERSENT_HOOD.get(),
                ESItems.SWAMP_SILVER_HELMET.get(),
                ESItems.SWAMP_SILVER_CHESTPLATE.get()
        );
        tag(ItemTags.TRIM_MATERIALS).add(
                ESItems.RED_STARLIGHT_CRYSTAL_SHARD.get(),
                ESItems.BLUE_STARLIGHT_CRYSTAL_SHARD.get(),
                ESItems.AETHERSENT_INGOT.get(),
                ESItems.THERMAL_SPRINGSTONE_INGOT.get(),
                ESItems.GLACITE_SHARD.get(),
                ESItems.SWAMP_SILVER_INGOT.get(),
                ESItems.AMARAMBER_INGOT.get(),
                ESItems.GOLEM_STEEL_INGOT.get(),
                ESItems.TENACIOUS_PETAL.get(),
                ESItems.BROKEN_DOOMEDEN_BONE.get()
        );
        tag(ItemTags.STONE_TOOL_MATERIALS).add(
                ESItems.GRIMSTONE.get(),
                ESItems.COBBLED_GRIMSTONE.get(),
                ESItems.VOIDSTONE.get(),
                ESItems.COBBLED_VOIDSTONE.get()
        );
        tag(ItemTags.STONE_CRAFTING_MATERIALS).add(
                ESItems.GRIMSTONE.get(),
                ESItems.COBBLED_GRIMSTONE.get(),
                ESItems.VOIDSTONE.get(),
                ESItems.COBBLED_VOIDSTONE.get()
        );
    }
}
