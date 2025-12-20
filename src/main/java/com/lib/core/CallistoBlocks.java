package com.lib.core;

import com.lib.Callisto;
import com.lib.core.blocks.PlushBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CallistoBlocks {
    public static final Block PLUSH_ADDIE = registerBlock("plush_addie", new PlushBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL).nonOpaque()));
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Callisto.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Callisto.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }
    public static void registerCallistoBlocks() {}
}
