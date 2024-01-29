package io.github.techtastic.tis_3d_vs.forge

import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(MOD_ID)
class TIS3DVSModForge {
    val MOD_ID = "tis_3d_vs"

    init {
        MOD_BUS.addListener { event: FMLClientSetupEvent? ->
            clientSetup(
                event
            )
        }
    }

    private fun clientSetup(event: FMLClientSetupEvent?) {
    }

    companion object {
        fun getModBus(): IEventBus = MOD_BUS
    }
}
