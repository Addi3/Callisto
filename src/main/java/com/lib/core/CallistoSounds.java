package com.lib.core;

import com.lib.Callisto;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class CallistoSounds {
    public static final SoundEvent BOOP = register("boop");
    private static SoundEvent register(String name) {
        Identifier id = Callisto.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    public static void init() {
    }
}
