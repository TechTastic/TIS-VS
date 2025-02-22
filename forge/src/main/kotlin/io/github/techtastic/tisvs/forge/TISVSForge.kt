package io.github.techtastic.tisvs.forge

import dev.architectury.platform.forge.EventBuses
import io.github.techtastic.tisvs.TISVS.MOD_ID
import io.github.techtastic.tisvs.TISVS.id
import io.github.techtastic.tisvs.TISVS.init
import io.github.techtastic.tisvs.TISVS.initClient
import io.github.techtastic.tisvs.manual.TISVSManual
import io.github.techtastic.tisvs.module.TISVSModules
import io.github.techtastic.tisvs.serial.TISVSSerialInterfaces
import li.cil.manual.api.util.Constants
import li.cil.tis3d.api.module.ModuleProvider
import li.cil.tis3d.api.serial.SerialInterfaceProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.inventory.InventoryMenu
import net.minecraftforge.client.event.TextureStitchEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.KotlinModLoadingContext
import java.util.*


@Mod(MOD_ID)
class TISVSForge {
    init {
        val bus = KotlinModLoadingContext.get().getKEventBus()
        EventBuses.registerModEventBus(MOD_ID, bus)

        bus.addListener(this::clientSetup)
        bus.addListener(this::handleTextureStitchEvent)
        bus.addListener(this::registryCallback)

        init()
    }

    private fun clientSetup(event: FMLClientSetupEvent) {
        initClient()
    }

    private fun handleTextureStitchEvent(event: TextureStitchEvent.Pre) {
        if (Objects.equals(event.atlas.location(), InventoryMenu.BLOCK_ATLAS)) {
            event.addSprite(ResourceLocation(MOD_ID, "block/overlay/distance_module"))
            event.addSprite(ResourceLocation(MOD_ID, "block/overlay/altitude_module"))
            event.addSprite(ResourceLocation(MOD_ID, "block/overlay/gyroscopic_module"))
            event.addSprite(ResourceLocation(MOD_ID, "block/overlay/velocity/x"))
            event.addSprite(ResourceLocation(MOD_ID, "block/overlay/velocity/y"))
            event.addSprite(ResourceLocation(MOD_ID, "block/overlay/velocity/z"))
            event.addSprite(ResourceLocation(MOD_ID, "block/overlay/rotation_speed/roll"))
            event.addSprite(ResourceLocation(MOD_ID, "block/overlay/rotation_speed/pitch"))
            event.addSprite(ResourceLocation(MOD_ID, "block/overlay/rotation_speed/yaw"))
        }
    }

    private fun registryCallback(event: RegisterEvent) {
        TISVSModules.registryCallback { name, supp -> event.register(ModuleProvider.REGISTRY, id(name), supp) }
        TISVSSerialInterfaces.registryCallback { name, supp -> event.register(SerialInterfaceProvider.REGISTRY, id(name), supp) }

        TISVSManual.tabRegistryCallback { name, supp -> event.register(Constants.TAB_REGISTRY, id(name), supp::get) }
        TISVSManual.pathRegistryCallback { name, supp -> event.register(Constants.PATH_PROVIDER_REGISTRY, id(name), supp::get) }
        TISVSManual.contentRegistryCallback { name, supp -> event.register(Constants.DOCUMENT_PROVIDER_REGISTRY, id(name), supp::get) }
    }
}
