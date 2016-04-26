package com.aktheknight.akutils.blocks.tileentities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

/**
 * Created by alex on 26/04/16.
 */
public class TELevitatorMobs extends TELevitatorBase {

    @Override
    public void update() {
        AxisAlignedBB area = new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 7, pos.getZ() + 1);
        List<EntityLivingBase> entities = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, area);
        int blockY = this.pos.getY();
        for (EntityLivingBase entity: entities) {
            doLevitate(entity, blockY);
        }
    }
}
