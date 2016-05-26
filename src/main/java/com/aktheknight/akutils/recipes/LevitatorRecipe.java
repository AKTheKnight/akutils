package com.aktheknight.akutils.recipes;

import com.aktheknight.akutils.blocks.levitators.Levitator;
import com.aktheknight.akutils.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by alex on 24/05/16.
 */
public class LevitatorRecipe extends ShapelessOreRecipe {

    public LevitatorRecipe(Object item) {
        super(ModBlocks.levitator, ModBlocks.levitator, item);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        for (int slots = 0; slots < inv.getSizeInventory(); slots++) {
            if (inv.getStackInSlot(slots) != null
                    && inv.getStackInSlot(slots).getItem() instanceof ItemBlock
                    && Block.getBlockFromItem(inv.getStackInSlot(slots).getItem()) instanceof Levitator) {
                ItemStack levStack = inv.getStackInSlot(slots);
                ItemStack result = levStack.copy();
                result.stackSize = 1;
                Levitator lev = (Levitator) Block.getBlockFromItem(levStack.getItem());
                NBTTagCompound tag;
                if (levStack.getTagCompound() != null) {
                     tag = levStack.getTagCompound();
                } else {
                    tag = new NBTTagCompound();
                }

                for (int slot = 0; slot < inv.getSizeInventory(); slot++) {
                    if (inv.getStackInSlot(slot) != null) {
                        if (inv.getStackInSlot(slot).getItem() == Items.PAPER) {
                            if (tag.hasKey("playerLev")
                                    && tag.getBoolean("playerLev")) {
                                tag.removeTag("playerLev");
                            }
                            if (tag.hasKey("mobLev")
                                    && tag.getBoolean("mobLev")) {
                                tag.removeTag("mobLev");
                            }
                            if (tag.hasKey("itemLev")
                                    && tag.getBoolean("itemLev")) {
                                tag.removeTag("itemLev");
                            }

                            result.setTagCompound(tag);
                            return result;
                        }

                        if (inv.getStackInSlot(slot).getItem() == Items.BONE) {
                            if (!tag.hasKey("mobLev")) {
                                tag.setBoolean("mobLev", true);
                            }
                            result.setTagCompound(tag);
                            return result;
                        }

                        if (inv.getStackInSlot(slot).getItem() == Items.ARMOR_STAND) {
                            if (!tag.hasKey("playerLev")) {
                                tag.setBoolean("playerLev", true);
                            }
                            result.setTagCompound(tag);
                            return result;
                        }

                        if (inv.getStackInSlot(slot).getItem() == Items.ITEM_FRAME) {
                            if (!tag.hasKey("itemLev")) {
                                tag.setBoolean("itemLev", true);
                            }
                            result.setTagCompound(tag);
                            return result;
                        }

                        //TODO Redstone
                    }
                }
            }
        }
        return this.getRecipeOutput().copy();
    }

}
