package io.github.techtastic.tisvs.fabric

import dev.architectury.registry.registries.RegistrarManager
import io.github.techtastic.tisvs.TISVS.id
import io.github.techtastic.tisvs.TISVS.init
import io.github.techtastic.tisvs.TISVS.initClient
import io.github.techtastic.tisvs.manual.TISVSManual
import io.github.techtastic.tisvs.module.TISVSModules
import io.github.techtastic.tisvs.serial.TISVSSerialInterfaces
import li.cil.manual.api.prefab.tab.AbstractTab
import li.cil.manual.api.util.Constants
import li.cil.tis3d.api.module.ModuleProvider
import li.cil.tis3d.api.serial.SerialInterfaceProvider
import li.cil.tis3d.common.item.ModCreativeTabs
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.core.Registry
import org.valkyrienskies.mod.fabric.common.ValkyrienSkiesModFabric

object TISVSFabric: ModInitializer {
    override fun onInitialize() {
        // force VS2 to load before TIS: VS
        ValkyrienSkiesModFabric().onInitialize()

        init()
        
        ItemGroupEvents.modifyEntriesEvent(ModCreativeTabs.COMMON.key).register { entries ->
            entries.accept(TISVSModules.ALTITUDE_ITEM.get())
            entries.accept(TISVSModules.DISTANCE_ITEM.get())
            entries.accept(TISVSModules.GYROSCOPIC_ITEM.get())
            entries.accept(TISVSModules.OMEGA_ITEM.get())
            entries.accept(TISVSModules.VELOCITY_ITEM.get())
        }

        TISVSModules.registryCallback { name, supp -> RegistrarManager.get("tis3d").get(ModuleProvider.REGISTRY).register(id(name), supp) }
        TISVSSerialInterfaces.registryCallback { name, supp -> RegistrarManager.get("tis3d").get(SerialInterfaceProvider.REGISTRY).register(id(name), supp) }

        TISVSManual.tabRegistryCallback { name, supp -> RegistrarManager.get("tis3d").get(Constants.TAB_REGISTRY).register(id(name), supp) }
        TISVSManual.pathRegistryCallback { name, supp -> RegistrarManager.get("tis3d").get(Constants.PATH_PROVIDER_REGISTRY).register(id(name), supp) }
        TISVSManual.contentRegistryCallback() { name, supp -> RegistrarManager.get("tis3d").get(Constants.DOCUMENT_PROVIDER_REGISTRY).register(id(name), supp) }
    }

    @Environment(EnvType.CLIENT)
    class Client : ClientModInitializer {
        override fun onInitializeClient() {
            initClient()
        }
    }
}
