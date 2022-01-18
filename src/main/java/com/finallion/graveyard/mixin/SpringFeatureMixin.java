package com.finallion.graveyard.mixin;

import com.finallion.graveyard.init.TGStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.SpringFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpringFeature.class)
public class SpringFeatureMixin {

    @Inject(method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z", at = @At(value = "HEAD"), cancellable = true)
    private void generateNoLavaLakes(FeaturePlaceContext<SpringConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        if (context.config().state.is(FluidTags.LAVA)) {
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            SectionPos chunkPos;
            for (Direction face : Direction.Plane.HORIZONTAL) {
                mutable.set(context.origin()).move(face);
                chunkPos = SectionPos.of(mutable);
                for (StructureFeature<?> structure : TGStructures.MOD_STRUCTURES) {
                    if (!context.level().startsForFeature(chunkPos, structure).isEmpty()) {
                        cir.setReturnValue(false);
                    }
                }
            }
        }

    }

}