package net.notcherry.dungeonmod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.Vec3;
import net.notcherry.dungeonmod.entity.ModEntities;
import net.notcherry.dungeonmod.entity.ai.LightOrbFollowGoal;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class LightOrbSpell extends Animal {
    private int age;
    private int health;
    private Player followingPlayer;

    private final double bobbingSpeed = 0.05;
    private final double bobbingAmplitude = 0.2;
    private double bobbingTimer = 0.0;

    public LightOrbSpell(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPos(this.getX(), this.getY(), this.getZ());
        this.health = 5;
    }

    @Override
    protected void registerGoals() {
//        this.goalSelector.addGoal(0, new LightOrbFollowGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.FOLLOW_RANGE, 24F)
                .add(Attributes.MAX_HEALTH, 0.1F);
    }

    @Override
    public boolean isInWall() {
        return false;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        if (!this.level().isClientSide && !this.isRemoved()) {
            if (this.isInvulnerableTo(pSource)) {
                return false;
            } else if (this.level().isClientSide) {
                return true;
            } else {
                this.markHurt();
                this.health = (int)((float)this.health - pAmount);
                if (this.health <= 0) {
                    this.discard();
                }

                return true;
            }
        } else {
            return false;
        }
    }



    @Override
    public void tick() {
        super.tick();

        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();

        if (this.isEyeInFluid(FluidTags.WATER)) {
            this.setUnderwaterMovement();
        } else if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.03, 0.0));
        }

        if (this.level().getFluidState(this.blockPosition()).is(FluidTags.LAVA)) {
            this.setDeltaMovement((double)((this.random.nextFloat() - this.random.nextFloat()) * 0.2F), 0.20000000298023224, (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.2F));
        }

        if (!this.level().noCollision(this.getBoundingBox())) {
            this.moveTowardsClosestSpace(this.getX(), (this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0, this.getZ());
        }

        if (this.tickCount % 20 == 1) {
            this.scanForEntities();
        }

        if (this.followingPlayer != null) {
            double distance = this.followingPlayer.distanceToSqr(this);
            if (distance > 3 * 3) {
                Vec3 vec3 = new Vec3(this.followingPlayer.getX() - this.getX(), this.followingPlayer.getY() + (double)this.followingPlayer.getEyeHeight() / 2.0 - this.getY() + 1, this.followingPlayer.getZ() - this.getZ());
                double d1 = 3.0 - Math.sqrt(distance) / 20.0;
                this.setDeltaMovement(this.getDeltaMovement().add(vec3.normalize().scale(d1 * d1 * 0.1)));
            } else {
                this.setDeltaMovement(Vec3.ZERO);
//                bobbingTimer += bobbingSpeed;
//                double bobbing = Math.sin(bobbingTimer) * bobbingAmplitude;
//                this.setDeltaMovement(0, bobbing, 0);
            }
        }

        Vec3 deltaMovement = this.getDeltaMovement();
        if (Double.isFinite(deltaMovement.x) && Double.isFinite(deltaMovement.y) && Double.isFinite(deltaMovement.z)) {
            this.move(MoverType.SELF, deltaMovement);
        } else {
            System.err.println("Invalid delta movement detected: " + deltaMovement);
        }

        float f = 0.98F;
        if (this.onGround()) {
            BlockPos pos = this.getBlockPosBelowThatAffectsMyMovement();
            f = this.level().getBlockState(pos).getFriction(this.level(), pos, this) * 0.98F;
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(f, 0.98, f));
        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, -0.9, 1.0));
        }

        ++this.age;
        if (this.age >= 6000) {
            this.discard();
        }

//        spawnParticles();
    }

//    private void spawnParticles() {
//        // Example: Spawning particle of type FLAME
//        for (int i = 0; i < 5; i++) { // Adjust the number of particles as needed
//            double xOffset = random.nextGaussian() * 0.2; // Random offset
//            double yOffset = random.nextGaussian() * 0.2;
//            double zOffset = random.nextGaussian() * 0.2;
//
//            level().addParticle(ParticleTypes.ELECTRIC_SPARK, getX() + xOffset, getY() + yOffset, getZ() + zOffset, 0, 0, 0);
//        }
//    }


    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }

    @Override
    public boolean onClimbable() {
        return false;
    }

    private void scanForEntities() {
        if (this.followingPlayer == null || this.followingPlayer.distanceToSqr(this) > 64.0) {
            this.followingPlayer = this.level().getNearestPlayer(this, 20.0);
        }
    }

    private void setUnderwaterMovement() {
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.x * 0.9900000095367432, Math.min(vec3.y + 5.000000237487257E-4, 0.05999999865889549), vec3.z * 0.9900000095367432);
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    protected MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}
