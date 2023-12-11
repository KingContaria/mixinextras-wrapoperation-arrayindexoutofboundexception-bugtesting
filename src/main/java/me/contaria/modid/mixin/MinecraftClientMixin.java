package me.contaria.modid.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.UserCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.io.File;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

/*
    @WrapOperation(method = "handleInputEvents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;scheduleTerrainUpdate()V"))
    private void test(WorldRenderer instance, Operation<Void> original) {
        original.call(instance);
    }

 */

/*
    @WrapOperation(
            method = "startIntegratedServer(Ljava/lang/String;Lnet/minecraft/util/registry/RegistryTracker$Modifiable;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function4;ZLnet/minecraft/client/MinecraftClient$WorldLoadAction;)V",
            at = @At(
                    value = "NEW",
                    target = "Lcom/mojang/authlib/yggdrasil/YggdrasilAuthenticationService;",
                    remap = false
            )
    )
    private YggdrasilAuthenticationService loadYggdrasilAuthenticationService(Proxy proxy, String clientToken, Operation<YggdrasilAuthenticationService> original) {
        return original.call(proxy, clientToken);
    }

 */

    @WrapOperation(
            method = "startIntegratedServer(Ljava/lang/String;Lnet/minecraft/util/registry/RegistryTracker$Modifiable;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function4;ZLnet/minecraft/client/MinecraftClient$WorldLoadAction;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/authlib/yggdrasil/YggdrasilAuthenticationService;createProfileRepository()Lcom/mojang/authlib/GameProfileRepository;",
                    remap = false
            )
    )
    private GameProfileRepository loadGameProfileRepository(YggdrasilAuthenticationService service, Operation<GameProfileRepository> original) {
        return original.call(service);
    }

    @WrapOperation(
            method = "startIntegratedServer(Ljava/lang/String;Lnet/minecraft/util/registry/RegistryTracker$Modifiable;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function4;ZLnet/minecraft/client/MinecraftClient$WorldLoadAction;)V",
            at = @At(
                    value = "NEW",
                    target = "Lnet/minecraft/util/UserCache;"
            )
    )
    private UserCache loadUserCache(GameProfileRepository profileRepository, File cacheFile, Operation<UserCache> original) {
        return original.call(profileRepository, cacheFile);
    }

/*
    @Redirect(
            method = "startIntegratedServer(Ljava/lang/String;Lnet/minecraft/util/registry/RegistryTracker$Modifiable;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function4;ZLnet/minecraft/client/MinecraftClient$WorldLoadAction;)V",
            at = @At(
                    value = "NEW",
                    target = "Lnet/minecraft/util/UserCache;"
            )
    )
    private UserCache loadUserCache(GameProfileRepository profileRepository, File cacheFile) {
        return new UserCache(profileRepository, cacheFile);
    }

 */
}
