package net.notcherry.dungeonmod.entity.ai;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.notcherry.dungeonmod.entity.custom.LightOrbSpell;

import java.util.EnumSet;

public class LightOrbFollowGoal extends Goal {
    private final LightOrbSpell lightOrb;
    private Player followingPlayer;
    private static final double FOLLOW_SPEED = 1.0;
    private static final int FOLLOW_RANGE = 24;

    public LightOrbFollowGoal(LightOrbSpell lightOrb) {
        this.lightOrb = lightOrb;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        this.followingPlayer = this.getFollowingPlayer();
        return this.followingPlayer != null && this.lightOrb.distanceToSqr(this.followingPlayer) < FOLLOW_RANGE * FOLLOW_RANGE;
    }

    @Override
    public void start() {
        this.lightOrb.getNavigation().moveTo(this.followingPlayer, FOLLOW_SPEED);
    }

    @Override
    public void tick() {
        if (this.followingPlayer != null) {
            double distanceSq = this.lightOrb.distanceToSqr(this.followingPlayer);
            if (distanceSq > FOLLOW_RANGE * FOLLOW_RANGE) {
                this.lightOrb.getNavigation().moveTo(this.followingPlayer, FOLLOW_SPEED);
            }
        }
    }

    public void setFollowingPlayer(Player player) {
        this.followingPlayer = player;
    }

    public Player getFollowingPlayer() {
        return followingPlayer;
    }
}