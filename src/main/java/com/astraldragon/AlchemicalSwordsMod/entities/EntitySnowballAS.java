package com.astraldragon.AlchemicalSwordsMod.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntitySnowballAS extends EntitySnowball {
	
	public EntitySnowballAS(World worldIn, EntityLivingBase p_i1783_2_) {
		super(worldIn, p_i1783_2_);

		}
	
	@Override
	protected void onImpact(RayTraceResult p_70184_1_)
    {
        if (p_70184_1_.entityHit != null)
        {
            byte b0 = 6;

            if (p_70184_1_.entityHit instanceof EntityBlaze)
            {
                b0 = 8;
            }
            
            ((EntityLivingBase)p_70184_1_.entityHit).addPotionEffect(new PotionEffect(Potion.getPotionById(2),100,1));
            p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)b0);
        }

        for (int i = 0; i < 8; ++i)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
	
	
	
}
