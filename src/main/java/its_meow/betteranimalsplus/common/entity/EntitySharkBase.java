package its_meow.betteranimalsplus.common.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class EntitySharkBase extends EntityBasicWaterCreature {

    public EntitySharkBase(EntityType<? extends EntitySharkBase> type, World world) {
        super(type, world);
    }
    
    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        float f = (float) this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
        int i = 0;

        if (entityIn instanceof LivingEntity) {
            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((LivingEntity) entityIn).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag) {
            if (i > 0 && entityIn instanceof LivingEntity) {
                ((PlayerEntity) entityIn).knockBack(this, i * 0.5F, MathHelper.sin(this.rotationYaw * 0.017453292F), (-MathHelper.cos(this.rotationYaw * 0.017453292F)));
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0) {
                entityIn.setFire(j * 4);
            }

            if (entityIn instanceof PlayerEntity) {
                PlayerEntity entityplayer = (PlayerEntity) entityIn;
                ItemStack itemstack = this.getHeldItemMainhand();
                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer)) {
                    float f1 = 0.25F + EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

                    if (this.rand.nextFloat() < f1) {
                        entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
                        this.world.setEntityState(entityplayer, (byte) 30);
                    }
                }
            }

            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

}