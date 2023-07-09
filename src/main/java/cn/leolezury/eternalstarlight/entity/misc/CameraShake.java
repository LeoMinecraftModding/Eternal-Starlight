package cn.leolezury.eternalstarlight.entity.misc;

import cn.leolezury.eternalstarlight.init.EntityInit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CameraShake extends Entity {
    private static final TrackedData<Float> RADIUS = DataTracker.registerData(CameraShake.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Float> MAGNITUDE = DataTracker.registerData(CameraShake.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Integer> DURATION = DataTracker.registerData(CameraShake.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> FADE_DURATION = DataTracker.registerData(CameraShake.class, TrackedDataHandlerRegistry.INTEGER);

    public CameraShake(EntityType<?> type, World world) {
        super(type, world);
    }

    public CameraShake(World world, Vec3d pos, float radius, float magnitude, int duration, int fadeDuration) {
        super(EntityInit.CAMERA_SHAKE, world);
        setRadius(radius);
        setMagnitude(magnitude);
        setDuration(duration);
        setFadeDuration(fadeDuration);
        setPosition(pos.x, pos.y, pos.z);
    }

    @Environment(EnvType.CLIENT)
    public float getShakeAmount(PlayerEntity player, float delta) {
        float ticksDelta = age + delta;
        float timeFrac = 1.0f - (ticksDelta - getDuration()) / (getFadeDuration() + 1.0f);
        float baseAmount = ticksDelta < getDuration() ? getMagnitude() : timeFrac * timeFrac * getMagnitude();
        Vec3d playerPos = player.getCameraPosVec(delta);
        float distFrac = (float) (1.0f - MathHelper.clamp(getPos().distanceTo(playerPos) / getRadius(), 0, 1));
        return baseAmount * distFrac * distFrac;
    }

    @Override
    public void tick() {
        super.tick();
        if (age > getDuration() + getFadeDuration()) discard() ;
    }

    @Override
    protected void initDataTracker() {
        getDataTracker().startTracking(RADIUS, 10.0f);
        getDataTracker().startTracking(MAGNITUDE, 1.0f);
        getDataTracker().startTracking(DURATION, 0);
        getDataTracker().startTracking(FADE_DURATION, 5);
    }

    public float getRadius() {
        return getDataTracker().get(RADIUS);
    }

    public void setRadius(float radius) {
        getDataTracker().set(RADIUS, radius);
    }

    public float getMagnitude() {
        return getDataTracker().get(MAGNITUDE);
    }

    public void setMagnitude(float magnitude) {
        getDataTracker().set(MAGNITUDE, magnitude);
    }

    public int getDuration() {
        return getDataTracker().get(DURATION);
    }

    public void setDuration(int duration) {
        getDataTracker().set(DURATION, duration);
    }

    public int getFadeDuration() {
        return getDataTracker().get(FADE_DURATION);
    }

    public void setFadeDuration(int fadeDuration) {
        getDataTracker().set(FADE_DURATION, fadeDuration);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbtCompound) {
        setRadius(nbtCompound.getFloat("radius"));
        setMagnitude(nbtCompound.getFloat("magnitude"));
        setDuration(nbtCompound.getInt("duration"));
        setFadeDuration(nbtCompound.getInt("fade_duration"));
        age = nbtCompound.getInt("ticks_existed");
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbtCompound) {
        nbtCompound.putFloat("radius", getRadius());
        nbtCompound.putFloat("magnitude", getMagnitude());
        nbtCompound.putInt("duration", getDuration());
        nbtCompound.putInt("fade_duration", getFadeDuration());
        nbtCompound.putInt("ticks_existed", age);
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    public static void cameraShake(World world, Vec3d pos, float radius, float magnitude, int duration, int fadeDuration) {
        if (!world.isClient) {
            CameraShake cameraShake = new CameraShake(world, pos, radius, magnitude, duration, fadeDuration);
            world.spawnEntity(cameraShake);
        }
    }
}

