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
        this(settings, "", showTooltip);
    }

    public TooltipItem(Settings settings, String path, boolean showTooltip) {
        super(settings);

        this.showTooltip = showTooltip;
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        this.handleTooltip(stack, tooltip);
        super.appendTooltip(stack, world, tooltip, context);
    }

    private void handleTooltip(ItemStack stack, List<Text> tooltip) {
        if (!showTooltip)
            return;

        if (!Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.callisto.holdformoreinfo").formatted(Formatting.GRAY)
                    .formatted(Formatting.ITALIC));
            return;
        }
    }

}
