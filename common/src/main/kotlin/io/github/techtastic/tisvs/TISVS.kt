package io.github.techtastic.tisvs

import dev.architectury.registry.registries.DeferredRegister
import io.github.techtastic.tisvs.module.TISVSModules
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item

object TISVS {
    const val MOD_ID = "tisvs"

    private val ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_REGISTRY)
    val LOGO = ITEMS.register("logo") { Item(Item.Properties()) }

    @JvmStatic
    fun init() {
        TISVSModules.registerItems()

        ITEMS.register()
        TISVSGameRules.register()
    }

    @JvmStatic
    fun initClient() {
    }

    fun id(path: String) = ResourceLocation(MOD_ID, path)
}
