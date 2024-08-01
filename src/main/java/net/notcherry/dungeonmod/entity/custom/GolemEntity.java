package net.notcherry.dungeonmod.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.notcherry.dungeonmod.entity.ModEntities;
import net.notcherry.dungeonmod.sounds.ModSounds;
import org.jetbrains.annotations.Nullable;

public class GolemEntity extends Animal {

    public GolemEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPos(this.getX(), this.getY(), this.getZ());
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
//        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25, Ingredient.of(Items.ENDER_EYE), false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 100D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.5f)
                .add(Attributes.ATTACK_KNOCKBACK, 1f)
                .add(Attributes.ATTACK_DAMAGE, 0.3f)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }

//    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
//        ItemStack $$2 = pPlayer.getItemInHand(pHand);
//        if ($$2.is(Items.AIR) && !this.isBaby()) {
//            pPlayer.playSound(SoundEvents.TROPICAL_FISH_FLOP, 1.0F, 1.0F);
//            ItemStack $$3 = ItemUtils.createFilledResult($$2, pPlayer, ModItems.MANDRAKE.get().getDefaultInstance());
//            pPlayer.setItemInHand(pHand, $$3);
//            return InteractionResult.sidedSuccess(this.level().isClientSide);
//        } else if ($$2.is(Items.DIAMOND_SWORD) && !this.isBaby()) {
//            pPlayer.playSound(SoundEvents.TROPICAL_FISH_FLOP, 1.0F, 1.0F);
//            ItemStack $$3 = ItemUtils.createFilledResult($$2, pPlayer, ModItems.MANDRAKE_HEAD.get().getDefaultInstance());
//            pPlayer.setItemInHand(pHand, $$3);
//            return InteractionResult.sidedSuccess(this.level().isClientSide);
//        } else {
//            return super.mobInteract(pPlayer, pHand);
//        }
//    }

//    @Override
//    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
//        ItemStack heldItem = pPlayer.getItemInHand(pHand);
//        if (heldItem.getItem() instanceof SwordItem) {
//
//        }
//    }

    @Override
    public boolean isInWall() {
        return false; // Prevent suffocation by ensuring the entity is not considered inside a wall
    }

//    @Override
//    public boolean isNoGravity() {
//        return true;
//    }

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

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.GOLEM.get().create(pLevel);
    }

//    @Override
//    public boolean isFood(ItemStack pStack) {
//        return pStack.is(Items.ENDER_EYE);
//    }

//    @Nullable
//    @Override
//    protected SoundEvent getAmbientSound() {
//        return ModSounds.WALKING_MUSHROOM_SQUISH.get();
//    }

//    @Nullable
//    @Override
//    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
//        return ModSounds.WALKING_MUSHROOM_SQUISH.get();
//    }
//
//    @Nullable
//    @Override
//    protected SoundEvent getDeathSound() {
//        return ModSounds.MANDRAKE_DEATH.get();
//    }
}
