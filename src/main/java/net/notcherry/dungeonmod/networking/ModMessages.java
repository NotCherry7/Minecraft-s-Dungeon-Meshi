package net.notcherry.dungeonmod.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.networking.packet.ConsumeManaC2SPacket;
import net.notcherry.dungeonmod.networking.packet.ManaDataSyncS2CPacket;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(DungeonMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

//        net.messageBuilder(ExampleC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(ExampleC2SPacket::new)
//                .encoder(ExampleC2SPacket::toBytes)
//                .consumerMainThread(ExampleC2SPacket::handle)
//                .add();

        net.messageBuilder(ConsumeManaC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ConsumeManaC2SPacket::new)
                .encoder(ConsumeManaC2SPacket::toBytes)
                .consumerMainThread(ConsumeManaC2SPacket::handle)
                .add();

        net.messageBuilder(ManaDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ManaDataSyncS2CPacket::new)
                .encoder(ManaDataSyncS2CPacket::toBytes)
                .consumerMainThread(ManaDataSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
