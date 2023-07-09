package cn.leolezury.eternalstarlight.entity.misc;

import cn.leolezury.eternalstarlight.init.BlockInit;
import cn.leolezury.eternalstarlight.init.EntityInit;
import cn.leolezury.eternalstarlight.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.world.World;

public class SLBoat extends BoatEntity {
    private static final TrackedData<Integer> BOAT_TYPE = DataTracker.registerData(SLBoat.class, TrackedDataHandlerRegistry.INTEGER);

    public SLBoat(EntityType<? extends BoatEntity> type, World world) {
        super(type, world);
        this.intersectionChecked = true;
    }

    public SLBoat(World world, double x, double y, double z) {
        this(EntityInit.BOAT, world);
        this.setPos(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    public SLBoat.Type getSLBoatType() {
        return SLBoat.Type.byId(this.dataTracker.get(BOAT_TYPE));
    }

    @Override
    public Item asItem() {
        return switch (this.getSLBoatType()) {
            case LUNAR -> ItemInit.LUNAR_BOAT;
            case NORTHLAND -> ItemInit.NORTHLAND_BOAT;
            case STARLIGHT_MANGROVE -> ItemInit.STARLIGHT_MANGROVE_BOAT;
        };
    }

    public void setSLBoatType(SLBoat.Type boatType) {
        this.dataTracker.set(BOAT_TYPE, boatType.ordinal());
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(BOAT_TYPE, Type.LUNAR.ordinal());
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbtCompound) {
        nbtCompound.putString("Type", this.getSLBoatType().getName());
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbtCompound) {
        if (nbtCompound.contains("Type", 8)) {
            this.setSLBoatType(SLBoat.Type.getTypeFromString(nbtCompound.getString("Type")));
        }
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    public enum Type {
        LUNAR((Block) BlockInit.LUNAR_PLANKS, "lunar"),
        NORTHLAND((Block) BlockInit.NORTHLAND_PLANKS, "northland"),
        STARLIGHT_MANGROVE((Block) BlockInit.STARLIGHT_MANGROVE_PLANKS, "starlight_mangrove"),
        ;

        private final String name;
        private final Block block;

        Type(Block block, String name) {
            this.name = name;
            this.block = block;
        }

        public String getName() {
            return this.name;
        }

        public Block asPlank() {
            return this.block;
        }

        public String toString() {
            return this.name;
        }

        public static SLBoat.Type byId(int id) {
            SLBoat.Type[] SLBoat$type = values();
            if (id < 0 || id >= SLBoat$type.length) {
                id = 0;
            }

            return SLBoat$type[id];
        }

        public static SLBoat.Type getTypeFromString(String nameIn) {
            SLBoat.Type[] boatTypeArray = values();

            for (Type type : boatTypeArray) {
                if (type.getName().equals(nameIn)) {
                    return type;
                }
            }

            return boatTypeArray[0];
        }
    }
}
