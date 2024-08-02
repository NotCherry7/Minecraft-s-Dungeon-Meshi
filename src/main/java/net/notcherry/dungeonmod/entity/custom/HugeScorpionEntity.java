package net.notcherry.dungeonmod.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.notcherry.dungeonmod.effect.ModEffects;
import net.notcherry.dungeonmod.entity.ModEntities;
import net.notcherry.dungeonmod.entity.ai.HugeScorpionAttackGoal;
import net.notcherry.dungeonmod.entity.ai.HugeScorpionRetaliateGoal;
import net.notcherry.dungeonmod.sounds.ModSounds;
import org.jetbrains.annotations.Nullable;

public class HugeScorpionEntity extends Animal {
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(HugeScorpionEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> RETALIATING =
            SynchedEntityData.defineId(HugeScorpionEntity.class, EntityDataSerializers.BOOLEAN);

    public HugeScorpionEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPos(this.getX(), this.getY(), this.getZ());
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public AnimationState retaliateAnimationState = new AnimationState();
    public int retaliateAnimationTimeout = 0;

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
//        if(isRetaliating()) {
            this.goalSelector.addGoal(1, new HugeScorpionRetaliateGoal(this, 1.4D, true));
//        }
        this.goalSelector.addGoal(2, new HugeScorpionAttackGoal(this, 1.3D, true));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.1));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.25, Ingredient.of(Items.ENDER_EYE), false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 40D)
                .add(Attributes.MOVEMENT_SPEED, 0.18D)
                .add(Attributes.ARMOR_TOUGHNESS, 5f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.2f)
                .add(Attributes.ATTACK_DAMAGE, 5f)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.5D);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() instanceof Player) {
            this.setRetaliating(true);
        }
        return super.hurt(source, amount);
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 22; // Length in ticks of your animation (1.1 seconds = 22 ticks)
            this.attackAnimationState.start(this.tickCount);
        } else if (!this.isAttacking() && attackAnimationTimeout > 0) {
            --attackAnimationTimeout;
        }

        if (this.isRetaliating() && retaliateAnimationTimeout <= 0) {
            retaliateAnimationTimeout = 20; // Length in ticks of your animation (1.0 seconds = 20 ticks)
            this.retaliateAnimationState.start(this.tickCount);
        } else if (!this.isRetaliating() && retaliateAnimationTimeout > 0) {
            --retaliateAnimationTimeout;
        }

        if (!this.isAttacking()) {
            this.attackAnimationState.stop();
        }

        if (!this.isRetaliating()) {
            this.retaliateAnimationState.stop();
        }

    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setRetaliating(boolean retaliating) {
        this.entityData.set(RETALIATING, retaliating);
    }

    public boolean isRetaliating() {
        return this.entityData.get(RETALIATING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
        this.entityData.define(RETALIATING, false);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.HUGE_SCORPION.get().create(pLevel);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.ENDER_EYE);
    }

//    @Override
//    protected boolean shouldDespawnInPeaceful() {
//        return true;
//    }


}