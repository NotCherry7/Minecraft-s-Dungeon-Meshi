package net.notcherry.dungeonmod.effect.custom;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ManaSicknessPotionEffect extends MobEffect {
    private static final Map<Player, Map<KeyMapping, Boolean>> originalKeyStates = new HashMap<>();
    private static boolean isManaSickened = false;
    private static final UUID MOVEMENT_SPEED_MODIFIER_ID = UUID.randomUUID();

    public ManaSicknessPotionEffect() {
        super(MobEffectCategory.HARMFUL, 0x272180); // color
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level().isClientSide()) {
            pLivingEntity.setDeltaMovement(0, 0, 0);
            // Decrease movement speed by 50%
//            AttributeModifier speedModifier = new AttributeModifier(MOVEMENT_SPEED_MODIFIER_ID, "Mana sickness speed decrease", -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
//            pLivingEntity.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(speedModifier);
            pLivingEntity.setJumping(false);
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);

        if (pLivingEntity instanceof Player) {
            Player player = (Player) pLivingEntity;
            // Disable input for the player
            disablePlayerInput(player);
//            Entity.MovementEmission.valueOf(null);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Applies the effect every tick
        return true;
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, net.minecraft.world.entity.ai.attributes.AttributeMap attributes, int amplifier) {
        super.removeAttributeModifiers(entity, attributes, amplifier);
        entity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(MOVEMENT_SPEED_MODIFIER_ID);
        if (entity instanceof Player) {
            Player player = (Player) entity;
            // Re-enable input for the player
            enablePlayerInput(player);
//            enableMouseInput(player);
        }
    }

    private void disablePlayerInput(Player player) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == player) {
            Map<KeyMapping, Boolean> keyStates = new HashMap<>();
            for (KeyMapping key : mc.options.keyMappings) {
                keyStates.put(key, key.isDown());
                key.setDown(false);
            }
            originalKeyStates.put(player, keyStates);
        }
    }

    private void enablePlayerInput(Player player) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == player) {
            Map<KeyMapping, Boolean> keyStates = originalKeyStates.remove(player);
            if (keyStates != null) {
                for (Map.Entry<KeyMapping, Boolean> entry : keyStates.entrySet()) {
                    entry.getKey().setDown(entry.getValue());
                }
            }
        }
    }



//    @Override
//    public void applyEffectTick(LivingEntity entity, int amplifier) {
//        if (entity instanceof Player) {
//            Player player = (Player) entity;
//            // Disable player movement and controls
////            player.setDeltaMovement(0, 0, 0); // Stop movement
////            player.noPhysics = true; // Disable physics
////            player.getAbilities().flying = false; // Disable flying
////            player.getAbilities().mayfly = false; // Disable mayfly
////            player.getAbilities().mayBuild = false; // Disable mayfly
////            player.getAbilities().setWalkingSpeed(0.0f); // Set walk speed to 0
////            player.getAbilities().setFlyingSpeed(0.0f); // Set flying speed to 0
//
//            isStunned = true;
//        }
//    }

//    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
//    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo info) {
//        Minecraft mc = Minecraft.getInstance();
//        if (mc.player != null && StunPotionEffect.isStunned(mc.player)) {
//
//            info.cancel();
//        }
//    }

//    @Override
//    public boolean isDurationEffectTick(int duration, int amplifier) {
//        // Apply the effect every tick
//        isStunned = true;
//        return true;
//    }

    public static boolean isManaSickened(Player player) {
        return isManaSickened;
    }

    public static void setManaSickened(boolean manaSickened) {
        isManaSickened = manaSickened;
    }

//    @Override
//    public void removeAttributeModifiers(LivingEntity entity, net.minecraft.world.entity.ai.attributes.AttributeMap attributes, int amplifier) {
//        super.removeAttributeModifiers(entity, attributes, amplifier);
//        if (entity instanceof Player) {
////            Player player = (Player) entity;
////            // Restore player controls and capabilities here if needed
////            player.noPhysics = false;
////            player.getAbilities().flying = true;
////            player.getAbilities().mayfly = true;
////            player.getAbilities().setWalkingSpeed(0.1f); // Default walk speed
////            player.getAbilities().setFlyingSpeed(0.05f); // Default flying speed
//
//            isStunned = false;
//        }
//    }

//    @Inject(method = "onMouseButton", at = @At("HEAD"), cancellable = true)
//    private void onMouseButton(long window, int button, int action, int mods, CallbackInfo callbackInfo) {
//        if (StunPotionEffect.isStunned(Minecraft.getInstance().player) && !Minecraft.getInstance().isPaused()) {
//            callbackInfo.cancel();
//        }
//    }
//
//    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
//    private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo callbackInfo) {
//        if (StunPotionEffect.isStunned(Minecraft.getInstance().player) && !Minecraft.getInstance().isPaused()) {
//
//            callbackInfo.cancel();
//        }
//    }

}
