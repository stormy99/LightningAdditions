package com.stormy.lightningadditions.utility.tubing;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import static net.minecraft.util.EnumFacing.DOWN;
import static net.minecraft.util.EnumFacing.EAST;
import static net.minecraft.util.EnumFacing.NORTH;
import static net.minecraft.util.EnumFacing.SOUTH;
import static net.minecraft.util.EnumFacing.UP;
import static net.minecraft.util.EnumFacing.WEST;

public enum EnumFlow implements IStringSerializable
{
    DU(DOWN, UP),
    DN(DOWN, NORTH),
    DS(DOWN, SOUTH),
    UN(UP, NORTH),
    US(UP, SOUTH),
    NS(NORTH, SOUTH),
    NW(NORTH, WEST),
    NE(NORTH, EAST),
    SW(SOUTH, WEST),
    SE(SOUTH, EAST),
    WE(WEST, EAST),
    WD(WEST, DOWN),
    WU(WEST, UP),
    ED(EAST, DOWN),
    EU(EAST, UP),
    UD(UP, DOWN),
    ND(NORTH, DOWN),
    SD(SOUTH, DOWN),
    NU(NORTH, UP),
    SU(SOUTH, UP),
    SN(SOUTH, NORTH),
    WN(WEST, NORTH),
    EN(EAST, NORTH),
    WS(WEST, SOUTH),
    ES(EAST, SOUTH),
    EW(EAST, WEST),
    DW(DOWN, WEST),
    UW(UP, WEST),
    DE(DOWN, EAST),
    UE(UP, EAST);

    private final EnumFacing input;

    private final EnumFacing output;

    EnumFlow(EnumFacing input, EnumFacing output)
    {
        this.input = input;
        this.output = output;
    }

    public EnumFacing getInput()
    {
        return input;
    }

    public EnumFacing getOutput()
    {
        return output;
    }

    @Override
    public String getName()
    {
        return (input.name() + output.name()).toLowerCase();
    }


    public static EnumFlow get(EnumFacing input, EnumFacing output)
    {
        for (EnumFlow flow : EnumFlow.values())
        {
            if (flow.input == input && flow.output == output)
            {
                return flow;
            }
        }
        return null;
    }
}
