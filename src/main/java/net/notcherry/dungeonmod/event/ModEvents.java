package net.notcherry.dungeonmod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.effect.ModEffects;
import net.notcherry.dungeonmod.item.ModItems;
import net.notcherry.dungeonmod.mana.PlayerMana;
import net.notcherry.dungeonmod.mana.PlayerManaProvider;
import net.notcherry.dungeonmod.networking.ModMessages;
import net.notcherry.dungeonmod.networking.packet.ManaDataSyncS2CPacket;

import java.util.List;

@Mod.EventBusSubscriber(modid = DungeonMod.MOD_ID)
public class ModEvents {
//    @SubscribeEvent
//    public static void addCustomTrades(VillagerTradesEvent event) {
//        if(event.getType() == VillagerProfession.TOOLSMITH) {
//            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
//            ItemStack stack = new ItemStack(ModItems.EIGHT_BALL.get(), 1);
//            int villagerLevel = 1;
//
//            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 2),
//                    stack,10,8,0.02F));
//        }
//
//        if(event.getType() == ModVillagers.JUMP_MASTER.get()) {
//            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
//            ItemStack stack = new ItemStack(ModItems.BLUEBERRY.get(), 15);
//            int villagerLevel = 1;
//
//            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 5),
//                    stack,10,8,0.02F));
//        }
//    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerManaProvider.PLAYER_MANA).isPresent()) {
                event.addCapability(new ResourceLocation(DungeonMod.MOD_ID, "properties"), new PlayerManaProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerMana.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player pPlayer = Minecraft.getInstance().player;
        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                if(mana.getMana() > 0 && event.player.getRandom().nextFloat() < 0.005f) { // Once Every 10 Seconds on Avg
                    mana.subMana(1);
//                    pPlayer.playSound(SoundEvents.GLASS_BREAK, 1.0F, 1.0F);
                    ModMessages.sendToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), ((ServerPlayer) event.player));
                }

                if(mana.getMana() < 10) {
//                    assert pPlayer != null;
//                    pPlayer.addEffect(new MobEffectInstance(ModEffects.MANA_SICKNESS_EFFECT.get(), 1));
//                    pPlayer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 1, 5));
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    ModMessages.sendToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), player);
                });
            }
        }
    }
}
