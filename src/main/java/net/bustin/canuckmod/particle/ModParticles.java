package net.bustin.canuckmod.particle;

import net.bustin.canuckmod.CanuckMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, CanuckMod.MOD_ID);

    public static final Supplier<SimpleParticleType> MAPLE_PARTICLE =
            PARTICLE_TYPES.register("maple_particle", ()-> new SimpleParticleType(true));


    public static void register(IEventBus eventBus){
        PARTICLE_TYPES.register(eventBus);
    }
}
