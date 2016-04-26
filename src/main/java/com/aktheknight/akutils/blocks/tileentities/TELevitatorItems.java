package com.aktheknight.akutils.blocks.tileentities;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

/**
 * Created by alex on 26/04/16.
 */
public class TELevitatorItems extends TELevitatorBase {
    @Override
    public void update() {
        AxisAlignedBB area = new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 7, pos.getZ() + 1);
        List<EntityItem> entities = worldObj.getEntitiesWithinAABB(EntityItem.class, area);
        int blockY = this.pos.getY();
        for (EntityItem entity: entities) {
            doLevitate(entity, blockY);
        }
    }
}
