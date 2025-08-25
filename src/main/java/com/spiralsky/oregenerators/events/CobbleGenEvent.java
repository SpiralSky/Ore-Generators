package com.spiralsky.oregenerators.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;
import net.minecraftforge.registries.tags.ITagManager;

import java.util.ArrayList;
import java.util.Objects;

import static com.spiralsky.oregenerators.OreGenerators.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CobbleGenEvent {
    static final ArrayList<Block> oreBlocks = new ArrayList<>();
    static int currentIndex = 0;

    @SubscribeEvent
    public static void fluidInteractionEventHandler(BlockEvent.FluidPlaceBlockEvent event) {
        if (event.getNewState().getBlock() == Blocks.COBBLESTONE) {
            ArrayList<Block> orePool = getOrePool();
            if (orePool.isEmpty()) {
                return;
            }

            int index = currentIndex == oreBlocks.size() - 1 ? currentIndex = 0 : currentIndex++;
            event.setNewState(orePool.get(index).defaultBlockState());
        }
    }

    @SuppressWarnings("SameReturnValue")
    public static ArrayList<Block> getOrePool() {
        if (!CobbleGenEvent.oreBlocks.isEmpty()) {
            return CobbleGenEvent.oreBlocks;
        }
        CobbleGenEvent.updateOrePool();
        return CobbleGenEvent.oreBlocks;
    }

    public static void updateOrePool() {
        final ITagManager<Block> blockTagManager = ForgeRegistries.BLOCKS.tags();
        final TagKey<Block> oreTagKey = Objects.requireNonNull(blockTagManager).createTagKey(ResourceLocation.fromNamespaceAndPath("forge", "ores"));
        final ITag<Block> oreTag = blockTagManager.getTag(oreTagKey);

        ForgeRegistries.BLOCKS.getValues().forEach(value -> {
            if (oreTag.contains(value)) {
                CobbleGenEvent.oreBlocks.add(value);
            }
        });
    }
}
