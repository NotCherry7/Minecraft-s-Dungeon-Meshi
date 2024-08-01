package net.notcherry.dungeonmod.networking.packet;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;
import net.notcherry.dungeonmod.mana.PlayerManaProvider;
import net.notcherry.dungeonmod.networking.ModMessages;

import java.util.function.Supplier;

public class ConsumeManaC2SPacket {
    private static final String MESSAGE_CONSUME_MANA = "message.dungeonmod.consume_mana";
    private static final String MESSAGE_NO_MANA = "message.dungeonmod.no_mana";

    public ConsumeManaC2SPacket() {

    }

    public ConsumeManaC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel();

            if (hasWaterAroundThem(player, level, 2)) {
                // Notify the player that water has been drunk
                player.sendSystemMessage(Component.translatable(MESSAGE_CONSUME_MANA).withStyle(ChatFormatting.DARK_AQUA));
                // play the drinking sound
                level.playSound(null, player.getOnPos(), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS,
                        0.5F, level.random.nextFloat() * 0.1F + 0.9F);

                // increase the water level / mana level of player
                // Output the current mana level
                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    mana.addMana(1);
                    player.sendSystemMessage(Component.literal("Current Mana " + mana.getMana())
                            .withStyle(ChatFormatting.AQUA));
                    ModMessages.sendToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), player);
                });


            } else {
                // Notify the player that there is no water around!
                player.sendSystemMessage(Component.translatable(MESSAGE_NO_MANA).withStyle(ChatFormatting.RED));
                // Output the current mana level
                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    player.sendSystemMessage(Component.literal("Current Mana " + mana.getMana())
                            .withStyle(ChatFormatting.AQUA));
                    ModMessages.sendToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), player);
                });
            }
        });
        return true;
    }

    private boolean hasWaterAroundThem(ServerPlayer player, ServerLevel level, int size) {
        return level.getBlockStates(player.getBoundingBox().inflate(size))
                .filter(state -> state.is(Blocks.WATER)).toArray().length > 0;
    }
}
