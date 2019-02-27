package its_meow.betteranimalsplus.client.renderer.entity;

import its_meow.betteranimalsplus.client.model.ModelCustomWolf;
import its_meow.betteranimalsplus.client.renderer.entity.layers.LayerWolfEyes;
import its_meow.betteranimalsplus.common.entity.EntityFeralWolf;
import its_meow.betteranimalsplus.init.TextureRegistry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCustomWolf extends RenderLiving<EntityFeralWolf> {

	public RenderCustomWolf(RenderManager manager) {
		super(manager, new ModelCustomWolf(), 0.5F);
		this.addLayer(new LayerWolfEyes(this));
	}


	/**
	 * Defines wwhat float the third param in setRotationAngles of ModelBase is
	 */
	@Override
	protected float handleRotationFloat(EntityFeralWolf livingBase, float partialTicks) {
		return livingBase.getTailRotation();
	}

	/**
	 * Renders the desired {@code T} type Entity.
	 */
	@Override
	public void doRender(EntityFeralWolf entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if(entity.isWolfWet()) {
			float f = entity.getBrightness() * entity.getShadingWhileWet(partialTicks);
			GlStateManager.color3f(f, f, f);
		}

		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(EntityFeralWolf entity) {
		return this.getWolfTexture(entity.getTypeNumber(), entity);
	}

	private ResourceLocation getWolfTexture(int typeNumber, EntityFeralWolf entity) {
		ResourceLocation result = null;


		if(entity.isTamed()) {
			switch(typeNumber) {
			case 1:
				result = TextureRegistry.wolf_black_neutral;
				break;
			case 2:
				result = TextureRegistry.wolf_snowy_neutral;
				break;
			case 3:
				result = TextureRegistry.wolf_timber_neutral;
				break;
			}
		} else {
			switch(typeNumber) {
			case 1:
				result = TextureRegistry.wolf_black;
				break;
			case 2:
				result = TextureRegistry.wolf_snowy;
				break;
			case 3:
				result = TextureRegistry.wolf_timber;
				break;
			}
		}
		return result;
	}

}
