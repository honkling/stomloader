package me.honkling.stomloader.mixins;

import me.honkling.stomloader.StomLoader;
import net.minecraft.server.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Main.class)
public class MainMixin {
    @Inject(at = @At("HEAD"), method = "main", cancellable = true)
    private static void main(CallbackInfo ci) {
        ci.cancel();
        StomLoader.main();
    }
}
