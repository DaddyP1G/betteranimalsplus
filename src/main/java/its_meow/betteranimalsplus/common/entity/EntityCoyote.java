package its_meow.betteranimalsplus.common.entity;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.init.BlockRegistry;
import its_meow.betteranimalsplus.init.MobRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityCoyote extends EntityFeralWolf {

	public EntityCoyote(World worldIn) {
		super(MobRegistry.getType(EntityCoyote.class), worldIn);
		this.world = worldIn;
		this.setSize(0.8F, 0.9F);
		this.setTamed(false);
	}

	@Override
	protected void initEntityAI() {
		this.aiSit = new EntityAISit(this);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(8, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(10, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(4,
				new EntityAITargetNonTamed<>(this, EntityPlayer.class, false, new NullPredicate()));
		this.targetTasks.addTask(4,
				new EntityAITargetNonTamed<>(this, EntityAnimal.class, false,
						(@Nullable Entity p_apply_1_) -> p_apply_1_ instanceof EntitySheep
								|| p_apply_1_ instanceof EntityRabbit));
		this.targetTasks.addTask(4,
				new EntityAITargetNonTamed<>(this, EntityVillager.class, false, new NullPredicate()));
		this.targetTasks.addTask(4,
				new EntityAITargetNonTamed<>(this, AbstractIllager.class, false, new NullPredicate()));
		this.targetTasks.addTask(4,
				new EntityAITargetNonTamed<>(this, EntityChicken.class, false, new NullPredicate()));
		this.targetTasks.addTask(5,
				new EntityAINearestAttackableTarget<>(this, AbstractSkeleton.class, false));
	}

	public boolean isDaytime() {
		long time = this.world.getDayTime() % 24000L; // Time can go over values
														// of 24000, so divide
														// and take the
														// remainder
		return !(time >= 13000L && time <= 23000L);
	}

	@Override
	public void setAttackTarget(EntityLivingBase entitylivingbaseIn) {
		if(!this.isDaytime()) {
			super.setAttackTarget(entitylivingbaseIn);
		} else if(!this.isTamed()) {
			super.setAttackTarget(null);
		} else {
			super.setAttackTarget(entitylivingbaseIn);
		}
		if(this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
			super.setAttackTarget(null);
		}
	}

	@Override
	protected SoundEvent getAmbientSound() {
		if(!this.isDaytime() && !this.isTamed()) {
			return SoundEvents.ENTITY_WOLF_GROWL;
		} else if(this.rand.nextInt(3) == 0) {
			return this.isTamed() && this.dataManager.get(EntityFeralWolf.DATA_HEALTH_ID).floatValue() < 10.0F
					? SoundEvents.ENTITY_WOLF_WHINE
					: SoundEvents.ENTITY_WOLF_PANT;
		} else if(this.getAttackTarget() != null) {
			return SoundEvents.ENTITY_WOLF_GROWL;
		}
		return null;
	}

	@Override
	public void onDeath(DamageSource cause) {
		if(!this.isChild()) {
			if(this.rand.nextInt(12) == 0) {
				ItemStack stack = new ItemStack(BlockRegistry.wolfhead.getItemBlock());
				// stack.setTagCompound(new NBTTagCompound());
				// stack.getTagCompound().setInteger("TYPENUM", 4); // 4 is the
				// coyote value for wolfheads
				this.entityDropItem(stack, 0.5F);
			}
		}
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);

		if(this.isTamed()) {
			if(!itemstack.isEmpty()) {
				if(itemstack.getItem() instanceof ItemFood) {
					ItemFood itemfood = (ItemFood) itemstack.getItem();

					if(itemfood.isMeat() && this.dataManager.get(EntityFeralWolf.DATA_HEALTH_ID).floatValue() < 20.0F) {
						if(!player.isCreative()) {
							itemstack.shrink(1);
						}

						this.heal(itemfood.getHealAmount(itemstack));
						return true;
					}
				}
			}

			if(this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack)
					&& (!(itemstack.getItem() instanceof ItemFood) || !((ItemFood) itemstack.getItem()).isMeat())) {
				this.aiSit.setSitting(!this.isSitting());
				this.isJumping = false;
				this.navigator.clearPath();
				this.setAttackTarget((EntityLivingBase) null);
			}
		} else if(itemstack.getItem() == Items.RABBIT) {
			if(this.isDaytime()) {


				if(!player.isCreative()) {
					itemstack.shrink(1);
				}

				if(!this.world.isRemote) {
					if(this.rand.nextInt(100) <= 14
							&& !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
						this.setTamedBy(player);
						this.navigator.clearPath();
						this.setAttackTarget((EntityLivingBase) null);
						this.aiSit.setSitting(true);
						this.setHealth(20.0F);
						this.playTameEffect(true);
						this.world.setEntityState(this, (byte) 7);
					} else {
						this.playTameEffect(false);
						this.world.setEntityState(this, (byte) 6);
					}
				}

				return true;
			} else {
				if(!this.world.isRemote) {
					player.sendMessage(new TextComponentString(
							"This coyote is currently hostile. Perhaps it could be tamed outside of its hunting hours?"));

				}
				return true;
			}
		}


		return false;
	}

	@Override
	public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner) {
		if(!(target instanceof EntityCreeper) && !(target instanceof EntityGhast)
				&& (this.isTamed() || !this.isDaytime())) {
			if(target instanceof EntityCoyote) {
				EntityCoyote entityferalwolf = (EntityCoyote) target;

				if(entityferalwolf.isTamed() && entityferalwolf.getOwner() == owner) {
					return false;
				}
			}

			if(target instanceof EntityPlayer && owner instanceof EntityPlayer
					&& !((EntityPlayer) owner).canAttackPlayer((EntityPlayer) target)) {
				return false;
			} else {
				return !(target instanceof AbstractHorse) || !((AbstractHorse) target).isTame();
			}
		} else {
			return false;
		}
	}


}
