package com.stormy.lightninglib.lib.utils;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.UUID;


public final class ItemNBT
{

    public static NBTTagCompound getCompound(ItemStack stack) {
        if (stack.getTagCompound() == null) stack.setTagCompound(new NBTTagCompound());
        return stack.getTagCompound();
    }

    public static ItemStack setByte(ItemStack stack, String tag, byte b) {
        getCompound(stack).setByte(tag, b);
        return stack;
    }

    public static ItemStack setBoolean(ItemStack stack, String tag, boolean b) {
        getCompound(stack).setBoolean(tag, b);
        return stack;
    }

    public static ItemStack setShort(ItemStack stack, String tag, short s) {
        getCompound(stack).setShort(tag, s);
        return stack;
    }

    public static ItemStack setInteger(ItemStack stack, String tag, int i) {
        getCompound(stack).setInteger(tag, i);
        return stack;
    }

    public static ItemStack setLong(ItemStack stack, String tag, long i) {
        getCompound(stack).setLong(tag, i);
        return stack;
    }

    public static ItemStack setFloat(ItemStack stack, String tag, float f) {
        getCompound(stack).setFloat(tag, f);
        return stack;
    }

    public static ItemStack setDouble(ItemStack stack, String tag, double d) {
        getCompound(stack).setDouble(tag, d);
        return stack;
    }

    public static ItemStack setString(ItemStack stack, String tag, String s) {
        getCompound(stack).setString(tag, s);
        return stack;
    }

