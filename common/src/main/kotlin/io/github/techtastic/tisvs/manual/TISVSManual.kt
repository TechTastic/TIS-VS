package io.github.techtastic.tisvs.manual

import io.github.techtastic.tisvs.TISVS.MOD_ID
import io.github.techtastic.tisvs.manual.custom.TISVSTab
import io.github.techtastic.tisvs.manual.custom.TISVSDocumentProvider
import io.github.techtastic.tisvs.manual.custom.TISVSPathProvider
import li.cil.manual.api.prefab.provider.NamespaceDocumentProvider
import li.cil.manual.api.prefab.provider.NamespacePathProvider
import li.cil.manual.api.prefab.tab.AbstractTab
import java.util.function.BiConsumer
import java.util.function.Supplier

object TISVSManual {
    fun tabRegistryCallback(callback: BiConsumer<String, Supplier<AbstractTab>>) {
        callback.accept(MOD_ID, ::TISVSTab)
    }

    fun pathRegistryCallback(callback: BiConsumer<String, Supplier<NamespacePathProvider>>) {
        callback.accept("path_provider", ::TISVSPathProvider)
    }

    fun contentRegistryCallback(callback: BiConsumer<String, Supplier<NamespaceDocumentProvider>>) {
        callback.accept("content_provider", ::TISVSDocumentProvider)
    }
}