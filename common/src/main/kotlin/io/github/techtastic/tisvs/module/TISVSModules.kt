package io.github.techtastic.tisvs.module

import dev.architectury.registry.registries.DeferredRegister
import io.github.techtastic.tisvs.TISVS.MOD_ID
import io.github.techtastic.tisvs.module.altitude.AltitudeModule
import io.github.techtastic.tisvs.module.distance.DistanceModule
import io.github.techtastic.tisvs.module.gyroscopic.GyroscopicModule
import io.github.techtastic.tisvs.module.omega.OmegaModule
import io.github.techtastic.tisvs.module.velocity.VelocityModule
import li.cil.tis3d.api.module.ModuleProvider
import li.cil.tis3d.common.item.ModCreativeTabs
import li.cil.tis3d.common.item.ModuleItem
import li.cil.tis3d.common.provider.module.SimpleModuleProvider
import net.minecraft.core.Registry
import net.minecraft.world.item.Item
import java.util.function.BiConsumer
import java.util.function.Supplier

object TISVSModules {
    private val MODULES = mutableMapOf<String, Supplier<ModuleProvider>>()
    private val ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_REGISTRY)

    val ALTITUDE_ITEM = ITEMS.register("altitude_module") { ModuleItem(Item.Properties().tab(ModCreativeTabs.COMMON)) }
    val ALTITUDE_MODULE = registerModule("altitude_module") { SimpleModuleProvider(ALTITUDE_ITEM, ::AltitudeModule) }

    val DISTANCE_ITEM = ITEMS.register("distance_module") { ModuleItem(Item.Properties().tab(ModCreativeTabs.COMMON)) }
    val DISTANCE_MODULE = registerModule("distance_module") { SimpleModuleProvider(DISTANCE_ITEM, ::DistanceModule) }

    val GYROSCOPIC_ITEM = ITEMS.register("gyroscopic_module") { ModuleItem(Item.Properties().tab(ModCreativeTabs.COMMON) ) }
    val GYROSCOPIC_MODULE = registerModule("gyroscopic_module") { SimpleModuleProvider(GYROSCOPIC_ITEM, ::GyroscopicModule) }

    val OMEGA_ITEM = ITEMS.register("omega_module") { ModuleItem(Item.Properties().tab(ModCreativeTabs.COMMON)) }
    val OMEGA_MODULE = registerModule("omega_module") { SimpleModuleProvider(OMEGA_ITEM, ::OmegaModule) }

    val VELOCITY_ITEM = ITEMS.register("velocity_module") { ModuleItem(Item.Properties().tab(ModCreativeTabs.COMMON)) }
    val VELOCITY_MODULE = registerModule("velocity_module") { SimpleModuleProvider(VELOCITY_ITEM, ::VelocityModule) }

    fun registerItems() {
        ITEMS.register()
    }

    fun registerModule(name: String, module: Supplier<ModuleProvider>): Supplier<ModuleProvider> {
        MODULES[name] = module
        return module
    }

    fun registryCallback(consumer: BiConsumer<String, Supplier<ModuleProvider>>) {
        MODULES.forEach(consumer::accept)
    }
}