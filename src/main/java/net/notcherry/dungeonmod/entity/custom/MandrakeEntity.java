package net.notcherry.dungeonmod.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomStandGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.notcherry.dungeonmod.effect.ModEffects;
import net.notcherry.dungeonmod.entity.ModEntities;
//import net.notcherry.dungeonmod.entity.damage.ModDamageSources;
import net.notcherry.dungeonmod.item.ModItems;
import net.notcherry.dungeonmod.sounds.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MandrakeEntity extends Animal {
    private boolean isUnderground = true;
    private boolean initialPositionSet = false;
    private final double undergroundOffset = 0.1;
    private static final float HEIGHT = 3f;
    private static final float OFFSET = 5f;

    public MandrakeEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPos(this.getX(), this.getY() + OFFSET, this.getZ());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

//        this.goalSelector.addGoal(1, new BreedGoal(this, 1.15D));
//        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2D, Ingredient.of(Items.ENDER_EYE), false));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 1D)
                .add(Attributes.MOVEMENT_SPEED, 0D)
                .add(Attributes.ARMOR_TOUGHNESS, 0f)
                .add(Attributes.ATTACK_KNOCKBACK, 0f)
                .add(Attributes.ATTACK_DAMAGE, 0f)
                .add(Attributes.FOLLOW_RANGE, 0D);
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

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack heldItem = pPlayer.getItemInHand(pHand);
        if (heldItem.getItem() instanceof SwordItem) {
            if (isUnderground) {
                isUnderground = false; // Make the entity rise up
                this.setPos(this.getX(), this.getY() + undergroundOffset, this.getZ());
                pPlayer.playSound(SoundEvents.TROPICAL_FISH_FLOP, 1.0F, 1.0F);
                // Drop the item when right-clicked with a sword
                ItemStack dropStack = new ItemStack(ModItems.MANDRAKE_HEAD.get());
                this.spawnAtLocation(dropStack);
                pPlayer.playSound(SoundEvents.ITEM_PICKUP, 1.0F, 1.0F);
                this.kill();
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else if (!(heldItem.getItem() instanceof SwordItem)) {
            //pPlayer.playSound(ModSounds.MANDRAKE_SCREAM.get(), 50.0F, 1.0F);
            // Drop the item when right-clicked with a sword

            ItemStack dropStack = new ItemStack(ModItems.MANDRAKE.get());
            this.spawnAtLocation(dropStack);
            pPlayer.playSound(SoundEvents.ITEM_PICKUP, 1.0F, 1.0F);
            this.kill();

            // Define radii
            double radius4 = 4.0;
            double radius7 = 7.0;
            double radius13 = 13.0;
            double radius30 = 30.0;

            // Get players within each radius
            List<LivingEntity> playersInRadius4 = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(this.blockPosition()).inflate(radius4));
            List<LivingEntity> playersInRadius7 = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(this.blockPosition()).inflate(radius7));
            List<LivingEntity> playersInRadius13 = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(this.blockPosition()).inflate(radius13));
            List<LivingEntity> playersInRadius30 = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(this.blockPosition()).inflate(radius30));

            // Example: Do something with the players
            for (LivingEntity entity : playersInRadius4) {
                // Do something with players in 4 blocks radius
                // Apply the custom effect for 5 seconds (100 ticks) with amplifier 0
                entity.kill();
//                entity.hurt(new DamageSource(mob), 999f);
            }

            for (LivingEntity entity : playersInRadius7) {
                // Do something with players in 7 blocks radius
                entity.addEffect(new MobEffectInstance(ModEffects.STUN_EFFECT.get(), 600, 0));
            }

            for (LivingEntity entity : playersInRadius13) {
                // Do something with players in 13 blocks radius
                entity.addEffect(new MobEffectInstance(ModEffects.STUN_EFFECT.get(), 200, 0));
            }

            for (LivingEntity entity : playersInRadius30) {
                // Do something with players in 30 blocks radius
                entity.addEffect(new MobEffectInstance(ModEffects.STUN_EFFECT.get(), 60, 0));
                entity.playSound(ModSounds.MANDRAKE_SCREAM.get(), 50.0F, 1.0F);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }

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
        if (isUnderground && !initialPositionSet) {
            this.setPos(this.getX(), this.getY() - undergroundOffset, this.getZ());
            initialPositionSet = true;
        }
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return super.getDimensions(pose).scale(0.2f, HEIGHT / 2.0f);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.MANDRAKE.get().create(pLevel);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.ENDER_EYE);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.MANDRAKE_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSounds.MANDRAKE_SCREAM.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.MANDRAKE_SCREAM.get();
    }
}
