package com.lib.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TooltipItem extends Item {

    private final boolean showTooltip;

    public TooltipItem(Settings settings, boolean showTooltip) {
        super(settings);
        this.showTooltip = showTooltip;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (!showTooltip) {
            super.appendTooltip(stack, world, tooltip, context);
            return;
        }

        if (!Screen.hasShiftDown()) {
            tooltip.add(
                    Text.translatable("tooltip.callisto.holdformoreinfo")
                            .formatted(Formatting.GRAY, Formatting.ITALIC)
            );
        } else {
            appendShiftTooltip(stack, tooltip);
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

    protected void appendShiftTooltip(ItemStack stack, List<Text> tooltip) {

    }
}