    public static boolean verifyExistance(ItemStack stack, String tag) {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound == null) return false;
        else return stack.getTagCompound().hasKey(tag);
    }

    public static byte getByte(ItemStack stack, String tag, byte defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getTagCompound().getByte(tag) : defaultExpected;
    }

    public static boolean getBoolean(ItemStack stack, String tag, boolean defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getTagCompound().getBoolean(tag) : defaultExpected;
    }

    public static short getShort(ItemStack stack, String tag, short defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getTagCompound().getShort(tag) : defaultExpected;
    }

    public static int getInteger(ItemStack stack, String tag, int defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getTagCompound().getInteger(tag) : defaultExpected;
    }

    public static long getLong(ItemStack stack, String tag, long defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getTagCompound().getLong(tag) : defaultExpected;
    }

    public static float getFloat(ItemStack stack, String tag, float defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getTagCompound().getFloat(tag) : defaultExpected;
    }

    public static double getDouble(ItemStack stack, String tag, double defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getTagCompound().getDouble(tag) : defaultExpected;
    }

    public static String getString(ItemStack stack, String tag, String defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getTagCompound().getString(tag) : defaultExpected; }

        public static class NBTUtil
        { //Stolen from UtilNBT - Amber!
            public static String posToStringCSV(BlockPos position) {
                if (position == null) { return ""; }
                return position.getX() + "," + position.getY() + "," + position.getZ();
            }
            public static void setItemStackBlockPos(ItemStack item, BlockPos pos) {
                NBTUtil.setItemStackNBTVal(item, "xpos", pos.getX());
                NBTUtil.setItemStackNBTVal(item, "ypos", pos.getY());
                NBTUtil.setItemStackNBTVal(item, "zpos", pos.getZ());
            }
            public static BlockPos getItemStackBlockPos(ItemStack item) {
                if (item.getTagCompound() == null
                        || !item.getTagCompound().hasKey("xpos")) { return null; }
                return new BlockPos(getItemStackNBTVal(item, "xpos"), getItemStackNBTVal(item, "ypos"), getItemStackNBTVal(item, "zpos"));
            }
            public static void setItemStackNBTVal(ItemStack item, String prop, int value) {
                if (item.isEmpty()) { return; }
                getItemStackNBT(item).setInteger(prop, value);
            }
            public static void setItemStackNBTVal(ItemStack item, String prop, String value) {
                if (item.isEmpty()) { return; }
                getItemStackNBT(item).setString(prop, value);
            }
            public static int getItemStackNBTVal(ItemStack held, String prop) {
                if (held.isEmpty()) { return 0; }
                NBTTagCompound tags = getItemStackNBT(held);
                if (!tags.hasKey(prop)) { return 0; }
                return tags.getInteger(prop);
            }
            /**
             * empty string if null or empty, otherwise the value in string form for
             * tooltips
             *
             * @param held
             * @param prop
             * @return
             */
            public static String getItemStackDisplayInteger(ItemStack held, String prop) {
                if (held.isEmpty()) { return ""; }
                NBTTagCompound tags = getItemStackNBT(held);
                if (!tags.hasKey(prop)) { return ""; }
                return tags.getInteger(prop) + "";
            }
            public static NBTTagCompound getItemStackNBT(ItemStack held) {
                if (held.getTagCompound() == null) {
                    held.setTagCompound(new NBTTagCompound());
                }
                return held.getTagCompound();
            }
            public static BlockPos stringCSVToBlockPos(String csv) {
                String[] spl = csv.split(",");
                // on server i got java.lang.ClassCastException: java.lang.String cannot
                // be cast to java.lang.Integer
                // ?? is it from this?
                BlockPos p = null;
                try {
                    if (spl != null && spl.length == 3 && spl[0] != "")
                        p = new BlockPos(Integer.parseInt(spl[0]), Integer.parseInt(spl[1]), Integer.parseInt(spl[2]));
                }
                catch (java.lang.ClassCastException e) {
                    //      ModCyclic.logger.info("exc: bad string: " + csv);
                }
                return p;
            }
            public static void incrementPlayerIntegerNBT(EntityPlayer player, String prop, int inc) {
                int prev = player.getEntityData().getInteger(prop);
                prev += inc;
                player.getEntityData().setInteger(prop, prev);
            }
            public static void writeTagsToInventory(IInventory invo, NBTTagCompound tags, String key) {
                NBTTagList items = tags.getTagList(key, tags.getId());
                ItemStack stack;
                int slot;
                for (int i = 0; i < items.tagCount(); ++i) {
                    // tagAt(int) has changed to getCompoundTagAt(int)
                    NBTTagCompound item = items.getCompoundTagAt(i);
                    stack = NBTUtil.itemFromNBT(item);
                    slot = item.getInteger("slot");
                    // list.add(ItemStack.loadItemStackFromNBT(item));
                    invo.setInventorySlotContents(slot, stack);
                }
            }
            public static NBTTagCompound writeInventoryToTag(IInventory invo, NBTTagCompound returnTag, String key) {
                ItemStack chestItem;
                NBTTagCompound itemTag;
                NBTTagList nbttaglist = new NBTTagList();
                for (int i = 0; i < invo.getSizeInventory(); i++) {
                    // zeroes to avoid nulls, and signify nothing goes there
                    chestItem = invo.getStackInSlot(i);
                    if (chestItem.isEmpty() || chestItem.getCount() == 0) {
                        continue;
                    } // not an error; empty chest slot
                    itemTag = chestItem.writeToNBT(new NBTTagCompound());
                    itemTag.setInteger("slot", i);
                    nbttaglist.appendTag(itemTag);
                    // its either in the bag, or dropped on the player
                    invo.setInventorySlotContents(i, ItemStack.EMPTY);
                }
                returnTag.setTag(key, nbttaglist);
                return returnTag;
            }
            public static NBTTagCompound writeInventoryToNewTag(IInventory invo, String key) {
                return writeInventoryToTag(invo, new NBTTagCompound(), key);
            }
            public static int countItemsFromNBT(NBTTagCompound tags, String key) {
                if (tags == null) { return 0; }
                NBTTagList items = tags.getTagList(key, tags.getId());
                if (items == null) { return 0; }
                return items.tagCount();
            }
            public static ArrayList<ItemStack> readItemsFromNBT(NBTTagCompound tags, String key) {
                ArrayList<ItemStack> list = new ArrayList<ItemStack>();
                NBTTagList items = tags.getTagList(key, tags.getId());
                for (int i = 0; i < items.tagCount(); ++i) {
                    // tagAt(int) has changed to getCompoundTagAt(int)
                    NBTTagCompound item = items.getCompoundTagAt(i);
                    list.add(NBTUtil.itemFromNBT(item));
                }
                return list;
            }
            public static ItemStack enchantItem(Item item, Enchantment ench, short level) {
                ItemStack stack = new ItemStack(item);
                stack.addEnchantment(ench, level);
                return stack;
            }

            public static ItemStack buildEnchantedNametag(String customNameTag) {
                ItemStack nameTag = new ItemStack(Items.NAME_TAG, 1);
                NBTTagCompound nbt = new NBTTagCompound();
                NBTTagCompound display = new NBTTagCompound();
                display.setString("Name", customNameTag);
                nbt.setTag("display", display);
                nbt.setInteger("RepairCost", 1);
                nameTag.setTagCompound(nbt);// put the data into the item stack
                return nameTag; }

            public static ItemStack itemFromNBT(NBTTagCompound tag) { return new ItemStack(tag); }

            public static void setEntityBoolean(EntityEvokerFangs entityevokerfangs, String string) {
                entityevokerfangs.getEntityData().setBoolean(string, true);
            }
            public static boolean getEntityBoolean(Entity entityevokerfangs, String string) {
                return entityevokerfangs.getEntityData().getBoolean(string);
            }
        }

}
