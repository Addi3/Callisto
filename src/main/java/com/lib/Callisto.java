package com.lib;

import com.lib.core.CallistoBlocks;
import com.lib.core.CallistoSounds;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Callisto implements ModInitializer {
	public static final String MOD_ID = "callisto";

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CallistoBlocks.registerCallistoBlocks();
		CallistoSounds.init();
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.add(CallistoBlocks.PLUSH_ADDIE.asItem());
		});
	}
}