package com.aktheknight.akutils.blocks.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

/**
 * Created by alex on 26/04/16.
 */
public class TELevitatorPlayer extends TELevitatorBase {

    @Override
    public void update() {
        AxisAlignedBB area = new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 7, pos.getZ() + 1);
        List<EntityPlayer> entities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, area);
        int blockY = this.pos.getY();
        for (EntityPlayer entity: entities) {
            doLevitate(entity, blockY);
        }
    }
}
