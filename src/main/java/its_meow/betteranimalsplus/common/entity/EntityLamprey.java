package its_meow.betteranimalsplus.common.entity;

import its_meow.betteranimalsplus.common.entity.ai.EntityAIFindEntityNearestPredicate;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIMoveTowardsAttackTarget;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityLamprey extends EntityWaterMobWithTypes implements IMob {

	private boolean resetPassengerState = true;
	private float passengerX = 0.0F;
	private float passengerZ = 0.0F;

	public EntityLamprey(World worldIn) {
		super(worldIn);
		this.setSize(1.0F, 0.7F);
	}

	@Override
	protected void initEntityAI() {
		//this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIMoveTowardsAttackTarget(this, 0.8D, true));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityWaterMob.class, 10.0F));
		this.targetTasks.addTask(0, new EntityAIFindEntityNearestPredicate(this, EntityLivingBase.class, e -> e instanceof EntityLivingBase && !(e instanceof IMob) && !(e instanceof EntityLamprey), true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.8D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
	}

	@Override
	protected PathNavigate createNavigator(World worldIn) {
		return new PathNavigateSwimmer(this, worldIn);
	}

	public void travel(float strafe, float vertical, float forward) {
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!this.inWater) {
			this.motionX *= 0.2F;
			this.motionZ *= 0.2F;
			if (!this.hasNoGravity()) {
				this.motionY -= 0.08D;
			}

			this.motionY *= 0.9800000190734863D;
			if(this.isBeingRidden()) {
				this.getRidingEntity().dismountRidingEntity();
			}
		} else if(!world.isRemote) {
			if(!this.navigator.noPath()) {
				Vec3d target = this.navigator.getPath().getCurrentPos();
				this.motionX = (target.x - this.posX) * 0.05F;
				this.motionY = (target.y - this.posY) * 0.05F;
				this.motionZ = (target.z - this.posZ) * 0.05F;
			} else if(this.getMoveHelper().isUpdating()) {
				this.motionX = (this.getMoveHelper().getX() - this.posX) * 0.05F;
				this.motionZ = (this.getMoveHelper().getZ() - this.posZ) * 0.05F;
				this.motionY = (this.getMoveHelper().getY() - this.posY) * 0.05F;
			}
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
		if(entityIn instanceof EntityLivingBase) {
			f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entityIn).getCreatureAttribute());
		}
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);
		if(flag) {
			if(entityIn instanceof EntityPlayer) {
				EntityPlayer entityplayer = (EntityPlayer)entityIn;


				int weakTicks = 0;
				if (this.world.getDifficulty() == EnumDifficulty.EASY) {
					weakTicks = 200;
				} else if (this.world.getDifficulty() == EnumDifficulty.NORMAL) {
					weakTicks = 300;
				} else if (this.world.getDifficulty() == EnumDifficulty.HARD) {
					weakTicks = 600;
				}
				entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("weakness"), weakTicks, 1, false, false));

				ItemStack itemstack = this.getHeldItemMainhand();
				ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;
				if(!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer)) {
					float f1 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;
					if(this.rand.nextFloat() < f1) {
						entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
						this.world.setEntityState(entityplayer, (byte)30);
					}
				}
			}
			this.applyEnchantments(this, entityIn);
		}
		return flag;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if(!this.world.isRemote && this.getAttackTarget() != null) {
			if(this.isRidingOrBeingRiddenBy(this.getAttackTarget())) {
				if(this.getLastAttackedEntityTime() + 10F < this.ticksExisted) {
					this.attackEntityAsMob(this.getAttackTarget());
				}
			} else if(this.getDistanceSq(this.getAttackTarget()) < 2) {
				this.grabTarget(this.getAttackTarget());
			}
		}
	}

	public void grabTarget(Entity entity) {
		if(entity == this.getAttackTarget() && !this.isRidingOrBeingRiddenBy(entity) && this.inWater) {
			entity.startRiding(this);
			this.resetPassengerState = true;
		}
	}
	
	@Override
	public boolean canRiderInteract() {
		return true;
	}
	
	@Override
	public boolean shouldRiderSit() {
		return false;
	}

	@Override
	public void updatePassenger(Entity passenger) {
		if(this.isPassenger(passenger)) {
			if(this.resetPassengerState) {
				this.resetPassengerState = false;
				this.setOffsetFor(passenger);
			}
			passenger.setPosition(this.posX + (passengerX), this.posY, this.posZ + (passengerZ));
		}
	}

	private void setOffsetFor(Entity passenger) {
		boolean side = Math.random() >= 0.5;
		float modX = Math.random() >= 0.5 ? 1F : -1F;
		float modZ = Math.random() >= 0.5 ? 1F : -1F;
		if(side) {
			modX = Math.random() >= 0.5 ? 0.5F : -0.5F;
		} else {
			modZ = Math.random() >= 0.5 ? 0.5F : -0.5F;
		}
		this.passengerX = passenger.width * modX;
		this.passengerZ = passenger.width * modZ;
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	public boolean getCanSpawnHere() {
		return this.posY > 45.0D && this.posY < (double)this.world.getSeaLevel() && super.getCanSpawnHere();
	}

	@Override
	public int getVariantMax() {
		return 3;
	}

}