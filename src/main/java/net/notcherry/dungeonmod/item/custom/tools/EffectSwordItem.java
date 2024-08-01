package net.notcherry.dungeonmod.item.custom.tools;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import java.util.Random;

public class EffectSwordItem extends SwordItem {

    private static final Random RANDOM = new Random();
    private final MobEffect effect;
    private final int duration;
    private final int amplifier;
    private final double probability;
    private boolean damageable;

    public EffectSwordItem(Tier tier, int attackDamageModifier, float attackSpeedModifier, Properties properties,
                           MobEffect effect, int duration, int amplifier, double probability, boolean damageable) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.probability = probability;
        this.damageable = damageable;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (RANDOM.nextFloat() < probability) { // Apply the effect based on the passed probability
            target.addEffect(new MobEffectInstance(effect, duration, amplifier));
        }
        stack.hurtAndBreak(1, attacker, (entity) -> entity.broadcastBreakEvent(entity.getUsedItemHand()));
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return damageable;
    }
}
