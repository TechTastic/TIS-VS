package io.github.techtastic.tisvs.serial

import li.cil.tis3d.api.serial.SerialInterfaceProvider
import io.github.techtastic.tisvs.serial.custom.FramedMapSerialInterfaceProvider
import java.util.function.BiConsumer
import java.util.function.Supplier

object TISVSSerialInterfaces {
    fun registryCallback(consumer: BiConsumer<String, Supplier<SerialInterfaceProvider>>) {
        consumer.accept("framed_map", ::FramedMapSerialInterfaceProvider)
    }
}