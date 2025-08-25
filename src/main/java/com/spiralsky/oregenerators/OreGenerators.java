package com.spiralsky.oregenerators;

import com.spiralsky.oregenerators.events.CobbleGenEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.mojang.text2speech.Narrator.LOGGER;

@Mod(OreGenerators.MODID)
public class OreGenerators {
    public static final String MODID = "oregenerators";

    public OreGenerators(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("[OreGenerators] Setting Up...");
        CobbleGenEvent.getOrePool();
    }
}
