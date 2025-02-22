package io.github.techtastic.tisvs.forge

import dev.architectury.platform.forge.EventBuses
import dev.architectury.registry.registries.RegistrarManager
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import io.github.techtastic.tisvs.TISVS.MOD_ID
import io.github.techtastic.tisvs.TISVS.id
import io.github.techtastic.tisvs.TISVS.init
import io.github.techtastic.tisvs.TISVS.initClient
import io.github.techtastic.tisvs.manual.TISVSManual
import io.github.techtastic.tisvs.module.TISVSModules
import io.github.techtastic.tisvs.serial.TISVSSerialInterfaces
import li.cil.manual.api.Tab
import li.cil.manual.api.prefab.tab.AbstractTab
import li.cil.manual.api.util.Constants
import li.cil.tis3d.api.module.ModuleProvider
import li.cil.tis3d.api.serial.SerialInterfaceProvider
import li.cil.tis3d.common.item.ModCreativeTabs
import net.minecraftforge.common.CreativeModeTabRegistry
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent
import net.minecraftforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.KotlinModLoadingContext
import java.util.function.Supplier

@Mod(MOD_ID)
class TISVSForge {
    init {
        val bus = KotlinModLoadingContext.get().getKEventBus()
        EventBuses.registerModEventBus(MOD_ID, bus)

        bus.addListener(this::clientSetup)
        bus.addListener(this::registryCallback)
        bus.addListener(this::onTabRegistry)

        init()
    }

    private fun clientSetup(event: FMLClientSetupEvent) {
        initClient()
    }

    private fun registryCallback(event: RegisterEvent) {
        TISVSModules.registryCallback { name, supp -> event.register(ModuleProvider.REGISTRY, id(name), supp) }
        TISVSSerialInterfaces.registryCallback { name, supp -> event.register(SerialInterfaceProvider.REGISTRY, id(name), supp) }

        TISVSManual.tabRegistryCallback { name, supp -> event.register(Constants.TAB_REGISTRY, id(name), supp::get) }
        TISVSManual.pathRegistryCallback { name, supp -> event.register(Constants.PATH_PROVIDER_REGISTRY, id(name), supp::get) }
        TISVSManual.contentRegistryCallback { name, supp -> event.register(Constants.DOCUMENT_PROVIDER_REGISTRY, id(name), supp::get) }
    }

    private fun onTabRegistry(event: BuildCreativeModeTabContentsEvent) {
        if (event.tab == ModCreativeTabs.COMMON.get()) {
            event.accept(TISVSModules.ALTITUDE_ITEM)
            event.accept(TISVSModules.DISTANCE_ITEM)
            event.accept(TISVSModules.GYROSCOPIC_ITEM)
            event.accept(TISVSModules.OMEGA_ITEM)
            event.accept(TISVSModules.VELOCITY_ITEM)
        }
    }
}
