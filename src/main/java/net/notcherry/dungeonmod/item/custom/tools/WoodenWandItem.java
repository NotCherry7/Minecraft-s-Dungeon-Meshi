package net.notcherry.dungeonmod.item.custom.tools;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.notcherry.dungeonmod.entity.ModEntities;
import net.notcherry.dungeonmod.item.custom.SpellType;
import net.notcherry.dungeonmod.mana.PlayerManaProvider;
import net.notcherry.dungeonmod.networking.ModMessages;
import net.notcherry.dungeonmod.networking.packet.ManaDataSyncS2CPacket;
import net.notcherry.dungeonmod.sounds.ModSounds;
import net.notcherry.dungeonmod.util.DelayedAction;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class WoodenWandItem extends Item {
    private static final String SPELL_STATE_TAG = "SpellState";
    private static Integer MANA_AMOUNT = 0;
    private DelayedAction delayedAction = new DelayedAction(1);

    private static final Map<SpellType, Integer> SPELL_MANA_COSTS = Map.of(
            SpellType.NONE, 0,
            SpellType.EXPLOSION, 20,
            SpellType.LIGHT, 15,
            SpellType.HEALING, 40,
            SpellType.WATER_WALKING, 10
    );

    public WoodenWandItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isShiftKeyDown()) {
            changeSpellState(stack);
            return new InteractionResultHolder<>(InteractionResult.PASS, stack);
        }

        int spellState = stack.getOrCreateTag().getInt(SPELL_STATE_TAG);
        SpellType spellType = SpellType.getByIndex(spellState);
        int manaCost = getManaCost(spellType);
        getMana(player);
        delayedAction.tick();
//        if (checkMana(player, manaCost)) {
        if (delayedAction.isReady()) {
            if (MANA_AMOUNT >= manaCost) {
                applySpellEffect(level, player, spellType);
                removeMana(player, manaCost);
                player.playSound(SoundEvents.BEACON_DEACTIVATE);
            } //else {
//                player.displayClientMessage(Component.literal("Not enough mana!"), true);
//            }
        }

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    private void changeSpellState(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        int currentState = tag.getInt(SPELL_STATE_TAG);
        int newState = (currentState + 1) % SpellType.values().length; // Cycle through spells
        tag.putInt(SPELL_STATE_TAG, newState);
    }

    private void applySpellEffect(Level level, Player player, SpellType spellType) {
        switch (spellType) {
            case EXPLOSION:
//                System.out.println("EXPLOSION -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                summonFireball(level, player);
                break;
            case LIGHT:
//                System.out.println("LIGHT -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                summonEntity(level, player, ModEntities.LIGHT_ORB_SPELL.get());
                break;
            case HEALING:
//                System.out.println("HEALING -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                break;
            case WATER_WALKING:
//                System.out.println("WATER_WALKING -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                break;
        }
    }

    public static void summonEntity(Level level, Player player, EntityType<?> entityType) {
        if (level != null && entityType != null) {
            Entity entity = entityType.create(level);
            if (entity != null) {
                double x = player.getX();
                double y = player.getY() + 1;
                double z = player.getZ();
                entity.setPos(x, y, z);
                level.addFreshEntity(entity);
            }
        }
    }

    public static void summonFireball(Level level, Player player) {
        if (!level.isClientSide()) {
            LargeFireball smallFireball = new LargeFireball(level, player,
                    player.getViewVector(1f).x, player.getViewVector(1f).y, player.getViewVector(1f).z, 2);
            smallFireball.setPos(smallFireball.getX(), player.getY(0.5) + 0.5, smallFireball.getZ());
            smallFireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(smallFireball);
        }
    }

    public static void activateWaterWalking() {

    }



    //    public boolean checkMana(Player player, int amount) {
//        AtomicInteger hasEnoughMana = new AtomicInteger(0);
//
//        player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
//            if (MANA_AMOUNT > amount) {
//                hasEnoughMana.set(2); // 0 = not set | 1 = false | 2 = true
//            }
//        });
//
//        System.out.println("returns: hasEnoughMana - " + hasEnoughMana);
//        if(hasEnoughMana.get() == 0) {
//            return null;
//        }
//    }

    public boolean checkMana(Player player, int amount) {
        AtomicBoolean hasEnoughMana = new AtomicBoolean(false);

        player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
            if (MANA_AMOUNT > amount) {
                hasEnoughMana.set(true);
            }
        });

        System.out.println("returns: hasEnoughMana - " + hasEnoughMana);
        return hasEnoughMana.get();
    }

    public void getMana(Player player) {
        player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
            MANA_AMOUNT = mana.getMana();
        });
    }

    public static void removeMana(Player player, int amount) {
        player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
            mana.subMana(amount);
            ModMessages.sendToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), ((ServerPlayer) player));
        });
    }

    public Integer getManaCost(SpellType spellType) {
        return SPELL_MANA_COSTS.getOrDefault(spellType, 0);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        CompoundTag tag = pStack.getOrCreateTag();
        int spellState = tag.getInt(SPELL_STATE_TAG);
        SpellType spellType = SpellType.getByIndex(spellState);

        Component currentSpellText = Component.literal("Current Spell: ")
                .withStyle(ChatFormatting.GRAY);
        Component spellNameText = Component.literal(spellType.name())
                .withStyle(ChatFormatting.GOLD);

        Component currentCostText = Component.literal("Mana Cost: ")
                .withStyle(ChatFormatting.GRAY);
        Component manaCostText = Component.literal("" + getManaCost(spellType))
                .withStyle(ChatFormatting.AQUA);

        if (spellType.name().equals(SpellType.EXPLOSION.name())) {
            spellNameText = Component.literal(spellType.name())
                    .withStyle(ChatFormatting.GOLD);
        } else if (spellType.name().equals(SpellType.LIGHT.name())) {
            spellNameText = Component.literal(spellType.name())
                    .withStyle(ChatFormatting.YELLOW);
        } else if (spellType.name().equals(SpellType.HEALING.name())) {
            spellNameText = Component.literal(spellType.name())
                    .withStyle(ChatFormatting.GREEN);
        } else if (spellType.name().equals(SpellType.WATER_WALKING.name())) {
            spellNameText = Component.literal(spellType.name())
                    .withStyle(ChatFormatting.AQUA);
        }

//        pTooltipComponents.add(Component.literal("Current Spell: " + spellType.name()));
//        pTooltipComponents.add(Component.literal("Mana Cost: " + getManaCost(spellType)));
        pTooltipComponents.add(currentSpellText.copy().append(spellNameText));
        pTooltipComponents.add(currentCostText.copy().append(manaCostText));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}

