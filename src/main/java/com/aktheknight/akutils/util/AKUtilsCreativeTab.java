package com.aktheknight.akutils.util;

import com.aktheknight.akutils.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by alex on 26/04/16.
 */
public class AKUtilsCreativeTab extends CreativeTabs {

    public AKUtilsCreativeTab(int index, String label) {
        super(index, label);
    }

    @Override
    public Item getTabIconItem() {
        return ModItems.superMeal;
    }
}
