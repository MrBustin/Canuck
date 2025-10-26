package net.bustin.canuckmod.entity.custom;

import net.bustin.canuckmod.entity.ModEntities;
import net.bustin.canuckmod.util.ChopTreeGoal;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class BeaverEntity extends Animal {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public BeaverEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);

        this.moveControl = new MoveControl(this) {
            @Override
            public void tick() {
                if (BeaverEntity.this.isInWater()) {
                    this.speedModifier *= 1.5; // swim faster in water
                }
                super.tick();
            }
        };
        this.navigation = new AmphibiousPathNavigation(this, level);
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, travelVector); // adjust for stronger strokes
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D)); // friction
        } else {
            super.travel(travelVector);
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this) {
            @Override
            public boolean canUse() {
                return BeaverEntity.this.isInWater() && BeaverEntity.this.getY() < BeaverEntity.this.level().getSeaLevel() - 1;
            }
        });

        this.goalSelector.addGoal(1, new ChopTreeGoal(this));

        this.goalSelector.addGoal(2, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.25, stack -> stack.is(Items.STICK), false));

        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.25));

        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0) {
            @Override
            public boolean canUse() {
                return !BeaverEntity.this.isInWater() && super.canUse();
            }
        });

        this.goalSelector.addGoal(7, new RandomSwimmingGoal(this, 1.0, 40) {
            @Override
            public boolean canUse() {
                return BeaverEntity.this.isInWater() && super.canUse();
            }
        });

        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10d)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }


    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.STICK);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return ModEntities.BEAVER.get().create(level);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.isInWater()) {
            this.setSwimming(true);

            if (this.getRandom().nextInt(100) == 0) {
                this.getNavigation().moveTo(this.getX(),
                        this.getY() + 1.5D,
                        this.getZ(),
                        1.2D);
            }
        } else {
            this.setSwimming(false);
        }
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    @Override
    public int getMaxAirSupply() {
        return 4800; // 4x vanilla, tweak as needed
    }

    @Override
    protected int increaseAirSupply(int air) {
        return this.getMaxAirSupply(); // instantly refill when at surface
    }

    /*SOUNDS*/

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.RABBIT_AMBIENT;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.PANDA_HURT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.ARMADILLO_DEATH;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.DOLPHIN_SWIM;
    }
}
